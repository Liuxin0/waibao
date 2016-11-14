package com.example.rental;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.example.rental.model.LocateModel;
import com.example.rental.service.Listener;
import com.example.rental.service.LocateService;
import com.loopj.android.http.RequestParams;

/**
 * Created by 1111 on 2016/10/17.
 */
public class Locate extends Activity {
    private MapView mMapView = null;
    private AMap aMap;
    private UiSettings uiSettings;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //初始化AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mMapView = (MapView) findViewById(R.id.map);

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        textView = (TextView) findViewById(R.id.now_position);
        initLocation();

        uiSettings = aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        aMap.setMyLocationEnabled(true);// 可触发定位并显示定位层
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mLocationClient.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    private void initLocation() {
        //获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String longitude = Double.toString(aMapLocation.getLongitude());//longitude 经度
                        String latitude = Double.toString(aMapLocation.getLatitude());//latitude 纬度
                        String locate = longitude + "," + latitude;
                        postLocate(locate);

                        String a = aMapLocation.getCity() + aMapLocation.getDistrict() + aMapLocation.getStreet();
                        textView.setText("当前位置:" + a);
                        //可在其中解析amapLocation获取相应内容。
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void postLocate(final String locate) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String SecretKey = sharedPreferences.getString("SecretKey", null);
        String UserPhone = sharedPreferences.getString("UserPhone", null);
        RequestParams requestParams = new RequestParams();
        requestParams.add("locate", locate);
        requestParams.add("SecretKey", SecretKey);
        requestParams.add("UserPhone", UserPhone);
        LocateService locateService = new LocateService();
        locateService.post(Locate.this, "locate", requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                LocateModel locateModel = (LocateModel) object;
                Integer state = locateModel.getState();
                if (state == 1) {
                    Log.i("Locate", "postLocate succecc");
                } else {
                    Log.e("Locate", "posLocate state != 1");
                }
            }

            @Override
            public void onFailure(String msg) {
                Log.e("locate", "连接失败");
            }
        });
    }
}
