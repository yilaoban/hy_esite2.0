<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function daochu(){
		url="/${oname}/cd-user/export.action?"+$('#form1').serialize();
		window.location=url;
		
		
	}
</script>
<div class="wrap_content">
  <form action="/${oname}/cd-user/index.action" class="pb10" id="form1">
    <label>微信昵称:</label>
    <input name="hyUser.nickname" class="s_name" value="${hyUser.nickname}" />
    <label>电话号码:</label>
    <input name="hyUser.telphone" class="s_phone" value="${hyUser.telphone}" />
    <input type="submit" class="btn btn-primary" value="筛选" />
    <a href="/${oname}/cd-user/index.action" class="btn btn-success">重置</a>
    <input type="button" value="导出数据" style="float: right;" class="btn btn-primary" onclick="daochu()"/>
  </form>
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>用户</th>
        <th>微信昵称</th>
        <th>性别</th>
        <th>区域</th>
        <th>电话号码</th>
        <th>关注时间</th>
        <th>金币数</th>
        <th>兑换券</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.list" var="l" status="st">
        <tr>
          <td>
            <img src="${l.wxUser.headimgurl}" style="width: 50px; height: 50px;" />
          </td>
          <td>${l.wxUser.nickname}</td>
          <td>
            <s:if test="wxUser.sex==1">男</s:if>
            <s:elseif test="wxUser.sex==0">女</s:elseif>
            <s:else>未知</s:else>
          </td>
          <td>${l.wxUser.province}${l.wxUser.city}</td>
          <td>${l.telphone}</td>
          <td>
            <s:date name="wxUser.subscribe_time" format="yyyy-MM-dd HH:mm:ss" />
          </td>
          <td>
            <s:if test="balance_total>0">
              <a href="javascript:balance('${l.id}');">${l.balance_total-l.balance_used}</a>
            </s:if>
            <s:else>0</s:else>
          </td>
          <td>
            <a href="javascript:order('${l.id}');">明细</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script>
	$(document).ready(function() {
	});

	function balance(id) {
		layer.open({
			type : 2,
			area : [ '800px', '600px' ],
			title : "金币明细",
			content : "/${oname}/cd-user/balance.action?hyUser.id=" + id
		});
	}

	function order(id) {
		layer.open({
			type : 2,
			area : [ '800px', '600px' ],
			title : "兑换券详情",
			content : "/${oname}/cd-user/order.action?hyUser.id=" + id
		});
	}

	
</script>