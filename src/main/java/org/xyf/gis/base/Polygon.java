package org.xyf.gis.base;

import org.xyf.gis.exception.GeometryResolutionException;
import org.xyf.gis.utils.RasterUtils;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

  private final Point[] points;

  public Polygon(Point[] points) {
    Point start = points[0];
    Point end = points[points.length - 1];
    if (start.isSame(end)) {
      this.points = points;
    } else {
      throw new RuntimeException("Start and end must be the same coordinates.");
    }
  }

  public Point[] getPoints() {
    return points;
  }

  public int[] getTopLeft() {
    int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
    for (Point p : points) {
      if (p.getX() < minX) minX = p.getX();
      if (p.getY() < minY) minY = p.getY();
    }
    return new int[]{minX, minY};
  }

  public int[] getBottomRight() {
    int maxX = 0, maxY = 0;
    for (Point p : points) {
      if (p.getX() > maxX) maxX = p.getX();
      if (p.getY() > maxY) maxY = p.getY();
    }
    return new int[]{maxX, maxY};
  }

  public Line[] eachLine() {
    List<Line> lines = new ArrayList<>();
    int countMaybe = points.length - 1;
    for (int l = 0; l < countMaybe; l++) {
      try {
        lines.add(new Line(points[l], points[l + 1]));
      } catch (GeometryResolutionException ignored) {
      }
    }
    return lines.toArray(new Line[]{});
  }

  public Object[][] getFillPolygon(int xSize, int ySize) {
    Object[][] grid = RasterUtils.getPureRaster(xSize, ySize);
    int[] topLeft = getTopLeft(); // also an offsets
    int[] bottomRight = getBottomRight();
    int[] offsets = RasterUtils.getOffsetsFromTopLeft(topLeft);
    int sizeX = Math.abs(topLeft[0] - bottomRight[0]) + 1;
    int sizeY = Math.abs(topLeft[1] - bottomRight[1]) + 1;
    Object[][] innerGrid = RasterUtils.getPureRaster(sizeX, sizeY);
    Line[] lines = eachLine();
    Boolean isUp = null;
    for (Line line : lines) {
      isUp = fillDataInGrid(innerGrid, offsets, line, isUp);
      // RasterPrinter.printRaster(innerGrid);
    }
    for (int y = 0; y < innerGrid.length; y++) {
      for (int x = 0; x < innerGrid[y].length; x++) {
        int toY = y + topLeft[1];
        int toX = x + topLeft[0];
        if (toY >= grid.length) continue;
        if (toX >= grid[toY].length) continue;
        grid[toY][toX] = (int) innerGrid[y][x] >= 1 ? 1 : 0;
      }
    }
    // fill the lines
    for (Line line : lines) {
      int[][] pathXY = line.getPathXY();
      for (int[] xy : pathXY) {
        if (xy[1] >= ySize || xy[1] < 0) continue;
        if (xy[0] >= xSize || xy[0] < 0) continue;
        grid[xy[1]][xy[0]] = 1;
      }
    }
    return grid;
  }

  private boolean fillDataInGrid(Object[][] grid, int[] offsets, Line line, Boolean isUp) {
    boolean isUpCurrent = false;
    int[] eachY = line.eachY();
    int offsetX = offsets[0];
    int offsetY = offsets[1];
    Line.LineDirection direction = line.getLineDirection();
    switch (direction) {
      case North, NorthEast, NorthWest -> isUpCurrent = true;
    }
    // only handle y between line's y1 and y2
    for (int yInLine : eachY) {
      if (isUp != null && isUp == isUpCurrent && yInLine == eachY[0]) {
        // Wouldn't calculate the first y when we match the same direction next
        continue;
      }
      // y is the grid index
      int y = yInLine + offsetY;
      for (int x = 0; x < grid[y].length; x++) {
        int yA = line.getTargetY(x - offsetX) + offsetY;
        switch (direction) {
          case NorthWest -> {
            if (yA <= y) grid[y][x] = (int) grid[y][x] + 1;
          }
          case NorthEast -> {
            if (yA >= y) grid[y][x] = (int) grid[y][x] + 1;
          }
          case North -> {
            if (x <= line.getX1() + offsetX) grid[y][x] = (int) grid[y][x] + 1;
          }
          case SouthWest -> {
            if (yA > y) grid[y][x] = (int) grid[y][x] - 1;
          }
          case SouthEast -> {
            if (yA < y) grid[y][x] = (int) grid[y][x] - 1;
          }
          case South -> {
            if (x < line.getX1() + offsetX) grid[y][x] = (int) grid[y][x] - 1;
          }
        }
      }
    }
    return isUpCurrent;
  }

}
