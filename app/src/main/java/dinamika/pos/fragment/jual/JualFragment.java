package dinamika.pos.fragment.jual;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dinamika.pos.DatabaseHelper;
import dinamika.pos.R;
import dinamika.pos.adapter.JualAdapter;
import dinamika.pos.entitas.Produk;

public class JualFragment extends Fragment {

    private RecyclerView rvHome;
    private FloatingActionButton fabAdd;
    private JualAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jual, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Inisialisasi View & Database
        rvHome = view.findViewById(R.id.rvHome);
        fabAdd = view.findViewById(R.id.fabAdd);
        dbHelper = new DatabaseHelper(getContext());

        // 2. Setup Awal RecyclerView dengan List Kosong
        List<Produk> listProduk = new ArrayList<>();
        adapter = new JualAdapter(listProduk);

        // Cek konfigurasi orientasi layar saat ini
        int orientasi = getResources().getConfiguration().orientation;
        int jumlahKolom;

        if (orientasi == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            jumlahKolom = 4; // Layar Horizontal / Tidur
        } else {
            jumlahKolom = 2; // Layar Vertikal / Berdiri / Default
        }

        rvHome.setLayoutManager(new GridLayoutManager(getContext(), jumlahKolom));
        rvHome.setAdapter(adapter);


        fabAdd.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new DetilJualFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // 3. Logika Klik Menu Pilihan di Tombol Titik Tiga
        adapter.setOnProdukMenuClickListener(new JualAdapter.OnProdukMenuClickListener() {
            @Override
            public void onEditClick(Produk produk) {
                // Logika untuk mengedit produk (Sama seperti logika klik Anda sebelumnya)
                DetilJualFragment fragmentEdit = new DetilJualFragment();
                String hargaMentah = produk.getHarga().replace("Rp ", "").replace(".", "").trim();

                Bundle args = new Bundle();
                args.putString("aksi", "edit");
                args.putString("kode", produk.getKode());
                args.putString("nama", produk.getNama());
                args.putString("harga", hargaMentah);
                args.putString("satuan", produk.getSatuan());
                args.putString("foto", produk.getFoto());
                fragmentEdit.setArguments(args);

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragmentEdit)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onDeleteClick(Produk produk) {
                // Logika untuk menghapus produk langsung dari database SQLite
                boolean berhasilHapus = dbHelper.deleteProduk(produk.getKode());

                if (berhasilHapus) {
                    Toast.makeText(getContext(), "Produk '" + produk.getNama() + "' berhasil dihapus", Toast.LENGTH_SHORT).show();

                    // Ambil data paling segar dari SQLite dan segarkan isi grid RecyclerView
                    List<Produk> dataTerbaru = dbHelper.getAllProduk();
                    adapter.updateData(dataTerbaru);
                } else {
                    Toast.makeText(getContext(), "Gagal menghapus produk", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        if (dbHelper != null && adapter != null) {
            List<Produk> dataTerbaru = dbHelper.getAllProduk();
            adapter.updateData(dataTerbaru);
        }
    }
}