package com.docom.http;

import android.content.Context;
import android.os.Message;

/**
 * 异步访问服务器
 *
 * @author Administrator
 */
public class HttpThread extends Thread {

    private HttpNetWork httpNetWork; //获取网络信息
    private HttpPost httpPost;//获取网络信息
    private Context context;
    private HttpHandler httpHandler;
    private String httpUrl;
    private String httpParame;
    private int what;

    public HttpThread(Context context, HttpHandler httpHandler, String httpUrl,
                      String httpParame, int what) {
        super();
        this.context = context;
        this.httpHandler = httpHandler;
        this.httpUrl = httpUrl;
        this.httpParame = httpParame;
        this.what = what;

        init();
    }

    public void init() {
        httpNetWork = new HttpNetWork(context);
        httpPost = new HttpPost();
    }

    @Override
    public void run() {
        super.run();
        int netWorkType = httpNetWork.getNetWork();
        if (netWorkType != HttpErrorCode.NetWork_Error) {
            //请求数据
            String result = httpPost.doPost(context, httpUrl, httpParame, what);
            if (result.equals(String.valueOf(HttpErrorCode.Post_Error))) {
                //错误
                httpHandler.sendEmptyMessage(HttpErrorCode.Post_Error);
            } else {
                //成功
                Message message = Message.obtain();
                message.what = what;
                message.obj = result;
                httpHandler.sendMessage(message);
            }
        } else {
            //错误
            httpHandler.sendEmptyMessage(HttpErrorCode.NetWork_Error);
        }
    }
}