<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test="data_select==0">class="selected"</s:if>><a href="appointment_data.action?aptid=${aptid }&data_select=0&redirectCb=${redirectCb}">微博数据</a></li>
	  <li <s:if test="data_select==1">class="selected"</s:if>><a href="appointment_data.action?aptid=${aptid }&data_select=1&redirectCb=${redirectCb}">微信数据</a></li>
	  <li <s:if test="data_select==2">class="selected"</s:if>><a href="appointment_data.action?aptid=${aptid }&data_select=2&redirectCb=${redirectCb}">用户数据</a></li>
	  <li <s:if test="data_select==-1">class="selected"</s:if>><a href="appointment_data.action?aptid=${aptid }&data_select=-1&redirectCb=${redirectCb}">匿名数据</a></li>
	  </ul>
	  <input type="button" class="btn btn-primary" onclick="dataShow('${aptid }','${oname }')"  value="配置"/>
	  <input type="button" class="btn btn-primary" onclick="dataExport('${aptid}','${data_select }','${oname }')"  value="导出"/>
	  <s:if test='data_select==1'>
	  	<a href="/${oname}/interact/appointmentMoveToGroup.action?aptid=${aptid }&gz_i=1" class="button">移动到组</a>
	  </s:if>
	  <s:if test="redirectCb!=0">
	  	<a href="/${oname }/interact/cbAptEdit.action?cbid=${redirectCb}" class="return" title="返回"></a>
	  </s:if>
	  <s:else>
		  <a href="/${oname }/interact/index.action?mid=10000" class="return" title="返回"></a>
	  </s:else>
 	<input type="hidden" value="${id }">
 	</div>
 	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
				<s:if test="data_select != -1">
					<th>用户名</th>
				</s:if>
				<s:if test="data_select == 0">
					<th>微博昵称</th>
				</s:if>
				<s:elseif test="data_select == 1">
					<th>微信昵称</th>
				</s:elseif>
				<s:elseif test="data_select == 2">
					<th>微博昵称</th>
					<th>微信昵称</th>
				</s:elseif>
				<s:iterator value="dto.mapping" var="m">
					<th>${m.name }</th>
				</s:iterator>
				<th>提交时间</th>
				<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s">
			<tr align="center" >
				<s:if test="data_select != -1">
					<td>${s.tel }</td>
				</s:if>
				<s:if test="data_select == 0 || data_select == 1">
					<td>${s.nickname }</td>
				</s:if>
				<s:elseif test="data_select == 2">
					<td>${s.nickname }</td>
					<td>${s.wxnickname }</td>
				</s:elseif>
				<s:iterator value="list" var="t" status="st">
					<s:if test="#st.first">
						<td>
						<a href="javascript:showAptDetail('${oname }','${s.id}')">
							<s:if test='dto.mapping[#st.index].stype=="I"'>
								<s:if test='t!=""'>
									<img src="${t }" height="100px"/>
								</s:if>
								<s:else>
									<img src="/images/nopic.png" height="100px"/>
								</s:else>
							</s:if>
							<s:else>${t}</s:else>
						</a>
						</td>
					</s:if>
					<s:else>
						<td>
							<s:if test='dto.mapping[#st.index].stype=="I"'><img src="${t }" height="100px"/></s:if>
							<s:else>${t}</s:else>
						</td>
					</s:else>
				</s:iterator>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center"><a href="javascript:showAptDetail('${oname }','${s.id}')">查看详情</a><i class="split">|</i>
				<a href="javascript:void(0)" onclick="del_apt(${s.id },'${oname}')">删除</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	<div id="exportHtml" style="display: none">
		<p>导出开始时间:<input type="text" id="start" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate"></p>
		<p>导出结束时间:<input type="text" id="end" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate"></p>
	</div>