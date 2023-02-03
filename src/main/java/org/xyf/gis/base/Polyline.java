package org.xyf.gis.base;

public class Polyline {

  private final Point[] points;

  public Polyline(Point[] points) {
    this.points = points;
  }

  public Point[] getPoints() {
    return points;
  }

  public Line[] eachLine() {
    Line[] lines = new Line[points.length - 1];
    for (int l = 0; l < lines.length; l++) {
      lines[l] = new Line(points[l], points[l + 1]);
    }
    return lines;
  }

}
