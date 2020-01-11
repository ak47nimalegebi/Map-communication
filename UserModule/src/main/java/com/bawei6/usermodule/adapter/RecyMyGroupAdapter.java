package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.bean.GetMyGroupBean;
import com.bawei6.usermodule.R;

import java.util.List;

public class RecyMyGroupAdapter extends RecyclerView.Adapter<RecyMyGroupAdapter.MygroupViewholder> {

    private Context context;
    private List<GetMyGroupBean.DataBean> list;

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
    public void onBindViewHolder(@NonNull MygroupViewholder holder, int position) {
        holder.my_group_tv_groupName.setText(list.get(position).getGroupname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MygroupViewholder extends RecyclerView.ViewHolder {
        TextView my_group_tv_groupName;
        public MygroupViewholder(@NonNull View itemView) {
            super(itemView);
            my_group_tv_groupName=itemView.findViewById(R.id.my_group_tv_groupName);
        }
    }
}
