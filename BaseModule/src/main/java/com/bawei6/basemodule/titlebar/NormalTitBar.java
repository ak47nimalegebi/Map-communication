package com.bawei6.basemodule.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bawei6.basemodule.R;

public class NormalTitBar extends LinearLayout {

    private ImageView normal_img_left;
    private TextView normal_tv_title;
    private ImageView normal_img_right;
    public NormalTitBar(Context context) {
        super(context);
    }

    public NormalTitBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.normaltitbar, null);
        normal_img_left=inflate.findViewById(R.id.normal_img_left);
        normal_tv_title=inflate.findViewById(R.id.normal_tv_title);
        normal_img_right=inflate.findViewById(R.id.normal_img_right);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NormalTitBar);
        int left_img = typedArray.getResourceId(R.styleable.NormalTitBar_normal_img_left, 0);
        int tv_titlr = typedArray.getResourceId(R.styleable.NormalTitBar_normal_tv_title, 0);
        int right_img = typedArray.getResourceId(R.styleable.NormalTitBar_normal_img_right, 0);


        if(left_img!=0){
            normal_img_left.setImageResource(left_img);
        }

        if(tv_titlr!=0){
            normal_tv_title.setText(tv_titlr);
        }

        if(right_img!=0){
            normal_img_right.setImageResource(right_img);
        }

        typedArray.recycle();
        this.addView(inflate);

    }

    public NormalTitBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void leftOnclick(View.OnClickListener listener){
        normal_img_left.setOnClickListener(listener);
    }

    public void titleOnclick(View.OnClickListener listener){
        normal_tv_title.setOnClickListener(listener);
    }


    public void rightOnclick(View.OnClickListener listener){
        normal_img_right.setOnClickListener(listener);
    }



}
