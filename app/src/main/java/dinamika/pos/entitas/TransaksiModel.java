package dinamika.pos.entitas;

public class TransaksiModel {

    private String idTransaksi;
    private String tanggal;
    private int totalBayar;

    // 1. Constructor (Untuk membuat objek transaksi baru saat membaca data dari database)
    public TransaksiModel(String idTransaksi, String tanggal, int totalBayar) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.totalBayar = totalBayar;
    }

    // 2. Getter dan Setter (Untuk mengambil dan memperbarui nilai data)
    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }
}
