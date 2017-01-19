<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:if test="dto.cc.size()>0">
  <form id="form1" class="toolbar">
    <input type="hidden" name="cbid" value="${cbid}" />
    <label>新闻目录：</label>
    <select id="cc" name="ccid">
      <option value="0">未选择</option>
      <s:iterator value="dto.cc" var="c">
        <option value="${c.id}" <s:if test="ccid==#c.id">selected="selected"</s:if>>${c.name}</option>
      </s:iterator>
    </select>
  </form>
</s:if>
<s:else>没有已发布的新闻页面</s:else>
<s:if test="dto.news.size()>0">
  <table width="100%" class="tb_normal">
    <tr>
      <td>标题</td>
      <td>图片</td>
      <td>操作</td>
    </tr>
    <s:iterator value="dto.news" var="n">
      <tr>
        <td>${n.title }</td>
        <td>
          <img src="${imgDomain }${n.simgurl }" />
        </td>
        <td>
          <a href="javascript:;" onclick="choose('${n.id}','${n.title}')">选择</a>
        </td>
      </tr>
    </s:iterator>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</s:if>
<script type="text/javascript">
	$(document).ready(function() {
		$("#cc").change(function() {
			$("#form1").submit();
		});
	});

	function choose(newsid, title) {
		$("#enid", window.parent.document).val(newsid);
		$("#m_title", window.parent.document).html(title);
		parent.layer.closeAll();
	}
</script>
