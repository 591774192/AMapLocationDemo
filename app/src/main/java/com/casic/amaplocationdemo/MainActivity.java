package com.casic.amaplocationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。

                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        String address = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        String country = aMapLocation.getCountry();//国家信息
                        String province = aMapLocation.getProvince();//省信息
                        String city = aMapLocation.getCity();//城市信息
                        String district = aMapLocation.getDistrict();//城区信息
                        String street = aMapLocation.getStreet();//街道信息
                        String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
                        aMapLocation.getCityCode();//城市编码
                        aMapLocation.getAdCode();//地区编码
                        String aoiName = aMapLocation.getAoiName();//获取当前定位点的AOI信息
                        String buildingId = aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        aMapLocation.getFloor();//获取当前室内定位的楼层
                        int gpsAccuracyStatus = aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        String format = df.format(date);

                        Log.i(TAG, "地址:" + address + "\t国家信息:" + country + "\t省信息:" + province + "\t城市信息:" + city + "\t城区信息:" + district + "\t街道信息:" + street + "\t街道门牌号信息:" + streetNum + "\t当前定位点的AOI信息:" + aoiName+ "\t定位时间:" + format);
                        //地址:北京市丰台区外环西路114靠近总部资产大厦	国家信息:中国	省信息:北京市	城市信息:北京市	城区信息:丰台区	街道信息:外环西路	街道门牌号信息:114	当前定位点的AOI信息:总部基地18区	定位时间:2019-04-24 18:15:44


                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e(TAG, "AmapError--location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }


        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);


        //===============设置单次定位===============
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);


        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);


        //设置定位定位请求超时时间
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);


        //设置是否开启定位缓存机制
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);


        //==============启动定位==============
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();



        //==============停止定位==============
        //mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁

        //==============销毁定位客户端==============
        //mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。

    }
}
