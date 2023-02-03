package org.xyf.gis.base;

public class Point {

  private final int x;
  private final int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int[] getXY() {
    return new int[] {x, y};
  }

  public int[] getXY(int[] offsets) {
    return new int[] {x + offsets[0], y + offsets[1]};
  }

  public boolean isSame(Point point) {
    return this.x == point.getX() && this.y == point.getY();
  }
}
