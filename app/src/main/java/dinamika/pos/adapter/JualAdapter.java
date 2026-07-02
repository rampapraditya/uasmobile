package dinamika.pos.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import dinamika.pos.R;
import dinamika.pos.entitas.Produk;

public class JualAdapter extends RecyclerView.Adapter<JualAdapter.ProdukViewHolder> {

    private List<Produk> listProduk;
    private OnProdukMenuClickListener listener;

    // Interface baru untuk mendeteksi pilihan menu Edit atau Delete
    public interface OnProdukMenuClickListener {
        void onEditClick(Produk produk);
        void onDeleteClick(Produk produk);
    }

    public JualAdapter(List<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    public void setOnProdukMenuClickListener(OnProdukMenuClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<Produk> newList) {
        this.listProduk = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProdukViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ProdukViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdukViewHolder holder, int position) {
        Produk produk = listProduk.get(position);

        holder.tvNama.setText(produk.getNama());
        holder.tvHarga.setText(produk.getHarga());
        holder.tvSatuan.setText(produk.getSatuan());

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

        // KONDISI KLIK PADA TOMBOL TITIK TIGA
        holder.imgMenuMore.setOnClickListener(v -> {
            // Membuat PopupMenu dinamis di samping tombol titik tiga
            PopupMenu popup = new PopupMenu(v.getContext(), v);

            // Menambahkan item menu secara langsung lewat kode Java (tanpa file XML menu terpisah)
            popup.getMenu().add(0, 1, 0, "Edit");
            popup.getMenu().add(0, 2, 1, "Hapus");

            // Mengatur aksi ketika salah satu menu dipilih
            popup.setOnMenuItemClickListener(item -> {
                if (listener != null) {
                    if (item.getItemId() == 1) {
                        listener.onEditClick(produk); // Memicu fungsi edit di Fragment
                        return true;
                    } else if (item.getItemId() == 2) {
                        listener.onDeleteClick(produk); // Memicu fungsi delete di Fragment
                        return true;
                    }
                }
                return false;
            });
            popup.show(); // Menampilkan menu ke layar
        });
    }

    @Override
    public int getItemCount() {
        return listProduk != null ? listProduk.size() : 0;
    }

    public static class ProdukViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHarga, tvSatuan;
        ImageView imgFoto, imgMenuMore;

        public ProdukViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaProduk);
            tvHarga = itemView.findViewById(R.id.tvHargaProduk);
            tvSatuan = itemView.findViewById(R.id.tvSatuanProduk);
            imgFoto = itemView.findViewById(R.id.imgProduk);
            imgMenuMore = itemView.findViewById(R.id.imgMenuMore); // Inisialisasi ID titik tiga
        }
    }
}
