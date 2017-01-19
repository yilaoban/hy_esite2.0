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
	<form action="/page/non_anonVisit_data_report.action" method="post" id="findForm">
		<s:hidden name="pageId" id="pageId"/>
		<input type="hidden" name="activityid" value="<s:property value="%{activityid}"/>">
	    <div>               
	    	<input type="hidden" name="sitegroupid" value="<s:property value="%{sitegroupid}"/>">  
	    	访问时间段：<input id="starttime" type="text"
					name="visitTime1" class="Wdate"
					value="<s:property value="visitTime1"/>"
					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',startDate:'%y-%M-%d 00:00:01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />至 <input id="endtime" type="text"
					name="visitTime2" class="Wdate" value="<s:property value="visitTime2"/>"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />
	      <!--    访问时间段：<s:textfield name="visitTime1"/>至<s:textfield name="visitTime2"/>-->                         
	        终端：
	        	<select name="terminal">
	        		<option value="0">全部</option>
						<s:iterator value="dto.terminal" var="f">
						  <option value ="${f.name}">
						  	<s:if test='#f.name == "C"'>PC</s:if>
						  	<s:elseif test='#f.name == "P"'>PHONE</s:elseif>
						  	<s:elseif test='#f.name == "A"'>PAD</s:elseif>
						  </option>
						</s:iterator>  
					</select>
	              来源：<s:select name="source" list="dto.source" listKey="name" listValue="name" headerKey="0" headerValue="全部"/>
	             <input type="button" value="查询" onclick="findData(this)"/>
	    </div>
	</form> 
	<form action="/page/nonAnonVisit_data_report.action" method="post" id="seek">
		<s:hidden name="pageId" id="page"/>
		<input type="hidden" name="activityid" value="<s:property value="%{activityid}"/>">
	    <div>
	   		<input type="hidden" name="sitegroupid" value="<s:property value="%{sitegroupid}"/>">    
	    	<s:textfield name="nickname" id="nickname" onclick="soClick(this)" onblur="soBlur(this)"/>
	    	<input type="button" value="搜索" onclick="seekData(this)"/>
	    </div>
	</form>
		<div>
			共${dto.pager.totalCount} 个搜索
		</div>
	    <div>            
	        <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
				<thead>
					<tr>
						<th>微博昵称</th>
						<th>访问时间</th>
						<th>访问IP</th>
						<th>终端</th>
						<th>来源</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="dto.vistLogList" var="s">
						<tr align="center" >
						    <td>${s.nickname}</td>
						    <td><s:date name="#s.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${s.ip}</td>
						    <td><s:if test='#s.terminal=="A"'>PAD</s:if><s:elseif test='#s.terminal=="C"'>PC</s:elseif><s:elseif test='#s.terminal=="P"'>PHONE</s:elseif></td>
						    <td>${s.source}</td>
						</tr>	
					</s:iterator>
				</tbody>
			</table>
	    </div> 
</div>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>