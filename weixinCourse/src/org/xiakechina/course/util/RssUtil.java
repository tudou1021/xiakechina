package org.xiakechina.course.util;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xiakechina.course.pojo.RssBean;

public class RssUtil
{
    public static List<RssBean> parse(String Url,String someWhere)
    {
    	List<RssBean> rssList=new ArrayList<RssBean>();
        try
        {
            URL url = new URL(Url);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(url);

            Element channel = (Element) document.getRootElement().element("channel");
            for (Iterator i = channel.elementIterator("item"); i.hasNext();)
            {	
                Element element = (Element) i.next();
                RssBean rb=new RssBean();
                rb.setTitle(element.elementText("title"));
                String content=element.elementText("description").replace("[/backcolor]", "");
                rb.setDescription(content);
                rb.setLink(element.elementText("link"));
                if(someWhere.equals("xiakechina")){
                	rb.setImgUrl(element.element("enclosure").attributeValue("url"));
                }
                rb.setCategory(element.elementText("category"));
//                String date=DateUtils.getFormattedString(DateUtils.strToDate(element.elementText("pubDate")), "yyyy-MM-dd hh:mm:ss");
                rb.setPubDate(element.elementText("pubDate"));
                rssList.add(rb);
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rssList;
    }
    
    
    public static List<RssBean> retRssList(List<RssBean> rssList,String activeLevel){
    	List<RssBean> cxList=new ArrayList<RssBean>();
    	List<RssBean> dxList=new ArrayList<RssBean>();
    	List<RssBean> rbList=new ArrayList<RssBean>();
    	for(RssBean rss:rssList){
    		if(rss.getCategory().equals("长线远行")){
    			cxList.add(rss);
    		}else if(rss.getCategory().equals("一日休闲")){
    			dxList.add(rss);
    		}
    	}
    	if(activeLevel.equals("1")){
    		rbList.addAll(cxList);
    	}else if(activeLevel.equals("2")){
    		rbList.addAll(dxList);
    	}
    	return rbList;
    }
    
    public static List<RssBean> retList(List<RssBean> rssList,String timeActive){
    	List<RssBean> syList=new ArrayList<RssBean>();
    	List<RssBean> zqList=new ArrayList<RssBean>();
    	List<RssBean> yrList=new ArrayList<RssBean>();
    	List<RssBean> rbList=new ArrayList<RssBean>();
    	for(RssBean rss:rssList){
    		String title=rss.getTitle();
    		if(rss.getCategory().equals("长线远行")){
    			if(title.indexOf("十一")!=-1){
        			syList.add(rss);
        		}else if(title.indexOf("中秋")!=-1){
        			zqList.add(rss);
        		}
    		}else if(rss.getCategory().equals("一日休闲")){
    			if(title.indexOf("中秋")!=-1){
    				yrList.add(rss);
        		}
    			
    		}
    	}
    	if(timeActive.equals("1")){
    		rbList.addAll(syList);
    	}else if(timeActive.equals("2")){
    		rbList.addAll(zqList);
    	}else{
    		rbList.addAll(yrList);
    	}
    	return rbList;
    }
    public static void main(String[] args) {
		 new RssUtil();
		 List<RssBean> rssList=RssUtil.parse("http://www.xiakechina.cn/forum.php?mod=rss","xiakechina");
		 List<RssBean> retList=new RssUtil().retList(rssList, "2");
		 for(RssBean rss:retList){
			 System.out.println(rss.getTitle());
		 }
		 
    }
}
