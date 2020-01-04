package com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei6.usermodule.R;

public class UserLianGroupFragment extends Fragment {


    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.user_lian_group, null);
        textView=inflate.findViewById(R.id.tvaaa);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(200f);
                valueAnimator.setDuration(3000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float a=(float) valueAnimator.getAnimatedValue();
                        textView.setX(a);
                        textView.setY(a);
                    }
                });

                valueAnimator.start();
            }
        });

        return inflate;
    }
}
