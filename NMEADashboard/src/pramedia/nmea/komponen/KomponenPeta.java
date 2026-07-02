package pramedia.nmea.komponen;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import pramedia.nmea.waypoint.MarkerPeta;
import pramedia.nmea.waypoint.MyWaypoint;
import pramedia.nmea.waypoint.WaypointRender;

/**
 *
 * @author RAMPA
 */
public class KomponenPeta extends JXMapViewer {

    // Menyimpan daftar koordinat (waypoint) di dalam Set agar tidak duplikat
    private final Set<MyWaypoint> daftarWaypoint = new HashSet<>();

    // Menyimpan referensi khusus untuk penanda alat GPS utama Anda
    private MyWaypoint markerGpsAlat;

    public KomponenPeta() {
        // 1. Set properti agen HTTP agar server OpenStreetMap tidak memblokir aplikasi
        System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        // 2. Hubungkan ke server ubin resmi OpenStreetMap (PERBAIKAN URL DI SINI)
        TileFactoryInfo info = new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        this.setTileFactory(tileFactory);

        // 3. Atur lokasi default awal (Surabaya) sebelum alat GPS mengirim data serial
        GeoPosition posisiAwal = new GeoPosition(-7.3106307, 112.7798443);
        this.setAddressLocation(posisiAwal);
        this.setZoom(5);

        // 4. Pasang kontrol interaksi mouse (Geser klik kiri, Zoom roda mouse)
        MouseInputListener mil = new PanMouseInputListener(this);
        this.addMouseListener(mil);
        this.addMouseMotionListener(mil);
        this.addMouseWheelListener(new ZoomMouseWheelListenerCenter(this));
    }

    /**
     * METODE GPS: Dipanggil oleh fungsi parseNMEA di JFrame saat koordinat baru
     * masuk
     *
     * @param lat Koordinat Latitude (Garis Lintang) hasil parsing desimal
     * @param lon Koordinat Longitude (Garis Bujur) hasil parsing desimal
     */
    public void perbaruiLokasiGps(double lat, double lon) {
        GeoPosition posisiBaru = new GeoPosition(lat, lon);

        // Geser kamera peta secara otomatis ke lokasi GPS terbaru
        this.setAddressLocation(posisiBaru);

        if (markerGpsAlat == null) {
            // Jika penanda belum ada, buat komponen MarkerPeta baru (Ukuran 32x32 piksel)
            // Pastikan Anda sudah meletakkan file gambar di src/main/resources/icon/pin.png
            MarkerPeta visualMarker = new MarkerPeta("/pramedia/nmea/icon/pin.png", 32, 32);

            // Bungkus ke dalam objek MyWaypoint
            markerGpsAlat = new MyWaypoint("Perangkat GPS", posisiBaru, visualMarker);

            // Tambahkan ke dalam sistem visual map viewer
            this.addWayPoint(markerGpsAlat);
        } else {
            // Jika alat bergerak, cukup perbarui titik koordinat bumi tanpa membuat objek tombol baru
            markerGpsAlat.setPosition(posisiBaru);

            // Segarkan ulang visual komponen peta agar tombol berpindah koordinat piksel layar secara instan
            this.initWayPoint();
        }
    }

    /**
     * Mengubah tipe visualisasi peta berdasarkan indeks JComboBox peninjau
     *
     * @param index Nilai indeks pilihan (0: OSM, 1: Bing Map, 2: Bing Hybrid,
     * 3: Bing Satelit)
     */
    public void setTipePeta(int index) {
        // Ambil posisi kamera dan level zoom saat ini agar tidak ter-reset saat ganti jenis peta
        GeoPosition posisiSekarang = this.getAddressLocation();
        int zoomSekarang = this.getZoom();

        TileFactoryInfo info = null;

        info = switch (index) {
            case 0 -> new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
            case 1 -> new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
            case 2 -> new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
            case 3 -> new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
            default -> new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
        };

        if (info != null) {
            DefaultTileFactory tileFactory = new DefaultTileFactory(info);
            this.setTileFactory(tileFactory);

            // Kembalikan posisi kamera dan level zoom ke kondisi sebelum peta diganti
            this.setAddressLocation(posisiSekarang);
            this.setZoom(zoomSekarang);

            // Gambar ulang seluruh elemen waypoint / penanda yang melekat
            this.initWayPoint();
        }
    }

    /**
     * Daftarkan objek waypoint baru ke dalam peta
     */
    public void addWayPoint(MyWaypoint wayPoint) {
        // Pasang event klik pada komponen MarkerPeta sebelum direkatkan ke kontainer
        if (wayPoint.getMarker() != null) {
            // Bersihkan action listener lama untuk menghindari duplikasi trigger klik di memori
            for (java.awt.event.ActionListener al : wayPoint.getMarker().getActionListeners()) {
                wayPoint.getMarker().removeActionListener(al);
            }
            // Definisikan aksi jika ikon marker tersebut diklik oleh pengguna
            wayPoint.getMarker().addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "Lokasi: " + wayPoint.getName()
                        + "\nKoordinat: " + wayPoint.getPosition().getLatitude()
                        + ", " + wayPoint.getPosition().getLongitude());
            });
        }

        daftarWaypoint.add(wayPoint);
        initWayPoint();
    }

    /**
     * Sinkronisasi ulang dan menggambar semua komponen visual di atas ubin peta
     */
    public void initWayPoint() {
        // A. Bersihkan seluruh komponen fisik MarkerPeta (JButton) lama dari kontainer utama
        this.removeAll();

        // B. Buat painter terikat dengan WaypointRender kustom kita untuk kalkulasi titik piksel
        WaypointPainter<MyWaypoint> wp = new WaypointRender();
        wp.setWaypoints(daftarWaypoint);
        this.setOverlayPainter(wp);

        // C. Tempelkan kembali komponen fisik tombol penanda ke struktur kontainer peta
        for (MyWaypoint d : daftarWaypoint) {
            if (d.getMarker() != null) {
                this.add(d.getMarker());
            }
        }

        // D. Pastikan peta mendengarkan perubahan zoom atau pergeseran kamera agar posisi marker ikut bergerak
        this.addPropertyChangeListener("zoom", evt -> repaint());
        this.addPropertyChangeListener("addressLocation", evt -> repaint());

        this.revalidate();
        this.repaint();
    }

    /**
     * Hapus semua penanda (marker) yang ada di peta jika ingin melakukan reset
     * data
     */
    public void clearWayPoint() {
        for (MyWaypoint d : daftarWaypoint) {
            if (d.getMarker() != null) {
                this.remove(d.getMarker());
            }
        }
        daftarWaypoint.clear();
        markerGpsAlat = null; // Reset objek penanda GPS utama
        this.setOverlayPainter(null);
        this.revalidate();
        this.repaint();
    }
}
