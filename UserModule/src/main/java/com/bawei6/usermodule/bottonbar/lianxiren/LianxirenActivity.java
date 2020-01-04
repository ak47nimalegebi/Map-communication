package com.bawei6.usermodule.bottonbar.lianxiren;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bawei6.basemodule.titlebar.NormalTitBar;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.adapter.UserLianPagerAdapter;
import com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment.UserLianChatFragment;
import com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment.UserLianFriendsFragment;
import com.bawei6.usermodule.bottonbar.lianxiren.user_lian_fragment.UserLianGroupFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class LianxirenActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private Fragment user_lian_friends,user_lian_group,user_lian_chat;

    private UserLianPagerAdapter adapter;

    private NormalTitBar normalTitBar;

    private RecyclerView user_lian_shou_recy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lianxiren);


        final Intent intent = getIntent();
        final String code = intent.getStringExtra("codde");
        Log.i("AK47",code);



        tabLayout=findViewById(R.id.user_lian_tab);
        viewPager=findViewById(R.id.user_lian_pager);
        normalTitBar=findViewById(R.id.user_lian_normaltitbar);
        user_lian_shou_recy=findViewById(R.id.user_lian_shou_recy);



        user_lian_chat=new UserLianChatFragment();
        user_lian_friends=new UserLianFriendsFragment(code);
        user_lian_group=new UserLianGroupFragment();
        titleList.add("好友");
        titleList.add("分组");
        titleList.add("群聊");
        fragmentList.add(user_lian_friends);
        fragmentList.add(user_lian_group);
        fragmentList.add(user_lian_chat);

        adapter=new UserLianPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);


        normalTitBar.rightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LianxirenActivity.this, AddFriends.class);
                intent1.putExtra("mycode",code);
                startActivity(intent1);
            }
        });

    }
}
