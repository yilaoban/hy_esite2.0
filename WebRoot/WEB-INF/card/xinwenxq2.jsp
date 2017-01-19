<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- ΢新闻详情2-->

<link href="/css/liebiao/xinwenxq2/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="xqy_141112_box_1" class_data="hy20150507132410">

	<s:if test='dto.news != null'>
		<div class="xqy_141112_box_1_center clearfix block" class_data="hy20150507132346">
			<p class="zxf_p" class_data="hy20150507132280">${dto.news.title }</p>
			<p><s:date name="dto.news.publishtime" format="yyyy-MM-dd"/><span class="author">${dto.news.author }</span> <span class="author">${dto.news.source }</span></p>
		    <div class="xqy_141112_box_1_c_nr">
		    <s:if test='dto.news.bimgurl != null && dto.news.bimgurl !="" '>
			    <img src="${imgDomain }${dto.news.bimgurl}" style="max-width: 100%;height: auto;width: auto\9;border:none;" hydesc="640*300">
		    </s:if>
		     ${dto.news.content}
		    </div>
		</div>
		
		<s:if test='dto.news.fatie=="Y"'>
		<link href="/css/hudong/shequ/index.css" rel="stylesheet" type="text/css" />
		<script src="/js/mustache.js"></script>
		<script src="/js/xwcomment.js"></script>
		<div style="text-align:right;">
			<input type="hidden" id="pid" value="${dto.news.id }"/>
			<input type="hidden" name="topicid" id="topicid">
			<input type="hidden" id="oname" value="${oname }">
			<input type="hidden" name="forumid" id="forumid">
			<img src="/images/zan.png" /><span class="wxzan" id="zan${dto.news.id }" onclick="likeTopic('${dto.news.id}')"></span>
			<img src="/images/cai.png"  style="margin-left:10px;"/><span class="wxcai" id="cai${dto.news.id }" onclick="dislikeTopic('${dto.news.id}')"></span>
			<img src="/images/pin.png"  style="margin-left:10px;"/><span id="pinglun${dto.news.id }" style="margin-right:20px;"></span>
		</div>
		<div class="zxhd_xq_plist">
				<ul>
				</ul>
		</div>
		<div class="zxhd_xq_plist_load" id="loadMore" page="1">
				<span onclick="loadComments('${dto.news.topicid}')">点击加载</span>
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
		
		
		<div id="modaltopic" style="position:fixed;top:0;right:0;bottom:0;left:0;background:#b5b5b5;opacity:.9;display:none;z-index:1001;"></div>
		<div id="topic_create" style="display:none;position:fixed;top:0;right:0;bottom:0;left:0;padding:20px;z-index:1002;">
			<textarea id="topic_text" class="ufw_jy_textares1" placeholder="请在这填写回复"></textarea>
			<div style="text-align:right;">
				<div class="post-btn" onclick="cancelComment()" style="margin-top:10px;">取消</div>
				<div class="post-btn" onclick="submitComment('${dto.news.topicid}')" style="margin-top:10px;">回复</div>
			</div>
			<div id="as"></div>
		</div>
			
			
		<script type="text/javascript">
		$(document).ready(function(){
			var pids=$("#pid").val();
	 		if(pids==""){
	 			return;
	 		}
	 		$.post("/${oname }/user/bbsContent.action",{"pids":pids,"entityType":"N"},function(data){
	 			$.each(data,function(index,value){
	 				$("#zan"+value.pid).text(value.zan);
		 			$("#cai"+value.pid).text(value.cai);
					$("#pinglun"+value.pid).text(value.comment);
					$("#topicid").val(value.topicid);
					$("#forumid").val(value.forumid);
				});
		 		loadComments('${dto.news.topicid}');
	 		});
		});
		</script>
		</s:if>
		
	</s:if>
	<s:else>
		<div class="xqy_141112_box_1_center clearfix block" class_data="hy20150507132144">
			<p class="zxf_p" class_data="hy20150507132294">营销姿势不对，小米电视才没火起来</p>
			<div class="xqy_141112_box_1_c_nr" class_data="hy20150507132702">
				<img src="/images/liebiao/xinwenxq2/1394182656737_big.jpg" style="max-width: 100%;height: auto;width: auto\9;border:none;" hyvar="img" hydesc="640*300">
				&nbsp;&nbsp;&nbsp;&nbsp;巨资打造全新梦幻《童话》系列---我们美丽公主，住在华丽的城堡，等待着白马王子，您的童话梦想实现了吗？选择韩国艺匠将您变成美丽的公主！巨资打造全新梦幻《童话》系列---我们美丽公主，住在华丽的城堡，等待着白马王子，您的童话梦想实现了吗？选择韩国艺匠<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;巨资打造全新梦幻《童话》系列---我们美丽公主，住在华丽的城堡，等待着白马王子，您的童话梦想实现了吗？选择韩国艺匠将您变成美丽的公主！巨资打造全新梦幻《童话》系列---我们美丽公主，住在华丽的城堡，等待着白马王子，您的童话梦想实现了吗？选择韩国艺匠
			</div>
		</div>
	</s:else>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
	$("title").html("${dto.news.title}");
	$("meta[name='description']").attr("content","${dto.news.shortdesc}");
	var title = "${dto.news.title}";
	var desc = "${dto.news.shortdesc}";
	var imgUrl = '${imgDomain}${dto.news.simgurl}';
	var link = " ${pageDomain }/${oname}/user/show/${pageid}/${sessionScope.visitUser.source}/n-${dto.news.id}.html";
	wxshare(title,desc,imgUrl,link);
</script>
