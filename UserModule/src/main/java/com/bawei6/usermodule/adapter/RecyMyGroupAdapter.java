package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.bean.GetMyGroupBean;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;

import java.util.List;

public class RecyMyGroupAdapter extends RecyclerView.Adapter<RecyMyGroupAdapter.MygroupViewholder> {

    private Context context;
    private List<GetMyGroupBean.DataBean> list;
    private CallBackOnclick callBackOnclick;

    public void getcallBackOnclick(CallBackOnclick callBackOnclick){
        this.callBackOnclick=callBackOnclick;
    }

    public RecyMyGroupAdapter(Context context, List<GetMyGroupBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MygroupViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.mygroup_item, parent, false);
        return new MygroupViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MygroupViewholder holder, final int position) {
        holder.my_group_tv_groupName.setText(list.get(position).getGroupname());
        holder.my_group_tv_outgroup.setText("退群");
        holder.my_group_tv_outgroup.setOnClickListener(new View.OnClickListener() {
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

    class MygroupViewholder extends RecyclerView.ViewHolder {
        TextView my_group_tv_groupName;
        TextView my_group_tv_outgroup;
        public MygroupViewholder(@NonNull View itemView) {
            super(itemView);
            my_group_tv_groupName=itemView.findViewById(R.id.my_group_tv_groupName);
            my_group_tv_outgroup=itemView.findViewById(R.id.my_group_tv_outgroup);
        }
    }
}
