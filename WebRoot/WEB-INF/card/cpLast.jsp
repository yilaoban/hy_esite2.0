<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 测评结果 -->
<%@include file="/WEB-INF/card/background.jsp"%>

<div style="line-height:22px;">
${dto.examResult.content }
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
