<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<div class="wrap_content left_module">
  <div class="template-list clearfix">
	   <s:iterator value="pdto.plist" var="t">
	   	<div class="iphone-frame">
	   	<div class="rel">
	      <img class="template-pic" src="${imgDomain }${t.img }" />
	      <div class="qrcode-div"></div>
	      <div id="qrcode${t.id }" class="qrcode" align="center"><b>${t.name }</b></div>
	      </div>
	    </div>
	      <script type="text/javascript">
		    var qrcode = new QRCode(document.getElementById("qrcode${t.id }"), {
				width : 120,
				height : 120
			});
	    	function makeCode () {
			  var domain='${pageDomain}';
			  var elText = domain+"/user/show/"+${t.pageid}+".html";
			  qrcode.makeCode(elText);
			}
			makeCode();
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