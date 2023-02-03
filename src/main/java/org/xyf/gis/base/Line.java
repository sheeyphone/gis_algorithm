package org.xyf.gis.base;

public class Line {

  private final int x1;
  private final int y1;
  private final int x2;
  private final int y2;

  public Line(Point p1, Point p2) {
    this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  public Line(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    if (getRowDiff() == 0 && getColDiff() == 0) {
      throw new RuntimeException("The same point at this line.");
    }
  }

  public LineDirection getLineDirection() {
    if (y1 == y2) return x2 > x1 ? LineDirection.East : LineDirection.West;
    if (x1 == x2) return y2 < y1 ? LineDirection.North : LineDirection.South;
    if (x2 > x1 && y2 > y1) return LineDirection.SouthEast;
    if (x2 > x1) return LineDirection.NorthEast;
    if (y2 > y1) return LineDirection.SouthWest;
    return LineDirection.NorthWest;
  }

  public int[] eachX() {
    int count = getColDiff() + 1;
    int step = x2 > x1 ? 1 : -1;
    int[] array = new int[count];
    for (int i = 0; i < count; i++) {
      array[i] = x1 + i * step;
    }
    return array;
  }

  public int[] eachY() {
    int count = getRowDiff() + 1;
    int step = y2 > y1 ? 1 : -1;
    int[] array = new int[count];
    for (int i = 0; i < count; i++) {
      array[i] = y1 + i * step;
    }
    return array;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }

  public int getColDiff() {
    return Math.abs(x1 - x2);
  }

  public int getRowDiff() {
    return Math.abs(y1 - y2);
  }

  public int[][] getPathXY() {
    int[][] result;
    int rowDiff = getRowDiff();
    int colDiff = getColDiff();
    if (colDiff == 0) {
      int[] eachY = eachY();
      result = new int[eachY.length][2];
      int i = 0;
      for (int y : eachY) {
        result[i] = new int[]{x1, y};
        i++;
      }
      return result;
    }
    if (rowDiff == 0) {
      int[] eachX = eachX();
      result = new int[eachX.length][2];
      int i = 0;
      for (int x : eachX) {
        result[i] = new int[]{x, y1};
        i++;
      }
      return result;
    }
    if (colDiff > rowDiff) {
      int[] eachX = eachX();
      result = new int[eachX.length][2];
      int i = 0;
      for (int x : eachX) {
        int yA = getTargetY(x);
        result[i] = new int[]{x, yA};
        i++;
      }
    } else {
      int[] eachY = eachY();
      result = new int[eachY.length][2];
      int i = 0;
      for (int y : eachY) {
        int xA = getTargetX(y);
        result[i] = new int[]{xA, y};
        i++;
      }
    }
    return result;
  }

  public int getTargetX(int y) {
    if (y1 == y2) return x1;
    return Math.round((float) (x2 - x1) * (y - y1) / (y2 - y1) + x1);
  }

  public int getTargetY(int x) {
    if (x1 == x2) return y1;
    return Math.round((float) (y2 - y1) * (x - x1) / (x2 - x1) + y1);
  }

  /**
   * Line direction of 2 points below. (x1,y1)->(x2,y2) There are 8 Directions from centre
   */
  public enum LineDirection {
    North, NorthEast, East, SouthEast, South, SouthWest, West, NorthWest
  }
}
