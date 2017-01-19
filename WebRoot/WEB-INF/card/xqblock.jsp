<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<script src="/js/mustache.js"></script>
<link href="/css/hudong/shequ/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css" />
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css" />
<div class="ucp_container clearfix" style="background: #ebebeb;padding-bottom:40px;">
	<div class="zxhd_xq_body">
		<div class="zxhd_xq_b_center topic" style="text-align: left;"></div>
		<div class="zxhd_xq_b_center">
			<div class="zxhd_xq_plist">
				<h1>
					<a href="javascript:void(0);">0</a>
					条评论
				</h1>
				<ul>
				</ul>
			</div>
			<div class="zxhd_xq_plist_load" id="loadMore" page="1" style="display:none;">
				<span onclick="loadComments()">点击加载</span>
			</div>
			<div class="zxhd_abs">
				<div class="zxhd_ck">
					<a href="javascript:void(0);" onclick="addComment()">
						<div class="zxhd_ck_img1">
							<img src="http://img.huiyee.com/site/1160/370/images/usq/s_03.png" />
						</div>
						我要评论
					</a>
				</div>
			</div>
		</div>
		<div style="width: 100%; float: left; height: 20px;"></div>
	</div>
</div>

<div id="modaltopic" style="position:fixed;top:0;right:0;bottom:0;left:0;background:#b5b5b5;opacity:.9;display:none;z-index:1001;"></div>
<div id="topic_create" style="display:none;position:fixed;top:0;right:0;bottom:0;left:0;padding:20px;z-index:1002;">
	<textarea id="topic_text" class="ufw_jy_textares1" placeholder="请在这填写回复"></textarea>
	<div style="text-align:right;">
		<div class="post-btn" onclick="cancelComment()" style="margin-top:10px;">取消</div>
		<div class="post-btn" onclick="submitComment()" style="margin-top:10px;">回复</div>
	</div>
	<div id="as"></div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
	if('${method}' != "E"){
		loadComments();
	}
	});

	function loadComments() {
		var source = "0-0-0";
		if('${nid}' !=null && '${nid}' != "" && '${method}' != "E"){
			source = '<s:property value="dto.source"/>';
		}else{
			return;
		}
		var page = parseInt($("#loadMore").attr("page"));
		$.post("/${oname}/user/bbs/topic/showComments-" + source + ".action", {
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

			topic.CREATE_TIME = new Date(topic.CREATE_TIME).Format("yyyy-MM-dd HH:mm:ss");
			$(".topic").html(Mustache.render(topic_html, topic)).show();

			//comment
			if (comments.length > 0) {
				$(".zxhd_xq_plist").find("h1").find("a").html(comments[0].total);
			}
			var comment_html = "";
			comment_html += '<li>';
			comment_html += '	<div class="zxhd_xq_plist_box">';
			comment_html += '		<div class="zxhd_xq_plimg">';
			comment_html += '			{{#creater_img}}<img src="{{creater_img}}" />{{/creater_img}}';
			comment_html += '		</div>';
			comment_html += '		<div class="zxhd_xq_plright">';
			comment_html += '			<span>{{creater}}</span>';
			comment_html += '			<font>&nbsp;{{CREATE_TIME}}</font>';
			comment_html += '			<p>{{{content}}}</p>';
			comment_html += '			<div class="zxhd_xq_pldz">';
			comment_html += '				<a id="lc_{{id}}" href="javascript:void(0);" onclick="likeComment({{id}})">{{#liked}}已赞{{/liked}}{{^liked}}点赞{{/liked}}</a> |';
			comment_html += '				<a id="rc_{{id}}" href="javascript:void(0);" onclick="replyComment({{INDEX_COUNT}})">回复</a>';
			comment_html += '			</div>';
			comment_html += '		</div>';
			comment_html += '	</div>';
			comment_html += '</li>';
			for (var i = 0; i < comments.length; i++) {
				comments[i].CREATE_TIME = new Date(comments[i].CREATE_TIME).Format("yyyy-MM-dd HH:mm:ss");
				var rendered = Mustache.render(comment_html, comments[i]);
				$(".zxhd_xq_plist").find("ul").append(rendered);
			}
		});
	}
	function likeComment(id) {
		var source = "0-0-0";
		if('${nid}' !=null && '${nid}' != "" && '${method}' != "E"){
			source = '<s:property value="dto.source"/>';
		}else{
			return;
		}
		if ($("#lc_" + id).html().trim() == "已赞") {
			return;
		}
		$.post("/${oname}/user/bbs/topicuser/likeComment-" + source + ".action", {
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
				$("#lc_" + id).html("已赞");
				$("#lc_" + id).removeAttr("onclick");
			}else if(data == -1){
				alert("该话题已被删除！");
				window.history.back();
			}
		});
	}
	function replyComment(index) {
		addComment()
		$("#topic_text").text("回复" + index + "楼：");
	}
	function addComment() {
		$("#modaltopic").show();
		$("#topic_create").show();
		$("#topic_text").focus();
	}
	function cancelComment() {
		$("#modaltopic").hide();
		$("#topic_create").hide();
	}
	function submitComment() {
		var source = "0-0-0";
		if('${nid}' !=null && '${nid}' != "" && '${method}' != "E"){
			source = '<s:property value="dto.source"/>';
		}else{
			return;
		}
		var content = $("#topic_text").val().trim();
		if (content == "") {
			alert("内容不能为空");
			return;
		}

		$.post("/${oname}/user/bbs/topicuser/addComment-" + source + ".action", {
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
			}else if(data == -1){
				alert("该话题已被删除！");
				window.history.back();
			}
		});
	}
	Date.prototype.Format = function(fmt) { //author: meizz   
		var o = {
			"M+" : this.getMonth() + 1, //月份   
			"d+" : this.getDate(), //日   
			"h+" : this.getHours(), //小时   
			"m+" : this.getMinutes(), //分   
			"s+" : this.getSeconds(), //秒   
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度   
			"S" : this.getMilliseconds()
		//毫秒   
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}

	
</script>