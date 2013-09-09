package org.xiakechina.course.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;
/**
 * �õ�δ46�������(������)
 * @author Chewl
 *
 */
public class Weather { 
    String Ctiyid; 
    URLConnection connectionData; 
    StringBuilder sb; 
    BufferedReader br;// ��ȡdata����� 
    JSONObject jsonData; 
    JSONObject info; 
    Log log = LogFactory.getLog(Weather.class);    
    public String  Weather(String Ctiyid) throws IOException ,NullPointerException{ 
    	// �����ip��ַ 
        this.Ctiyid = Ctiyid; 
        log.info(Ctiyid);
        // l����������̨��API 
        URL url = new URL("http://m.weather.com.cn/data/" + Ctiyid + ".html"); 
        connectionData = url.openConnection(); 
        connectionData.setConnectTimeout(1000); 
        try { 
            br = new BufferedReader(new InputStreamReader( 
                    connectionData.getInputStream(), "UTF-8")); 
            sb = new StringBuilder(); 
            String line = null; 
            while ((line = br.readLine()) != null) 
                sb.append(line); 
        } catch (SocketTimeoutException e) { 
            System.out.println("l�ӳ�ʱ"); 
        } catch (FileNotFoundException e) { 
            System.out.println("�����ļ����"); 
        } 
            String datas = sb.toString();   
            
           jsonData = JSONObject.fromObject(datas); 
          //  System.out.println(jsonData.toString());  
           info = jsonData.getJSONObject("weatherinfo"); 
        
        //�õ�1��6����������
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i=1;i<=3;i++){
        	//�õ�δ46�������
        	Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DAY_OF_YEAR, i-1);
        	Date date = cal.getTime();
        	SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        	
        	Map<String,Object> map = new HashMap<String, Object>();
        	map.put("city", info.getString("city").toString());//����
        	map.put("date_y", sf.format(date));//����
        	map.put("week", getWeek(cal.get(Calendar.DAY_OF_WEEK)));//����
        	map.put("fchh", info.getString("fchh").toString());//����ʱ��
        	map.put("weather", info.getString("weather"+i).toString());//����
        	map.put("temp", info.getString("temp"+i).toString());//�¶�
        	map.put("wind", info.getString("wind"+i).toString());//���
        	map.put("fl", info.getString("fl"+i).toString());//����
        	map.put("index", info.getString("index").toString());// ����Ĵ���ָ�� 
        	map.put("index_uv", info.getString("index_uv").toString());// ����ָ�� 
        	map.put("index_tr", info.getString("index_tr").toString());// ����ָ�� 
        	map.put("index_co", info.getString("index_co").toString());// ����ָ�� 
        	map.put("index_cl", info.getString("index_cl").toString());// ��wָ�� 
        	map.put("index_xc", info.getString("index_xc").toString());//ϴ��ָ�� 
        	map.put("index_d", info.getString("index_d").toString());//������ϸ����ָ�� 
        	list.add(map);
        }
        //����̨��ӡ������
        StringBuffer sbString=new StringBuffer();
       for(int j=0;j<list.size();j++){
    	   Map<String,Object> wMap = list.get(j);
//    	   System.out.println(wMap.get("city")+"\t"+wMap.get("date_y")+"\t"+wMap.get("week")+"\t"
//    			   +wMap.get("weather")+"\t"+wMap.get("temp")+"\t"+wMap.get("index_uv")+"\t"+wMap.get("index_d"));
    	   sbString.append(wMap.get("city")+"\t"+wMap.get("date_y")+"\t"+wMap.get("week")+"\t"
    			   +wMap.get("weather")+"\n"+wMap.get("temp")+"\t紫外线"+wMap.get("index_uv")+"\n穿衣指数："+wMap.get("index_d"));
    	   sbString.append("\n\n");
       }
    	   return sbString.toString();
 
    } 
    private String getWeek(int iw){
    	String weekStr = "";
    	switch (iw) {
		case 1:weekStr = "星期日";break;
		case 2:weekStr = "星期一";break;
		case 3:weekStr = "星期二";break;
		case 4:weekStr = "星期三";break;
		case 5:weekStr = "星期四";break;
		case 6:weekStr = "星期五";break;
		case 7:weekStr = "星期六";break;
		default:
			break;
		}
    	return weekStr;
    }
    public static void main(String[] args) { 
        try { 

        	String txt="天气拉萨";
        	if(txt.contains("天气")){
        		String city=txt.replace("天气", "");
        		System.out.println(new Weather().Weather(LoadCode.getWeatherCode(city)));
        	}else{
        		System.out.println("123123");
        	}
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 