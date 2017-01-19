<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<style>
.s_box {
	position: relative;
}

.s_ipt {
	width: 400px;
	height: 29px;
}

.s_item {
	margin: 10px 0;
	overflow: hidden;
}

.s_item a {
	text-decoration: underline;
	color: #00c;
}

.s_item img {
	width: 200px;
	margin-right: 10px;
	float: left;
}

a:hover {
	text-decoration: none;
	color: #00A0E9
}

.s_item b {
	color: #c00;
}

.s_del {
	width: 32px;
	height: 32px;
	background:
		url(https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/global/img/quickdelete_9c14b01a.png)
		no-repeat;
	background-position: 10px 10px;
	display: inline-block;
	position: absolute;
	left: 371px;
}
</style>

<div class="wrap_content">
  <div class="s_box">
    <input type="text" id="text" class="s_ipt" value="${wd}" />
    <a href="javascript:;" id="quickdelete" class="s_del" title="清空" onclick="quickdelete()"></a>
    <button class="btn btn-default" onclick="search()">搜索</button>
  </div>
  <s:iterator value="dto.docs" var="d">
    <div class="s_item">
      <h5>
        <a href="${d.url}" target="_blank">${d.titlecn}${d.titleen}</a>
      </h5>
      <s:if test="#d.simgurl_s!=null">
        <img src="${d.simgurl_s}" />
      </s:if>
      <s:iterator value="#d.desccn" var="desc">
        <p>${desc}</p>
      </s:iterator>
      <s:iterator value="#d.descen" var="desc">
        <p>${desc}</p>
      </s:iterator>

      <s:iterator value="#d.cntcn" var="cnt">
        <p>${cnt}</p>
      </s:iterator>
      <s:iterator value="#d.cnten" var="cnt">
        <p>${cnt}</p>
      </s:iterator>
    </div>
  </s:iterator>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#text").keydown(function(e) {
			if (e.keyCode == 13) {
				search();
			}
		});
	});

	function quickdelete() {
		$("#text").val("");
	}

	function search() {
		var text = $("#text").val().trim();
		if (text == "") {
			return;
		}
		window.location.href = "/${oname}/interact/site_search.action?wd=" + text;
	}

	
</script>