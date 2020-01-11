package com.bawei6.usermodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.bawei6.basemodule.titlebar.BottomActionView;
import com.bawei6.common.LogUtils;
import com.bawei6.usermodule.adapter.RecyHuoAdapter;
import com.bawei6.usermodule.bean.MyHuoding;
import com.bawei6.usermodule.bottonbar.daun.DuanxinActivity;
import com.bawei6.usermodule.bottonbar.lianxiren.LianxirenActivity;

import java.util.ArrayList;
import java.util.List;


public class UserFragMent extends Fragment {

    MapView mMapView;

    private List<MyHuoding> list=new ArrayList<>();
    private RecyHuoAdapter recyHuoAdapter;
    private RecyclerView recyclerViewp;
    private BottomActionView user_bottomBar;

    private ImageView img_log;

    private String logcode,logname;
    public UserFragMent(String logcode, String logname) {
        this.logcode=logcode;
        this.logname=logname;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = getLayoutInflater().inflate(R.layout.fra, null);

       mMapView=inflate.findViewById(R.id.usermodule_map);
       recyclerViewp=inflate.findViewById(R.id.recy_huo);
       img_log=inflate.findViewById(R.id.img_group1);
       user_bottomBar=inflate.findViewById(R.id.user_bottombar);

        LogUtils.i("UserFrag----->"+logcode);
        LogUtils.i("UserFrag----->"+logname);


//        Resources resources = getContext().getResources();
//        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
//        int height = resources.getDimensionPixelSize(resourceId);


        user_bottomBar.Onclick1(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LianxirenActivity.class);
                intent.putExtra("userfragcode",logcode);
                intent.putExtra("userfragname",logname);
                startActivity(intent);
            }
        });


        user_bottomBar.Onclick2(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        user_bottomBar.Onclick3(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DuanxinActivity.class));
            }
        });

        user_bottomBar.Onclick4(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        user_bottomBar.Onclick5(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mMapView.onCreate(savedInstanceState);

        AMap aMap = null;
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

//                PopupWindow popupWindow = new PopupWindow(getContext());
//
//                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.popwindow, null);
//                popupWindow.setContentView(inflate1);
//                popupWindow.setHeight(200);
//                popupWindow.setWidth(100);
//                popupWindow.showAtLocation(mMapView,Gravity.LEFT,20,300);

        list.add(new MyHuoding("2019/12/19 16:30:30","已参与(5/10)人","下午3点 西二旗XX餐厅聚餐..."));

        recyHuoAdapter=new RecyHuoAdapter(getContext(),list);
        recyclerViewp.setAdapter(recyHuoAdapter);
        recyclerViewp.setLayoutManager(new LinearLayoutManager(getContext()));


//        img_log.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), Lian.class));
//            }
//        });


        return inflate;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();

//        mLocationClient.stopLocation();
//        mLocationClient.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
