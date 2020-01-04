package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.R;

import java.util.List;

public class RecyUserLianAdapter extends RecyclerView.Adapter<RecyUserLianAdapter.UserLianViewHolder> {

    private Context context;
    private List<UserFriBean.DataBean> list;

    public RecyUserLianAdapter(Context context, List<UserFriBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserLianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.userfri, parent, false);
        return new UserLianViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull UserLianViewHolder holder, int position) {
        holder.user_LianFra_tv_userName.setText(list.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserLianViewHolder extends RecyclerView.ViewHolder {
        TextView user_LianFra_tv_userName;
        public UserLianViewHolder(@NonNull View itemView) {
            super(itemView);
            user_LianFra_tv_userName=itemView.findViewById(R.id.user_lianfra_tv_username);
        }
    }
}
