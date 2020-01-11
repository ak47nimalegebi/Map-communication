package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.bean.FindGroupNameBean;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;

import java.util.List;

public class RecyNewGroupFindAdapter extends RecyclerView.Adapter<RecyNewGroupFindAdapter.FindGroupViewHolder>{

    private Context context;
    private List<FindGroupNameBean.DataBean> list;
    private CallBackOnclick callBackOnclick;

    public void getfindGroupOnclick(CallBackOnclick callBackOnclick){
        this.callBackOnclick=callBackOnclick;
    }

    public RecyNewGroupFindAdapter(Context context, List<FindGroupNameBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FindGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.find_group_item, parent, false);
        return new FindGroupViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindGroupViewHolder holder, final int position) {
        holder.newgroup_recy_tv_groupname.setText(list.get(position).getGroupname());
        holder.newgroup_recy_tv_groupname.setOnClickListener(new View.OnClickListener() {
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

    class FindGroupViewHolder extends RecyclerView.ViewHolder {
        TextView newgroup_recy_tv_groupname;
        public FindGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            newgroup_recy_tv_groupname=itemView.findViewById(R.id.newgrop_recy_tv_grouopname);
        }
    }
}
