<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test="type==0">class="selected"</s:if>><a href="/${oname }/interact/csData.action?csid=${csid }&mid=10013&type=0">微博数据</a></li>
	  <li <s:if test="type==1">class="selected"</s:if>><a href="/${oname }/interact/csData.action?csid=${csid }&mid=10013&type=1">微信数据</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10013" class="return" title="返回"></a>
	</div>
  <div>
 	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<th>IP</th>
			<th>来源(昵称)</th>
			<th>发起时间</th>
			<th>终端</th>
			<th>操作</th>
		</tr>
		<s:iterator value="dto.list" var="l">
			<tr>
				<td>${l.ip }</td>
				<td>
					<s:if test="type==0">
							微博<s:if test="nickname!=null">(${l.nickname })</s:if>
						</s:if>
					<s:elseif test="type==1">
							微信<s:if test="nickname!=null">(${l.nickname })</s:if>
					</s:elseif>
				</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<s:if test='terminal=="P"'>手机</s:if>
					<s:elseif test='terminal=="A"'>PAD</s:elseif>
					<s:elseif test='terminal=="C"'>PC</s:elseif>
					<s:else>其他</s:else>
				</td>
				<td><a href="javascript:csDetail('${imgDomain }${l.jcontent.img }','${l.jcontent.name }','${l.jcontent.sign }','${l.jcontent.content }')">传播详情</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	</div>
	<div id="csDetail" class="imgblock" style="margin-top: 30px;display: none">
		<table>
			<tr>
				<td>图片</td>
				<td><img id="var_img" src="" height="100px"/></td>
			</tr>
			<tr>
				<td>对方称呼</td>
				<td><span id="name"></span></td>
			</tr>
			<tr>
				<td>内容</td>
				<td><span id="content"></span></td>
			</tr>
			<tr>
				<td>署名</td>
				<td><span id="sign"></span></td>
			</tr>
		</table>
	</div>