package dinamika.pos.fragment.trans;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import dinamika.pos.DatabaseHelper;
import dinamika.pos.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import dinamika.pos.adapter.RiwayatTransaksiAdapter;
import dinamika.pos.entitas.KeranjangModel;
import dinamika.pos.entitas.TransaksiModel;

public class RiwayatTransFragment extends Fragment {

    private RecyclerView rvRiwayatTransaksi;
    private FloatingActionButton fabTambahTransaksi;

    private DatabaseHelper dbHelper;
    private List<TransaksiModel> listTransaksi = new ArrayList<>();
    private RiwayatTransaksiAdapter riwayatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Inflate layout utama halaman riwayat
        View view = inflater.inflate(R.layout.fragment_riwayat_trans, container, false);

        // 2. Inisialisasi Komponen UI dari XML
        rvRiwayatTransaksi = view.findViewById(R.id.rvRiwayatTransaksi);
        fabTambahTransaksi = view.findViewById(R.id.fabTambahTransaksi);

        // 3. Inisialisasi Database Helper
        dbHelper = new DatabaseHelper(getContext());

        // 4. Atur susunan list secara vertikal ke bawah
        rvRiwayatTransaksi.setLayoutManager(new LinearLayoutManager(getContext()));

        // 5. Muat data dari SQLite dan pasang ke RecyclerView
        muatRiwayatTransaksi();

        // 6. ALUR GESER TAMPILAN: Saat tombol + diklik, pindah ke halaman mesin kasir
        fabTambahTransaksi.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TransFragment()) // Pastikan R.id.fragment_container sesuai dengan ID di activity_main.xml Anda
                    .addToBackStack(null) // Mengizinkan tombol 'Back' fisik HP untuk kembali ke riwayat
                    .commit();
        });

        return view;
    }

    private void muatRiwayatTransaksi() {
        listTransaksi.clear();
        listTransaksi.addAll(dbHelper.getAllTransaksi());

        // Tangani aksi menu popup "Detail Transaksi" dari adapter
        riwayatAdapter = new RiwayatTransaksiAdapter(listTransaksi, transaksi -> {

            // 1. TAMPILKAN MODAL DIALOG KUSTOM
            androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_detail_riwayat, null);
            dialogBuilder.setView(dialogView);

            TextView tvDetailNotaID = dialogView.findViewById(R.id.tvDetailNotaID);
            TextView tvDetailRincianBarang = dialogView.findViewById(R.id.tvDetailRincianBarang);
            TextView tvDetailTotalBayar = dialogView.findViewById(R.id.tvDetailTotalBayar);
            Button btnTutupDetail = dialogView.findViewById(R.id.btnTutupDetail);

            // 2. Set ID dan Total Bayar Nota
            tvDetailNotaID.setText("ID Nota: " + transaksi.getIdTransaksi());

            java.text.NumberFormat formatter = java.text.NumberFormat.getInstance(new java.util.Locale("id", "ID"));
            tvDetailTotalBayar.setText("Rp " + formatter.format(transaksi.getTotalBayar()));

            // 3. Ambil data item dari database berdasarkan ID Nota
            List<KeranjangModel> listItems = dbHelper.getDetailTransaksi(transaksi.getIdTransaksi());
            StringBuilder stringBuilder = new StringBuilder();

            for (KeranjangModel item : listItems) {
                stringBuilder.append("•  ")
                        .append(item.getNamaProduk())
                        .append("  (")
                        .append(item.getJumlah())
                        .append("x)\n   ")
                        .append("Rp ").append(formatter.format(item.getSubtotal()))
                        .append("\n\n");
            }

            // Pasang hasil rangkuman teks belanjaan ke TextView
            tvDetailRincianBarang.setText(stringBuilder.toString().trim());

            // Tampilkan jendela modal ke layar
            androidx.appcompat.app.AlertDialog alertDialog = dialogBuilder.create();
            if (alertDialog.getWindow() != null) {
                // Memberikan efek rounded corner pada background dialog agar serasi dengan card aplikasi
                alertDialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
            alertDialog.show();

            // Logika untuk menutup jendela modal dialog
            btnTutupDetail.setOnClickListener(v -> alertDialog.dismiss());
        });

        rvRiwayatTransaksi.setAdapter(riwayatAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Segarkan data otomatis saat kasir kembali dari halaman TransFragment setelah sukses bertransaksi
        muatRiwayatTransaksi();
    }
}