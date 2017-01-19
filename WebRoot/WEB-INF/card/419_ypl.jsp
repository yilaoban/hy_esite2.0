<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 已评论 -->
<link rel="stylesheet" type="text/css" href="/css/hudong/419_pl/index.css" />
<div class="comment_div">
  <div class="comment_header">服务评价</div>
  <div class="comment_server">
    <div class="server_item"></div>
  </div>
  <div class="comment_show">
    <ul></ul>
  </div>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "/${oname}/user/pj_detail.action",
			type : "get",
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode == 0) {
						//servicer
						var servicer = res.pj.yuYueServicer;
						var html = "";
						html += '<img src="'+servicer.simg+'">';
						html += '<p>' + servicer.name + '</p>';
						$(".server_item").html(html);
						// rate
						var wdList = res.pj.wdList;
						for (var i = 0; i < wdList.length; i++) {
							var wd = wdList[i];
							html = '';
							html += '<li>';
							html += '  <span style="display:inline-block;width:80px;">' + wd.name + '：</span>';
							html += '  <span class="server_rate">';
							for (var j = 0; j < 5; j++) {
								if (j < wd.level) {
									html += '<span class="p_star"></span>';
								} else {
									html += '<span class="g_star_b"></span>';
								}
							}
							html += '  </span>';
							html += '</li>';
							$(".comment_show>ul").append(html);
						}
						// content
						var content = res.pj.content;
						html = '';
						html += '<li>';
						html += '  <span class="comment_title">评论内容：</span>';
						html += '  <p class="comment_p">' + content + '</p>';
						html += '</li>';
						$(".comment_show>ul").append(html);
						// dzcontent
						var dzcontent = res.pj.dzcontent;
						if (dzcontent != "") {
							html = '';
							html += '<li class="comment_dz" style="display: none;">';
							html += '  <span class="comment_title">商家回复：</span>';
							html += '  <p class="comment_p">' + dzcontent + '</p>';
							html += '</li>';
							$(".comment_show>ul").append(html);
						}
					} else {
						$.alert(res.errmsg);
					}
				}
			}
		});

	});
</script>
