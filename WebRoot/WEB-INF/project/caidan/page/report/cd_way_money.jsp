<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function daochu(){
		url="/${oname}/cd-report/export.action?"+$('#form1').serialize();
		window.location=url;
		
		
	}
</script>
<div class="wrap_content">
<div class="toolbar pb20">
	<form id="form1">
	<div class="left">
	开始时间<input type="text" id="start" name="sift.starttime" value="${sift.starttime }" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
	结束时间<input type="text" id="end" name="sift.endtime" value="${sift.endtime }" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
	渠道分类<select name="sift.catid">
			<option value="0" <s:if test="sift.catid==#c.id">selected="selected"</s:if>>不限</option>
			<s:iterator value="dto.catalogs" var="c">
			<option value="${c.id }" <s:if test="sift.catid==#c.id">selected="selected"</s:if>>${c.name }</option>
			</s:iterator>
		</select>
	渠道名称<input type="text" name="sift.wayname" value="${sift.wayname }">
	</div>
	<input type="submit" value="搜索" class="btn btn-primary btn-sm">
	</form>
	</div>
	<div class="toolbar pb20">
	<input type="button" value="导出数据" class="btn btn-primary" onclick="daochu()"/>
	</div>
	<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
		<tr>
			<td>兑换时间</td>
			<td>渠道名称</td>
			<td>省级<br>渠道经理</td>
			<td>县级<br>管理员</td>
			<td>彩票站</td>
			<td>微信昵称</td>
			<td>兑换券</td>
			<td>兑换金额</td>
			<td>提成金额</td>
			<td>县级<br>提成金额</td>
		</tr>
		<s:iterator value="dto.wkq"  var="l">
		<tr>
			<td><s:date name="#l.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${l.way.name }</td>
			<td>${sj.nickname }</td>
			<td>${dl.name }</td>
			<td>${cpz.name }</td>
			<td>${wxuser.nickname }</td>
			<td>
				<s:if test='#rmb.product.name.length()<10'>${rmb.product.name }</s:if><s:else><span title="${rmb.product.name }">${fn:substring(rmb.product.name, 0, 10)}...</span></s:else>
			</td>
			<td>${rmb.rmb/100 }(元)</td>
			<td>${rmb.zdrmb/100 }(元)</td>
			<td>${rmb.xjrmb/100 }(元)</td>
		</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>