package dinamika.pos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import dinamika.pos.entitas.KeranjangModel;
import dinamika.pos.entitas.Produk;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DinamikaPos.db";
    private static final int DATABASE_VERSION = 1;

    // Nama Tabel & Kolom
    private static final String TABLE_PRODUK = "produk";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_HARGA = "harga";
    private static final String KEY_SATUAN = "satuan";
    private static final String KEY_FOTO = "foto";

    // 2. NAMA TABEL & KOLOM - TRANSAKSI (INDOK/NOTA)
    public static final String TABLE_TRANSAKSI = "transaksi";
    public static final String KEY_TRANS_ID = "id_transaksi";       // Contoh: TX-20231024-001
    public static final String KEY_TRANS_TANGGAL = "tanggal";      // Format: YYYY-MM-DD HH:MM:SS
    public static final String KEY_TRANS_TOTAL = "total_bayar";

    // 3. NAMA TABEL & KOLOM - TRANSAKSI DETAIL (ISI KERANJANG)
    public static final String TABLE_TRANS_DETAIL = "transaksi_detail";
    public static final String KEY_DETAIL_ID = "id_detail";
    public static final String KEY_DETAIL_TRANS_ID = "id_transaksi"; // Menghubungkan ke TABLE_TRANSAKSI
    public static final String KEY_DETAIL_PRODUK_ID = "id_produk";   // Menghubungkan ke TABLE_PRODUK
    public static final String KEY_DETAIL_NAMA = "nama_produk";      // Duplikasi nama untuk histori jika produk dihapus
    public static final String KEY_DETAIL_HARGA = "harga_jual";      // Harga saat transaksi terjadi
    public static final String KEY_DETAIL_QTY = "jumlah";            // Quantity produk yang dibeli
    public static final String KEY_DETAIL_SUBTOTAL = "subtotal";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUK_TABLE = "CREATE TABLE " + TABLE_PRODUK + "("
                + KEY_ID + " TEXT PRIMARY KEY ,"
                + KEY_NAMA + " TEXT,"
                + KEY_HARGA + " TEXT,"
                + KEY_SATUAN + " TEXT,"
                + KEY_FOTO + " TEXT" + ")";

        // Query Pembuatan Tabel Transaksi
        String CREATE_TRANSAKSI_TABLE = "CREATE TABLE " + TABLE_TRANSAKSI + "("
                + KEY_TRANS_ID + " TEXT PRIMARY KEY,"
                + KEY_TRANS_TANGGAL + " TEXT,"
                + KEY_TRANS_TOTAL + " INTEGER" + ")";

        // Query Pembuatan Tabel Detail Transaksi (Menggunakan Foreign Key)
        String CREATE_TRANS_DETAIL_TABLE = "CREATE TABLE " + TABLE_TRANS_DETAIL + "("
                + KEY_DETAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_DETAIL_TRANS_ID + " TEXT,"
                + KEY_DETAIL_PRODUK_ID + " TEXT,"
                + KEY_DETAIL_NAMA + " TEXT,"
                + KEY_DETAIL_HARGA + " INTEGER,"
                + KEY_DETAIL_QTY + " INTEGER,"
                + KEY_DETAIL_SUBTOTAL + " INTEGER,"
                + "FOREIGN KEY(" + KEY_DETAIL_TRANS_ID + ") REFERENCES " + TABLE_TRANSAKSI + "(" + KEY_TRANS_ID + ") ON DELETE CASCADE,"
                + "FOREIGN KEY(" + KEY_DETAIL_PRODUK_ID + ") REFERENCES " + TABLE_PRODUK + "(" + KEY_ID + ")" + ")";

        db.execSQL(CREATE_PRODUK_TABLE);
        db.execSQL(CREATE_TRANSAKSI_TABLE);
        db.execSQL(CREATE_TRANS_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);

        onCreate(db);
    }

    // 1. FUNGSI SIMPAN DATA (INSERT)
    public boolean insertProduk(String kode, String nama, String harga, String satuan, String fotoUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, kode);
        values.put(KEY_NAMA, nama);
        values.put(KEY_HARGA, harga);
        values.put(KEY_SATUAN, satuan);
        values.put(KEY_FOTO, fotoUri);

        long result = db.insert(TABLE_PRODUK, null, values);
        db.close();
        return result != -1;
    }

    // 2. FUNGSI AMBIL SEMUA DATA (SELECT ALL)
    public List<Produk> getAllProduk() {
        List<Produk> listProduk = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUK + " ORDER BY " + KEY_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Produk produk = new Produk(
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAMA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_HARGA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_SATUAN)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_FOTO))
                );
                listProduk.add(produk);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listProduk;
    }

    // 3. FUNGSI HAPUS PRODUK (DELETE) - *Baru*
    // Membantu menghapus produk berdasarkan namanya
    public boolean deleteProduk(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PRODUK, KEY_ID + " = ?", new String[]{kode});
        db.close();
        return result > 0; // Return true jika ada baris yang terhapus
    }

    // 4. FUNGSI EDIT/UBAH DATA (UPDATE) - *Baru*
    // Digunakan saat Anda ingin membuat fitur edit info produk
    public boolean updateProduk(String kode, String namaBaru, String harga, String satuan, String fotoUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, namaBaru);
        values.put(KEY_HARGA, harga);
        values.put(KEY_SATUAN, satuan);
        values.put(KEY_FOTO, fotoUri);

        int result = db.update(TABLE_PRODUK, values, KEY_ID + " = ?", new String[]{kode});
        db.close();
        return result > 0;
    }

    // 5. FUNGSI CARI PRODUK (SEARCH) - *Baru*
    // Sangat berguna jika Anda ingin menambahkan fitur Search Bar di HomeFragment
    public List<Produk> searchProduk(String keyword) {
        List<Produk> listProduk = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PRODUK + " WHERE " + KEY_ID + " LIKE ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                Produk produk = new Produk(
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAMA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_HARGA)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_SATUAN)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_FOTO))
                );
                listProduk.add(produk);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listProduk;
    }

    // 6. FUNGSI BARU: SIMPAN TRANSAKSI BESERTA DETAILNYA (KERANJANG)
    public boolean insertTransaksi(String idTransaksi, String tanggal, int totalBayar, List<KeranjangModel> listKeranjang) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccess = false;

        // Menggunakan transaction agar jika salah satu detail gagal disimpan, seluruh transaksi dibatalkan (aman)
        db.beginTransaction();
        try {
            // A. Insert ke TABLE_TRANSAKSI (Struk Utama)
            ContentValues transValues = new ContentValues();
            transValues.put(KEY_TRANS_ID, idTransaksi);
            transValues.put(KEY_TRANS_TANGGAL, tanggal);
            transValues.put(KEY_TRANS_TOTAL, totalBayar);

            long transResult = db.insert(TABLE_TRANSAKSI, null, transValues);

            if (transResult != -1) {
                // B. Loop Keranjang untuk Insert ke TABLE_TRANS_DETAIL
                for (KeranjangModel item : listKeranjang) {
                    ContentValues detailValues = new ContentValues();
                    detailValues.put(KEY_DETAIL_TRANS_ID, idTransaksi);
                    detailValues.put(KEY_DETAIL_PRODUK_ID, item.getIdProduk());
                    detailValues.put(KEY_DETAIL_NAMA, item.getNamaProduk());
                    detailValues.put(KEY_DETAIL_HARGA, item.getHargaJual());
                    detailValues.put(KEY_DETAIL_QTY, item.getJumlah());
                    detailValues.put(KEY_DETAIL_SUBTOTAL, item.getSubtotal());

                    long detailResult = db.insert(TABLE_TRANS_DETAIL, null, detailValues);
                    if (detailResult == -1) {
                        // Jika ada 1 barang saja yang gagal dimasukkan, lempar exception untuk membatalkan semuanya
                        throw new Exception("Gagal memasukkan detail transaksi");
                    }
                }
                // Jika semua proses aman, tandai transaksi berhasil
                db.setTransactionSuccessful();
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            db.endTransaction(); // Mengakhiri sesi aman
            db.close();
        }

        return isSuccess;
    }

}