package com.cmt.mt_android_frame.view.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.cmt.mt_android_frame.R;
import com.cmt.mt_android_frame.utils.DensityUtil;

public class LoadingDialog extends Dialog {
    private Context context;
    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.loading_dialog,null,false);
        setContentView(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCanceledOnTouchOutside(false);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = (int) (DensityUtil.dip2px(context,140));
        lp.width = (int) (DensityUtil.dip2px(context,200));
        lp.dimAmount =0f;
        win.setAttributes(lp);
    }
}