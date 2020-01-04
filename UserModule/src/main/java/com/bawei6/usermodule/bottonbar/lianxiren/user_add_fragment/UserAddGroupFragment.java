package com.bawei6.usermodule.bottonbar.lianxiren.user_add_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.UserLianPagerAdapter;
import com.bawei6.usermodule.addgroupfragment.AddGroupFensiFragment;
import com.bawei6.usermodule.addgroupfragment.AddGroupGameFragment;
import com.bawei6.usermodule.addgroupfragment.AddGroupJiaoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class UserAddGroupFragment extends Fragment {

    private EditText edi_userName;
    private Button btn_find;
    private TextView tv_creategroup,tv_sao;
    private TabLayout tabLayout;
    private ViewPager pager;

    private FragmentManager manager;

    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private UserLianPagerAdapter adapter;

    private Fragment gameFra,fenFra,jiaoFra;

    public UserAddGroupFragment(FragmentManager manager) {
        this.manager = manager;
    }

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = getLayoutInflater().inflate(R.layout.addfriends_group, null);

        edi_userName=inflate.findViewById(R.id.addgroup_edi);
        btn_find=inflate.findViewById(R.id.addgroup_btn);
        tv_creategroup=inflate.findViewById(R.id.addgroup_tv_create);
        tv_sao=inflate.findViewById(R.id.addgroup_tv_sao);
        tabLayout=inflate.findViewById(R.id.addgroup_tab);
        pager=inflate.findViewById(R.id.addgroup_pager);
        recyclerView= inflate.findViewById(R.id.addgroup_recy);

        gameFra=new AddGroupGameFragment();
        fenFra=new AddGroupFensiFragment();
        jiaoFra=new AddGroupJiaoFragment();

        fragmentList.add(gameFra);
        fragmentList.add(fenFra);
        fragmentList.add(jiaoFra);

        titleList.add("游戏");
        titleList.add("粉丝");
        titleList.add("交友");

        adapter=new UserLianPagerAdapter(manager,fragmentList,titleList);

        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);

        return inflate;
    }
}
