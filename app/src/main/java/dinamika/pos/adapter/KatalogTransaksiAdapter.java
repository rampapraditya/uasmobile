package dinamika.pos.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import dinamika.pos.R;
import dinamika.pos.entitas.Produk;

public class KatalogTransaksiAdapter extends RecyclerView.Adapter<KatalogTransaksiAdapter.KatalogViewHolder> {

    private List<Produk> listProduk;
    private OnKatalogItemClickListener listener;

    // Interface untuk mendeteksi ketukan produk di TransFragment
    public interface OnKatalogItemClickListener {
        void onProdukClick(Produk produk);
    }

    public KatalogTransaksiAdapter(List<Produk> listProduk, OnKatalogItemClickListener listener) {
        this.listProduk = listProduk;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new KatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KatalogViewHolder holder, int position) {
        Produk produk = listProduk.get(position);

        holder.tvNama.setText(produk.getNama());
        holder.tvHarga.setText(produk.getHarga());
        holder.tvSatuan.setText(produk.getSatuan());

        // Menyembunyikan tombol titik tiga secara permanen di halaman transaksi
        if (holder.imgMenuMore != null) {
            holder.imgMenuMore.setVisibility(View.GONE);
        }

        // Memuat gambar dengan Glide seperti adapter lama Anda
        if (produk.getFoto() != null && !produk.getFoto().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(Uri.parse(produk.getFoto()))
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_gallery)
                    .centerCrop()
                    .into(holder.imgFoto);
        } else {
            holder.imgFoto.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        // Saat satu kotak menu produk diklik, langsung kirim datanya ke keranjang
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProdukClick(produk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduk != null ? listProduk.size() : 0;
    }

    public static class KatalogViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHarga, tvSatuan;
        ImageView imgFoto, imgMenuMore;

        public KatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaProduk);
            tvHarga = itemView.findViewById(R.id.tvHargaProduk);
            tvSatuan = itemView.findViewById(R.id.tvSatuanProduk);
            imgFoto = itemView.findViewById(R.id.imgProduk);
            imgMenuMore = itemView.findViewById(R.id.imgMenuMore); // Tetap diinisialisasi agar bisa disembunyikan
        }
    }
}