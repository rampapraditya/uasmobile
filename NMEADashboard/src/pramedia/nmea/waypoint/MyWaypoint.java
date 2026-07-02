package pramedia.nmea.waypoint;

import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;

public class MyWaypoint implements Waypoint {

    private String name;
    private GeoPosition position;
    private MarkerPeta marker; // Diubah dari JButton menjadi MarkerPeta

    public MyWaypoint() {
    }

    public MyWaypoint(String name, GeoPosition position, MarkerPeta marker) {
        this.name = name;
        this.position = position;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MarkerPeta getMarker() { // Diubah dari getButton()
        return marker;
    }

    public void setMarker(MarkerPeta marker) { // Diubah dari setButton()
        this.marker = marker;
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }

    @Override
    public GeoPosition getPosition() {
        return position;
    }
}
