<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="toolbar pb10">
  <form action="/${oname}/interact/findMaterial.action" method="post" class="formview jNice">
    <input type="hidden" name="aid" value="${aid }">
    <input type="text" placeholder="请输入标题" name="title" class="text-medium">
    <input type="submit" class="btn btn-info btn-sm" value="搜索">
  </form>
</div>
<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
  <thead>
    <tr>
      <th>标题</th>
      <th>图片</th>
      <th>操作</th>
    </tr>
  </thead>
  <tbody>
    <s:iterator value="dto.wxPageShowList" var="f">
      <tr align="center">
        <td>${f.title}</td>
        <td>
          <img src="${f.pic}" height="67px" width="67px" />
        </td>
        <td>
          <a href="javascript:;" onclick="choose('${f.pageid}','${f.title}')">选择</a>
        </td>
      </tr>
    </s:iterator>
  </tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
<script type="text/javascript">
	function choose(pageid, title) {
		$("#enid", window.parent.document).val(pageid);
		$("#m_title", window.parent.document).html(title);
		parent.layer.closeAll();
	}
</script>
