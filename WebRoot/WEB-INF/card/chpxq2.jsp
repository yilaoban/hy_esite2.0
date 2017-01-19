<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 产品详情 -->
<%
String url = request.getHeader("Referer");
%>
<link rel="stylesheet" href="/css/hudong/chpxq2/index.css" type="text/css"></link>
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='dto.product != null'>
	<div class="xq_top">
		<s:if test='dto.product.type != "J"'>
			<a href="/${oname}/user/payHome.action" target="_blank" hyvar="link"><img src="/images/hudong/chpxq2/back.png"  class="back" hyvar="img" hydesc="68*68"/> </a>
		</s:if>
		<s:else>
			<a href="/${oname}/user/payJfHome.action" target="_blank" hyvar="link"><img src="/images/hudong/chpxq2/back.png"  class="back" hyvar="img" hydesc="68*68"/> </a>
		</s:else>
		<span>${dto.product.name }</span>
	</div>
	<div class="banner"><img src="${imgDomain }${dto.product.bimgurl}" hyvar="img" hydesc="640*345"/></div>
	<div class="xq_title" hyblk="S">
	  <span hyvar="price1">${dto.product.salesprice }</span><font hyvar="price2">${dto.product.price }</font>
	  <s:if test='dto.product.linkurl != ""'>
	  	<a href="#" target="_blank" hyvar="link"><img src="/images/hudong/chpxq2/1.png" class="xq_kk" hyvar="img" hydesc="155*70"/></a>
	  </s:if>
	  <p id="hiddentag" hyvar="desc"> ${dto.product.tag}</p>
	</div>
	
	<div class="content">
	  <div>
	  <img src="/images/hudong/chpxq2/2.png" hyvar="img" hydesc="40*40"/>
	  <span hyvar="title">产品详情</span>
	  </div>
	  <div style="clear:both"></div>
	   
	  <div hyvar="introduction">
	  ${dto.product.detail}
	  </div>
	</div>
	
	<s:if test='dto.product.fatie=="Y"'>
		<link href="/css/hudong/shequ/index.css" rel="stylesheet" type="text/css" />
		<script src="/js/mustache.js"></script>
		<script src="/js/xwcomment.js"></script>
		<div style="text-align:right;">
			<input type="hidden" id="pid" value="${dto.product.id }"/>
			<input type="hidden" name="topicid" id="topicid">
			<input type="hidden" id="oname" value="${oname }">
			<input type="hidden" name="forumid" id="forumid">
			<img src="/images/zan.png" /><span class="wxzan" id="zan${dto.product.id }" onclick="likeTopic('${dto.product.id}')"></span>
			<img src="/images/cai.png"  style="margin-left:10px;"/><span class="wxcai" id="cai${dto.product.id }" onclick="dislikeTopic('${dto.product.id}')"></span>
			<img src="/images/pin.png"  style="margin-left:10px;"/><span id="pinglun${dto.product.id }" style="margin-right:20px;"></span>
		</div>
		<div class="zxhd_xq_plist">
				<ul>
				</ul>
		</div>
		<div class="zxhd_xq_plist_load" id="loadMore" page="1">
				<span onclick="loadComments('${dto.product.topicid}')">点击加载</span>
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
				<div class="post-btn" onclick="submitComment('${dto.product.topicid}')" style="margin-top:10px;">回复</div>
			</div>
			<div id="as"></div>
		</div>
			
			
		<script type="text/javascript">
		$(document).ready(function(){
			var pids=$("#pid").val();
	 		if(pids==""){
	 			return;
	 		}
	 		$.post("/${oname }/user/bbsContent.action",{"pids":pids,"entityType":"T"},function(data){
	 			$.each(data,function(index,value){
	 				$("#zan"+value.pid).text(value.zan);
		 			$("#cai"+value.pid).text(value.cai);
					$("#pinglun"+value.pid).text(value.comment);
					$("#topicid").val(value.topicid);
					$("#forumid").val(value.forumid);
				});
		 		loadComments('${dto.product.topicid}');
	 		});
		});
		
		</script>
		</s:if>
</s:if>
<s:else>
	<div class="xq_top" hyblk="S">
	 <a href="#" target="_blank" hyvar="link"><img src="/images/hudong/chpxq2/back.png"  class="back" hyvar="img1" hydesc="68*68"/> </a>
	 <span hyvar="title1">产品名称</span>
	</div>
	
	<div class="banner" hyblk="S"><img src="/images/hudong/chpxq2/tu_03.jpg" hyvar="img2" hydesc="640*345"/></div>
	
	<div class="xq_title" hyblk="S">
	  <span class="jiage" hyvar="price1">￥159</span><span class="yuanjia" hyvar="price2">原价￥359</span>
	  <a href="#" target="_blank" hyvar="link"><img src="/images/hudong/chpxq2/1.png" class="xq_kk" hyvar="img3" hydesc="155*70"/></a>
	  <p hyvar="desc">国内领先的数字营销平台及解决方案</p>
	  <p hyvar="desc">国内领先的数字营销平台及解决方案</p>
	</div>
	
	<div class="content" hyblk="S">
	  <div>
	  <img src="/images/hudong/chpxq2/2.png" hyvar="img4" hydesc="40*40"/>
	  <span hyvar="title2">产品详情</span>
	  </div>
	  <div style="clear:both"></div>
	   
	  <div hyvar="introduction">
	  <p>会易环宇科技（北京）有限公司（以下简称“会易”）是一家以移动化、社会化、大数据为核心的数字营销技术及解决方案提供商，会易致力于为客户提供一站式的数字营销平台、解决方案及专业服</p>
	  <p>8年来，基于众多品牌客户的营销服务经验，会易公司积累了丰富的先机。</p>
	  </div>
	</div>
</s:else>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
	var title = "${dto.product.name}";
	var desc = $("#hiddentag").text().trim();
	var img = "${imgDomain}${dto.product.simgurl}";
	var link = " ${pageDomain }/${oname}/user/show/${pageid}/${sessionScope.visitUser.source}/ctt-hy-${dto.product.id}.html";
	wxshare(title,desc,img,link);
	$("title").text(title);
	$("meta[name='description']").attr("content",desc);
</script>
