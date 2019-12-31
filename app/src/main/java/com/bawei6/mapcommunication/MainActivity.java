package com.bawei6.mapcommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.bawei6.usermodule.UserFragMent;

public class MainActivity extends AppCompatActivity {


    private Fragment fragment;
    private FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment=new UserFragMent();
        manager=getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fraaa,fragment,"f1")
                .addToBackStack("f1")
                .commit();



    }
}
