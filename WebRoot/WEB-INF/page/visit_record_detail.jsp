<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<script type="text/javascript" src="/js/functions.js"></script>
<script type="text/javascript" src="/js/ajaxData.js"></script>
<div >
      <div>
        <p>微博昵称：${dto.user.nickname }</p>  
        <p>访问总数：${dto.num } </p>
        <p>粉丝情况：${dto.user.fensishu }</p>
      </div>
      <div >
             访问信息
           <input type="hidden" id="sgid" name="sgid" value="${sgid}">
           <input type="hidden" id="siteid" name="siteid" value="${siteid}">
           <input type="hidden" id="wbuid" name="wbuid" value="${wbuid}">
   <table id="detailtable" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
      <thead>
		<tr>
			<th>访问时间</th>
			<th>访问终端</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s" >
			<tr align="center">
				<td align="center">
				 	${s.visittime }
			    </td>
				<td align="center">
					<s:if test='#s.terminal=="A"'>PAD</s:if><s:elseif test='#s.terminal=="C"'>PC</s:elseif><s:elseif test='#s.terminal=="P"'>PHONE</s:elseif>
				</td>
			</tr>
		</s:iterator>
	</tbody>
  </table>
   </div>
   <%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
