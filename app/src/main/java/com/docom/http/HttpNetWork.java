package com.docom.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;

/**
 * 提供联网的状态
 * @author Administrator
 *
 */
public class HttpNetWork {

	private Context context;

	public HttpNetWork(Context context) {
		super();
		this.context = context;
	}

	public int getNetWork(){

		//获取网络状态管理对象
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		//wifi
		NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if(networkInfo!=null){
			State state = networkInfo.getState();
			if(state.equals(State.CONNECTED)){
				Log.i("TAGName", "wifi连接成功");
				return HttpErrorCode.NetWork_Wifi;
			}
		}

		//mobile
		NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if(networkInfo2!=null){
			State state = networkInfo2.getState();
			if(state.equals(State.CONNECTED)){
				Log.i("TAGName", "移动网络连接成功！");
				return HttpErrorCode.NetWork_Mobile;
			}
		}

		return HttpErrorCode.NetWork_Error;
	}
}
