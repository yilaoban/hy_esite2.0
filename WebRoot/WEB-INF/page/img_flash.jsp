<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">图片裁剪</h4>
</div>
<div class="modal-body">
<div id="upload_div">
	<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
	codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
	WIDTH="650" HEIGHT="450" id="myMovieName">
	<PARAM NAME=movie VALUE="/flashupload/avatar.swf">
	<PARAM NAME=quality VALUE=high>
	<PARAM NAME=bgcolor VALUE=#FFFFFF>
	<param name="flashvars" value="imgUrl=/flashupload/default.jpg&uploadUrl=/page/img_flash_sub.action?uploadSrc=false&showCame=false&pCut=${cutwidth }|${cutheight}&pData=${width}|${height }|0|0|0|0&pSize=0|0|0|0|0|0" />
	<EMBED src="/flashupload/avatar.swf" quality=high bgcolor=#FFFFFF WIDTH="330" HEIGHT="450" wmode="transparent" flashVars="imgUrl=/flashupload/default.jpg&uploadUrl=/page/img_flash_sub.action?uploadSrc=false&showCame=false&pCut=${cutwidth }|${cutheight}&pData=${width}|${height }|0|0|0|0&pSize=0|0|0|0|0|0"
	NAME="myMovieName" ALIGN="" TYPE="application/x-shockwave-flash" allowScriptAccess="always"
	PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer">
	</EMBED>
	</OBJECT>
	<input type="hidden" value="${key}" id="img_key">
</div>
</div>
