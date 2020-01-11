package com.bawei6.usermodule.bottonbar.lianxiren.addgroupfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei6.usermodule.R;

public class AddGroupJiaoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.addgroup_jiao, null);

        return inflate;
    }
}
