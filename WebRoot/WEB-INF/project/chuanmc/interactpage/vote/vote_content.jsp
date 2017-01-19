<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
<div class="toolbar pb10">
  <ul class="c_switch">
  <li class="selected"><a href="">投票内容</a></li>
  </ul>
  <s:if test="redirectXc!=0">
	  <a class="btn btn-primary" href="/${oname }/interact/add_new_vote.action?voteid=${voteid }&type=${type }&redirectXc=${redirectXc }">新增投票内容</a>
	  <a href="/${oname }/interact/edit_xcLottery.action?xcid=${redirectXc}" class="return" title="返回"></a>
  </s:if>
  <s:else>
	<a class="btn btn-primary" href="/${oname }/interact/add_new_vote.action?voteid=${voteid }&type=${type }" >新增投票内容</a>
  	<a href="/${oname }/interact/index.action?mid=10002" class="return" title="返回"></a>
  </s:else>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>投票标题</th>
			<th>图片展示</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.list" var="s" status="st">
			<tr align="center" >
		     	<td align="center">${s.content }</td>
		      	<td align="center"><s:if test='#s.img == null || #s.img ==""'><img src="/images/error.gif" width="100" height="80"></s:if><s:else><img src="${imgDomain }${s.img }" width="100" height="80"></s:else></td>
		      	<td align="center"><a href="/${oname }/interact/find_one_option.action?id=${s.id }&type=${type }">编辑</a><i class="split">|</i>
				<input id="idx${st.count }" value="${s.idx }" type="hidden"/>
		      	<s:if test="#st.count>1"><a href="javascript:void(0);" onclick="up_content('${s.id }',${voteid },'${s.idx }','idx${st.count-1 }','${oname}')">上移</a><i class="split">|</i></s:if>
				<s:if test="#st.count < dto.list.size"><a href="javascript:void(0);" onclick="down_content('${s.id }',${voteid },'${s.idx }','idx${st.count+1 }','${oname}')">下移</a><i class="split">|</i></s:if>
		      	<a href="javascript:void(0);" onclick="del_content(${s.id })">删除</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>

