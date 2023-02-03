package org.xyf.gis.utils;

public class RasterUtils {

  public static Object[][] getPureRaster(int xSize, int ySize) {
    Object[][] raster = new Object[ySize][xSize];
    for (int y = 0; y < ySize; y++) {
      for (int x = 0; x < xSize; x++) {
        raster[y][x] = 0;
      }
    }
    return raster;
  }

  public static int[] getOffsetsFromTopLeft(int[] topLeft) {
    return new int[] {-topLeft[0], -topLeft[1]};
  }
}
