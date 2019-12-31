package com.bawei6.basemodule.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bawei6.basemodule.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MyCustTitleBar extends LinearLayout {

    private ImageView cust_img_left;
    private TextView cust_tv_title;
    private EditText cust_edi;
    private ImageView cust_img_right;

    public MyCustTitleBar(Context context) {
        super(context);
    }

    public MyCustTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MyCustTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initView(Context context,AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.customtitlebar, null);

        cust_img_left=inflate.findViewById(R.id.cust_img_left);
        cust_tv_title=inflate.findViewById(R.id.cust_tv_title);
        cust_edi=inflate.findViewById(R.id.cust_edi);
        cust_img_right=inflate.findViewById(R.id.cust_img_right);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustTitleBar);

        int left=typedArray.getResourceId(R.styleable.MyCustTitleBar_cust_img_left,0);
        int title=typedArray.getResourceId(R.styleable.MyCustTitleBar_cust_tv_title,0);
        int edi=typedArray.getResourceId(R.styleable.MyCustTitleBar_cust_edi,0);
        int right=typedArray.getResourceId(R.styleable.MyCustTitleBar_cust_img_right,0);

        if(left!=0){
            Glide.with(context).load(left).apply(new RequestOptions().circleCrop()).into(cust_img_left);
//            cust_img_left.setImageResource(left);
        }

        if(title!=0){
            cust_tv_title.setText(title);
        }


        if(right!=0){
            cust_img_right.setImageResource(right);
        }

        typedArray.recycle();
        this.addView(inflate);
    }

    public void leftOnclick(View.OnClickListener listener){
        cust_img_left.setOnClickListener(listener);
    }

    public void rightOnclick(View.OnClickListener listener){
        cust_img_right.setOnClickListener(listener);
    }

    public void tvOnclick(View.OnClickListener listener){
        cust_tv_title.setOnClickListener(listener);
    }

    public void EdiOnclick(View.OnClickListener listener){
        cust_edi.setOnClickListener(listener);
    }


}
