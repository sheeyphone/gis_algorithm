package org.xyf.gis.convertor;

import org.xyf.gis.base.Line;
import org.xyf.gis.base.Point;
import org.xyf.gis.base.Polygon;
import org.xyf.gis.base.Polyline;
import org.xyf.gis.utils.RasterUtils;

public class VectorToRaster {

  public static Object[][] pointToRaster(int xSize, int ySize, Point point) {
    Object[][] raster = RasterUtils.getPureRaster(xSize, ySize);
    raster[point.getY()][point.getX()] = 1;
    return raster;
  }

  public static Object[][] lineToRaster(int xSize, int ySize, Line line) {
    Object[][] raster = RasterUtils.getPureRaster(xSize, ySize);
    int[][] pathXY = line.getPathXY();
    for (int[] xy : pathXY) {
      if (xy[1] >= ySize || xy[1] < 0) continue;
      if (xy[0] >= xSize || xy[0] < 0) continue;
      raster[xy[1]][xy[0]] = 1;
    }
    return raster;
  }

  public static Object[][] polylineToRaster(int xSize, int ySize, Polyline polyline) {
    Object[][] raster = RasterUtils.getPureRaster(xSize, ySize);
    Line[] lines = polyline.eachLine();
    for (Line line : lines) {
      for (int[] xy : line.getPathXY()) {
        if (xy[1] >= ySize || xy[1] < 0) continue;
        if (xy[0] >= xSize || xy[0] < 0) continue;
        raster[xy[1]][xy[0]] = 1;
      }
    }
    return raster;
  }

  public static Object[][] polygonToRaster(int xSize, int ySize, Polygon polygon) {
    return polygon.getFillPolygon(xSize, ySize);
  }
}
