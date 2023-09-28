package Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luyentapchoi.R;

import java.util.ArrayList;

import DAO.SinhvienDao;
import DTO.Sinhvien_DTO;

public class svAdapter extends  RecyclerView.Adapter<svAdapter.viewHolder>{
        Context context;
        ArrayList<Sinhvien_DTO>lisst;

        SinhvienDao svdao;

    public svAdapter(Context context, ArrayList<Sinhvien_DTO> lisst) {
        this.context = context;
        this.lisst = lisst;
        svdao=new SinhvienDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater. inflate(R.layout.dongitem,null);
        return  new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
         Sinhvien_DTO sv=lisst.get(position);
        holder.txtis.setText(sv.getId()+"");
        holder.txtten.setText(sv.getTen());
        holder.txttuoi.setText(sv.getTuoi()+"");
        holder.txtsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v=inflater.inflate(R.layout.item_sua,null);
                builder.setView(view);
                Dialog dialog=builder. create();
                dialog.show();
                EditText estten=v.findViewById(R.id.sua);
                EditText edtuoi=v.findViewById(R.id.xoa);
                Button button=v.findViewById(R.id.btnsua);
                edtuoi.setText(lisst.get(position).getTuoi());
                estten.setText(lisst.get(position).getTen());

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("sua");
                        builder.setMessage("ban muon sua khong");
                        builder.setPositiveButton("cos", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              Sinhvien_DTO dto=lisst. get(position);
                              dto.setTen(estten.getText().toString());
                              dto.setTuoi(Integer.parseInt(edtuoi.getText().toString()));
                               int id=svdao.update(dto);
                               if (id>0){

                                   Toast.makeText(context, "sau thanh cong", Toast.LENGTH_SHORT).show();
                                   lisst.clear();
                                   lisst.addAll(svdao.gettAll());
                                   notifyItemChanged(holder.getAdapterPosition());
                                   dialog.dismiss();
                               }

                            }
                        });
                        builder.setNegativeButton("khong",null);

                    }
                });
                holder.txtxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("sua");
                        builder.setMessage("ban muon xoakhong");
                        builder.setPositiveButton("cos", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                int id=svdao.update(lisst.get(position));
                                if (id>0){
                                    lisst.remove(position);
                                    Toast.makeText(context, "sau thanh cong", Toast.LENGTH_SHORT).show();
                                    lisst.clear();
                                    lisst.addAll(svdao.gettAll());
                                    notifyItemChanged(holder.getAdapterPosition());

                                }

                            }
                        });
                        builder.setNegativeButton("khong",null);
                        builder.show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return lisst.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtis,txtten,txttuoi,txtsua,txtxoa;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtis=itemView.findViewById(R.id.txtid);
            txtsua=itemView.findViewById(R.id.txtsua);
            txtten=itemView.findViewById(R.id.txtten );
            txttuoi=itemView.findViewById(R.id.txttuoi);
            txtxoa=itemView.findViewById(R.id.txtxoa);
        }
    }
}
