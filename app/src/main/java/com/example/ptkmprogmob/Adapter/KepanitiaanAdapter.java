package com.example.ptkmprogmob.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.DetailKegiatanActivity;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.example.ptkmprogmob.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KepanitiaanAdapter extends RecyclerView.Adapter<KepanitiaanAdapter.MyViewHolder>{

    private List<Kepanitiaan> kepanitiaanList;
    private Context context;
    private LayoutInflater inflater;
    Kepanitiaan kepanitiaan;

    public KepanitiaanAdapter(Context context, List<Kepanitiaan> kepanitiaanList) {
        this.context = context;
        this.kepanitiaanList = kepanitiaanList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public KepanitiaanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.item_kegiatan, parent, false);
        return new KepanitiaanAdapter.MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull KepanitiaanAdapter.MyViewHolder holder, int position) {
        kepanitiaan = getKepanitiaanList().get(position);
        Glide.with(context)
                .load(UtilsApi.BASE_URL_IMAGE+kepanitiaan.getImage_kegiatan())
                .centerCrop()
                .placeholder(R.drawable.blank)
                .into(holder.imageKegiatan);
        Log.e("gg", kepanitiaan.getImage_kegiatan());

        holder.namaKegiatan.setText(kepanitiaan.getNama_kegiatan());
        holder.descKegiatan.setText(kepanitiaan.getDesc_kegiatan());

        holder.viewKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailKegiatanActivity.class);
                //intent.putExtra("id_kegiatan",kepanitiaan.get(position).getIdDocument());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return getKepanitiaanList().size();
    }

    public List<Kepanitiaan> getKepanitiaanList() {
        return kepanitiaanList;
    }

    public void setKepanitiaanList(List<Kepanitiaan> kepanitiaanList) {
        this.kepanitiaanList = kepanitiaanList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namaKegiatan, descKegiatan;
        private ImageView imageKegiatan, viewKegiatan, editKegiatan, deleteKegiatan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            namaKegiatan = itemView.findViewById(R.id.tvNamaKegiatan);
            descKegiatan = itemView.findViewById(R.id.tvDesc);
            imageKegiatan = itemView.findViewById(R.id.ivKepanitiaan);

            viewKegiatan = itemView.findViewById(R.id.ivViewKegiatan);
            editKegiatan = itemView.findViewById(R.id.ivEditKegiatan);
            deleteKegiatan = itemView.findViewById(R.id.ivDeleteKegiatan);

        }
    }
}
