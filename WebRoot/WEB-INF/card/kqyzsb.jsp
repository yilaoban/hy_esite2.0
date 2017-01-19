<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 卡券验证失败 -->
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="weui_msg">
   <div class="weui_icon_area"><i class="weui_icon_msg weui_icon_warn"></i></div>
   <div class="weui_text_area">
     <h2 class="weui_msg_title">验证失败！</h2>
     <p class="weui_msg_desc">可能原因：1、您不是店主身份2、卡券已被使用 3、卡券已过期
     </p>
   </div>
   <div class="weui_opr_area">
     <p class="weui_btn_area">
       <a href="javascript:closeWindow();" class="weui_btn weui_btn_primary">确定并关闭</a>
     </p>
   </div>
 </div>
<script type="text/javascript">
	function closeWindow(){
		wx.closeWindow();
	}
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
