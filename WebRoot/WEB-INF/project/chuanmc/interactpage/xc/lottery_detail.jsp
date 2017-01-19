<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<div class="switch_tab_div pb10">
		<span><a href="/${oname}/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i><a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>现场抽奖</span>
		<p class="clearfix"></p>
	</div>
	 <div class="toolbar pb10">
  	<ul class="c_switch">
		  <li><a href="/${oname }/page/xcPerson.action?xcid=${xcid }">参会人员</a></li>
		  <li><a href="/${oname }/interact/checkin_detail.action?xcid=${xcid }&utype=1">现场签到</a></li>
		  <li><a href="/${oname }/interact/comment_detail.action?xcid=${xcid }&utype=1">现场评论</a></li>
		  <li class="selected"><a href="/${oname }/interact/lottery_detail.action?xcid=${xcid }&utype=1">现场抽奖</a></li>
	  </ul>
	   <a href="/${oname }/interact/export_xc.action?xcid=${xcid }">导出</a>
	    <a href="/${oname }/page/xcSite.action?xcid=${xcid }" class="return" title="返回"></a>
	</div>
	<form action="">
		<input type="hidden" name="xcid" value="${xcid }"/>
		<input type="hidden" name="mid" value="${mid }"/>
		<input type="hidden" name="utype" value="${utype }"/>
		昵称:<input type="text" name="nickname" value="${nickname }"/>
		是否中奖:<select name="topType">
					<option value="A" <s:if test='topType=="A"'>selected="selected"</s:if>>全部</option>
					<option value="Y" <s:if test='topType=="Y"'>selected="selected"</s:if>>中奖</option>
					<option value="N" <s:if test='topType=="N"'>selected="selected"</s:if>>未中奖</option>
				</select>
		<input type="submit" value="搜索"/>
	</form>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>用户昵称</th>
			<th>创建日期</th>
			<th>参与次数</th>
			<th>开始次数</th>
			<th>中奖</th>
			<th>ip</th>
			<th>终端</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.cjRecordList" var="s">
			<tr align="center" >
				<td align="center">${s.nickname }</td>
				<td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">${s.joinnum }</td>
				<td align="center">${s.startnum }</td>
				<td align="center">
					<s:if test='#s.top == "Y"'>中奖</s:if>
					<s:elseif test='#s.top == "N"'>未中奖</s:elseif>
				</td>
				<td align="center">${s.ip }</td>
				<td align="center">
					<s:if test='#s.terminal == "P"'>PHONE</s:if>
					<s:elseif test='#s.terminal == "C"'>PC</s:elseif>
					<s:elseif test='#s.terminal == "A"'>PAD</s:elseif>
				</td>		
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
