package com.mxl.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author 莫小林
 *
 */
public class ImgUtil {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {	
		// TODO Auto-generated method stub
		String path="https://blog.csdn.net/lc_chentinghua/article/details/79611774";
		ArrayList<String> list=new ArrayList<>();
		String code=null;
		code= Jsoup.connect(path).execute().body();
		//将源代码转换成对象
		Document document = Jsoup.parse(code);
		Elements elements = document.getElementsByTag("img");
		for (int i=0;i<elements.size();i++) {
			list.add(elements.get(i).attr("src"));
		}
		for(int i=0;i<list.size();i++) {
		String url=list.get(i);
		try {
			URL newurl=new URL(url);
			//创建连接
			HttpURLConnection connection=(HttpURLConnection) newurl.openConnection();
			//获取输入流
			InputStream inputStream = connection.getInputStream();
			//存储图片
			byte[] bytes=null;
//			创建一个byte数组输出流
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			//定义一个缓冲区
			byte[] buffer=new byte[1024];
			int len=0;
			while((len=inputStream.read(buffer))!=-1) {
				byteArrayOutputStream.write(buffer,0,len);
			}
			bytes=byteArrayOutputStream.toByteArray();
			File file=new File("D:\\java_pa_chong\\"+System.currentTimeMillis()+".jpg");
			FileOutputStream fos=new FileOutputStream(file);
			fos.write(bytes);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  }
}

