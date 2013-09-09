package org.xiakechina.course.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;


public class BaiduMapService {
	Logger logger = Logger. getLogger("BaiduMapService");
	URLConnection connectionData; 
    StringBuilder sb; 
    BufferedReader br;
	public String getPalace(String query,String lat,String lng) throws ClientProtocolException, IOException{
		String url = palceRequestUrl(query,lat,lng);
		logger.log(Level.INFO, url);
		    URL u = new URL(url); 
	        connectionData = u.openConnection(); 
	        connectionData.setConnectTimeout(2000); 
	        try { 
	            br = new BufferedReader(new InputStreamReader( 
	                    connectionData.getInputStream(), "UTF-8")); 
	            sb = new StringBuilder(); 
	            String line = null; 
	            while ((line = br.readLine()) != null) 
	                sb.append(line); 
	        } catch (SocketTimeoutException e) { 
	            System.out.println("连接超时"); 
	            logger.log(Level.WARNING, "连接超时");
	        } catch (FileNotFoundException e) { 
	            System.out.println("文件没有找到"); 
	            logger.log(Level.WARNING, "文件没有找到");
	        } 
	            String datas = sb.toString();   
		logger.log(Level.INFO,"baidu response:"+datas);
		return datas;
	}
	
	public String palceRequestUrl(String query,String lat,String lng) throws UnsupportedEncodingException {
		String url = WeChatConstant.BASEURL + "place/v2/search?query=" + URLEncoder.encode(query,"UTF-8") + "&ak="
				+ WeChatConstant.MAPKEY +"&location="+lat+","+lng +"&radius=5000"+"&output=" + WeChatConstant.OUTPUTFORMAT;
		return url;
	}
	
	public String getGeoCode(String query) throws ClientProtocolException, IOException{
		HttpClient httpClient = new DefaultHttpClient();
		String url = geoCodeRequestUrl(query);
		logger.log(Level.INFO, url);
		URL u = new URL(url); 
        connectionData = u.openConnection(); 
        connectionData.setConnectTimeout(1000); 
        try { 
            br = new BufferedReader(new InputStreamReader( 
                    connectionData.getInputStream(), "UTF-8")); 
            sb = new StringBuilder(); 
            String line = null; 
            while ((line = br.readLine()) != null) 
                sb.append(line); 
        } catch (SocketTimeoutException e) { 
            System.out.println("连接超时"); 
        } catch (FileNotFoundException e) { 
            System.out.println("文件没有找到"); 
        } 
        String datas = sb.toString();   
        logger.log(Level.INFO,"baidu response:"+datas);
		return datas;
	}
	
	public String geoCodeRequestUrl(String query) throws UnsupportedEncodingException{
		String url = WeChatConstant.BASEURL + "geocoder?address=" + URLEncoder.encode(query,"UTF-8") + "&key="
				+ WeChatConstant.MAPKEY + "&output=" + WeChatConstant.OUTPUTFORMAT;
		return url;
	}
	
}
