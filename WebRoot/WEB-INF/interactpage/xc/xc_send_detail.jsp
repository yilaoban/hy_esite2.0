<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<div class="switch_tab_div pb10">
		<span><a href="/${oname}/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i><a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>参会人员</span>
		<p class="clearfix"></p>
	</div>
  <div class="toolbar pb10">
  	<ul class="c_switch">
		  <li class="selected"><a href="/${oname }/page/xcPerson.action?xcid=${xcid }">参会人员</a></li>
		  <li><a href="/${oname }/interact/checkin_detail.action?xcid=${xcid }&utype=1">现场签到</a></li>
		  <li><a href="/${oname }/interact/comment_detail.action?xcid=${xcid }&utype=1">现场评论</a></li>
		  <li><a href="/${oname }/interact/lottery_detail.action?xcid=${xcid }&utype=1">现场抽奖</a></li>
	  </ul>
	   <a href="/${oname }/page/xcSite.action?xcid=${xcid }" class="return" title="返回"></a>
	</div>
	<form action="" method="get">
			<input type="hidden" name="xcid" value="${xcid }">
		姓名:<input type="text" name="username" value="${username }"/>
		是否打开:
			<select name="siftDto.isInvited">
				<option value="A" <s:if test='siftDto.isInvited=="A"'>selected="selected"</s:if>>全部</option>
				<option value="Y" <s:if test='siftDto.isInvited=="Y"'>selected="selected"</s:if>>已打开</option>
				<option value="N" <s:if test='siftDto.isInvited=="N"'>selected="selected"</s:if>>未打开</option>
			</select>
		是否签到:
			<select name="siftDto.isChecked">
				<option value="A" <s:if test='siftDto.isChecked=="A"'>selected="selected"</s:if>>全部</option>
				<option value="Y" <s:if test='siftDto.isChecked=="Y"'>selected="selected"</s:if>>已签到</option>
				<option value="N" <s:if test='siftDto.isChecked=="N"'>selected="selected"</s:if>>未签到</option>
			</select>
		<input type="submit" value="查找"/>
	</form>
	<a href="javascript:xcupload(${xcid })">添加</a>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>姓名</th>
			<th>昵称</th>
			<th>创建时间</th>
			<th>是否打开</th>
			<th>是否签到</th>
			<th>操作</th>
			<th>申请详情</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
				<td align="center">${s.username }</td>
				<td align="center"><a href="javascript:void(0)" onclick="changeSdNickname(${s.id},'${s.nickname }')">${s.nickname }</a></td>
				<td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center"><s:if test='invited=="Y"'>是</s:if><s:else>否</s:else></td>
				<td align="center"><s:if test='checked=="Y"'>是</s:if><s:else>否</s:else></td>
				<td align="center">
					<a href="javascript:void(0)" onclick="delXcSd(${s.id},'${s.nickname }')">删除</a>
				</td>
				<td>
					<s:if test="dto.relationMap[#s.id]!=null"><a href="javascript:showAptDetail('${oname }','${dto.relationMap[s.id] }')">查看详情</a></s:if>
				</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
