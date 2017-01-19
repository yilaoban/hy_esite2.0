<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 评论 -->
<link rel="stylesheet" type="text/css" href="/css/hudong/419_pl/index.css" />
<link rel="stylesheet" type="text/css" href="/css/hudong/419_pl/slick.css" />
<div class="comment_div">
  <div class="comment_header">服务评价</div>
  <div class="comment_server">
    <p>请选择为您服务的技师：</p>
    <div class="slick"></div>
  </div>
  <div class="comment_wrap">
    <ul></ul>
  </div>
  <div style="text-align: center; background-color: #f0f0f0;">
    <input type="button" class="comment_button" value="发表评论" />
  </div>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript" src="/js/hudong/419_pl/slick.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//load servicers
		$.ajax({
			url : "/${oname}/user/pj_servicers.action",
			type : "get",
			cache : false,
			success : function(res) {
				if (res) {
					var list = res.list;
					for (var i = 0; i < list.length; i++) {
						var html = '';
						html += '<div class="server_item">';
						html += '  <img src="'+list[i].simg+'">';
						html += '  <p id="'+list[i].id+'">' + list[i].name + '</p>';
						html += '  <div class="selected_sign"></div>';
						html += '</div>';
						$(".slick").append(html);
					}
					$('.slick').slick({
						infinite : false,
						slidesToShow : 3,
						slidesToScroll : 3,
						arrows : false
					});
					$(".server_item").click(function() {
						$(".server_item").removeClass("selected");
						$(this).addClass("selected");
					});

					var wdList = res.wdList;
					for (var i = 0; i < wdList.length; i++) {
						var wd = wdList[i];
						var html = '';
						html += '<li>';
						html += '  <span style="display:inline-block;width:80px;">' + wd.name + '：</span>';
						html += '  <span class="server_rate" wdid="'+wd.id+'">';
						html += '    <span class="g_star_b"></span>';
						html += '    <span class="g_star_b"></span>';
						html += '    <span class="g_star_b"></span>';
						html += '    <span class="g_star_b"></span>';
						html += '    <span class="g_star_b"></span>';
						html += '  </span>';
						html += '</li>';
						$(".comment_wrap>ul").append(html);
					}
					$(".comment_wrap>ul").append('<li><textarea class="server_content" placeholder="写下您对服务的整体感受吧，对我们很有帮助哦！"></textarea></li>');
					$(".server_rate span").click(function() {
						$(this).attr("class", "p_star");
						$(this).prevAll().attr("class", "p_star");
						$(this).nextAll().attr("class", "g_star_b");
					});
				}
			}
		});

		$(".comment_button").click(function() {
			var post = {};

			var server_item = $(".server_item.selected");
			if (server_item.length == 0) {
				$.alert("请选择为你服务的技师");
				return;
			}
			post["pj.serid"] = server_item.find("p").attr("id");

			var i = 0;
			$(".server_rate").each(function() {
				var id = $(this).attr("wdid");
				var level = $(this).find(".p_star").length;
				post["pj.wdList[" + i + "].id"] = id;
				post["pj.wdList[" + i + "].level"] = level;
				i++;
			});

			var content = $(".server_content").val().trim();
			if (content != "") {
				post["pj.content"] = content;
			}

			$.ajax({
				url : "/${oname}/user/pj_add.action",
				type : "post",
				data : post,
				cache : false,
				success : function(res) {
					if (res) {
						if (res.errcode >= 0) {
							$.alert("评论成功，赠送您" + res.jf + "个积分", function() {
								wx.closeWindow();
							});
						} else {
							$.alert(res.errmsg);
						}
					} else {
						$.alert("请求异常");
					}
				}
			});
		});
	});
</script>



