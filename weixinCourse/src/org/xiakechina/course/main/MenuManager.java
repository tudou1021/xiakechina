package org.xiakechina.course.main;

import org.xiakechina.course.pojo.AccessToken;
import org.xiakechina.course.pojo.Button;
import org.xiakechina.course.pojo.CommonButton;
import org.xiakechina.course.pojo.ComplexButton;
import org.xiakechina.course.pojo.Menu;
import org.xiakechina.course.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 * 
 * @author xiakechina
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "http://1.xiakechina.duapp.com/coreServlet";
		// 第三方用户唯一凭证密钥
		String appSecret = "weixinCourse";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("公交查询");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("周边搜索");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("历史上的今天");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("经典游戏");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("美女电台");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("人脸识别");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("聊天唠嗑");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q友圈");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("电影排行榜");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲驿站");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
	
	public static String getMainMenu(){
		StringBuffer sbString=new StringBuffer();
    	sbString.append("您好，我是钢小七，请回复数字选择服务：").append("\n\n");
    	sbString.append("1、长线活动").append("\n");
    	sbString.append("2、一日活动").append("\n");
    	sbString.append("3、笑话").append("\n");
    	sbString.append("4、天气").append("\n");
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
}