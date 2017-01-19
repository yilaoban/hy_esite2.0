<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function xcMoveToGroup(xcid){
		var srcString = "/${oname}/interact/xcMoveToGroup.action?xcid=" +xcid+　"&utype=1";
		var	title="移动到组";
		layer.open({
				type : 2,
				area : ['400px','400px'],
				title : [title,true],
				content: srcString
		});
	}
	
</script>

<div class="wrap_content left_module">
	<div class="switch_tab_div pb10">
		<span><a href="/${oname}/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i><a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>现场签到</span>
		<p class="clearfix"></p>
	</div>
	 <div class="toolbar pb10">
  	<ul class="c_switch">
		  <li><a href="/${oname }/page/xcPerson.action?xcid=${xcid }">参会人员</a></li>
		  <li  class="selected"><a href="/${oname }/interact/checkin_detail.action?xcid=${xcid }&utype=1">现场签到</a></li>
		  <li><a href="/${oname }/interact/comment_detail.action?xcid=${xcid }&utype=1">现场评论</a></li>
		  <li><a href="/${oname }/interact/lottery_detail.action?xcid=${xcid }&utype=1">现场抽奖</a></li>
	  </ul>
	  	<a href="javascript:void(0);" onclick="xcMoveToGroup(${xcid})" class="button">移动到组</a>
	    <a href="/${oname }/page/xcSite.action?xcid=${xcid }" class="return" title="返回"></a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>用户昵称</th>
			<th>签到时间</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.checkinRecordList" var="s">
			<tr align="center" >
				<td align="center">${s.nickname }</td>
				<td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
