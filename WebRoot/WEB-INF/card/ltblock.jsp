<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<script type="text/javascript" src="/js/mustache.js"></script>
<link href="/css/hudong/shequ/index.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="usq_box_top_1 block" hydata="${blocks[0].rid}" status="0" style="${blocks[0].hyct}">
	<img src="${blocks[0].img}">${blocks[0].title}
	<!-- 
<a href="javascript:void(0);" onclick="bbsexit()" style="float: right">退出</a>
	 -->
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="usq_box_top_bt block" hydata="${blocks[1].rid}" status="0" style="${blocks[1].hyct}">
	<img src="${blocks[1].img}">${blocks[1].title}
</div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="usq_box_top_tx block" hydata="${blocks[2].rid}" status="0" style="${blocks[2].hyct}">${blocks[2].content}</div>
</s:if>
<s:if test='blocks[3].display=="Y"'>
<div class="usq_center block" hydata="${blocks[3].rid}" status="0" style="${blocks[3].hyct}">
	<div class="gysm_banner_150313">
		<ul>
			<s:if test="blocks[3].obj.size > 0">
				<s:iterator value="blocks[3].obj" var="c" status="st">
					<li onclick="changeTopic(this)" <s:if test="#st.first">class="current"</s:if> source="${c.cateid }-${c.id}" <s:if test="#st.last">style="border-right:none"</s:if>>
						<a href="javascript:void(0)">${c.title }</a>
					</li>
				</s:iterator>
			</s:if>
			<s:else>
				<li class="current">
					<a href="/${oname}/user/show/1874.html">社区热点</a>
				</li>
				<li>
					<a href="/${oname}/user/show/1875.html">体育运动</a>
				</li>
				<li>
					<a href="/${oname}/user/show/1871.html">跳蚤市场</a>
				</li>
				<li style="border-right:none">
					<a href="/${oname}/user/show/1876.html">兴趣交友</a>
				</li>
			</s:else>
		</ul>
	</div>
	<div style="width:95%; margin:0 auto;">
		<div class="zxhd_xq_plist">
			<ul></ul>
		</div>
		<div class="zxhd_xq_plist_load" id="loadMore" page="1" style="display:none;">
			<span onclick="showTopics()">点击加载</span>
		</div>
		<div class="zxhd_abs">
			<div class="zxhd_ck">
				<a href="javascript:void(0);" onclick="addTopic()">
					<div class="zxhd_ck_img1">
						<img src="http://img.huiyee.com/site/1160/370/images/usq/s_03.png" />
					</div>
					我来说两句
				</a>
			</div>
		</div>
	</div>
</div>
</s:if>
<!-- 弹出框 -->
<div id="modal" style="position:fixed;top:0;right:0;bottom:0;left:0;background:#000;opacity:.7;display:none;z-index:1001;"></div>
<img id="imgcontent" style="display:none;" />

<div id="modaltopic" style="position:fixed;top:0;right:0;bottom:0;left:0;background:#b5b5b5;opacity:.9;display:none;z-index:1001;"></div>
<div id="topic_create" style="position:fixed;display:none;top:0;right:0;bottom:0;left:0;padding:20px;z-index:1002;">
	<input id="topic_title" placeholder="醒目的标题" style="width:100%;margin:10px 0;" />
	<textarea id="topic_text" class="ufw_jy_textares1" placeholder="请在这发布话题"></textarea>
	<div style="text-align:right;">
		<div id="pickerr">
			<img src="/diyUpload/images/picupload.png" />
		</div>
		<div class="post-btn" onclick="cancelTopic()" style="margin-top:10px;">取消</div>
		<div class="post-btn" onclick="submitTopic()" style="margin-top:10px;">发表</div>
	</div>
	<div id="as"></div>
</div>
<script type="text/javascript">
	var pageid = '${pageid}'; // comment page id
	var pic = [];
	var pic_name = [];
	$(document).ready(function() {
		showTopics();
		jQuery("#as").diyUpload({
			url : "http://114.215.120.206:8081/${oname}/user/h5UploadPic.action",
			auto : true,
			pick : "#pickerr",
			fileNumLimit : 50,//最大上传的文件数量
			fileSizeLimit : 500000 * 1024,//总文件大小
			fileSingleSizeLimit : 50000 * 1024,//单个文件大小(单位字节)
			accept : {
				title : "Images",
				extensions : "gif,jpg,jpeg,bmp,png",
				mimeTypes : "image/*"
			},
			success : function(data) {
				var length = pic.length;
				pic[length] = data.picUrl;
				pic_name[length] = data.picName;
			},
			error : function(err) {
				console.info(err);
			},
			del : function(name) {
				for (var i = 0; i < pic_name.length; i++) {
					if (pic_name[i].indexOf(name) != -1) {
						pic_name.splice(i, 1);
						pic.splice(i, 1);
						break;
					}
				}
			}
		});
	});
	function changeTopic(li) {
		$(".current").removeClass("current");
		$(li).addClass("current");
		$(".zxhd_xq_plist").find("ul").empty();
		showTopics();
	}
	function showTopics() {
		var page = parseInt($("#loadMore").attr("page"));
		var source = $(".current").attr("source");
		if (source == null) {
			return;
		}
		$.post("/${oname}/user/bbs/forum/showTopics-" + source + ".action", {
			"pageId" : page
		}, function(data) {
			var html = "";
			html += '<li>';
			html += '	<div class="zxhd_xq_plist_box">';
			html += '		<div class="zxhd_xq_plimg">';
			html += '			{{#creater_img}}<img src="{{creater_img}}" />{{/creater_img}}';
			html += '		</div>';
			html += '		<div class="zxhd_xq_plright">';
			html += '			<span>{{{creater}}}</span>';
			html += '			<p>{{{TITLE}}}</p>';
			html += '			<p>{{CREATE_TIME}}</p>';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="usq_pl_b">';
			html += '		{{#top}} <img src="http://tb1.bdstatic.com/tb/static-frs/img/icon_bright/zding.gif?v=2" alt="置顶" /> {{/top}}';
			html += '		<p class="huati_content">{{{content}}}</p>';
			html += '		<div class="img_pl">';
			html += '			{{#pic_list}} <img src="{{.}}" style="height:80px;margin: 2px;" /> {{/pic_list}}';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="zxhd_b_pl">';
			html += '		<dl>';
			html += '			<dd>';
			html += '				<a href="/${oname}/user/newdetails.action?relationid=${blocks[3].rid }&nid={{id}}&type=B">';
			html += '					<img src="http://img.huiyee.com/site/1160/370/images/zxhd/2_20.png" />';
			html += '				</a>';
			html += '				<span>{{REPLY_COUNT}}</span>';
			html += '			</dd>';
			html += '			<dd>';
			html += '				<a href="javascript:void(0);" onclick="dislikeTopic({{id}})">';
			html += '					<img src="http://img.huiyee.com/site/1160/370/images/zxhd/2_19.png" style="margin-top:3px;"/>';
			html += '				</a>';
			html += '				<span id="dlt_{{id}}">{{down}}</span>';
			html += '			</dd>';
			html += '			<dd>';
			html += '				<a href="javascript:void(0);" onclick="likeTopic({{id}})">';
			html += '					<img src="http://img.huiyee.com/site/1160/370/images/zxhd/2_18.png" />';
			html += '				</a>';
			html += '				<span id="lt_{{id}}">{{up}}</span>';
			html += '			</dd>';
			html += '		</dl>';
			html += '	</div>';
			html += '</li>';
			for (var i = 0; i < data.length; i++) {
				data[i].pageid = pageid;
				data[i].source = source;
				data[i].top = data[i].top == 1;
				data[i].CREATE_TIME = new Date(data[i].CREATE_TIME).Format("yyyy-MM-dd hh:mm:ss");
				var rendered = Mustache.render(html, data[i]);
				$(".zxhd_xq_plist").find("ul").append(rendered);
			}

			if (data.length == 10) {
				$("#loadMore").attr("page", page + 1);
				$("#loadMore").show();
			} else {
				$("#loadMore").hide();
			}
			imgscale();
		});
	}
	function imgscale() {
		$(".img_pl img").click(function() {
			var current = $(this).attr("src");
			var urls = [];
			var imgs = $(this).parent().find("img");
			for (var i = 0; i < imgs.size(); i++) {
				urls[i] = imgs.eq(i).attr("src");
			}
			wx.previewImage({
				current : current, // 当前显示图片的http链接
				urls : urls
			// 需要预览的图片http链接列表
			});
		});
		$(".usq_pl_b").find("p").find("img").css("width","100%");
	};
	function likeTopic(topicid) {
		var source = $(".current").attr("source");
		$.post("/${oname}/user/bbs/topicuser/likeTopic-" + source + "-" + topicid + ".action", function(data) {
			if (data.indexOf("redirect:") != -1) {
				window.location.href = data.split(':')[1];
				return;
			}
			if (data.indexOf("err:") != -1) {
				alert(data.split(':')[1]);
				return;
			}
			if (data > 0) {
				var num = parseInt($("#lt_" + topicid).html());
				$("#lt_" + topicid).html(num + 1);
			}else if(data == -1){
				alert("该话题已经被删除");
				window.location.reload();
			}
			$("#lt_" + topicid).prev().removeAttr("onclick");
		});
	}
	function dislikeTopic(topicid) {
		var source = $(".current").attr("source");
		$.post("/${oname}/user/bbs/topicuser/dislikeTopic-" + source + "-" + topicid + ".action", function(data) {
			if (data.indexOf("redirect:") != -1) {
				window.location.href = data.split(':')[1];
				return;
			}
			if (data.indexOf("err:") != -1) {
				alert(data.split(':')[1]);
				return;
			}
			if (data > 0) {
				var num = parseInt($("#dlt_" + topicid).html());
				$("#dlt_" + topicid).html(num + 1);
			}else if(data == -1){
				alert("该话题已经被删除");
				window.location.reload();
			}
			$("#dlt_" + topicid).prev().removeAttr("onclick");
		});
	}
	function addTopic() {
		$("#modaltopic").show();
		$("#topic_create").show();
		$("#topic_title").focus();
	}
	function cancelTopic() {
		$("#modaltopic").hide();
		$("#topic_create").hide();
		$("#topic_title").val("");
		$("#topic_text").val("");
	}
	function submitTopic() {
		var source = $(".current").attr("source");
		var title = $("#topic_title").val().trim();
		if (title == "") {
			alert("标题不能为空");
			return;
		}
		var content = $("#topic_text").val().trim();
		var pic_url = pic + "";
		if (content == "" && pic_url == "") {
			alert("您还什么都没说呐！");
			return;
		}
		$.post("/${oname}/user/bbs/forumuser/addTopic-" + source + ".action", {
			"topic.TITLE" : title,
			"topic.content" : content,
			"topic.pic" : pic_url
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
				alert("发表成功");
				cancelTopic();
				$(".zxhd_xq_plist").find("ul").empty();
				$("#loadMore").attr("page", 1);
				showTopics();
			}else{
				alert("发表失败");
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
	
	function bbsexit(){
			$.post("/${oname}/user/bbsexit.action",function(data){
				if(data == "Y"){
					window.location.reload();
				}else {
					alert('退出失败');
				}
			});
		}
</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>