<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="container"  style="height:100%;"></div>
<script type="text/javascript" src="/js/pano2vr_player.js"></script>
<script type="text/javascript">
	var pano  = new pano2vrPlayer("container");
	pano.readConfigUrl("/site${p}");
</script>