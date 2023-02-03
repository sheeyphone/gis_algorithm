# GIS Algorithms
Basic algorithms of geography information sciences and spacial data processing.

(Notices: Only the RasterConvertor is finished yet.)

### The test codes and consoles are in this file: RasterPrinter.java
```
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
```
### And the result is:
```
> Task :RasterPrinter.main()
row: 5, col: 5
. . . . . 
. . . . . 
. . . . . 
. . x . . 
. . . . . 
row: 10, col: 10
. . . . . . . . . . 
. . . . . . . . . . 
. . . . . . . . . . 
. . x . . . . . . . 
. . . x . . . . . . 
. . . x . . . . . . 
. . . . x . . . . . 
. . . . x . . . . . 
. . . . . x . . . . 
. . . . . . . . . . 
row: 10, col: 10
. . . . . . . . . . 
. x . . . . . . . . 
. . x . . . . . . . 
. . x x . . . . . . 
. . . x x . . . . . 
. . . x . x . . . . 
. . . . x . x . . . 
. . . . x . . x . . 
. . . . . x x x x . 
. . . . . . . . . . 
row: 20, col: 20
x x . . . . . . . . . . . . . . . . . . 
x x x x x . . . . . . . . . . . . . . . 
. x x x x x x x . . . . . . . . . . . . 
. x x x x x x x x x . . . . . . . . . . 
. x x x x x x x x x x . . . . . . . . . 
. . x x x x x x x x x x . . . . . . . . 
. . x x x x x x x x x x x x . . . . . . 
. . x x x x . . x x x x x x x . . . . . 
. . . x x x . . . x x x x x x x . . . . 
. . . x x x . . . . x x x x x x x . . . 
. . . x x x . . . . . . x x x x x x . . 
. . . x x x . . . . . . . x x x x x x . 
. . . . x x x . . . . . . . . x x x x x 
. . . . x x x . . . . . . . . . x x x x 
. . . . x x x . . . . . . . . . . . x x 
. . . . . x x . . . . . . . . . . . . x 
. . . . . x x . . . . . . . . . . . . . 
. . . . . x x . . . . . . . . . . . . . 
. . . . . . x . . . . . . . . . . . . . 
. . . . . . x . . . . . . . . . . . . .
```