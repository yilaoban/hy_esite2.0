<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 回复评论 -->
<link rel="stylesheet" type="text/css" href="/css/hudong/419_pl/index.css" />
<div class="comment_div" style="background: #fff;">
  <div class="comment_header">服务评价</div>
  <div class="comment_server" style="background: #f0f0f0;">
    <div class="server_item"></div>
  </div>
  <div class="comment_show">
    <ul></ul>
    <input type="hidden" id="pjid" value="" />
    <input type="hidden" id="openid" value="" />
  </div>
</div>
<div class="c_bottom block" status="0" hydata="${blocks[0].rid}" style="position:<s:if test='method=="E"'>absolute</s:if><s:else>fixed</s:else>;bottom:0px;">
  <ul>
    <li>
      <a href="#" id="pjs">${blocks[0].title1}</a>
    </li>
    <li>
      <a href="#" id="pjc">${blocks[0].title2}</a>
    </li>
  </ul>
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
						var pj = res.pj;
						if (pj) {
							// user
							var user = pj.wxUser;
							var html = "";
							html += '<img src="'+user.headimgurl+'">';
							html += '<p>' + user.nickname + '</p>';
							$(".server_item").html(html);
							//servicer
							var servicer = pj.yuYueServicer;
							html = '';
							html += '<li>';
							html += '  <span class="comment_title">服务员工：</span>';
							html += '  <p class="comment_p servicer">' + servicer.name + '</p>';
							html += '</li>';
							$(".comment_show>ul").append(html);
							// rate
							var wdList = pj.wdList;
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
							var content = pj.content;
							html = '';
							html += '<li>';
							html += '  <span class="comment_title">评论内容：</span>';
							html += '  <p class="comment_p">' + content + '</p>';
							html += '</li>';
							$(".comment_show>ul").append(html);
							// dzcontent
							var dzcontent = pj.dzcontent || "感谢您的评价，欢迎下次光临！";
							html = '';
							html += '<li>';
							html += '  <span class="comment_title">回复评论：</span>';
							html += '  <textarea class="comment_p dzcontent" style="width: 70%; margin-top: 15px; margin-left: 10px">' + dzcontent + '</textarea>';
							if (!pj.dzcontent) {
								html += '<input type="button" class="shopper_comment_button" value="发布" onclick="reply()"/>';
							}
							html += '</li>';
							$(".comment_show>ul").append(html);
							// id & openid
							$("#pjid").val(pj.id);
							$("#openid").val(user.openid);
						}
						// link
						var pjp = res.pjp;
						if (pjp) {
							$("#pjs").attr("href", "/${oname}/user/show/" + pjp.pjsid + "/wxn/" + res.kv + ".html");
							$("#pjc").attr("href", "/${oname}/user/show/" + pjp.pjcid + "/wxn/" + res.kv + ".html");
						}
					} else {
						$.alert(res.errmsg);
					}
				}
			}
		});

	});

	function reply() {
		var id = $("#pjid").val();
		var openid = $("#openid").val();
		if (id == "" || openid == "") {
			$.alert("请求参数错误");
			return;
		}
		var dzcontent = $(".dzcontent").val().trim();
		if (dzcontent == "") {
			$.alert("回复内容不能为空");
			return;
		}

		$.ajax({
			url : "/${oname}/user/pj_reply.action",
			type : "post",
			data : {
				"pj.id" : id,
				"pj.dzcontent" : dzcontent,
				"pj.wxUser.openid" : openid
			},
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode == 0) {
						$.alert("回复成功");
					} else {
						$.alert(res.errmsg);
					}
				}
			}
		});
	}
</script>
