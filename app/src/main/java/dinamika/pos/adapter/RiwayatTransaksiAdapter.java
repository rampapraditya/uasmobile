package dinamika.pos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import dinamika.pos.R;
import dinamika.pos.entitas.TransaksiModel;

public class RiwayatTransaksiAdapter extends RecyclerView.Adapter<RiwayatTransaksiAdapter.RiwayatViewHolder> {

    private List<TransaksiModel> listTransaksi;
    private OnRiwayatMenuClickListener listener;

    // Interface untuk mendeteksi pilihan menu klik dari Fragment
    public interface OnRiwayatMenuClickListener {
        void onDetailClick(TransaksiModel transaksi);
    }

    // Perbarui Constructor agar menerima interface klik
    public RiwayatTransaksiAdapter(List<TransaksiModel> listTransaksi, OnRiwayatMenuClickListener listener) {
        this.listTransaksi = listTransaksi;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RiwayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_transaksi, parent, false);
        return new RiwayatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatViewHolder holder, int position) {
        TransaksiModel trans = listTransaksi.get(position);

        holder.tvID.setText(trans.getIdTransaksi());
        holder.tvTanggal.setText(trans.getTanggal());

        // Format nominal uang menjadi Rupiah Indonesia (Contoh: Rp 35.000)
        try {
            NumberFormat formatter = NumberFormat.getInstance(new Locale("id", "ID"));
            String hargaFormat = formatter.format(trans.getTotalBayar());
            holder.tvTotal.setText("Rp " + hargaFormat);
        } catch (Exception e) {
            holder.tvTotal.setText("Rp " + trans.getTotalBayar());
        }

        // LOGIKA KLIK TOMBOL TITIK TIGA
        holder.imgMenuMore.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), v);

            // Tambah opsi "Detail Transaksi" ke popup
            popup.getMenu().add(0, 1, 0, "Detail Transaksi");

            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == 1 && listener != null) {
                    listener.onDetailClick(trans); // Mengirim data nota kembali ke fragment utama
                    return true;
                }
                return false;
            });
            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return listTransaksi != null ? listTransaksi.size() : 0;
    }

    public static class RiwayatViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvTanggal, tvTotal;
        ImageView imgMenuMore; // Inisialisasi komponen baru

        public RiwayatViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvRiwayatID);
            tvTanggal = itemView.findViewById(R.id.tvRiwayatTanggal);
            tvTotal = itemView.findViewById(R.id.tvRiwayatTotal);
            imgMenuMore = itemView.findViewById(R.id.imgMenuMoreRiwayat); // Sesuai ID di XML baru
        }
    }
}