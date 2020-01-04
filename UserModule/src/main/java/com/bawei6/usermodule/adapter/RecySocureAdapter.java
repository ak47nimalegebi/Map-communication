package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.bean.ScoureBean;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecySocureAdapter extends RecyclerView.Adapter<RecySocureAdapter.MyViewHolderSocre> {

    private Context context;
    private List<ScoureBean.DataBean> list;
    private CallBackOnclick callBackOnclick;

    public void getonClick(CallBackOnclick callBackOnclick) {
        this.callBackOnclick = callBackOnclick;
    }

    public RecySocureAdapter(Context context, List<ScoureBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolderSocre onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.scour, parent, false);
        return new MyViewHolderSocre(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSocre holder, final int position) {
        Glide.with(context).load(R.drawable.lianxiren2).into(holder.recy_scoure_img);
        holder.recy_scoure_title.setText(list.get(position).getUsername());

        holder.recy_scoure_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackOnclick.getOnclick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolderSocre extends RecyclerView.ViewHolder {
        private ImageView recy_scoure_img;
        private TextView recy_scoure_title;
        public MyViewHolderSocre(@NonNull View itemView) {
            super(itemView);
            recy_scoure_img=itemView.findViewById(R.id.recy_scoure_img);
            recy_scoure_title=itemView.findViewById(R.id.recy_scoure_tv);
        }
    }
}
