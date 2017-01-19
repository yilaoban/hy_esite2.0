<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
  <a href="/${oname}/interact/marketing_activity.action" class="return" title="返回"></a>
  <form id="form1" class="formview">
    <input type="hidden" value="${cbaid }" name="cbaid" />
    <s:set name="pid" value="pageid"></s:set>
    <dl>
      <dt>活动名称</dt>
      <dd>${dto.current.title}</dd>
    </dl>
    <dl>
      <dt>活动页面</dt>
      <dd>${dto.current.m_title}</dd>
    </dl>
    <dl>
      <dt>排序</dt>
      <dd>
        <input type="radio" name="ord" value="s" <s:if test='ord=="s"'>checked="checked"</s:if> />
        转发
        <input type="radio" name="ord" value="c" <s:if test='ord=="c"'>checked="checked"</s:if> />
        点击
        <input type="radio" name="ord" value="h" <s:if test='ord=="h"'>checked="checked"</s:if> />
        互动
        <input type="radio" name="ord" value="g" <s:if test='ord=="g"'>checked="checked"</s:if> />
        关注
        <input type="radio" name="ord" value="w" <s:if test='ord=="w"'>checked="checked"</s:if> />
        外部
      </dd>
    </dl>
    <dl>
      <dt>传播时间段</dt>
      <dd>
        <input type="text" placeholder="请选择开始时间" id="start" value="${starttime }" name="starttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
        至
        <input type="text" placeholder="请选择结束时间" id="end" value="${endtime }" name="endtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
        <input type="submit" class="btn btn-info" value="搜索" />
      </dd>
    </dl>
  </form>
  <table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
    <tr>
      <td>姓名</td>
      <td>被转发</td>
      <td>被点击</td>
      <td>产生互动</td>
      <td>产生关注</td>
      <td>产生外部事件</td>
    </tr>
    <s:iterator value="dto.implList" var="l">
      <tr>
        <td>${l.name }</td>
        <td>${l.zhuanfa }</td>
        <td>${l.dianji }</td>
        <td>${l.hudong }</td>
        <td>${l.guanzhu }</td>
        <td>${l.waibuxiaoguo }</td>
      </tr>
    </s:iterator>
  </table>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("input[name='ord']").click(function() {
			$("#form1").submit();
		});

	});

	
</script>