<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function cpzindex(wxuid,wayid){
	var srcString = '/${oname }/cd-report/cpzIndex.action?wxuid=' + wxuid + "&wayid=" +wayid;
	var title="下线明细";
	layer.open({
			type : 2,
			area : ['600px','450px'],
			title : [title,true],
			content: srcString
	});
}
</script>
<div class="wrap_content">
	<div class="toolbar pb20">
	<form action="" method="post">
		<div class="left">
		开始时间:<input type="text" name="starttime" value="${starttime }" placeholder="请选择开始时间"  id="start" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
		结束时间:<input type="text" name="endtime" value="${endtime }" placeholder="请选择结束时间"  id="end" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endchange()},lang:'zh-cn'})" class="Wdate">
		<input type="submit" class="btn btn-primary btn-sm" value="查询"/>
		</div>
	</form>
	</div>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<s:iterator value="dto.list" var="r">
			<tr>
				<td colspan="4" style="background:#eee;"><span style="float:left">省级渠道:${r.way.name }</span> <span style="float:right">县级渠道总数:${r.list.size() }</span></td>
				<s:if test="#r.list.size()>0">
					<tr>
						<td>县级渠道名称</td>
						<td>发展时间</td>
						<td>下线数</td>
						<td>操作</td>
					</tr>
					<s:iterator value="#r.list" var="dl">
						<tr>
							<td>${dl.name }</td>
							<td><s:date name="#dl.createtime" format="yyyy-MM-dd"/></td>
							<td>${dl.cpzList.size() }</td>
							<td><a href="javascript:cpzindex('${dl.wxuid }','${r.way.id}')">详情</a></td>
						</tr>
					</s:iterator>
				</s:if>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>