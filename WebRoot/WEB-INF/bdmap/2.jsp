<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>ucp 导航</title>
		<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta name=”viewport” content=”width=device-width, initial-scale=1, maximum-scale=1″>
		<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	    <link href="http://esite.huiyee.com/res/370/css/index.css" rel="stylesheet" type="text/css">
	    <link href="http://esite.huiyee.com/res/370/css/reset.css" rel="stylesheet" type="text/css">
	    <link href="http://esite.huiyee.com/res/370/css/css/cooker.css" rel="stylesheet">
	    <link href="http://esite.huiyee.com/res/370/css/css/docs.css" rel="stylesheet">
	    <link href="http://esite.huiyee.com/res/370/css/css/drawer.min.css" rel="stylesheet">
	    <script src="http://esite.huiyee.com/res/370/js/js/jquery.min.js"></script>  
		<script src="http://esite.huiyee.com/res/370/js/js/iscroll.js"></script>  
		<script src="http://esite.huiyee.com/res/370/js/js/jquery.drawer.min.js"></script>  
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=025j9m7SviBpzAzFtN4pczG0"></script>
		<!-- 加载百度地图样式信息窗口 -->
		<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
	</head>
	<body class="drawer drawer-right">
<div style="top:0px;left:0px;width:100%;height:100%;position:fixed;z-index:-999;background:#ebebeb;background-size: cover; background-position: 50% 50%;"></div>
<div class="daohang_box">
  <div class="daohang_b_c">
    <div class="daohang_search">
      <form action="" method="get">
        <input id="tags" type="text" value="明德" class="daohang_search_top"/>
        <div class="dh_search_img"><a href="#" onclick="getLocation()"><img src="images/dh_03.png"/></a></div>
      </form>
    </div>
	  <div id="status" style="text-align: center"></div>
    <div id="container" class="daohang_body" style="width:100%;height:80%;"></div>
  </div>
</div>
		
		
	<div class="drawer-toggle drawer-hamberger"><span></span></div>
<div class="drawer-main drawer-default">
  <nav class="drawer-nav" role="navigation"> 
    <ul class="drawer-nav-list">
      <li><a href="index.html" target="_blank">首页</a></li>
      <li><a href="ufw_ctfw.html" target="_blank">U服务</a></li>
      <li><a href="mlucp.html" target="_blank">园区风采</a></li>
      <li><a href="tyzx_js.html" target="_blank">体育中心</a></li>
      <li><a href="daohang.html" target="_blank">园区导航</a></li>
      <li><a href="zxhd.html" target="_blank">最新活动</a></li>
       <li><a href="usq.html" target="_blank">U社区</a></li>
    </ul>
  </nav>
</div>

<script type="text/javascript">
	$(document).ready(function(){
	$('.drawer').drawer();
	$('.js-trigger').click(function(){
	  $('.drawer').drawer("open");
	});
	});
	
	
  var x;
  var y;
  var map;
  var customLayer;
  var tileLayer;

        tileLayer = new BMap.TileLayer();
        tileLayer.getTilesUrl = function(tileCoord, zoom) 
        {
          var a = tileCoord.x;
          var b = tileCoord.y;
          return '/maptiles/ucp/' + zoom + '/tile' + a + '_' + b + '.png';
         }
        var MyMap = new BMap.MapType('MyMap', tileLayer, {minZoom: 5, maxZoom: 18});
		map = new BMap.Map("container", {mapType: MyMap});
		add_control();
		var point = new BMap.Point(116.500594,39.996315);
		map.centerAndZoom(point,18);
		map.setMaxZoom(18)
		//addlayer();
        map.addControl(new BMap.NavigationControl());
		var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(
			function(r)
			{
				if(this.getStatus() == BMAP_STATUS_SUCCESS)
				{            
					    // x=r.point.lng;
					    x=116.500594;
					    
					    // y=r.point.lat;
					    y=39.996315;
					    var point1 = new BMap.Point(x,y);
					    //应该是r.point
						var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
						var mk = new BMap.Marker(point1,{icon:myIcon});
						map.addOverlay(mk);
						mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
						map.panTo(point1);
				}
				else 
				{
				        alert('failed'+this.getStatus());
				}        
			},{enableHighAccuracy: true});
 
  function add_control(){
		map.addTileLayer(tileLayer);
	};
	function delete_control(){
		map.removeTileLayer(tileLayer);
	};

function getLocation()
{
                $.post("/hy/user/bdmap/asub.action",{"x":x,"y":y,"tags":document.getElementById("tags").value},function(data){
                map.clearOverlays();   
                
                        var point1 = new BMap.Point(x,y);
					    //应该是r.point
						var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(300,157));
						var mk = new BMap.Marker(point1,{icon:myIcon});
						map.addOverlay(mk);
						mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
                
	  			var str="";
	  			$.each(data.contents,function(index,value){
	  				var ss="<tr class='tbvalue'><td>距离:"+value.distance+"</td><td>"+value.tags+"</td><td>"+value.title+"</td><td><a href='/hy/user/bdmap/dh.action?x="+x+"&y="+y+"&lc="+JSON.stringify(value)+"'>到这里去</a></td></tr>";
	  			    str+=ss;
	  			    var poi = new BMap.Point(value.location[0],value.location[1]);
	  			    var myIcon = new BMap.Icon("/images/bdtb.png", new BMap.Size(80,140));
	  			    var mk = new BMap.Marker(poi,{icon:myIcon});
	  			     var searchInfoWindow = new BMapLib.SearchInfoWindow(map, ss, {
				         title: value.title, //标题
				         width: 290, //宽度
				         height: 40, //高度
				         panel : "panel", //检索结果面板
				         enableAutoPan : true, //自动平移
				         enableSendToPhone: true, //是否显示发送到手机按钮
				         searchTypes :[
					     //BMAPLIB_TAB_SEARCH,   //周边检索
					     //BMAPLIB_TAB_TO_HERE,  //到这里去
					     //BMAPLIB_TAB_FROM_HERE //从这里出发
					     ]
			               });
	  			    mk.addEventListener('click',function(e)
	  			    {
			              searchInfoWindow.open(poi);
                     });
						map.addOverlay(mk);
						
						if(index==0){
						map.panTo(poi);
						}
					
	  			});
	  		       });
};
function addlayer()
{
		if (customLayer) 
		{
			map.removeTileLayer(customLayer);
		}
		customLayer=new BMap.CustomLayer({
			geotableId: 99207,
			q: '', //检索关键字
			tags: '', //空格分隔的多字符串
			filter: '' //过滤条件,参考http://developer.baidu.com/map/lbs-geosearch.htm#.search.nearby
		});
		map.addTileLayer(customLayer);
		map.addEventListener('click',callback);
}
</script>

	</body>
</html>