<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script src="/js/bdMap.js"></script>
<script src="/js/jquery.weather.build.js"></script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}
.demo{width:800px;margin:0 auto;}
.demo div{margin:40px 0 0 0;}
</style>
<div class="wrap_content">
	<div id="map" style="height:400px;width:600px;border:1px solid #bcbcbc;"></div>
	<script type="text/javascript">
	 ShowMap("map",{city:'武汉',addr:'南湖',title:'软件园',lawfirm:'会易科技公司',tel:'1362380952x',ismove:'0'}); 
	</script>
	
	<div class="demo">
		<div>
			<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=2&num=5" width="650" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
		</div>
	</div>
	<div>
			<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=7" style="border:solid 1px #7ec8ea" width="225" height="90" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
	</div>
	


<textarea id="e1" rows="12" cols="100" ></textarea>
<script type="text/javascript" > 
 new TQEditor('e1'); 
</script>
</div>
