<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">签到数据</a></li>
	  </ul>
	 <s:if test="utype==0">
	  	<a href="/${oname }/interact/index.action?mid=10008" class="return" title="返回"></a>
	 </s:if>
	 <s:elseif test="utype==1">
	 	<a href="/${oname }/interact/wxCheckin.action?mid=10008" class="return" title="返回"></a>
	 </s:elseif>	
	</div>
	<div style="width: 80%; height: 10%; float: left">
		共${dto.pager.totalCount }用户
	</div>
	<div style="width: 80%; height: 10%; float: left; margin-bottom: 20px">
	</div>
	<table width="100%" class="tb_normal" border="1" cellspacing="1"
		cellpadding="1">
		<thead>
			<tr>
				<th>
					昵称
				</th>
				<th>
					签到次数
				</th>
				<th>
					最后签到时间
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.record" var="l">
				<tr align="center">
					<td align="center">
						${l.nickName }
					</td>
					<td align="center">
						${l.total }
					</td>
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
