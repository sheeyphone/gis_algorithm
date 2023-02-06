package org.xyf.gis;

public class GeometryResolutionException extends Exception {

  private final String reason;

  public GeometryResolutionException(String reason) {
    super(reason);
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }
}
