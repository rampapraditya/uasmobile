package dinamika.pos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import dinamika.pos.R;
import dinamika.pos.entitas.KeranjangModel;

public class KeranjangAdapter extends RecyclerView.Adapter<KeranjangAdapter.KeranjangViewHolder> {

    private List<KeranjangModel> listKeranjang;
    // --- TAMBAHAN BARU: DEKLARASI LISTENER ---
    private OnKeranjangItemClickListener listener;

    public interface OnKeranjangItemClickListener {
        void onKeranjangClick(int position);
    }
    // ----------------------------------------

    // Perbarui Constructor agar menerima listener
    public KeranjangAdapter(List<KeranjangModel> listKeranjang, OnKeranjangItemClickListener listener) {
        this.listKeranjang = listKeranjang;
        this.listener = listener;
    }

    @NonNull
    @Override
    public KeranjangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keranjang, parent, false);
        return new KeranjangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeranjangViewHolder holder, int position) {
        KeranjangModel item = listKeranjang.get(position);

        holder.tvNama.setText(item.getNamaProduk());
        holder.tvQty.setText(item.getJumlah() + " x");
        holder.tvSubtotal.setText("Rp " + item.getSubtotal());

        // --- TAMBAHAN BARU: EVENT KLIK BARIS KERANJANG ---
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onKeranjangClick(position);
            }
        });
        // -------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return listKeranjang != null ? listKeranjang.size() : 0;
    }

    public static class KeranjangViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvQty, tvSubtotal;

        public KeranjangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNamaKeranjang);
            tvQty = itemView.findViewById(R.id.tvQtyKeranjang);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotalKeranjang);
        }
    }
}
