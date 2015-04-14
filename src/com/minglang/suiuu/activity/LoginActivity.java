package com.minglang.suiuu.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.minglang.suiuu.R;
import com.minglang.suiuu.chat.chat.DemoApplication;

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

    //登陆PopupWindow

    private View popupLoginRootView;

    private PopupWindow popupWindowLogin;

    private EditText popupLoginUserName;

    private EditText popupLoginPassword;

    private Button popupLoginBtn;

    /**
     * ***************************分割线***************************
     */

    //注册PopupWindow

    private View popupRegisterView;

    private PopupWindow popupWindowRegister;

    private EditText popupRegisterUserName;

    private EditText popupRegisterPassword1;

    private Button popupRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        ViewAction();

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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


        popupRegisterBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String st1 = getResources().getString(R.string.User_name_cannot_be_empty);
                String st2 = getResources().getString(R.string.Password_cannot_be_empty);
                String st3 = getResources().getString(R.string.Confirm_password_cannot_be_empty);
                String st4 = getResources().getString(R.string.Two_input_password);
                String st5 = getResources().getString(R.string.Is_the_registered);
                final String st6 = getResources().getString(R.string.Registered_successfully);
                final String username = popupRegisterUserName.getText().toString().trim();
                final String pwd = popupRegisterPassword1.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, st1, Toast.LENGTH_SHORT).show();
                    popupRegisterUserName.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, st2, Toast.LENGTH_SHORT).show();
                    popupRegisterPassword1.requestFocus();
                    return;
                }

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
                    final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage(st5);
                    pd.show();
                    final String st7 = getResources().getString(R.string.network_anomalies);
                    final String st8 = getResources().getString(R.string.User_already_exists);
                    final String st9 = getResources().getString(R.string.registration_failed_without_permission);
                    final String st10 = getResources().getString(R.string.Registration_failed);
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                // 调用sdk注册方法
                                EMChatManager.getInstance().createAccountOnServer(username, pwd);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        // 保存用户名
                                        pd.dismiss();
                                        DemoApplication.getInstance().setUserName(username);
                                        Toast.makeText(getApplicationContext(), st6, Toast.LENGTH_SHORT).show();
                                        popupWindowRegister.dismiss();
                                    }
                                });
                            } catch (final EaseMobException e) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (!LoginActivity.this.isFinishing())
                                            pd.dismiss();
                                        int errorCode=e.getErrorCode();
                                        if(errorCode== EMError.NONETWORK_ERROR){
                                            Toast.makeText(getApplicationContext(), st7, Toast.LENGTH_SHORT).show();
                                        }else if(errorCode==EMError.USER_ALREADY_EXISTS){
                                            Toast.makeText(getApplicationContext(), st8, Toast.LENGTH_SHORT).show();
                                        }else if(errorCode==EMError.UNAUTHORIZED){
                                            Toast.makeText(getApplicationContext(), st9, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), st10 + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }).start();

                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    private void initView() {

        DisplayMetrics dm = getResources().getDisplayMetrics();

        float density = dm.density;//屏幕密度（像素比例：0.75, 1.0, 1.5, 2.0）
        int densityDPI = dm.densityDpi;//屏幕密度（每寸像素：120, 160, 240, 320）

        int screenWidth = dm.widthPixels;//屏幕宽度

        Log.i(TAG, "像素比例:" + String.valueOf(density));
        Log.i(TAG, "每寸像素:" + String.valueOf(densityDPI));

        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);


        /**
         * ***************************分割线***************************
         */

        popupLoginRootView = LayoutInflater.from(this).inflate(R.layout.popup_login, null);

        popupLoginUserName = (EditText) popupLoginRootView.findViewById(R.id.userName);
        popupLoginPassword = (EditText) popupLoginRootView.findViewById(R.id.userPassword);
        popupLoginBtn = (Button) popupLoginRootView.findViewById(R.id.popupLoginBtn);

        ViewGroup.LayoutParams loginParams = popupLoginBtn.getLayoutParams();
        loginParams.width = screenWidth / 3;
        popupLoginBtn.setLayoutParams(loginParams);

        popupWindowLogin = new PopupWindow(popupLoginRootView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindowLogin.setBackgroundDrawable(new BitmapDrawable());

        /**
         * ***************************分割线***************************
         */

        popupRegisterView = LayoutInflater.from(this).inflate(R.layout.popip_register, null);

        popupRegisterUserName = (EditText) popupRegisterView.findViewById(R.id.registerUserInfo);
        popupRegisterPassword1 = (EditText) popupRegisterView.findViewById(R.id.registerPassword1);

        popupRegisterBtn = (Button) popupRegisterView.findViewById(R.id.registerBtn);

        ViewGroup.LayoutParams registerParams = popupRegisterBtn.getLayoutParams();
        registerParams.width = screenWidth / 3;
        popupRegisterBtn.setLayoutParams(registerParams);

        popupWindowRegister = new PopupWindow(popupRegisterView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
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