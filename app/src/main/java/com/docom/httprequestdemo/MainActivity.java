package com.docom.httprequestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.docom.http.HttpHandler;
import com.docom.http.HttpThread;
import com.docom.http.ISuccessCallBack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ISuccessCallBack {

    private Button mMainBtn;
    private TextView mMainTv;
    private HttpHandler mHandler;
    private int what = -200;
    private String httpUr_Test = "http://www.iwens.org/School_Sky/yohoadvert.php";
    private String httpUrl = "http://112.74.131.57:3024/Service1.asmx/GetDisplayInfo?Json={\"MerchantCode\":\"S20161005\"}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mHandler = new HttpHandler(this, this);
    }

    private void initView() {
        mMainBtn = (Button) findViewById(R.id.main_btn);
        mMainTv = (TextView) findViewById(R.id.main_tv);

        mMainBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn:
                requestDatas();
                break;
        }
    }

    /**
     * 请求数据
     */
    private void requestDatas() {
        new HttpThread(this, mHandler, httpUrl, null, what).start();
    }

    @Override
    public void onSuccess(String json, int what) {
        mMainTv.setText(json);
    }
}
