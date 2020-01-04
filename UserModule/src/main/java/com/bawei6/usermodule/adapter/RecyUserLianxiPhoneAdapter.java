package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usermodule.R;
import com.bawei6.usermodule.bean.MyPhone;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyUserLianxiPhoneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MyPhone> list;

    public RecyUserLianxiPhoneAdapter(Context context, List<MyPhone> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getFlag();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.phonebook, parent, false);
            return new  ViewHolder_tv_phonebook_label(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.namephone, parent, false);
            return new  ViewHolder_tv_name_phone(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(list.get(position).getFlag()==0){
             ViewHolder_tv_phonebook_label viewHolder_tv_phonebook_label= (ViewHolder_tv_phonebook_label) holder;
             viewHolder_tv_phonebook_label.tv_recy_phonebook_label.setText(list.get(position).getPhonebook_label());
        }else{
            ViewHolder_tv_name_phone holder_tv_name_phone= (ViewHolder_tv_name_phone) holder;
            holder_tv_name_phone.tv_recy_name.setText(list.get(position).getName());
            holder_tv_name_phone.tv_recy_phone.setText(list.get(position).getPhone());
            Glide.with(context).load(R.drawable.lianxiren).apply(new RequestOptions().circleCrop()).into(holder_tv_name_phone.img_recy_tu);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder_tv_phonebook_label extends RecyclerView.ViewHolder {
        TextView tv_recy_phonebook_label;
        public ViewHolder_tv_phonebook_label(@NonNull View itemView) {
            super(itemView);
            tv_recy_phonebook_label=itemView.findViewById(R.id.tv_recy_phonebook);
        }
    }

    class ViewHolder_tv_name_phone extends RecyclerView.ViewHolder {
        TextView tv_recy_name;
        TextView tv_recy_phone;
        ImageView img_recy_tu;
        public ViewHolder_tv_name_phone(@NonNull View itemView) {
            super(itemView);
            tv_recy_name=itemView.findViewById(R.id.tv_recy_name);
            tv_recy_phone=itemView.findViewById(R.id.tv_recy_phone);
            img_recy_tu=itemView.findViewById(R.id.img_recy_tu);
        }
    }
}
