package com.docom.http;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class HttpHandler extends Handler {
    private Context context;
    private ISuccessCallBack callBack;

    public HttpHandler(Context context, ISuccessCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case HttpErrorCode.NetWork_Error:
                Toast.makeText(context, "网络连接异常！", Toast.LENGTH_SHORT).show();
                break;
            case HttpErrorCode.Post_Error:
                Toast.makeText(context, "服务器连接异常", Toast.LENGTH_SHORT).show();
                break;
            default:
                //成功
                if (callBack != null) {
                    callBack.onSuccess(msg.obj.toString(), msg.what);
                }
                break;
        }
    }
}