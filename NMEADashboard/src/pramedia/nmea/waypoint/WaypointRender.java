package pramedia.nmea.waypoint;

import org.jxmapviewer.viewer.WaypointPainter;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import javax.swing.JButton;
import org.jxmapviewer.JXMapViewer;

public class WaypointRender extends WaypointPainter<MyWaypoint> {

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height) {
        // Melakukan perulangan untuk setiap titik waypoint yang didaftarkan
        for (MyWaypoint wp : getWaypoints()) {
            JButton convertButton = wp.getMarker();
            if (convertButton != null) {
                // Konversi titik koordinat bumi (Lat, Lon) menjadi koordinat piksel X dan Y di layar komputer
                Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
                Rectangle rect = map.getViewportBounds();
                
                // Hitung posisi relatif tombol berdasarkan area pandang (viewport) peta saat ini
                int x = (int) (p.getX() - rect.getX());
                int y = (int) (p.getY() - rect.getY());
                
                // Ambil ukuran dimensi asli tombol
                int buttonWidth = convertButton.getPreferredSize().width;
                int buttonHeight = convertButton.getPreferredSize().height;
                
                // Atur posisi tombol (koordinat X dikurangi setengah lebar tombol agar presisi di tengah titik)
                convertButton.setBounds(x - (buttonWidth / 2), y - buttonHeight, buttonWidth, buttonHeight);
            }
        }
    }
}
