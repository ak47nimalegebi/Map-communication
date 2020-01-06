package com.bawei6.usermodule;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimeLineItemDecoration extends RecyclerView.ItemDecoration {

    private int mCricleSize=14;
    private Paint mPaint;
    private int mPaintSize=6;
    private String mPaintColor="#B8B8B8";

    public TimeLineItemDecoration() {
        mPaint=new Paint();
        mPaint.setStrokeWidth(mPaintSize);
        mPaint.setColor(Color.parseColor(mPaintColor));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        Rect rect = new Rect(200, 0, 0, 0);
        outRect.set(rect);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount=parent.getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View childAt = parent.getChildAt(i);
            int left = childAt.getLeft();
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            int right = childAt.getRight();

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int bottomMargin = params.bottomMargin;
            int topMargin = params.topMargin;

            int leftX = left / 2;
            int height = childAt.getHeight();

            if(childCount==-1){
                c.drawLine(leftX,top,leftX,bottom-height/2,mPaint);
            }else {
                if(i==0){
                    c.drawLine(leftX, top + height / 2, leftX, bottom + bottomMargin, mPaint);
                }else if(i==childCount-1){
                    c.drawLine(leftX, top - topMargin, leftX, bottom - height / 2, mPaint);
                }else {
                    c.drawLine(leftX, top - topMargin, leftX, bottom - height / 2, mPaint);
                    c.drawLine(leftX, top + height / 2, leftX, bottom + bottomMargin, mPaint);
                }
            }
            c.drawCircle(leftX, top + height / 2, mCricleSize, mPaint);
        }
    }
}
