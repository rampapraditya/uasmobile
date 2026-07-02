package pramedia.nmea.waypoint;

/**
 *
 * @author RAMPA
 */
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import java.net.URL;

public class MarkerPeta extends JButton {

    /**
     * Konstruktor Marker Peta dengan parameter gambar kustom
     * @param imagePath Jalur file gambar di folder resource (contoh: "/icon/pin.png")
     * @param width Lebar piksel marker yang diinginkan (contoh: 24)
     * @param height Tinggi piksel marker yang diinginkan (contoh: 24)
     */
    public MarkerPeta(String imagePath, int width, int height) {
        // 1. Konfigurasi dasar agar tombol fisik Swing menjadi transparan
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        
        // 2. Ubah kursor menjadi bentuk tangan (hand cursor) saat melintas di atasnya
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // 3. Kunci ukuran dimensi komponen menggunakan PreferredSize
        Dimension ukuranMarker = new Dimension(width, height);
        this.setPreferredSize(ukuranMarker);
        this.setMinimumSize(ukuranMarker);
        this.setMaximumSize(ukuranMarker);
        
        // 4. Muat gambar berdasarkan parameter yang dikirim
        ubahIkon(imagePath);
    }

    /**
     * Metode untuk mengubah ikon penanda secara dinamis di tengah jalan
     * @param imagePath Jalur file gambar baru
     */
    public final void ubahIkon(String imagePath) {
        try {
            URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                this.setIcon(new ImageIcon(imgURL));
                this.setText(""); // Hapus teks cadangan jika gambar berhasil dimuat
            } else {
                System.err.println("Gagal memuat ikon: " + imagePath + " tidak ditemukan!");
                this.setText("📍"); // Teks cadangan jika file gambar corrupt/hilang
            }
        } catch (Exception e) {
            this.setText("📍");
        }
    }
}
