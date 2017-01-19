<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <form id="form1" class="left mb10">
    <label>开始时间</label>
    <input type="text" id="start" name="starttime" value="${starttime }" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" class="Wdate">
    <span>~</span>
    <input type="text" id="end" name="endtime" value="${endtime }" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd',lang:'zh-cn'})" class="Wdate">
    <label>类型</label>
    <select name="status">
      <option value="0" <s:if test="status==0">selected</s:if>>不限</option>
      <option value="1" <s:if test="status==1">selected</s:if>>增长</option>
      <option value="-1" <s:if test='status==-1'>selected</s:if>>消费</option>
    </select>
    <input type="submit" value="搜索" class="btn btn-success btn-sm" />
  </form>
  <button class="btn btn-primary right" onclick="daochu()">导出数据</button>
  <table width="100%" class="tb_normal mt10 mb10">
    <thead>
      <tr>
        <s:if test="status!=-1">
          <th>总增长</th>
        </s:if>
        <s:if test="status!=1">
          <th>总消费</th>
        </s:if>
      </tr>
    </thead>
    <tbody>
      <tr>
        <s:if test="status!=-1">
          <td>${dto.sum_add.intValue()}</td>
        </s:if>
        <s:if test="status!=1">
          <td>${dto.sum_sub.intValue()}</td>
        </s:if>
      </tr>
    </tbody>
  </table>
  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>昵称</th>
        <th>性别</th>
        <th>时间</th>
        <th>途径</th>
        <th>详细</th>
        <th>金币</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.docList" var="d">
        <tr>
          <td>${d.nickname}</td>
          <td>${d.sex}</td>
          <td>
            <s:date name="created" format="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>${d.desc}</td>
          <td>${d.detail}</td>
          <td>${d.score}</td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
	function daochu() {
		window.location = "/${oname}/cd-report/jfExport.action?" + $('#form1').serialize();
	}

	
</script>