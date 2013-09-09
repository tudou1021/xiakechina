package org.xiakechina.course.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xiakechina.course.message.resp.Article;
import org.xiakechina.course.message.resp.NewsMessage;
import org.xiakechina.course.message.resp.TextMessage;
import org.xiakechina.course.pojo.RssBean;
import org.xiakechina.course.util.G2BUtil;
import org.xiakechina.course.util.GeoUtil;
import org.xiakechina.course.util.GeoUtil.GaussSphere;
import org.xiakechina.course.util.LoadCode;
import org.xiakechina.course.util.MessageUtil;
import org.xiakechina.course.util.RssUtil;
import org.xiakechina.course.util.Weather;

/**
 * ���ķ�����
 * 
 * @author xiakechina
 * @date 2013-05-20
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	
	 @SuppressWarnings({ "static-access", "unchecked" })
	public static String processRequest(HttpServletRequest request) { 
		    Log log = LogFactory.getLog(CoreService.class);   
	        String respMessage = null;  
	        
	        
	        try {  
	            // Ĭ�Ϸ��ص��ı���Ϣ����  
	            String respContent = "�������쳣�����Ժ��ԣ�";  
	  
	            // xml�������  
	            Map<String, String> requestMap = MessageUtil.parseXml(request);  
	  
	            // ���ͷ��ʺţ�open_id��  
	            String fromUserName = requestMap.get("FromUserName");  
	            // �����ʺ�  
	            String toUserName = requestMap.get("ToUserName");  
	            // ��Ϣ����  
	            String msgType = requestMap.get("MsgType");  
	            // Ĭ�ϻظ����ı���Ϣ  
	            TextMessage textMessage = new TextMessage();  
	            textMessage.setToUserName(fromUserName);  
	            textMessage.setFromUserName(toUserName);  
	            log.info(fromUserName);
	            textMessage.setCreateTime(new Date().getTime());  
	            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
	            textMessage.setFuncFlag(0);  
	            // ����href����ֵ������˫�������������ַ��������˫���ų�ͻ������Ҫת��  
	            textMessage.setContent(getMainMenu2Special());  
	            // ���ı���Ϣ����ת����xml�ַ���  
	            respMessage = MessageUtil.textMessageToXml(textMessage);  
	            // �ı���Ϣ  
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { 
	            	// ��Ϣ����
		            String msgContent= requestMap.get("Content");
	                if("?".equals(msgContent)){
	                	textMessage.setContent(getMainMenu2Special());  
	    	            respMessage = MessageUtil.textMessageToXml(textMessage);  
	                }else if("1".equals(msgContent)){
	                	List<RssBean> rssList=new RssUtil().parse("http://www.xiakechina.cn/forum.php?mod=rss&fid=37&auth=0","xiakechina");
	                	List<Article> articleList = new ArrayList<Article>();  
	                	// ����ͼ����Ϣ  
	                    NewsMessage newsMessage = new NewsMessage();  	
	                	newsMessage.setToUserName(fromUserName);  
	                    newsMessage.setFromUserName(toUserName);  
	                    newsMessage.setCreateTime(new Date().getTime());  
	                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                    newsMessage.setFuncFlag(0);
	                    List<RssBean> rList=RssUtil.retList(rssList, "1");
	                    for(int i=0;i< rList.size();i++){
	                    	RssBean rb=rList.get(i);
	                    	Article article = new Article(); 
 	                    	article.setTitle(rb.getTitle());
	                 		article.setDescription(rb.getDescription());  
	                 		article.setPicUrl(rb.getImgUrl());  
	                 		article.setUrl(rb.getLink());  
	                 		articleList.add(article);
	                    }    
	                	newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
	                }else if("2".equals(msgContent)){
	                	List<RssBean> rssList=new RssUtil().parse("http://www.xiakechina.cn/forum.php?mod=rss&fid=37&auth=0","xiakechina");
	                	List<Article> articleList = new ArrayList<Article>();  
	                	// ����ͼ����Ϣ  
	                    NewsMessage newsMessage = new NewsMessage();  	
	                	newsMessage.setToUserName(fromUserName);  
	                    newsMessage.setFromUserName(toUserName);  
	                    newsMessage.setCreateTime(new Date().getTime());  
	                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                    newsMessage.setFuncFlag(0);
	                    List<RssBean> rList=RssUtil.retList(rssList, "2");
	                    for(int i=0;i< rList.size();i++){
	                    	RssBean rb=rList.get(i);
	                    	Article article = new Article(); 
 	                    	article.setTitle(rb.getTitle());
	                 		article.setDescription(rb.getDescription());  
	                 		article.setPicUrl(rb.getImgUrl());  
	                 		article.setUrl(rb.getLink());  
	                 		articleList.add(article);
	                    }    
	                	newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
	                }else if("3".equals(msgContent)){
	                	List<RssBean> rssList=new RssUtil().parse("http://www.xiakechina.cn/forum.php?mod=rss&fid=38&auth=0","xiakechina");
	                	List<Article> articleList = new ArrayList<Article>();  
	                	// ����ͼ����Ϣ  
	                    NewsMessage newsMessage = new NewsMessage();  	
	                	newsMessage.setToUserName(fromUserName);  
	                    newsMessage.setFromUserName(toUserName);  
	                    newsMessage.setCreateTime(new Date().getTime());  
	                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                    newsMessage.setFuncFlag(0);
	                    List<RssBean> rList=RssUtil.retList(rssList, "3");
	                    for(int i=0;i<rList.size();i++){
	                    	RssBean rb=rList.get(i);
	                    	System.out.println(rb.getCategory());
	                    	Article article = new Article(); 
 	                    	article.setTitle(rb.getTitle());
	                 		article.setDescription(rb.getDescription());  
	                 		article.setPicUrl(rb.getImgUrl());  
	                 		article.setUrl(rb.getLink());  
	                 		articleList.add(article);
	                    }
	                	newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage);  
	                }else if("4".equals(msgContent)){
	                	 List<RssBean> rssList=new RssUtil().parse("http://www.xiaohuayoumo.com/plus/rss/3.xml","");
		           		 RssBean rb=rssList.get((int) (Math.random() * rssList.size()));
		           		 respContent=rb.getDescription();
		           		 textMessage.setContent(respContent);  
			             respMessage = MessageUtil.textMessageToXml(textMessage);  
	                }else if("5".equals(msgContent)){
	                	 respContent=getWeatherMenu("");
	                	 textMessage.setContent(respContent);
	    	             respMessage = MessageUtil.textMessageToXml(textMessage);  
	                }else if("6".equals(msgContent)){
	                	 List<Article> articleList = new ArrayList<Article>();  
	                	 // ����ͼ����Ϣ  
	                     NewsMessage newsMessage = new NewsMessage();  	
	                	 newsMessage.setToUserName(fromUserName);  
	                     newsMessage.setFromUserName(toUserName);  
	                     newsMessage.setCreateTime(new Date().getTime());  
	                     newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                     newsMessage.setFuncFlag(0);
	                	 Article article = new Article(); 
                    	 article.setTitle("������");
                 		 article.setDescription("");  
                 		 article.setPicUrl("http://img02.nduoa.com/apk/328/328728/rotated/0.watermark.jpg");  
                 		 article.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Doudizhu/doudizhu.htm");  
                 		 Article article2 = new Article(); 
                 		 article2.setTitle("����С��Ϸ");
                 		 article2.setDescription("");  
                 		 article2.setPicUrl("http://i-7.vcimg.com/crop/b3ef9bbf519a1e50a5353c6c34481c9a22749(600x)/thumb.jpg");  
                 		 article2.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/shudu/shudu.html");  
                 		 Article article3 = new Article(); 
                 		 article3.setTitle("̨���ʦ");
                 		 article3.setDescription("");  
                 		 article3.setPicUrl("http://pic15.nipic.com/20110715/7842970_155910335132_2.jpg");  
                 		 article3.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/BilliardsMaster/BilliardsMaster.htm");  
                 		 articleList.add(article);
                 		 articleList.add(article2);
                 		 articleList.add(article3);
                 		 newsMessage.setArticleCount(articleList.size());  
                         newsMessage.setArticles(articleList);  
                         respMessage = MessageUtil.newsMessageToXml(newsMessage); 
	                }else{
	                	if(msgContent.trim().toString().contains("����")){
	                		 String city=msgContent.trim().replace("����", "");
		                	 String cityCode=LoadCode.getWeatherCode(city);
		                	 String weather="";
		                	 if(null!=cityCode){
		                	   weather=new Weather().Weather(cityCode);
		                	 }else{
	                		   weather=getWeatherMenu("\ue52b��������ȷ��������ʽ!");	 
		                	 }
		                	 respContent=weather;
		                	 textMessage.setContent(respContent);  
		                	 respMessage = MessageUtil.textMessageToXml(textMessage);  
	                	}else if(msgContent.trim().toString().contains("����")){
	                		 String content=msgContent.trim().replace("����", "");
	                		 log.info(content);
	                		 Map<String,String> lacMap=(Map<String, String>) request.getSession().getServletContext().getAttribute("location");
	                		 String location=lacMap.get(fromUserName);
	                		 log.info(location);
	                		 if(null==location||"null".equals(location)){
	                			 String locaMenu=getLocationMenu();
	                			 respContent="\ue252��Ǹ�͹٣���С\ue222����֪�����ľ���λ��\ue150\n���ȷ�������λ����Ϣ����ʹ�ø�����ѯ���ܣ�\ue01d";
			                	 textMessage.setContent(respContent);  
			                	 respMessage = MessageUtil.textMessageToXml(textMessage); 
	                		 }else{
	                			 NewsMessage newsMessage = new NewsMessage();
		     	            	 newsMessage.setToUserName(fromUserName);  
		                         newsMessage.setFromUserName(toUserName);  
		                         newsMessage.setCreateTime(new Date().getTime());  
		                         newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		                         newsMessage.setFuncFlag(0);
		                         List<Article> articleList = new ArrayList<Article>();
		                         String[] p1=location.split(",");
		                         articleList=getMapList(p1[0],p1[1],content);
		                         log.info(articleList.size());
		                         newsMessage.setArticleCount(articleList.size());  
		                         newsMessage.setArticles(articleList);  
		                         respMessage = MessageUtil.newsMessageToXml(newsMessage); 
	                		 }
	                		 
	                	}else{
	                		textMessage.setContent(getMainMenu2Special()); 
	                		respMessage = MessageUtil.textMessageToXml(textMessage);  
                		}
	    	            
	                }
	            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
	            	NewsMessage newsMessage = new NewsMessage();
	            	newsMessage.setToUserName(fromUserName);  
                    newsMessage.setFromUserName(toUserName);  
                    newsMessage.setCreateTime(new Date().getTime());  
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                    newsMessage.setFuncFlag(0);
                    List<Article> articleList = new ArrayList<Article>();
                    String p1x=requestMap.get("Location_X");
                    String p1y=requestMap.get("Location_Y");
                    Map<String,String> lacMap=(Map<String, String>) request.getSession().getServletContext().getAttribute("location");
                    lacMap.put(fromUserName, (p1x+","+p1y));
                    articleList=getMapList(p1x,p1y,"");
                	newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return respMessage;  
	    }  
	 
	 public static List<Article> getMapList(String p1x,String p1y,String content) throws Exception{
	    List<Article> articleList=new ArrayList<Article>();
     	String value=G2BUtil.G2B(Double.parseDouble(p1y), Double.parseDouble(p1x));
     	String y=value.split("_")[0];
     	String x=value.split("_")[1];
     	BaiduMapService bms = new BaiduMapService();
     	String searchCon="";
     	if("".equals(content)){
     		searchCon="С��";
     	}else{
     		searchCon=content;
     	}
 		String response = bms.getPalace(searchCon, x, y);
 		List<BaiduPlaceResponse> list = BaiduPlaceResponse.getBaiduPlace(response);
     	Map<Double,Article> arrMap=new HashMap<Double, Article>();
     	for(int i=0;i<list.size();i++){
     		 BaiduPlaceResponse bpr=list.get(i);
 			 Article a=new Article();
     		 Double distance = GeoUtil.DistanceOfTwoPoints(Double.valueOf(bpr.getLng()), Double.valueOf(bpr.getLat()), Double.valueOf(y), Double.valueOf(x), GaussSphere.Beijing54);
     		 a.setTitle(bpr.getName()+"\n"+"����Լ"+distance+"��");
     		 a.setUrl("http://0.tudoutest.duapp.com/map.jsp?myX="+x+"&myY="+y+"&toX="+bpr.getLat()+"&toY="+bpr.getLng());
 			 a.setPicUrl("http://0.tudoutest.duapp.com/image/023b5bb5c9ea15ce342780feb7003af33b87b27f.png"); 
     		 a.setDescription("");
     		 arrMap.put(distance, a);
     	}
     	Double[] strs = new Double[arrMap.keySet().size()];
     	int i=0;
     	System.out.println(arrMap.keySet().size());
     	for(Object obj:arrMap.keySet().toArray()){
     			strs[i]=Double.valueOf(obj.toString());
         		i++;
     	}
     	if(strs.length>0){
     		Arrays.sort(strs);
     		int size=0;
     		if(strs.length>10){
     			size=10;
     		}else{
     			size=strs.length;
     		}
         	for(int a=0;a<size;a++){
         		Double dis=strs[a];
         		articleList.add(arrMap.get(dis));
         	}
         	articleList.get(0).setPicUrl("http://0.tudoutest.duapp.com/image/1349762_1334363950ceqS_meitu_2.jpg");
     	}
     	return articleList;
	 }
	 
	 public static String getMainMenu2Special(){
		 StringBuffer sbString=new StringBuffer();
	    	sbString.append("\ue11b�͹�����!").append("\n");
			sbString.append("\ue418�����˾�����\ue329").append("\n");
			sbString.append("�Ҿ��Ǹ�С\ue222\ue41d!").append("\n");
			sbString.append("��ظ�����ѡ�����").append("\n\n");
			sbString.append("1��ʮһ�\ue112").append("\n");
	    	sbString.append("2������\ue106").append("\n");
	    	sbString.append("3��һ�ջ\ue418").append("\n");
	    	sbString.append("4��Ц��\ue334").append("\n");
	    	sbString.append("5������\ue04a").append("\n");
	    	sbString.append("6����Ϸ\ue10d").append("\n\n");
	    	sbString.append("�ظ���?����ʾ�˰����˵�").append("\n");
	    	return sbString.toString();
	 }
	 
	 public static String getWeatherMenu(String msg){
		 StringBuffer howWeather=new StringBuffer();
		 if(!"".equals(msg)){
			 howWeather.append(msg).append("\n");
		 }
	   	 howWeather.append("\ue04a����Ԥ��ʹ��ָ��").append("\n\n");
	   	 howWeather.append("�ظ�������+��������").append("\n");
	   	 howWeather.append("���磺��������").append("\n");
	   	 howWeather.append("���ߣ���������").append("\n\n");
	   	 howWeather.append("�ظ���?����ʾ���˵�");
	   	 return howWeather.toString();
	 }
	 
	 public static String getLocationMenu(){
		 StringBuffer locMenu=new StringBuffer();
		 locMenu.append("\ue151�ܱ�����ʹ��˵��").append("\n\n");
		 locMenu.append("1�����͵���λ��\ue138").append("\n");
		 locMenu.append("������ڵײ��ġ�+����ť��ѡ��λ�á����㡰���͡�").append("\n");
		 locMenu.append("\ue043Ĭ�������ܱߡ�С�ԡ�\ue33e").append("\n\n");
		 locMenu.append("2��ָ���ؼ�������\ue139").append("\n");
		 locMenu.append("��ʽ������+�ؼ���").append("\n");
		 locMenu.append("���磺����ATM\ue14a������KTV\ue312����������\ue309").append("\n\n");
		 locMenu.append("�ظ���?����ʾ���˵�");
	   	 return locMenu.toString();
	 }
}
