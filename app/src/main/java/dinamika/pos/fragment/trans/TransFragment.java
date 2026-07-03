package dinamika.pos.fragment.trans;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dinamika.pos.DatabaseHelper;
import dinamika.pos.R;
import dinamika.pos.adapter.KatalogTransaksiAdapter;
import dinamika.pos.adapter.KeranjangAdapter;
import dinamika.pos.entitas.KeranjangModel;
import dinamika.pos.entitas.Produk;

public class TransFragment extends Fragment {

    private RecyclerView rvKatalogTransaksi, rvKeranjang;
    private TextView tvTotalBayar;
    private Button btnCheckout;

    private DatabaseHelper dbHelper;
    private List<Produk> listProduk = new ArrayList<>();
    private List<KeranjangModel> listKeranjang = new ArrayList<>();

    private KatalogTransaksiAdapter katalogAdapter;
    private KeranjangAdapter keranjangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trans, container, false);

        // 1. Inisialisasi Komponen UI dari XML
        rvKatalogTransaksi = view.findViewById(R.id.rvKatalogTransaksi);
        rvKeranjang = view.findViewById(R.id.rvKeranjang);
        tvTotalBayar = view.findViewById(R.id.tvTotalBayar);
        btnCheckout = view.findViewById(R.id.btnCheckout);

        // 2. Inisialisasi Database Helper
        dbHelper = new DatabaseHelper(getContext());

        // 3. Atur Layout Manager untuk kedua RecyclerView
        // Katalog Menu menggunakan Grid (3 kolom) agar menyamping rapi
        int kolomDinamis = getResources().getInteger(R.integer.jumlah_kolom_grid);
        rvKatalogTransaksi.setLayoutManager(new GridLayoutManager(getContext(), kolomDinamis));
        // Keranjang Belanja menggunakan susunan vertikal (kebawah)
        rvKeranjang.setLayoutManager(new LinearLayoutManager(getContext()));

        // 4. Hubungkan Adapter Keranjang ke RecyclerView Sisi Kanan
        // Hubungkan Adapter Keranjang dengan logika kurangi/hapus barang
        keranjangAdapter = new KeranjangAdapter(listKeranjang, position -> {
            // Ambil data item keranjang berdasarkan posisi yang diklik
            KeranjangModel item = listKeranjang.get(position);

            if (item.getJumlah() > 1) {
                // Jika QTY lebih dari 1, cukup kurangi jumlahnya sebanyak 1
                item.setJumlah(item.getJumlah() - 1);
            } else {
                // Jika QTY tinggal 1, hapus item tersebut sepenuhnya dari list keranjang
                listKeranjang.remove(position);
            }

            // Segarkan tampilan recycler view kanan & kalkulasi ulang total bayar
            keranjangAdapter.notifyDataSetChanged();
            hitungTotalBelanja();
        });
        rvKeranjang.setAdapter(keranjangAdapter);


        // 5. Muat dan Tampilkan Data Katalog dari Database
        tampilkanKatalogProduk();

        // 6. Logika Tombol Proses / Checkout
        btnCheckout.setOnClickListener(v -> prosesTransaksiBelanja());

        return view;
    }

    private void tampilkanKatalogProduk() {
        listProduk.clear();
        listProduk.addAll(dbHelper.getAllProduk());

        // Memasang adapter katalog baru yang telah dibuat dengan listener klik
        katalogAdapter = new KatalogTransaksiAdapter(listProduk, produk -> {
            tambahKeKeranjang(produk); // Jalankan fungsi tambah keranjang saat produk diklik
        });

        rvKatalogTransaksi.setAdapter(katalogAdapter);
    }

    private void tambahKeKeranjang(Produk produk) {
        int hargaInt = 0;
        try {
            // Ambil string asli dari database (Misal: "Rp Rp 6.000")
            String hargaMentah = produk.getHarga();

            if (hargaMentah != null) {
                // KODE FIX: Hanya mengambil karakter ANGKA saja (0-9).
                // Semua huruf "R", "p", spasi, dan titik "." otomatis musnah total!
                String hargaBersih = hargaMentah.replaceAll("[^0-9]", "");

                if (!hargaBersih.isEmpty()) {
                    hargaInt = Integer.parseInt(hargaBersih);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            hargaInt = 0; // Jaga-jaga jika ada kegagalan parsing
        }

        boolean barangSudahAda = false;

        // Cari apakah barang sudah ada di keranjang
        for (KeranjangModel item : listKeranjang) {
            if (item.getIdProduk().equals(produk.getKode())) {
                item.setJumlah(item.getJumlah() + 1);
                barangSudahAda = true;
                break;
            }
        }

        // Jika benar-benar produk baru, buat baris baru
        if (!barangSudahAda) {
            KeranjangModel itemBaru = new KeranjangModel(
                    produk.getKode(),
                    produk.getNama(),
                    hargaInt, // Sekarang nilainya sudah berupa angka murni (6000)
                    1
            );
            listKeranjang.add(itemBaru);
        }

        // Segarkan tampilan list keranjang belanja di layar kanan
        if (keranjangAdapter != null) {
            keranjangAdapter.notifyDataSetChanged();
        }

        // Hitung ulang total belanjaan paling bawah (Total Bayar)
        hitungTotalBelanja();
    }


    private void hitungTotalBelanja() {
        int totalUang = 0;
        for (KeranjangModel item : listKeranjang) {
            totalUang += item.getSubtotal();
        }
        // Perbarui teks total bayar pada layout
        tvTotalBayar.setText("Rp " + totalUang);
    }

    private void prosesTransaksiBelanja() {
        // Validasi: Cegah proses jika user belum memilih barang sama sekali
        if (listKeranjang.isEmpty()) {
            Toast.makeText(getContext(), "Keranjang masih kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate ID Nota Transaksi otomatis berbasis waktu milidetik agar selalu unik
        String idTransaksi = "TX-" + System.currentTimeMillis();

        // Ambil waktu transaksi saat ini (Format standar SQLite: YYYY-MM-DD HH:MM:SS)
        String tanggalSekarang = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        // Hitung akumulasi total akhir yang harus dibayarkan
        int totalBayarInt = 0;
        for (KeranjangModel item : listKeranjang) {
            totalBayarInt += item.getSubtotal();
        }

        // Eksekusi penyimpanan data ganda (Transaksi & Detail) ke database local
        boolean sukses = dbHelper.insertTransaksi(idTransaksi, tanggalSekarang, totalBayarInt, listKeranjang);

        if (sukses) {
            Toast.makeText(getContext(), "Transaksi Berhasil Disimpan!", Toast.LENGTH_LONG).show();

            // Bersihkan isi keranjang belanja karena transaksi telah selesai
            listKeranjang.clear();
            if (keranjangAdapter != null) {
                keranjangAdapter.notifyDataSetChanged();
            }
            hitungTotalBelanja();
        } else {
            Toast.makeText(getContext(), "Gagal memproses transaksi ke database.", Toast.LENGTH_SHORT).show();
        }
    }
}