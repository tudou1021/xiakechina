package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class test {
	 public static void main(String[] args){  
	        try{    
	            URL url = new URL("http://localhost:8080/weixinCourse/coreServlet");            
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            
	            //����ΪPOST����
	            connection.setRequestMethod("POST");
	            // ����doOutput����Ϊtrue��ʾ��ʹ�ô�urlConnectionд������   
	            connection.setDoOutput(true);  
	            // �����д�����ݵ��������ͣ���������Ϊapplication/x-www-form-urlencoded����   
	            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");  
	            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());  
	           
	            StringBuffer testStr = new StringBuffer();
	            testStr.append("<channel>");
        		testStr.append("<ToUserName><![CDATA[toUser]]></ToUserName>");
				testStr.append("<FromUserName><![CDATA[fromUser]]></FromUserName>");
				testStr.append("<CreateTime>1348831860</CreateTime>");
				testStr.append("<MsgType><![CDATA[text]]></MsgType>");
				testStr.append("<Content><![CDATA[��������]]></Content>");
				testStr.append("<MsgId>1234567890123456</MsgId>");
				testStr.append("</channel>");
	            // ������д�������Body
	            out.write(testStr.toString());  
	            out.flush();  
	            out.close();  
	              
	            // �ӷ�������ȡ��Ӧ   
	            InputStream inputStream = connection.getInputStream();  
	            String encoding = connection.getContentEncoding();  
	            String body = IOUtils.toString(inputStream, encoding);  
	            System.out.println(body);  
	        }catch(IOException e){  
	            e.printStackTrace();
	        }  
	 }
}
