<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
		$(function(){
       			var name = $("#nickname").val();
       			if(name == null || name == "") {
       				document.getElementById('nickname').value = '微博昵称搜索';
       			}
       		});		
	
	      function soClick(obj){
          if(obj.value=="微博昵称搜索"){
            obj.value =""; 
          }
      }
      	  function soBlur(obj){
          if(obj.value==""){
            obj.value ="微博昵称搜索"; 
          }
      }
        function findData(obj){
            	document.getElementById("pageId").value=1;
            	document.getElementById("findForm").submit();
            }
        function seekData(obj){
        	document.getElementById("page").value=1;
        	document.getElementById("seek").submit();
        }     
</script>
<div class="frame_content">
	<form action="/page/activity_participate_details.action" method="post" id="findForm">
		<s:hidden name="pageId" id="pageId"/>
		<input type="hidden" name="terminalName" value="<s:property value="%{terminalName}"/>">
		<input type="hidden" name="activityid" value="<s:property value="%{activityid}"/>">
	    <div>               
	    	互动时间段：<input id="starttime" type="text"
					name="visitTime1" class="Wdate"
					value="<s:property value="visitTime1"/>"
					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',startDate:'%y-%M-%d 00:00:01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />至 <input id="endtime" type="text"
					name="visitTime2" class="Wdate" value="<s:property value="visitTime2"/>"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />
	              来源：<s:select name="source" list="dto.source" listKey="name" listValue="name" headerKey="0" headerValue="全部"/>
	             <input type="button" value="查询" onclick="findData(this)"/>
	    </div>
	</form> 
	<form action="/page/activity_participate_details_by_nickname.action" method="post" id="seek">
		<s:hidden name="pageId" id="page"/>
		<input type="hidden" name="terminalName" value="<s:property value="%{terminalName}"/>">
		<input type="hidden" name="activityid" value="<s:property value="%{activityid}"/>">
	    <div>
	    	<s:textfield name="nickname" id="nickname" onclick="soClick(this)" onblur="soBlur(this)"/>
	    	<input type="button" value="搜索" onclick="seekData(this)"/>
	    </div>
	</form>
		<div>
			共${dto.pager.totalCount} 个参与
		</div>
	    <div>            
	        <table width="70%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
				<thead>
					<tr>
						<th>微博昵称</th>
						<th>互动时间</th>
						<th>互动IP</th>
						<th>来源</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="dto.activityLogList" var="s">
						<tr align="center" >
						    <td>${s.nickname}</td>
						    <td><s:date name="#s.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${s.ip}</td>
						    <td>${s.source}</td>
						</tr>	
					</s:iterator>
				</tbody>
			</table>
	    </div> 
</div>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>