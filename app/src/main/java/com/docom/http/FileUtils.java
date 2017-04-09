package com.docom.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

/**
 * 缓存数据和读取数据
 * @author Administrator
 *
 */
public class FileUtils {

	private Context context;

	public FileUtils(Context context) {
		super();
		this.context = context;
	}

	//先判断是否存储
	public boolean isSave(int what){
		String fileName = "data"+what+".txt";
		//1.获取内存存储流存储的根路径
		File filesDir = context.getFilesDir();
		if(!filesDir.exists()){
			filesDir.mkdirs();
		}
		//2.判断文件是否存在
		File file  = new File(filesDir, fileName);
		return file.exists();
	}

	//存储
	public void saveJson(int what,String json){
		try {
			String fileName = "data"+what+".txt";
			//内部存储的输出流，保存信息
			FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			//保存数据
			fos.write(json.getBytes());
			//关闭
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//读取
	public String readJson(int what){
		String fileName = "data"+what+".txt";
		try {
			//内部存储的输入流，读取数据
			FileInputStream fis = context.openFileInput(fileName);
			//封装字符流，防止乱码
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String tmp = null;
			StringBuffer buffer = new StringBuffer();
			while((tmp=br.readLine())!=null){
				buffer.append(tmp);
			}
			br.close();
			fis.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
