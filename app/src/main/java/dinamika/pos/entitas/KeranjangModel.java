package dinamika.pos.entitas;

public class KeranjangModel {
    private String idProduk;
    private String namaProduk;
    private int hargaJual;
    private int jumlah; // Quantity (QTY) barang yang dibeli
    private int subtotal; // Hasil dari (hargaJual * jumlah)

    // 1. Constructor (Untuk membuat objek keranjang baru)
    public KeranjangModel(String idProduk, String namaProduk, int hargaJual, int jumlah) {
        this.idProduk = idProduk;
        this.namaProduk = namaProduk;
        this.hargaJual = hargaJual;
        this.jumlah = jumlah;
        this.subtotal = hargaJual * jumlah; // Otomatis menghitung subtotal
    }

    // 2. Getter and Setter (Untuk mengambil dan mengubah data)
    public String getIdProduk() { return idProduk; }
    public void setIdProduk(String idProduk) { this.idProduk = idProduk; }

    public String getNamaProduk() { return namaProduk; }
    public void setNamaProduk(String namaProduk) { this.namaProduk = namaProduk; }

    public int getHargaJual() { return hargaJual; }
    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
        this.subtotal = this.hargaJual * this.jumlah; // Update subtotal jika harga berubah
    }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        this.subtotal = this.hargaJual * this.jumlah; // Update subtotal jika jumlah berubah
    }

    public int getSubtotal() { return subtotal; }
}
