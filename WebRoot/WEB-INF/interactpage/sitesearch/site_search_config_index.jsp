<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<table id="table" class="tb_normal" width="100%" border="1" cellspacing="0" cellpadding="0">
  <thead>
    <tr>
      <th>站点</th>
      <th>操作</th>
    </tr>
  </thead>
  <tbody>
    <s:iterator value="dto.sg_list" var="l">
      <tr>
        <td>${l.groupname}</td>
        <td>
          <s:if test="entityid>0">已添加</s:if>
          <s:else>
            <a href="javascript:;" onclick="addIndex('${l.id}')">添加</a>
          </s:else>
        </td>
      </tr>
    </s:iterator>
  </tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
<script type="text/javascript" src="/js/bootbox.min.js"></script>
<script>
	function addIndex(sitegroupid) {
		$.ajax({
			url : "/${oname}/interact/site_search_index_add.action",
			data : {
				"ssi.sitegroupid" : sitegroupid
			},
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("添加成功", function() {
						window.location.reload();
					});
				} else {
					layer.alert("添加失败",0);
				}
			}
		});
	}

	
</script>