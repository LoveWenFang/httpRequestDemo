package com.docom.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;


/**
 * 实现网络数据的请求
 * @author Administrator
 *
 */
public class HttpPost {

	/**
	 * 请求数据
	 * @return 数据信息
	 * @param httpUrl 请求路径  httpParame 请求的参数
	 * @param what 缓存数据的文件名字  what>0 缓存  what<0 不缓存
	 */
	public String doPost(Context context,String httpUrl,String httpParame,int what){

		try {
			//请求数据
			URL url = new URL(httpUrl);
			//建立连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//设置连接参数
			connection.setConnectTimeout(60*1000);
			connection.setReadTimeout(120*1000);
			//设置读取方式
			connection.setRequestMethod("POST");
			//设置输入和输出流
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//携带参数
			if(httpParame!=null){
				OutputStream os = connection.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.write(httpParame.getBytes());
				dos.flush();
				dos.close();
				os.close();
			}
			//设置连接
			connection.connect();
			//获取返回的数据
			if(connection.getResponseCode() == 200){
				InputStream is = connection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String tmp = null;
				StringBuffer buffer = new StringBuffer();
				while((tmp=br.readLine())!=null){
					buffer.append(tmp);
				}

				br.close();
				is.close();

				//缓存数据
				if(what > 0){
					//保存数据
					FileUtils fileUtils = new FileUtils(context);
					fileUtils.saveJson(what, buffer.toString());
				}

				return buffer.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return String.valueOf(HttpErrorCode.Post_Error);
	}
}
