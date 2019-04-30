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
 * @author ĪС��
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
		//��Դ����ת���ɶ���
		Document document = Jsoup.parse(code);
		Elements elements = document.getElementsByTag("img");
		for (int i=0;i<elements.size();i++) {
			list.add(elements.get(i).attr("src"));
		}
		for(int i=0;i<list.size();i++) {
		String url=list.get(i);
		try {
			URL newurl=new URL(url);
			//��������
			HttpURLConnection connection=(HttpURLConnection) newurl.openConnection();
			//��ȡ������
			InputStream inputStream = connection.getInputStream();
			//�洢ͼƬ
			byte[] bytes=null;
//			����һ��byte���������
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			//����һ��������
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

