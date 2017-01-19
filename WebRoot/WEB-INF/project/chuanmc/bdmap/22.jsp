<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<title>ucp 导航</title>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=025j9m7SviBpzAzFtN4pczG0"></script>
		<!-- 加载百度地图样式信息窗口 -->
		<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
		<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	</head>
	<input id="tags" type="text" name="tags" value="明德" />
	<button onclick="getLocation()">
		搜索
	</button>
	   <!--   <input type="button" onclick="add_control();" value="添加" />
		<input type="button" onclick="delete_control();" value="删除" />
		-->
	<body style="margin: 50px 10px;">
		<div id="status" style="text-align: center"></div>
		<div
			style="width: 800px; height: 800px; border: 1px solid gray; margin: 30px auto"
			id="container"></div>
	</body>
	
	<div data-role="content">
		<div style="display: none" id="show">
			<table id="tb">
				<tr>
					<td>
						省份
					</td>
					<td>
						关键字
					</td>
					<td>
						坐标
					</td>
					<td>
						导航
					</td>
				</tr>
			</table>
		</div>

	</div>
</html>

<script type="text/javascript">
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
	  			$(".tbvalue").remove();
	  			$("#tb").append(str);
	  			$("#show").show();
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