package org.xiakechina.course.util;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;



public class G2BUtil {
	public static String G2B(double y,double x){
				// 转换前的GPS坐标
				//x = 116.329654;
				//y = 39.947282;
				// google 坐标转百度链接
				// //http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x=116.32715863448607&y=39.990912172420714&callback=BMap.Convertor.cbk_3694
				// gps坐标的type=0
				// google坐标的type=2
				// baidu坐标的type=4
				 
				String path="http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="+y+"&y="+x+"&callback=BMap.Convertor.cbk_3694";
				StringBuffer sbStr=new StringBuffer();
				try {
					// 使用http请求获取转换结果
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5 * 1000);
					InputStream inStream = conn.getInputStream();

					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = inStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, len);
					}
					// 得到返回的结果
					String res = outStream.toString();
					System.out.println(res);
					// 处理结果
					if (res.indexOf("(") > 0 && res.indexOf(")") > 0) {
						String str = res.substring(res.indexOf("(") + 1,
								res.indexOf(")"));
						String err = res.substring(res.indexOf("error") + 7,
								res.indexOf("error") + 8);
						if ("0".equals(err)) {
							JSONObject js = JSONObject.fromObject(str);
							// 编码转换
							String x1 = new String(Base64.decode(js.getString("x")));
							String y1 = new String(Base64.decode(js.getString("y")));
							System.out.println(x1.substring(0, x1.length()-5) + "  " + y1.substring(0, y1.length()-5));
							sbStr.append(x1.substring(0, x1.length()-5)+"_"+y1.substring(0, y1.length()-5));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return sbStr.toString();
		}
	
	public static void main(String[] args) {
		G2BUtil.G2B(39.9530313, 116.336205);
	}
}
