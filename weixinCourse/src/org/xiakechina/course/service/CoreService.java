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
 * 核心服务类
 * 
 * @author xiakechina
 * @date 2013-05-20
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	
	 @SuppressWarnings({ "static-access", "unchecked" })
	public static String processRequest(HttpServletRequest request) { 
		    Log log = LogFactory.getLog(CoreService.class);   
	        String respMessage = null;  
	        
	        
	        try {  
	            // 默认返回的文本消息内容  
	            String respContent = "请求处理异常，请稍候尝试！";  
	  
	            // xml请求解析  
	            Map<String, String> requestMap = MessageUtil.parseXml(request);  
	  
	            // 发送方帐号（open_id）  
	            String fromUserName = requestMap.get("FromUserName");  
	            // 公众帐号  
	            String toUserName = requestMap.get("ToUserName");  
	            // 消息类型  
	            String msgType = requestMap.get("MsgType");  
	            // 默认回复此文本消息  
	            TextMessage textMessage = new TextMessage();  
	            textMessage.setToUserName(fromUserName);  
	            textMessage.setFromUserName(toUserName);  
	            log.info(fromUserName);
	            textMessage.setCreateTime(new Date().getTime());  
	            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
	            textMessage.setFuncFlag(0);  
	            // 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义  
	            textMessage.setContent(getMainMenu2Special());  
	            // 将文本消息对象转换成xml字符串  
	            respMessage = MessageUtil.textMessageToXml(textMessage);  
	            // 文本消息  
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { 
	            	// 消息内容
		            String msgContent= requestMap.get("Content");
	                if("?".equals(msgContent)){
	                	textMessage.setContent(getMainMenu2Special());  
	    	            respMessage = MessageUtil.textMessageToXml(textMessage);  
	                }else if("1".equals(msgContent)){
	                	List<RssBean> rssList=new RssUtil().parse("http://www.xiakechina.cn/forum.php?mod=rss&fid=37&auth=0","xiakechina");
	                	List<Article> articleList = new ArrayList<Article>();  
	                	// 创建图文消息  
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
	                	// 创建图文消息  
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
	                	// 创建图文消息  
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
	                	 // 创建图文消息  
	                     NewsMessage newsMessage = new NewsMessage();  	
	                	 newsMessage.setToUserName(fromUserName);  
	                     newsMessage.setFromUserName(toUserName);  
	                     newsMessage.setCreateTime(new Date().getTime());  
	                     newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                     newsMessage.setFuncFlag(0);
	                	 Article article = new Article(); 
                    	 article.setTitle("斗地主");
                 		 article.setDescription("");  
                 		 article.setPicUrl("http://img02.nduoa.com/apk/328/328728/rotated/0.watermark.jpg");  
                 		 article.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Doudizhu/doudizhu.htm");  
                 		 Article article2 = new Article(); 
                 		 article2.setTitle("数独小游戏");
                 		 article2.setDescription("");  
                 		 article2.setPicUrl("http://i-7.vcimg.com/crop/b3ef9bbf519a1e50a5353c6c34481c9a22749(600x)/thumb.jpg");  
                 		 article2.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/shudu/shudu.html");  
                 		 Article article3 = new Article(); 
                 		 article3.setTitle("台球大师");
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
	                	if(msgContent.trim().toString().contains("天气")){
	                		 String city=msgContent.trim().replace("天气", "");
		                	 String cityCode=LoadCode.getWeatherCode(city);
		                	 String weather="";
		                	 if(null!=cityCode){
		                	   weather=new Weather().Weather(cityCode);
		                	 }else{
	                		   weather=getWeatherMenu("\ue52b请输入正确的天气格式!");	 
		                	 }
		                	 respContent=weather;
		                	 textMessage.setContent(respContent);  
		                	 respMessage = MessageUtil.textMessageToXml(textMessage);  
	                	}else if(msgContent.trim().toString().contains("附近")){
	                		 String content=msgContent.trim().replace("附近", "");
	                		 log.info(content);
	                		 Map<String,String> lacMap=(Map<String, String>) request.getSession().getServletContext().getAttribute("location");
	                		 String location=lacMap.get(fromUserName);
	                		 log.info(location);
	                		 if(null==location||"null".equals(location)){
	                			 String locaMenu=getLocationMenu();
	                			 respContent="\ue252抱歉客官，钢小\ue222还不知道您的具体位置\ue150\n请先发送您的位置信息，再使用附近查询功能！\ue01d";
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
     		searchCon="小吃";
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
     		 a.setTitle(bpr.getName()+"\n"+"距离约"+distance+"米");
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
	    	sbString.append("\ue11b客官您好!").append("\n");
			sbString.append("\ue418好男人就是我\ue329").append("\n");
			sbString.append("我就是钢小\ue222\ue41d!").append("\n");
			sbString.append("请回复数字选择服务：").append("\n\n");
			sbString.append("1、十一活动\ue112").append("\n");
	    	sbString.append("2、中秋活动\ue106").append("\n");
	    	sbString.append("3、一日活动\ue418").append("\n");
	    	sbString.append("4、笑话\ue334").append("\n");
	    	sbString.append("5、天气\ue04a").append("\n");
	    	sbString.append("6、游戏\ue10d").append("\n\n");
	    	sbString.append("回复“?”显示此帮助菜单").append("\n");
	    	return sbString.toString();
	 }
	 
	 public static String getWeatherMenu(String msg){
		 StringBuffer howWeather=new StringBuffer();
		 if(!"".equals(msg)){
			 howWeather.append(msg).append("\n");
		 }
	   	 howWeather.append("\ue04a天气预报使用指南").append("\n\n");
	   	 howWeather.append("回复：天气+城市名称").append("\n");
	   	 howWeather.append("例如：天气北京").append("\n");
	   	 howWeather.append("或者：北京天气").append("\n\n");
	   	 howWeather.append("回复“?”显示主菜单");
	   	 return howWeather.toString();
	 }
	 
	 public static String getLocationMenu(){
		 StringBuffer locMenu=new StringBuffer();
		 locMenu.append("\ue151周边搜索使用说明").append("\n\n");
		 locMenu.append("1）发送地理位置\ue138").append("\n");
		 locMenu.append("点击窗口底部的“+”按钮，选择“位置”，点“发送”").append("\n");
		 locMenu.append("\ue043默认搜索周边“小吃”\ue33e").append("\n\n");
		 locMenu.append("2）指定关键词搜索\ue139").append("\n");
		 locMenu.append("格式：附近+关键词").append("\n");
		 locMenu.append("例如：附近ATM\ue14a、附近KTV\ue312、附近厕所\ue309").append("\n\n");
		 locMenu.append("回复“?”显示主菜单");
	   	 return locMenu.toString();
	 }
}
