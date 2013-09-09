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
	            
	            //设置为POST请求
	            connection.setRequestMethod("POST");
	            // 设置doOutput属性为true表示将使用此urlConnection写入数据   
	            connection.setDoOutput(true);  
	            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型   
	            connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");  
	            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());  
	           
	            StringBuffer testStr = new StringBuffer();
	            testStr.append("<channel>");
        		testStr.append("<ToUserName><![CDATA[toUser]]></ToUserName>");
				testStr.append("<FromUserName><![CDATA[fromUser]]></FromUserName>");
				testStr.append("<CreateTime>1348831860</CreateTime>");
				testStr.append("<MsgType><![CDATA[text]]></MsgType>");
				testStr.append("<Content><![CDATA[北京天气]]></Content>");
				testStr.append("<MsgId>1234567890123456</MsgId>");
				testStr.append("</channel>");
	            // 把数据写入请求的Body
	            out.write(testStr.toString());  
	            out.flush();  
	            out.close();  
	              
	            // 从服务器读取响应   
	            InputStream inputStream = connection.getInputStream();  
	            String encoding = connection.getContentEncoding();  
	            String body = IOUtils.toString(inputStream, encoding);  
	            System.out.println(body);  
	        }catch(IOException e){  
	            e.printStackTrace();
	        }  
	 }
}
