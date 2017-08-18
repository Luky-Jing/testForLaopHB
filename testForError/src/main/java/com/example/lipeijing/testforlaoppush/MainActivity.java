package com.example.lipeijing.testforlaoppush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    //定位相关

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(msu);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.id_map_traffic:
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通（Off）");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通（On）");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
