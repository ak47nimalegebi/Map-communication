package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei6.usermodule.R;

import java.util.List;

public class ChatGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public ChatGridAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if(view==null){
            holder=new MyViewHolder();
            view=inflater.inflate(R.layout.griditem,viewGroup,false);
            holder.grid_face=view.findViewById(R.id.grid_face);
            view.setTag(holder);
        }else {
            holder= (MyViewHolder) view.getTag();
        }

        holder.grid_face.setText(list.get(i));

        return view;
    }

    class MyViewHolder{
        TextView grid_face;
    }
}
