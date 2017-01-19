<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <table width="100%" class="tb_normal">
    <tr>
      <th>二维码</th>
      <th>限粉丝</th>
      <th>应用站点</th>
      <th>创建时间</th>
      <th>关联消费积分</th>
      <th>操作</th>
    </tr>
    <s:iterator value="dto.source" var="p">
      <tr>
        <td>${p.name }</td>
        <td>
          <s:if test='#p.fensi=="Y"'>是</s:if>
          <s:else>否</s:else>
        </td>
        <td>${p.spage.sitename }</td>
        <td>
          <s:date name="#p.createtime" format="yyyy-MM-dd" />
        </td>
        <td>
          <s:if test='#p.type==1'>是 </s:if>
          <s:else>否</s:else>
        </td>
        <td>
          <a href="javascript:;" onclick="pjInvite('${p.id}')">选择</a>
        </td>
      </tr>
    </s:iterator>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
	function pjInvite(sourceid) {
		var index = layer.load();
		$.ajax({
			url : "/${oname}/setting/user_pjInvite.action",
			type : "post",
			data : {
				"pj.wxuid" : '${wxuid}',
				"pj.sourceid" : sourceid
			},
			cache : false,
			success : function(res) {
				console.log(res);
				if (res.errcode == 0) {
					layer.alert("邀请发送成功", function() {
						parent.layer.closeAll();
					});
				} else {
					layer.msg(res.errmsg, {
						icon : 5
					});
				}
				layer.close(index);
			}
		});
	}

	
</script>