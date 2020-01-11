package com.bawei6.usermodule.bottonbar.lianxiren.addgroupfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usermodule.R;

public class AddGroupGameFragment extends Fragment {

    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.addgroup_game, null);

        recyclerView=inflate.findViewById(R.id.group_game_recy);


        return inflate;
    }
}
