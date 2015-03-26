package com.minglang.suiuu.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.minglang.suiuu.R;

/**
 * 登录页面
 */
public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button loginBtn;

    private Button registerBtn;

    /**
     * ***************************分割线***************************
     */

    private View popupLoginRootView;

    private PopupWindow popupWindowLogin;

    private EditText popupLoginUserName;

    private EditText popupLoginPassword;

    private Button popupLoginBtn;

    private Button popupLoginTest;

    /**
     * ***************************分割线***************************
     */

    private View popupRegisterView;

    private PopupWindow popupWindowRegister;

    private EditText popupRegisterUserName;

    private EditText popupRegisterPassword1;

    private EditText popupRegisterPassword2;

    private Button popupRegisterBtn;

    private Button popupRegisterTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        ViewAction();

        DisplayMetrics dm = getResources().getDisplayMetrics();

        float density = dm.density;//屏幕密度（像素比例：0.75, 1.0, 1.5, 2.0）
        int densityDPI = dm.densityDpi;//屏幕密度（每寸像素：120, 160, 240, 320）

        Log.i(TAG, "像素比例:"+String.valueOf(density));
        Log.i(TAG, "每寸像素:"+String.valueOf(densityDPI));

    }

    private void ViewAction() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowLogin.showAtLocation(popupLoginRootView, Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowRegister.showAtLocation(popupRegisterView, Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        popupLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowLogin.dismiss();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        popupLoginTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_userName = popupLoginUserName.getText().toString();
                String str_password = popupLoginPassword.getText().toString();

                Toast.makeText(LoginActivity.this, "UserName:" + str_userName + ",Password:" + str_password, Toast.LENGTH_SHORT).show();
            }
        });

        popupRegisterBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        popupRegisterTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_Register_UserName = popupRegisterUserName.getText().toString();
                String str_Register_Password1 = popupRegisterPassword1.getText().toString();
                String str_Register_Password2 = popupRegisterPassword2.getText().toString();

                Toast.makeText(LoginActivity.this, "UserName:" + str_Register_UserName + ",Password1:" + str_Register_Password1 + ",Password2:" + str_Register_Password2, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    private void initView() {
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        /**
         * ***************************分割线***************************
         */

        popupLoginRootView = LayoutInflater.from(this).inflate(R.layout.popup_login, null);

        popupLoginUserName = (EditText) popupLoginRootView.findViewById(R.id.userName);
        popupLoginPassword = (EditText) popupLoginRootView.findViewById(R.id.userPassword);
        popupLoginBtn = (Button) popupLoginRootView.findViewById(R.id.popupLoginBtn);
        popupLoginTest = (Button) popupLoginRootView.findViewById(R.id.popupTest);

        popupWindowLogin = new PopupWindow(popupLoginRootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindowLogin.setBackgroundDrawable(new BitmapDrawable());

        /**
         * ***************************分割线***************************
         */

        popupRegisterView = LayoutInflater.from(this).inflate(R.layout.popip_register, null);

        popupRegisterUserName = (EditText) popupRegisterView.findViewById(R.id.registerUserName);
        popupRegisterPassword1 = (EditText) popupRegisterView.findViewById(R.id.registerPassword1);
        popupRegisterPassword2 = (EditText) popupRegisterView.findViewById(R.id.registerPassword2);

        popupRegisterBtn = (Button) popupRegisterView.findViewById(R.id.registerBtn);
        popupRegisterTest = (Button) popupRegisterView.findViewById(R.id.registerTest);

        popupWindowRegister = new PopupWindow(popupRegisterView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindowRegister.setBackgroundDrawable(new BitmapDrawable());

    }

    @Override
    public void onBackPressed() {

        if (popupWindowLogin.isShowing()) {
            popupWindowLogin.dismiss();
        } else if (popupWindowRegister.isShowing()) {
            popupWindowRegister.dismiss();
        } else {
            super.onBackPressed();
        }
    }
}