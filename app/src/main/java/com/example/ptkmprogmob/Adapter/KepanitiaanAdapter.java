package com.example.ptkmprogmob.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.DetailKegiatanActivity;
import com.example.ptkmprogmob.Model.Kepanitiaan;
import com.example.ptkmprogmob.R;
import com.example.ptkmprogmob.UpdateKegiatanActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KepanitiaanAdapter extends RecyclerView.Adapter<KepanitiaanAdapter.MyViewHolder>{

    private List<Kepanitiaan> kepanitiaanList;
    private Context context;
    private LayoutInflater inflater;
    Kepanitiaan kepanitiaan;
    String id_kegiatan="";

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
    public void onBindViewHolder(@NonNull KepanitiaanAdapter.MyViewHolder holder, final int position) {
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
                intent.putExtra("id_kegiatan", getKepanitiaanList().get(position).getId_kegiatan());
                intent.putExtra("nama_kegiatan", getKepanitiaanList().get(position).getNama_kegiatan());
                intent.putExtra("tanggal_kegiatan", getKepanitiaanList().get(position).getTanggal_kegiatan());
                intent.putExtra("tempat_kegiatan", getKepanitiaanList().get(position).getTempat_kegiatan());
                intent.putExtra("desc_kegiatan", getKepanitiaanList().get(position).getDesc_kegiatan());
                intent.putExtra("image_kegiatan", getKepanitiaanList().get(position).getImage_kegiatan());
                context.startActivity(intent);

            }
        });

        holder.editKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateKegiatanActivity.class);
                intent.putExtra("id_kegiatan", getKepanitiaanList().get(position).getId_kegiatan());
                intent.putExtra("nama_kegiatan", getKepanitiaanList().get(position).getNama_kegiatan());
                intent.putExtra("tanggal_kegiatan", getKepanitiaanList().get(position).getTanggal_kegiatan());
                intent.putExtra("tempat_kegiatan", getKepanitiaanList().get(position).getTempat_kegiatan());
                intent.putExtra("desc_kegiatan", getKepanitiaanList().get(position).getDesc_kegiatan());
                intent.putExtra("image_kegiatan", getKepanitiaanList().get(position).getImage_kegiatan());
                context.startActivity(intent);

            }
        });

        holder.deleteKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?").setMessage("Delete this Document").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteKegiatan(kepanitiaan.getId_kegiatan(), position);
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

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
            imageKegiatan = itemView.findViewById(R.id.ivKegiatanImage);

            viewKegiatan = itemView.findViewById(R.id.ivViewKegiatan);
            editKegiatan = itemView.findViewById(R.id.ivEditKegiatan);
            deleteKegiatan = itemView.findViewById(R.id.ivDeleteKegiatan);

        }
    }

    private void deleteKegiatan(String id_kegiatan, final int position){
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .deleteKegiatan(id_kegiatan)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast toast = Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT);
                        toast.show();

                        kepanitiaanList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, kepanitiaanList.size());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
