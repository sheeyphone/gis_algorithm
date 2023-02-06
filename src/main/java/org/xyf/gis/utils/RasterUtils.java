package org.xyf.gis.utils;

public class RasterUtils {

  /**
   * Create the pure 2d raster matrix, filled with integer data 0.
   *
   * @param xSize Object[][xSize]
   * @param ySize Object[ySize][]
   * @return Object grid
   */
  public static Object[][] getPureRaster(int xSize, int ySize) {
    return getPureRaster(xSize, ySize, 0);
  }

  /**
   * Create the pure 2d raster matrix, filled with the parameter 'filled'.
   *
   * @param xSize Object[][xSize]
   * @param ySize Object[ySize][]
   * @return Object grid
   */
  public static Object[][] getPureRaster(int xSize, int ySize, Object filled) {
    Object[][] raster = new Object[ySize][xSize];
    for (int y = 0; y < ySize; y++) {
      for (int x = 0; x < xSize; x++) {
        raster[y][x] = filled;
      }
    }
    return raster;
  }

  /**
   * Convert the topLeft config (suggest to providing a positive numbers) into topLeft Offsets
   * config.
   *
   * @param topLeft topLeft config array formed by {top,left}
   * @return offsets config array ormed by {top,left}
   */
  public static int[] getOffsetsFromTopLeft(int[] topLeft) {
    return new int[] {-topLeft[0], -topLeft[1]};
  }

  /**
   * Merge target matrix into source matrix. Notice that it must be the same scale of dimension and
   * grid size between the target and source.
   *
   * @param source source matrix
   * @param target target matrix
   * @param targetValue specify the value of target matrix to settle.
   */
  public static void mergeRaster(Object[][] source, Object[][] target, Object targetValue) {
    for (int y = 0; y < source.length; y++) {
      for (int x = 0; x < source[y].length; x++) {
        if (targetValue == target[y][x]) {
          source[y][x] = target[y][x];
        }
      }
    }
  }

}
