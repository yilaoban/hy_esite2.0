<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">口碑营销内容管理</a></li>
	  </ul>
	    <s:if test='dto.sm.type=="SRE"'>
	  <a href="/${oname }/interact/add_spread_option.action?id=${id }&mid=${mid }" class="button">添加内容</a>
	  </s:if>
	  <a href="/${oname }/interact/index.action?mid=${mid }" class="return" title="返回"></a>
	</div>
   <form action="update_spread_wbid.action" method="post" name="myform" class="formview jNice">
   <input type="hidden" name="spreadid" value="${id }">
   <input type="hidden" name="id" value="${dto.sp.id }">
  <p>活动类型：<s:if test='dto.sm.type=="SRE"'>直发</s:if>
  <s:elseif test='dto.sm.type == "FWD"'>转发<p>微博url：<input type="text" style="width: 300px" id="wbid" name="sp.wbid" onblur="checkWbid()" value="${dto.sp.wburl }"><span id="wbid_"></span></p><p><input type="button" value="确认" onclick="checkin()"></p></s:elseif>
  <s:elseif test='dto.sm.type=="FAC"'>转发并评论<p>微博url：<input type="text" style="width: 300px" id="wbid" name="sp.wbid" onblur="checkWbid()" value="${dto.sp.wburl }"><span id="wbid_"></span><p><input type="button" value="确认" onclick="checkin()"></p></s:elseif></p>
  </form>
  <s:if test='dto.sm.type=="SRE"'>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>序号</th>
			<th>标题</th>
			<th>内容</th>
			<th>图片</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="dto.list" var="s" status="st">
			<tr align="center" >
			     <td align="center">${st.count }</td>
			     <td align="center">${s.title }</td>
			     <td align="center">${s.content }</td>
			     <td align="center"><s:if test='#s.pic == ""'><img src="/images/error.gif" width="100" height="80"></s:if><s:else><img src="${imgDomain }${s.pic }" width="100" height="80"></s:else></td>
			      <td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
			      <td align="center"><a href="/${oname }/interact/update_spread_option_pre.action?spreadid=${s.id }&mid=10010">编辑</a><i class="split">|<a href="javascript:void(0);" onclick="del_spread(${s.id })">删除</a><i class="split">|</td>
			</tr>
		</s:iterator>
	</tbody>
	</table> 
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	</s:if>
</div>
<input type="hidden" id="result" value="${result }"/>
<script type="text/javascript">
	$(document).ready(function() {
          var result=$("#result").val();
			if(result.length>0){
				alert(result);
			}
        });
</script>
