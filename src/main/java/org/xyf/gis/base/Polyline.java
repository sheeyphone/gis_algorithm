package org.xyf.gis.base;

import org.xyf.gis.exception.GeometryResolutionException;

import java.util.ArrayList;
import java.util.List;

public class Polyline {

  private final Point[] points;

  public Polyline(Point[] points) {
    this.points = points;
  }

  public Point[] getPoints() {
    return points;
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
}
