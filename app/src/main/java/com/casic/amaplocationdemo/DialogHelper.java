package com.casic.amaplocationdemo;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;

/**
 * @author 郭宝
 * @project： IntelligentMine
 * @package： com.casic.intelligentmine.utils
 * @date： 2019/4/24 0024 9:40
 * @brief:
 */
public class DialogHelper {



    /**
     * 显示合理性对话框
     * @param shouldRequest
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showRationaleDialog(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;

        View updateView = LayoutInflater.from(topActivity).inflate(R.layout.dialog_confirm, null);
        Dialog dialog = null;
        if (dialog==null){
            dialog = new Dialog(topActivity, R.style.Theme_Light_Dialog);
        }
        dialog.setContentView(updateView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        TextView tv_content = dialog.findViewById(R.id.content);
        tv_content.setText(R.string.permission_rationale_message);
        final Dialog finalDialog = dialog;
        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shouldRequest.again(true);
                finalDialog.cancel();
            }
        });
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shouldRequest.again(false);
            }
        });
        if (!dialog.isShowing()){
            //如果Activity没有销毁那么显示对话框
            if (!topActivity.isDestroyed()){
                dialog.show();
            }
        }
    }

    /**
     * 显示“打开应用程序设置”对话框
     */
    public static void showOpenAppSettingDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;

        View updateView = LayoutInflater.from(topActivity).inflate(R.layout.dialog_confirm, null);
        Dialog dialog = null;
        if (dialog==null){
            dialog = new Dialog(topActivity, R.style.Theme_Light_Dialog);
        }
        dialog.setContentView(updateView);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        TextView tv_content = dialog.findViewById(R.id.content);
        tv_content.setText(R.string.permission_denied_forever_message);
        final Dialog finalDialog = dialog;
        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalDialog.cancel();
                PermissionUtils.openAppSettings();
            }
        });
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        if (!dialog.isShowing()){
            //如果Activity没有销毁那么显示对话框
            if (!topActivity.isDestroyed()){
                dialog.show();
            }
        }

    }


}
