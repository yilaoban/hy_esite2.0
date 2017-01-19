<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 全部评论 -->
<link rel="stylesheet" type="text/css" href="/css/hudong/419_pl/index.css" />
<div class="comment_list"></div>
<div class="weui-infinite-scroll">
  <div class="infinite-preloader"></div>
  <span>正在加载...</span>
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
		loadComment();
	});

	var pageId = 1;
	var loading = false;
	$(document.body).infinite().on("infinite", function() {
		if (loading) {
			return;
		}
		loading = true;
		loadComment();
	});
	function loadComment() {
		$.ajax({
			url : "/${oname}/user/pj_contents.action",
			type : "get",
			data : {
				"pageId" : pageId
			},
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode == 0) {
						// pageId
						var max_page = parseInt(res.total / 10) + 1;
						var page = res.pageId;
						if (page < max_page) {
							pageId = page + 1;
						} else {
							$(".weui-infinite-scroll").remove();
							$(document.body).destroyInfinite();
						}
						// link
						var pjp = res.pjp;
						if (pjp) {
							$("#pjs").attr("href", "/${oname}/user/show/" + pjp.pjsid + "/wxn/" + res.kv + ".html");
							$("#pjc").attr("href", "/${oname}/user/show/" + pjp.pjcid + "/wxn/" + res.kv + ".html");
						}
						// comment list
						var list = res.list;
						for (var i = 0; i < list.length; i++) {
							var pj = list[i];
							if (pj) {
								var user = pj.wxUser;
								var ser = pj.yuYueServicer;
								var wdList = pj.wdList;
								var html = '';
								html += '<div class="comment">';
								html += '  <div class="comment_portrait">';
								html += '    <img src="'+user.headimgurl+'">';
								html += '  </div>';
								html += '  <div class="comment_customer">' + user.nickname + '</div>';
								html += '  <div class="comment_server">';
								html += '    <span class="customer_server">' + ser.name + '</span>';
								html += '    <p class="customer_stars">';
								for (var j = 0; j < wdList.length; j++) {
									var wd = wdList[j];
									html += '  <span style="display:inline-block;font-size:13px;width:99px;">' + wd.name + '：</span>';
									html += '  <span class="server_rate">';
									for (var k = 0; k < 5; k++) {
										if (k < wd.level) {
											html += '<span class="y_star"></span>';
										} else {
											html += '<span class="y_star_b"></span>';
										}
									}
									html += '  </span>';
								}
								html += '    </p>';
								html += '  </div>';
								html += '  <div class="comment_content">' + pj.content + '</div>';
								html += '  <div class="comment_time">' + date2String(pj.createtime) + '</div>';
								html += '</div>';
								if (pj.dzcontent) {
									html += '<div class="angle"></div>';
									html += '<div class="shopper_reply">';
									html += '  <div class="reply_content">';
									html += '    <span class="replier">商家回复：</span>';
									html += '    <span">' + pj.dzcontent + '</span>';
									html += '  </div>';
									html += '</div>';
								}
								$(".comment_list").append(html);
							}
						}
					} else {
						$.alert(res.errmsg);
					}
				} else {
					$.alert("请求错误");
				}
				loading = false;
			}
		});
	}

	function date2String(date) {
		var yyyy = date.year + 1900;
		var MM = add0(date.month + 1);
		var dd = add0(date.date);
		var HH = add0(date.hours);
		var mm = add0(date.minutes);
		var ss = add0(date.seconds);
		return yyyy + "-" + MM + "-" + dd + " " + HH + ":" + mm + ":" + ss;
	}
	function add0(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}

	
</script>