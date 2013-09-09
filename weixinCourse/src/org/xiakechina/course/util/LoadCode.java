package org.xiakechina.course.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class LoadCode {
	public static String getWeatherCode(String cityName) throws DocumentException{
		String base=LoadCode.class.getResource("/code.xml").getPath();
		SAXReader saxReader = new SAXReader();
		File file=new File(base);
	    Document document = saxReader.read(file);

	    Element channel = (Element) document.getRootElement();
	    Map<String,Map<String,String>> codeMap=new HashMap<String, Map<String,String>>();
	    Map<String,String> strMap=new HashMap<String, String>();
	    for (Iterator i = channel.elementIterator("p"); i.hasNext();)
	    {	
	        Element element = (Element) i.next();
	        for(Iterator p=element.elementIterator("c");p.hasNext();){
	        	Element e = (Element) p.next();
	        	for(Iterator d=e.elementIterator("d");d.hasNext();){
	        		Element f = (Element) d.next();
		        	strMap.put(f.getText(), f.attributeValue("d_id"));
	        	}	
	        }
	    }
	    return strMap.get(cityName);
	}
}
