function loadComments(topicid) {
	var topicid = $("#topicid").val();
	var forumid = $("#forumid").val();
	var source = "0-" + forumid + "-" + topicid;
	var page = parseInt($("#loadMore").attr("page"));
	$.post("/" + $("#oname").val() + "/user/bbs/topic/showComments-" + source + ".action", {
		"pageId" : page
	}, function(data) {
		var topic = data.topic;
		var comments = data.comments;
		var head_img = data.head_img;
		if (comments.length == 10) {
			$("#loadMore").attr("page", page + 1);
			$("#loadMore").show();
		} else {
			$("#loadMore").hide();
		}
		// topic
		var topic_html = "";
		topic_html += '<div class="zxhd_xq_plist_box">';
		topic_html += '	<div class="zxhd_xq_plimg">';
		topic_html += '		{{#creater_img}}<img src="{{creater_img}}" />{{/creater_img}}';
		topic_html += '	</div>';
		topic_html += '	<div class="zxhd_xq_plright">';
		topic_html += '		<span>{{{creater}}}</span>';
		topic_html += '		<p>{{CREATE_TIME}}</p>';
		topic_html += '	</div>';
		topic_html += '</div>';
		topic_html += '<div class="usq_pl_b">';
		topic_html += '	{{#top}} <img src="http://tb1.bdstatic.com/tb/static-frs/img/icon_bright/zding.gif?v=2" alt="置顶" /> {{/top}}';
		topic_html += '	<p>{{{content}}}</p>';
		topic_html += '	{{#pic_list}} <img src="{{.}}" width="30%" style="margin: 2px;" /> {{/pic_list}}';
		topic_html += '</div>';

		topic.CREATE_TIME = new Date(topic.CREATE_TIME).Format("yyyy-MM-dd hh:mm:ss");
		$(".topic").html(Mustache.render(topic_html, topic)).show();

		// comment
		if (comments.length > 0) {
			$(".zxhd_xq_plist").find("h1").find("a").html(comments[0].total);
		}
		var comment_html = "";
		comment_html += '<li>';
		comment_html += '	<div class="zxhd_xq_plist_box">';
		comment_html += '		<div class="zxhd_xq_plimg">';
		comment_html += '			{{#creater_img}}<img src="{{creater_img}}" />{{/creater_img}}';
		comment_html += '		</div>';
		comment_html += '		<div class="zxhd_xq_plct">';
		comment_html += '			<span class="wxzan" id="lc_{{id}}" {{^liked}}onclick="likeComment({{id}})"{{/liked}}>{{ZAN}}</span>';
		comment_html += '			<p class="pl_author">{{creater}}</p>';
		comment_html += '			<p class="pl_content">{{{content}}}</p>';
		comment_html += '			<p class="pl_time">{{CREATE_TIME}}</p>';
		comment_html += '		</div>';
		comment_html += '	</div>';
		comment_html += '</li>';
		for (var i = 0; i < comments.length; i++) {
			comments[i].CREATE_TIME = new Date(comments[i].CREATE_TIME).Format("yyyy-MM-dd hh:mm:ss");
			var rendered = Mustache.render(comment_html, comments[i]);
			$(".zxhd_xq_plist").find("ul").append(rendered);
		}
	});
}

function likeTopic(pid) {
	var topicid = $("#topicid").val();
	var forumid = $("#forumid").val();
	if (topicid == 0) {
		alert("产品对应帖子不存在.");
		return;
	}
	$.post("/" + $("#oname").val() + "/user/bbs/topicuser/likeTopic-0-" + forumid + "-" + topicid + ".action", function(data) {
		if (data == 0) {
			alert("您已赞过了");
			return;
		}
		if (data.indexOf("err:") != -1) {
			alert(data.split(':')[1]);
			return;
		}
		if (data.indexOf("redirect:") != -1) {
			window.location = data.split(':')[1];
			return;
		}
		if (data > 0) {
			var num = parseInt($("#zan" + pid).html());
			$("#zan" + pid).html(num + 1);
		}
	});
}

function dislikeTopic(topicid) {
	var topicid = $("#topicid").val();
	var forumid = $("#forumid").val();
	if (topicid == 0) {
		alert("产品对应帖子不存在.");
		return;
	}
	$.post("/" + $("#oname").val() + "/user/bbs/topicuser/dislikeTopic-0-" + forumid + "-" + topicid + ".action", function(data) {
		if (data == 0) {
			alert("您已踩过了");
			return;
		}
		if (data.indexOf("err:") != -1) {
			alert(data.split(':')[1]);
			return;
		}
		if (data.indexOf("redirect:") != -1) {
			window.location = data.split(':')[1];
			return;
		}
		if (data > 0) {
			var num = parseInt($("#cai" + pid).text());
			$("#cai" + pid).text(num + 1);
		}
		$("#cai" + pid).removeAttr("onclick");
	});
}

function addComment() {
	$("#modaltopic").show();
	$("#topic_create").show();
	$("#topic_text").focus();
}

function submitComment(topicid) {
	var topicid = $("#topicid").val();
	var forumid = $("#forumid").val();
	var source = "0-" + forumid + "-" + topicid;
	var content = $("#topic_text").val().trim();
	if (content == "") {
		alert("内容不能为空");
		return;
	}

	$.post("/" + $("#oname").val() + "/user/bbs/topicuser/addComment-" + source + ".action", {
		"comment.content" : content
	}, function(data) {
		if (data.indexOf("redirect:") != -1) {
			window.location.href = data.split(':')[1];
			return;
		}
		if (data.indexOf("err:") != -1) {
			alert(data.split(':')[1]);
			return;
		}
		if (data > 0) {
			window.location.reload();
		}
	});
}

function likeComment(id) {
	var topicid = $("#topicid").val();
	var forumid = $("#forumid").val();
	var source = "0-" + forumid + "-" + topicid;

	$.post("/" + $("#oname").val() + "/user/bbs/topicuser/likeComment-" + source + ".action", {
		"comment.id" : id
	}, function(data) {
		if (data.indexOf("redirect:") != -1) {
			window.location.href = data.split(':')[1];
			return;
		}
		if (data.indexOf("err:") != -1) {
			alert(data.split(':')[1]);
			return;
		}
		if (data > 0) {
			var num = parseInt($("#lc_" + id).html());
			$("#lc_" + id).html(num + 1);
			$("#lc_" + id).removeAttr("onclick");
		}
	});
}

function replyComment(index) {
	addComment();
	$("#topic_text").text("回复" + index + "楼：");
}

function cancelComment() {
	$("#modaltopic").hide();
	$("#topic_create").hide();
}
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}