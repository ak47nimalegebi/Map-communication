package com.bawei6.usermodule.bottonbar.lianxiren;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.UserLianPagerAdapter;
import com.bawei6.usermodule.bottonbar.lianxiren.user_add_fragment.UserAddGroupFragment;
import com.bawei6.usermodule.bottonbar.lianxiren.user_add_fragment.UserAddPeopleFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddFriends extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private Fragment user_add_people,user_add_group;

    private UserLianPagerAdapter adapter;
    private FragmentManager manager=getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);

        tabLayout=findViewById(R.id.user_add_tab);
        viewPager=findViewById(R.id.user_add_pager);

        Intent intent = getIntent();
        String mycode = intent.getStringExtra("mycode");
        Log.i("Fricode","my"+mycode);
        user_add_people=new UserAddPeopleFragment(mycode);
        user_add_group=new UserAddGroupFragment(manager);

        titleList.add("找人");
        titleList.add("找群");
        fragmentList.add(user_add_people);
        fragmentList.add(user_add_group);

        adapter=new UserLianPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
