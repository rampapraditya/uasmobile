package dinamika.pos.entitas;

public class Produk {

    private String kode;
    private String nama;
    private String harga;
    private String satuan;
    private String foto; // Menyimpan URI Gambar dari galeri atau path string

    // Constructor Baru
    public Produk(String kode, String nama, String harga, String satuan, String foto) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.satuan = satuan;
        this.foto = foto;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}