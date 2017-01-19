<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function showlog(wxuid,sourceid){
		var srcString ='/${oname}/interact/offCheckLog.action?wxuid='+wxuid+"&sourceid="+sourceid;
		var title="签到详情";
		layer.open({
				type : 2,
				area : ['650px','440px'],
				title : [title,true],
				content: srcString
			});
	}
</script>
<div class="wrap_content">
<div class="toolbar mt20">
	<form>
		来源:<select name="sift.sourceid">
			<option value="0">不限</option>
			<s:iterator value="dto.source" var="s">
				<option value="${s.id }" <s:if test="#s.id==sift.sourceid[0]">selected="selected"</s:if>>${s.name }</option>
			</s:iterator>
		</select>
		&nbsp;&nbsp;&nbsp;
		最近签到时间段:<input type="text" name="sift.starttime" id="start" value="${sift.starttime[0] }" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">~<input type="text" name="sift.endtime" value="${sift.endtime[0] }" id="end" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
		<input type="submit" value="搜索" class="btn btn-info btn-sm"/>
	</form>
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
			<tr>
				<th>二维码</th>
				<th>昵称</th>
				<th>签到次数</th>
				<th>最近签到时间</th>
				<th>最近签到区域</th>
				<th>详情</th>
			</tr>
		<s:iterator value="dto.records" var="p">
			<tr>
				<td>${p.source.name }</td>
				<td><a href="javascript:editAptDetail('${oname }','${dto.ofc.aptid }','${p.wxuid }')">${p.wxUser.nickname }</a></td>
				<td>${p.vistnum }</td>
				<td><s:date name="#p.lastvisttime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${p.lastarea }</td>
				<td>
					<a href="javascript:showlog(${p.wxuid },${p.sid })">查看</a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>