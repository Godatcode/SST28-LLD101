package com.example.map;

/**
 * A single map marker.
 *
 * Extrinsic state (unique per marker): lat, lng, label
 * Intrinsic state (shared via Flyweight): style
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat()        { return lat; }
    public double getLng()        { return lng; }
    public String getLabel()      { return label; }
    public MarkerStyle getStyle() { return style; }
}
