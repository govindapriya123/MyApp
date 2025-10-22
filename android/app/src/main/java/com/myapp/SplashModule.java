package com.myapp;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import com.facebook.react.bridge.Promise;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class SplashModule extends ReactContextBaseJavaModule {

    private Dialog splashDialog;

    public SplashModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "SplashModule";
    }

    @ReactMethod
    public void show() {
        final Activity activity = getCurrentActivity();
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(() -> {
            if (splashDialog == null) {
                splashDialog = new Dialog(activity);
                splashDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                LinearLayout layout = new LinearLayout(activity);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);
                layout.setBackgroundColor(Color.WHITE);
                layout.setLayoutParams(new LinearLayout.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

                ImageView iv = new ImageView(activity);
                iv.setImageResource(R.drawable.splash_logo);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(300, 300); // 300px x 300px
                ivParams.gravity = Gravity.CENTER; // Center it in the layout
                iv.setLayoutParams(ivParams);
                layout.addView(iv);

                // Get Started button
                Button btn = new Button(activity);
                btn.setText("Get Started");
                LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                btnParams.gravity = Gravity.CENTER;
                btn.setLayoutParams(btnParams);
                btn.setOnClickListener(v -> {
                    splashDialog.dismiss();
                    splashDialog = null;
                });
                layout.addView(btn);

                splashDialog.setContentView(layout);
                splashDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                splashDialog.setCancelable(false);
            }

            if (!splashDialog.isShowing()) {
                splashDialog.show();
            }
        });

    }

    @ReactMethod
    public void hide() {
        final Activity activity = getCurrentActivity();
        if (activity == null || splashDialog == null)
            return;

        activity.runOnUiThread(() -> {
            if (splashDialog.isShowing()) {
                splashDialog.dismiss();
            }
            splashDialog = null;
        });
    }
}
