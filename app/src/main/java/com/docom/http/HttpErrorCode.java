package com.docom.http;

/**
 * 提供静态的常量，方便操作
 * @author Administrator
 *
 */
public interface HttpErrorCode {

	//网络的状态
	int NetWork_Error = 1;
	int NetWork_Wifi = 2;
	int NetWork_Mobile = 3;

	//请求信息
	int Post_Error = 400;
}

