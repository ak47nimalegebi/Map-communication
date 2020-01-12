package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usermodule.R;
import com.bawei6.usermodule.bean.MyHuoding;

import java.util.List;

public class RecyHuoAdapter extends RecyclerView.Adapter<RecyHuoAdapter.MyViewHodler> {

    private Context context;
    private List<MyHuoding> list;

    public RecyHuoAdapter(Context context, List<MyHuoding> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.user_recy_huo, parent, false);
        return new MyViewHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        holder.recy_huo_tv_num.setText(list.get(position).getNum());
        holder.recy_huo_tv_time.setText(list.get(position).getTime());
        holder.recy_huo_tv_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        TextView recy_huo_tv_time;
        TextView recy_huo_tv_num;
        TextView recy_huo_tv_title;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            recy_huo_tv_num=itemView.findViewById(R.id.recy_huo_tv_num);
            recy_huo_tv_time=itemView.findViewById(R.id.recy_huo_tv_time);
            recy_huo_tv_title=itemView.findViewById(R.id.recy_huo_tv_title);
        }
    }
}
