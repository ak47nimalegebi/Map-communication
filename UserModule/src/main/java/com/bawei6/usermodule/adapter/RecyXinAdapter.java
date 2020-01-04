package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.basemodule.bean.UserFriBean;
import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;

import java.util.List;

public class RecyXinAdapter extends RecyclerView.Adapter<RecyXinAdapter.MyXinViewHolder> {

    private Context context;
    private List<UserFriBean.DataBean> list;
    private CallBackOnclick callBackOnclick;

    public void getXinOnclick(CallBackOnclick callBackOnclick){
        this.callBackOnclick=callBackOnclick;
    }

    public RecyXinAdapter(Context context, List<UserFriBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyXinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.xin_recy, parent, false);

        return new MyXinViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyXinViewHolder holder, final int position) {
            holder.xin_tv_userName.setText(list.get(position).getUsername());
            holder.xin_tv_userName.setOnClickListener(new View.OnClickListener() {
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

    class MyXinViewHolder extends RecyclerView.ViewHolder {
        TextView xin_tv_userName;
        public MyXinViewHolder(@NonNull View itemView) {
            super(itemView);
            xin_tv_userName=itemView.findViewById(R.id.xin_tv_userName);

        }
    }
}
