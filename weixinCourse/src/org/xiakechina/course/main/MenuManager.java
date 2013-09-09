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
 * �˵���������
 * 
 * @author xiakechina
 * @date 2013-08-08
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "http://1.xiakechina.duapp.com/coreServlet";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "weixinCourse";

		// ���ýӿڻ�ȡaccess_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// ���ýӿڴ����˵�
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// �жϲ˵��������
			if (0 == result)
				log.info("�˵������ɹ���");
			else
				log.info("�˵�����ʧ�ܣ������룺" + result);
		}
	}

	/**
	 * ��װ�˵�����
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("����Ԥ��");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("������ѯ");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("�ܱ�����");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("��ʷ�ϵĽ���");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("�����㲥");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("������Ϸ");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("��Ů��̨");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("����ʶ��");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("�������");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q��Ȧ");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("��Ӱ���а�");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("��ĬЦ��");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("��������");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("������վ");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("��������");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

		/**
		 * ���ǹ��ں�xiaoqrobotĿǰ�Ĳ˵��ṹ��ÿ��һ���˵����ж����˵���<br>
		 * 
		 * ��ĳ��һ���˵���û�ж����˵��������menu����ζ����أ�<br>
		 * ���磬������һ���˵���ǡ��������顱����ֱ���ǡ���ĬЦ��������ômenuӦ���������壺<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
	
	public static String getMainMenu(){
		StringBuffer sbString=new StringBuffer();
    	sbString.append("���ã����Ǹ�С�ߣ���ظ�����ѡ�����").append("\n\n");
    	sbString.append("1�����߻").append("\n");
    	sbString.append("2��һ�ջ").append("\n");
    	sbString.append("3��Ц��").append("\n");
    	sbString.append("4������").append("\n");
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
}