package dinamika.pos.fragment.jual;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import dinamika.pos.DatabaseHelper;
import dinamika.pos.R;

public class DetilJualFragment extends Fragment {

    private TextInputEditText etKode, etNama, etHarga, etSatuan;
    private Button btnSimpan;
    private CardView cardBrowse;
    private ImageView imgPreview;
    private LinearLayout layoutPlaceholder;
    private DatabaseHelper dbHelper;
    private Uri imageUri = null;

    private String statusAksi = "tambah"; // Defaultnya adalah tambah data
    private String namaProdukLama = "";   // Untuk menampung kunci pencarian update SQLite

    // Launcher Sistem Pencari Berkas Media (Browse File) HP
    private final ActivityResultLauncher<String> browseLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            result -> {
                if (result != null) {
                    imageUri = result;

                    // Tampilkan gambar dan sembunyikan petunjuk tulisan di tengah card
                    imgPreview.setVisibility(View.VISIBLE);
                    layoutPlaceholder.setVisibility(View.GONE);
                    imgPreview.setImageURI(imageUri);
                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tambah_produk, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi View & DB seperti biasa
        cardBrowse = view.findViewById(R.id.cardBrowseFoto);
        imgPreview = view.findViewById(R.id.imgPreviewProduk);
        layoutPlaceholder = view.findViewById(R.id.layoutBrowsePlaceholder);

        etKode = view.findViewById(R.id.etKodeProduk);
        etNama = view.findViewById(R.id.etNamaProduk);
        etHarga = view.findViewById(R.id.etHargaProduk);
        etSatuan = view.findViewById(R.id.etSatuanProduk);
        btnSimpan = view.findViewById(R.id.btnSimpan);
        dbHelper = new DatabaseHelper(getContext());

        // Cek apakah ada data kiriman (Bundle) dari HomeFragment
        if (getArguments() != null) {
            statusAksi = getArguments().getString("aksi", "tambah");

            if (statusAksi.equals("edit")) {
                // 1. Ubah teks judul atau tombol jika diperlukan
                btnSimpan.setText("Perbarui Produk");

                // 2. Isi otomatis form inputan dengan data lama
                etKode.setEnabled(false);
                etKode.setText(getArguments().getString("kode"));
                etNama.setText(getArguments().getString("nama"));
                etHarga.setText(getArguments().getString("harga"));
                etSatuan.setText(getArguments().getString("satuan"));

                // 3. Tampilkan pratinjau foto lama
                String fotoString = getArguments().getString("foto");
                if (fotoString != null && !fotoString.isEmpty()) {
                    imageUri = Uri.parse(fotoString);
                    imgPreview.setVisibility(View.VISIBLE);
                    layoutPlaceholder.setVisibility(View.GONE);
                    imgPreview.setImageURI(imageUri);
                }
            }
        }

        // Aksi klik browse gambar tetap sama
        cardBrowse.setOnClickListener(v -> browseLauncher.launch("image/*"));

        // Modifikasi Logika Tombol Simpan/Perbarui
        btnSimpan.setOnClickListener(v -> {
            String kode = etKode.getText().toString().trim();
            String nama = etNama.getText().toString().trim();
            String hargaInput = etHarga.getText().toString().trim();
            String satuan = etSatuan.getText().toString().trim();

            if (imageUri == null) {
                Toast.makeText(getContext(), "Silakan pilih foto produk melalui menu Browse!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(hargaInput) || TextUtils.isEmpty(satuan)) {
                Toast.makeText(getContext(), "Semua kolom teks wajib diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            String hargaFormatted = "Rp " + formatRupiah(hargaInput);

            // PERUBAHAN DI SINI: Salin file gambar ke internal storage dan ambil path barunya
            String fotoPathYangDisimpan = simpanKeInternalStorage(imageUri);

            // Antisipasi jika proses penyalinan gambar ke internal gagal
            if (TextUtils.isEmpty(fotoPathYangDisimpan)) {
                Toast.makeText(getContext(), "Gagal memproses gambar produk!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isSuccess;

            // Eksekusi Query berdasarkan status aksi (Edit vs Tambah)
            if (statusAksi.equals("edit")) {
                // PERUBAHAN DI SINI: Gunakan fotoPathYangDisimpan, bukan imageUri.toString()
                isSuccess = dbHelper.updateProduk(kode, nama, hargaFormatted, satuan, fotoPathYangDisimpan);
            } else {
                // PERUBAHAN DI SINI: Gunakan fotoPathYangDisimpan, bukan imageUri.toString()
                isSuccess = dbHelper.insertProduk(kode, nama, hargaFormatted, satuan, fotoPathYangDisimpan);
            }

            if (isSuccess) {
                String pesan = statusAksi.equals("edit") ? "Produk berhasil diperbarui!" : "Produk berhasil disimpan!";
                Toast.makeText(getContext(), pesan, Toast.LENGTH_SHORT).show();

                if (getParentFragmentManager() != null) {
                    getParentFragmentManager().popBackStack();
                }
            } else {
                Toast.makeText(getContext(), "Proses ke database gagal!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Fungsi utilitas sederhana untuk merapikan ribuan angka harga dengan tanda titik
    private String formatRupiah(String angka) {
        try {
            double parsed = Double.parseDouble(angka);
            return String.format("%,.0f", parsed).replaceAll(",", ".");
        } catch (NumberFormatException e) {
            return angka;
        }
    }

    private String simpanKeInternalStorage(Uri uri) {
        if (uri == null) return "";

        // Keamanan Ekstra: Jika URI sudah merupakan file internal milik aplikasi sendiri
        // (misalnya saat proses edit tanpa mengubah foto), langsung kembalikan string aslinya.
        if (uri.toString().contains(requireContext().getPackageName())) {
            return uri.toString();
        }

        try {
            // Buat nama file unik menggunakan timestamp agar tidak saling menimpa
            String namaFileBaru = "produk_" + System.currentTimeMillis() + ".jpg";
            File fileTujuan = new File(requireContext().getFilesDir(), namaFileBaru);

            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(fileTujuan);

            // PASTIKAN BAGIAN INI TERULIS [1024] AGAR UKURAN BUFFER VALID
            byte[] buffer = new byte[1024];
            int bytesRead;
            if (inputStream != null) {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
            }
            outputStream.close();

            // Mengembalikan URI dalam bentuk file path internal yang sah
            return Uri.fromFile(fileTujuan).toString();

        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Jika gagal menyalin, kembalikan string kosong agar sistem tahu gambar tidak valid
        }
    }
}
