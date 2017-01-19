<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	  <div class="toolbar pb10">
	  	<ul class="c_switch">
		  <li class="selected"><a href="">积分规则</a></li>
		  </ul>
		  <a href="/${oname }/interact/index.action?mid=10009" class="return" title="返回"></a>
		</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<td>类型</td>
				<td>积分数</td>
				<td>描述</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.list" var="s">
				<tr>
					<td>
						<s:if test='type=="GUZ"'>关注</s:if>
						<s:elseif test='type=="FNX"'>分享</s:elseif>
						<s:elseif test='type=="ZHF"'>转发</s:elseif>
						<s:elseif test='type=="COM"'>评论</s:elseif>
						<s:elseif test='type=="CHN"'>签到</s:elseif>
						<s:elseif test='type=="ZAN"'>赞</s:elseif>
						<s:elseif test='type=="SUB"'>订阅</s:elseif>
					</td>
					<td>${s.addnum }</td>
					<td>${s.hydesc }</td>
					<td><a href="addRule.action?ownerwbuid=${ownerwbuid }&id=${s.id }&mid=10009">编辑</a>&nbsp;<a href="javascript:jifendel(${s.id })">删除</a></td>
				</tr>
				</s:iterator>
		</tbody>
	</table>
 </div>