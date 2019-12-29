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

import com.example.ptkmprogmob.APIHelper.BaseApiService;
import com.example.ptkmprogmob.APIHelper.RetrofitClient;
import com.example.ptkmprogmob.APIHelper.UtilsApi;
import com.example.ptkmprogmob.DetailKegiatanActivity;
import com.example.ptkmprogmob.DetailSkpActivity;
import com.example.ptkmprogmob.Model.Skp;
import com.example.ptkmprogmob.R;
import com.example.ptkmprogmob.UpdateSkpActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkpAdapter extends RecyclerView.Adapter<SkpAdapter.MyViewHolder>{

    private List<Skp> skpList;
    private Context context;
    private LayoutInflater inflater;
    Skp skp;
    String id_skp1="";

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
    public void onBindViewHolder(@NonNull SkpAdapter.MyViewHolder holder, final int position) {
        skp = getSkpList().get(position);

        holder.namaSkp.setText(getSkpList().get(position).getNamaSkp());
        holder.kategoriSkp.setText(skp.getKategoriSkp());
        holder.pointSkp.setText(skp.getPointSkp());

        holder.cvSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailSkpActivity.class);
                intent.putExtra("id_skp1", getSkpList().get(position).getId_skp());
                intent.putExtra("nama", getSkpList().get(position).getNamaSkp());
                intent.putExtra("tgl_awal", getSkpList().get(position).getTgl_awal());
                intent.putExtra("tgl_akhir", getSkpList().get(position).getTgl_akhir());
                intent.putExtra("tempat", getSkpList().get(position).getTempat_skp());
                intent.putExtra("bukti_skp", getSkpList().get(position).getBukti_skp());
                intent.putExtra("isVerifed", getSkpList().get(position).getIsVerifed());
                intent.putExtra("id_detail", getSkpList().get(position).getId_detail());
                intent.putExtra("id_user", getSkpList().get(position).getId_user());
                intent.putExtra("kategori", getSkpList().get(position).getKategoriSkp());
                intent.putExtra("tingkat", getSkpList().get(position).getTingkatSkp());
                intent.putExtra("keterangan", getSkpList().get(position).getKeteranganSkp());
                intent.putExtra("point", getSkpList().get(position).getPointSkp());
                context.startActivity(intent);

            }
        });

        holder.editSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, UpdateSkpActivity.class);
                intent.putExtra("id_skp1", getSkpList().get(position).getId_skp());
                intent.putExtra("nama", getSkpList().get(position).getNamaSkp());
                intent.putExtra("tgl_awal", getSkpList().get(position).getTgl_awal());
                intent.putExtra("tgl_akhir", getSkpList().get(position).getTgl_akhir());
                intent.putExtra("tempat", getSkpList().get(position).getTempat_skp());
                intent.putExtra("bukti_skp", getSkpList().get(position).getBukti_skp());
                intent.putExtra("isVerifed", getSkpList().get(position).getIsVerifed());
                intent.putExtra("id_detail", getSkpList().get(position).getId_detail());
                intent.putExtra("id_user", getSkpList().get(position).getId_user());
                intent.putExtra("kategori", getSkpList().get(position).getKategoriSkp());
                intent.putExtra("tingkat", getSkpList().get(position).getTingkatSkp());
                intent.putExtra("keterangan", getSkpList().get(position).getKeteranganSkp());
                intent.putExtra("point", getSkpList().get(position).getPointSkp());
                context.startActivity(intent);

            }
        });

        holder.deleteSkp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?").setMessage("Delete this Document").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSkp(skp.getId_skp(), position);
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
        return getSkpList().size();
    }

    public List<Skp> getSkpList() {
        return skpList;
    }

    public void setSkpList(List<Skp> skpList) {
        this.skpList = skpList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namaSkp, kategoriSkp, pointSkp;
        private ImageView editSkp, deleteSkp;
        private CardView cvSkp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cvSkp = itemView.findViewById(R.id.cvSkp);
            editSkp = itemView.findViewById(R.id.ivEditSkp);
            deleteSkp = itemView.findViewById(R.id.ivDeleteSkp);
            namaSkp = itemView.findViewById(R.id.tvNamaSkp);
            kategoriSkp = itemView.findViewById(R.id.tvKategori);
            pointSkp = itemView.findViewById(R.id.tvPoint);


        }
    }

    private void deleteSkp(String id_skp1, final int position){
        RetrofitClient.getClient(UtilsApi.BASE_URL_API)
                .create(BaseApiService.class)
                .deleteSkp(id_skp1)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast toast = Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT);
                        toast.show();

                        skpList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, skpList.size());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
