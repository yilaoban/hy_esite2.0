<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
		    <li <s:if test='type=="F"'> class="selected" </s:if>><a href="/${oname }/template/index.action?categoryid=${categoryid}&type=F">首页</a></li>
			<li <s:if test='type=="S"'> class="selected" </s:if>><a href="/${oname }/template/index.action?categoryid=${categoryid}&type=S">图文</a></li>
			<li <s:if test='type=="C"'> class="selected" </s:if>><a href="/${oname }/template/index.action?categoryid=${categoryid}&type=C">列表</a></li>
			<li <s:if test='type=="D"'> class="selected" </s:if>><a href="/${oname }/template/index.action?categoryid=${categoryid}&type=D">互动</a></li>
		</ul>
	</div>
	 
	  <div class="template-list clearfix">
	   <s:iterator value="dto.cards" var="t">
	   	<div class="iphone-frame">
	   	<div class="rel">
	      <img class="template-pic" src="${t.bimg }" />
	      <div class="qrcode-div"></div>
	      <div id="qrcode${t.id }" class="qrcode" align="center"><b>${t.name }</b></div>
	      </div>
	    </div>
	      <script type="text/javascript">
		    var qrcode = new QRCode(document.getElementById("qrcode${t.id }"), {
				width : 120,
				height : 120
			});
	
			qrcode.makeCode("${t.url}");
		</script>
	    </s:iterator>
	   </div>
</div>
<script type="text/javascript">
	$(".iphone-frame").hover(
     function(){
	    $(this).addClass("hover");
	  },
     function(){
     	$(this).removeClass("hover");
	  }
	 );
</script>