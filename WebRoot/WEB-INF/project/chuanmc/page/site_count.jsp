<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="toolbar pb10">
	<span class="breadcumb">微博PAGE >> ${dto.sitegroup.groupname}</span>
	<div class="clearfix"></div>
</div>
<s:if test="dto.siteCountList.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th width="20%">访问人数</th>
			<th width="20%">匿名</th>
			<th width="20%">最近更新</th>
			<th width="20%">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.siteCountList" >
			<tr align="center">
				<td>
				${visteNum}
				</td>
				<td>
				${type}
				</td>
				<td>
				${updateTime}
				</td>
				<td>
					<a href="javascript:void(0);" onclick="showSiteCountDetail(${dto.sitegroup.id},'${type}')">查看明细</a>&nbsp;
					<s:if test='type=="是"'>
						<a href="exportSiteCountDetail.action?sitegroupid=${dto.sitegroup.id }&type=Y">导出</a>
					</s:if><s:elseif test='type=="否"'>
						<a href="exportSiteCountDetail.action?sitegroupid=${dto.sitegroup.id }&type=N">导出</a>
					</s:elseif>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

</s:if>
<s:else>
	该site没有统计记录
</s:else>
<div id="feature_div">
</div>
</div>