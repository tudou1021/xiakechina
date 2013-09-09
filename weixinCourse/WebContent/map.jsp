<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7c108375343c699320c58ab27744ff31"></script>
<title>步行导航检索</title>
</head>
<body>
<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
//百度地图API功能
function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
} 
//百度地图API功能
//谷歌坐标
var myP1 = new BMap.Point(GetRequest().myY,GetRequest().myX);   //起点
var myP2 = new BMap.Point(GetRequest().toY,GetRequest().toX);    //终点
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point(GetRequest().myY, GetRequest().myX), 11);
var walking = new BMap.WalkingRoute(map, {renderOptions:{map: map, autoViewport: true}});
var myIcon = new BMap.Icon("http://icons.iconarchive.com/icons/visualpharm/icons8-metro-style/32/Maps-and-Geolocation-Street-view-icon.png", new BMap.Size(32, 70), {    //小车图片
    //offset: new BMap.Size(0, -5),    //相当于CSS精灵
    imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
  });
var walking2 = new BMap.WalkingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //步行实例
walking2.search(myP1, myP2);    //显示一条步行线路

window.run = function (){
    var walking = new BMap.WalkingRoute(map);    //步行实例
    walking.search(myP1, myP2);
    walking.setSearchCompleteCallback(function(){
        var pts = walking.getResults().getPlan(0).getRoute(0).getPath();    //通过步行实例，获得一系列点的数组
        var paths = pts.length;    //获得有几个点

        var carMk = new BMap.Marker(pts[0],{icon:myIcon});
        map.addOverlay(carMk);
        i=0;
        function resetMkPoint(i){
            carMk.setPosition(pts[i]);
            if(i < paths){
                setTimeout(function(){
                    i++;
                    resetMkPoint(i);
                },500);
            }
        }
        setTimeout(function(){
            resetMkPoint(1);
        },500)

    });
}
setTimeout(function(){
    run();
},1500);


var opts = {
	  position : myP1,    // 指定文本标注所在的地理位置
	  offset   : new BMap.Size(10,-50)    //设置文本偏移量
	 
	}
var label = new BMap.Label("当前位置", opts);  // 创建文本标注对象
	label.setStyle({
		 color : "black",
		 fontSize : "12px",
		 height : "20px",
		 lineHeight : "20px",
		 fontFamily:"微软雅黑"
	 });
map.addOverlay(label);  

var opts1 = {
		  position : myP2,    // 指定文本标注所在的地理位置
		  offset   : new BMap.Size(10,-50)    //设置文本偏移量
	}
var label1 = new BMap.Label("目的地", opts1);  // 创建文本标注对象
	label1.setStyle({
		 color : "black",
		 fontSize : "12px",
		 height : "20px",
		 lineHeight : "20px",
		 fontFamily:"微软雅黑"
	 });
map.addOverlay(label1); 

var marker = new BMap.Marker(myP1);  // 创建标注
map.addOverlay(marker);
var marker1 = new BMap.Marker(myP2);  // 创建标注
map.addOverlay(marker1);
</script>


