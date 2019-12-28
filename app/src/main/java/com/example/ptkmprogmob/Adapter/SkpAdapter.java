package com.example.ptkmprogmob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptkmprogmob.Model.Skp;
import com.example.ptkmprogmob.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkpAdapter extends RecyclerView.Adapter<SkpAdapter.MyViewHolder>{
    private List<Skp> skpList;
    private Context context;
    private LayoutInflater inflater;
    Skp skp;

    public SkpAdapter(Context context, List<Skp> skpList) {
        this.context = context;
        this.skpList = skpList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public SkpAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_skp, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkpAdapter.MyViewHolder holder, int position) {
        skp = skpList.get(position);
//        Glide.with(context)
//                .load(UtilsApi.BASE_URL_API+skp.getBukti_skp())
//                .centerCrop()
//                .placeholder(R.drawable.)
//                .into(holder.mImage);

        holder.tvNama.setText(skp.getNama_kegiatan());
        holder.tvKategori.setText(skp.getKategori());
        holder.tvPoint.setText(skp.getPoint());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama, tvKategori, tvPoint;
//        private ImageView mImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNamaSkp);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvPoint = itemView.findViewById(R.id.tvPoint);

        }
    }
}
