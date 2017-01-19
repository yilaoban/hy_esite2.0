<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<div style="top:0px;left:0px;right:0;bottom:0;width: 100%;height: 100vh;position:<s:if test='method=="E"'>absolute</s:if><s:else>fixed</s:else>;z-index:-999;background: ${dto.tc.bg };background-size:100%,100%;"> </div>
