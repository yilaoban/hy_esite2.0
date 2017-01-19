<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 站内搜索 -->
<link href="/css/znss/index.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="search_box block" status="0" hydata="${blocks[0].rid}" style="${blocks[0].hyct};">
    <form action="" method="post">
      <input name="wd" type="text"  class="qz_search" />
      <span onclick="result(1)"><img src="/images/search.png" /></span>
    </form>
</div>

<div class="search_jgd block" status="0" hydata="${blocks[1].rid}" style="${blocks[1].hyct};">
  <ul class="search_jg">
    <li>
      <span>
        <a>市场沟通协调员</a>
      </span>
      <p>工作经验:3－5年工作性质:全职最低学历:本科招聘人数:010-59850292语言要求:English英语发布日期:2016-01-25岗位描述1</p>
    </li>
    <li>
      <span>市场沟通协调员</span>
      <p>工作经验:3－5年工作性质:全职最低学历:本科招聘人数:010-59850292语言要求:English英语发布日期:2016-01-25岗位描述1</p>
    </li>
    <li>
      <span>市场沟通协调员</span>
      <p>工作经验:3－5年工作性质:全职最低学历:本科招聘人数:010-59850292语言要求:English英语发布日期:2016-01-25岗位描述1</p>
    </li>
  </ul>
  <nav class="text-center">
    <ul class="pagination"></ul>
  </nav>
</div>
<script src="/js/mustache.js"></script>
<script src="/js/bootstrap/js/bootstrap-paginator.js"></script>
<script>
	$(document).ready(function() {
		if("${method}"=="E"){
			return;
		}
		result(1);
	});

	function result(pageId) {
		$.ajax({
			url : "/${oname}/user/search_result.action",
			type : "post",
			data : {
				"wd" : $(".qz_search").val().trim(),
				"pageId" : pageId
			},
			cache : false,
			success : function(res) {
				if (res) {
					$(".qz_search").val(res.wd);

					$(".search_jg").empty();
					for (var i = 0; i < res.docs.length; i++) {
						var html = '';
						html += '<li>';
						html += '  <span><a href="{{url}}">{{titlecn}}{{titleen}}</a></span>';
						html += '  {{#desccn}}<p>{{{.}}}</p>{{/desccn}}';
						html += '  {{#descen}}<p>{{{.}}}</p>{{/descen}}';
						html += '  {{#cntcn}}<p>{{{.}}}</p>{{/cntcn}}';
						html += '  {{#cnten}}<p>{{{.}}}</p>{{/cnten}}';
						html += '</li>';
						$(".search_jg").append(Mustache.render(html, res.docs[i]));
					}

					if (res.totalPages > 1) {
						$(".pagination").bootstrapPaginator({
							bootstrapMajorVersion : 3, // 版本
							currentPage : res.currentPage, // 当前页数
							totalPages : res.totalPages, // 总页数
							onPageClicked : function(event, originalEvent, type, page) {
								result(page);
							}
						});
					}
				}
			}
		});
	}
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
