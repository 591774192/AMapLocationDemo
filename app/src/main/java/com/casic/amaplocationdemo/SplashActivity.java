package com.casic.amaplocationdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;

import java.util.List;

/**
 * @author 郭宝
 * @project： AMapLocationDemo
 * @package： com.casic.amaplocationdemo
 * @date： 2019/4/24 0024 18:11
 * @brief:
 */
public class SplashActivity extends AppCompatActivity {

    public static final String TAG  = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        requestPermission();

        findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });
    }


    private void requestPermission() {
        //GROUP_LOCATION
        requestRermission(PermissionConstants.LOCATION,PermissionConstants.PHONE,PermissionConstants.STORAGE);

    }

    /**
     * 请求权限
     * @param permission
     */
    private void requestRermission(final String... permission){
        LogUtils.d(TAG,"requestRermission()");
        PermissionUtils.permission(permission)
                .rationale(new PermissionUtils.OnRationaleListener() {

                    /**
                     * 应该的请求
                     * @param shouldRequest
                     */
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        LogUtils.d(TAG,"rationale()");
                        DialogHelper.showRationaleDialog(shouldRequest);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        //updateAboutPermission();
                        LogUtils.d(TAG,"onGranted()");
                    }

                    /**
                     *  被拒绝的权限
                     * @param permissionsDeniedForever  永远拒绝的权限
                     * @param permissionsDenied 被拒绝的权限
                     */
                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {

                        LogUtils.d(TAG,"onDenied()");
                        if (!permissionsDeniedForever.isEmpty()) {
                            LogUtils.d(TAG,"有被永远拒绝的权限");
                            DialogHelper.showOpenAppSettingDialog();
                            return;
                        }
                        LogUtils.d(permissionsDeniedForever, permissionsDenied);
                        requestRermission(permission);
                    }
                })
                .request();
    }
}
