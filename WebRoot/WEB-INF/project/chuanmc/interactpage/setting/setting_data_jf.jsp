<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="wrap_content">
  <div class="toolbar">
    <ul class="c_switch">
      <li>
        <a href="/${oname}/setting/data_rmb.action">会员卡</a>
      </li>
      <li class="selected">
        <a href="/${oname}/setting/data_jf.action">积分</a>
      </li>
    </ul>
  </div>
  <div>
    <form action="/${oname}/setting/data_jf.action">
      <label>时间范围:</label>
      <input id="startTime" name="startTime" value="${startTime}" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
      <span>~</span>
      <input id="endTime" name="endTime" value="${endTime}" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
      <input type="submit" class="btn btn-primary" value=" 选择" />
    </form>
  </div>
  <table width="100%" class="tb_normal mt10 mb10">
    <thead>
      <tr>
        <th>总增长</th>
        <th>总消费</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${sum_add.intValue()}</td>
        <td>${sum_sub.intValue()}</td>
      </tr>
    </tbody>
  </table>
  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>入账时间</th>
        <th>入账类型</th>
        <th>入账积分</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.docList" var="d">
        <tr>
          <td>
            <s:date name="created" format="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${d.desc}</td>
          <td>${d.score}</td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>

<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

	});

	
</script>