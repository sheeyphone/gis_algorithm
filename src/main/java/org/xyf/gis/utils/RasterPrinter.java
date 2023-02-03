package org.xyf.gis.utils;

import org.xyf.gis.base.Line;
import org.xyf.gis.base.Point;
import org.xyf.gis.base.Polygon;
import org.xyf.gis.base.Polyline;
import org.xyf.gis.convertor.VectorToRaster;

public class RasterPrinter {

  public static void main(String[] args) {
    Object[][] obj;
    obj = VectorToRaster.pointToRaster(5, 5, new Point(2, 3));
    printRaster(obj, true);
    obj = VectorToRaster.lineToRaster(10, 10, new Line(2, 3, 5, 8));
    printRaster(obj, true);
    obj =
        VectorToRaster.polylineToRaster(
            10,
            10,
            new Polyline(
                new Point[] {new Point(2, 3), new Point(5, 8), new Point(8, 8), new Point(1, 1)}));
    printRaster(obj, true);
    obj =
        VectorToRaster.polygonToRaster(
            20,
            20,
            new Polygon(
                new Point[] {
                  new Point(0, 0),
                  new Point(6, 19),
                  new Point(5, 5),
                  new Point(39, 29),
                  new Point(9, 3),
                  new Point(0, 0)
                }));
    printRaster(obj, true);
  }

  public static void printRaster(Object[][] objects) {
    int rowSize = objects.length;
    int colSize = objects[0].length;
    System.out.printf("row: %s, col: %s\n", rowSize, colSize);
    for (int row = 0; row < rowSize; row++) {
      for (int col = 0; col < colSize; col++) {
        System.out.printf("%s\t", objects[row][col]);
      }
      System.out.println();
    }
  }

  public static void printRaster(Object[][] objects, boolean isFormat) {
    int rowSize = objects.length;
    int colSize = objects[0].length;
    System.out.printf("row: %s, col: %s\n", rowSize, colSize);
    for (int row = 0; row < rowSize; row++) {
      for (int col = 0; col < colSize; col++) {
        int v = (int) objects[row][col];
        System.out.printf("%s\t", isFormat && v == 0 ? "." : "x");
      }
      System.out.println();
    }
  }
}
