package com.casic.amaplocationdemo;

import android.app.Application;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.Utils;

import java.util.List;

/**
 * @author 郭宝
 * @project： AMapLocationDemo
 * @package： com.casic.amaplocationdemo
 * @date： 2019/4/24 0024 17:55
 * @brief:
 */
public class BaseApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);

    }


}
