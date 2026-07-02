package dinamika.pos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

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
        db.execSQL(CREATE_PRODUK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUK);
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
    public boolean updateProduk(String namaLama, String namaBaru, String harga, String satuan, String fotoUri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, namaBaru);
        values.put(KEY_HARGA, harga);
        values.put(KEY_SATUAN, satuan);
        values.put(KEY_FOTO, fotoUri);

        int result = db.update(TABLE_PRODUK, values, KEY_ID + " = ?", new String[]{namaLama});
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
}