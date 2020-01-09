package com.bawei6.usermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usermodule.CallBackOnclick;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.bean.ChatBean;
import com.bawei6.usermodule.bottonbar.daun.SendType;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ChatBean> list;
    private CallBackOnclick callBackOnclick;
    public void getClick(CallBackOnclick callBackOnclick){
        this.callBackOnclick=callBackOnclick;
    }


    public RecyChatAdapter(Context context, List<ChatBean> list) {
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
            View inflate = LayoutInflater.from(context).inflate(R.layout.chat_left, parent, false);
            return new MyLeftChatViewHolder(inflate);
        }else{
            View inflate = LayoutInflater.from(context).inflate(R.layout.chat_right, parent, false);
            return new MyRightChatViewHolder(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int flag = list.get(position).getFlag();
        String type = list.get(position).getType();

        if(flag==0){
            MyLeftChatViewHolder myLeftChatViewHolder= (MyLeftChatViewHolder) holder;
            if(type.equals(SendType.textType)){
                myLeftChatViewHolder.chat_recy_tv_left_msg.setVisibility(View.VISIBLE);
                myLeftChatViewHolder.chat_recy_img_left_pic.setVisibility(View.GONE);
                myLeftChatViewHolder.chat_recy_img_left_yu.setVisibility(View.GONE);
                myLeftChatViewHolder.chat_recy_tv_left_msg.setText(list.get(position).getMsg());
            }
            if(type.equals(SendType.imgType)){
                myLeftChatViewHolder.chat_recy_tv_left_msg.setVisibility(View.GONE);
                myLeftChatViewHolder.chat_recy_img_left_pic.setVisibility(View.VISIBLE);
                myLeftChatViewHolder.chat_recy_img_left_yu.setVisibility(View.GONE);
                RequestOptions requestOptions = RequestOptions
                        .circleCropTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);
                Glide.with(context).load(list.get(position).getMsg()).apply(requestOptions).into(myLeftChatViewHolder.chat_recy_img_left_pic);

                myLeftChatViewHolder.chat_recy_img_left_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callBackOnclick.getOnclick(view,position);
                    }
                });
            }

            if(type.equals(SendType.audioType)){
                    myLeftChatViewHolder.chat_recy_tv_left_msg.setVisibility(View.GONE);
                    myLeftChatViewHolder.chat_recy_img_left_pic.setVisibility(View.GONE);
                    myLeftChatViewHolder.chat_recy_img_left_yu.setVisibility(View.VISIBLE);

                    myLeftChatViewHolder.chat_recy_img_left_yu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            callBackOnclick.getOnclick(view,position);
                        }
                    });
            }



        }else {
            MyRightChatViewHolder myRightChatViewHolder= (MyRightChatViewHolder) holder;
            if(type.equals(SendType.textType)){
                myRightChatViewHolder.chat_recy_tv_right_msg.setVisibility(View.VISIBLE);
                myRightChatViewHolder.chat_recy_img_right_pic.setVisibility(View.GONE);
                myRightChatViewHolder.chat_recy_img_right_yu.setVisibility(View.GONE);
                myRightChatViewHolder.chat_recy_tv_right_msg.setText(list.get(position).getMsg());
            }
            if(type.equals(SendType.imgType)){
                myRightChatViewHolder.chat_recy_tv_right_msg.setVisibility(View.GONE);
                myRightChatViewHolder.chat_recy_img_right_pic.setVisibility(View.VISIBLE);
                myRightChatViewHolder.chat_recy_img_right_yu.setVisibility(View.GONE);
                RequestOptions requestOptions = RequestOptions
                        .circleCropTransform()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true);
                Glide.with(context).load(list.get(position).getMsg()).apply(requestOptions).into(myRightChatViewHolder.chat_recy_img_right_pic);


                myRightChatViewHolder.chat_recy_img_right_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callBackOnclick.getOnclick(view,position);
                    }
                });


            }

            if(type.equals(SendType.audioType)){
                myRightChatViewHolder.chat_recy_tv_right_msg.setVisibility(View.GONE);
                myRightChatViewHolder.chat_recy_img_right_pic.setVisibility(View.GONE);
                myRightChatViewHolder.chat_recy_img_right_yu.setVisibility(View.VISIBLE);

                myRightChatViewHolder.chat_recy_img_right_yu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callBackOnclick.getOnclick(view,position);
                    }
                });

            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyLeftChatViewHolder extends RecyclerView.ViewHolder {
        TextView chat_recy_tv_left_msg;
        ImageView chat_recy_img_left_pic;
        ImageView chat_recy_img_left_yu;
        public MyLeftChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_recy_tv_left_msg=itemView.findViewById(R.id.chat_recy_tv_left_msg);
            chat_recy_img_left_pic=itemView.findViewById(R.id.chat_recy_img_left_pic);
            chat_recy_img_left_yu=itemView.findViewById(R.id.chat_recy_img_left_yu);
        }
    }

    class MyRightChatViewHolder extends RecyclerView.ViewHolder{
        TextView chat_recy_tv_right_msg;
        ImageView chat_recy_img_right_pic;
        ImageView chat_recy_img_right_yu;
        public MyRightChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_recy_tv_right_msg=itemView.findViewById(R.id.chat_recy_tv_right_msg);
            chat_recy_img_right_pic=itemView.findViewById(R.id.chat_recy_img_right_pic);
            chat_recy_img_right_yu=itemView.findViewById(R.id.chat_recy_img_right_yu);
        }
    }
}
