<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/qrcode.js"></script> 
<div class="wrap_content left_module">
		<div class="toolbar">
			<ul class="c_switch">
				<s:if test="dto.tagList.size()>0">
					<li <s:if test="0 == dto.tagid">class="selected"</s:if>><a href="/${oname}/template/index.action?type=${type}">全部(${dto.total})</a></li>
				<s:iterator value="dto.tagList" var="t">
					<li <s:if test="#t.id == dto.tagid">class="selected"</s:if>><a href="/${oname}/template/index.action?type=${type }&tagid=${t.id }" >${t.name}(${t.count })</a></li>
				</s:iterator>
			</s:if>
			</ul>
		</div>
		  <div class="template-list clearfix">
		  	  <div class="iphone-frame">
				<div class="rel" id="0">
					 <img class="template-pic" src="/images/kb.jpg" />
				      <div class="template-txt">自行创建</div>
					  <div class="template-btn">
					  <s:if test='type=="C"'>
						  <a href="javascript:void(0);" onclick="useMb(0,'E')"><span class="use"  style="width:100%">使用</span></a>
					  </s:if>
					  <s:elseif test='type=="W"'>
					 	 <a href="javascript:void(0);" onclick="useMb(0,'W')"><span class="use"  style="width:100%">使用</span></a>
					  </s:elseif>
					  </div>
				</div>
		  	  </div>
		  <s:if test="dto.mbList.size()>0">
	        	<s:iterator value="dto.mbList" var="t" status="st">
					<div class="iphone-frame" >
				   	<div class="rel" id="${t.id }">
				      <img class="template-pic" src="${t.img }" style="width:270px;height:186px;"/>
				      <div class="template-txt">${t.title }</div>
					  <div class="template-btn">
					  <s:if test='type=="C"'>
						  <a href="javascript:void(0);" onclick="useMb(${t.id},'E')"><span class="use">使用</span></a>
						  <a href="javascript:void(0);" onclick="preMb('${t.link}','${t.id }','E')"><span class="pre">预览</span></a>
					  </s:if>
					  <s:elseif test='type=="W"'>
					 	 <a href="javascript:void(0);" onclick="useMb(${t.id},'W')"><span class="use">使用</span></a>
					 	 <a href="javascript:void(0);" onclick="preMb('${t.link}','${t.id }','W')"><span class="pre">预览</span></a>
					  </s:elseif>
					  </div>
				      <div class="qrcode-div"></div>
				      <div id="qrcode${t.id }" class="qrcode" align="center">
			    		</div>
				      </div>
				    </div>
				      <script type="text/javascript">
					    var qrcode = new QRCode(document.getElementById("qrcode${t.id }"), {
							width : 120,
							height : 120
						});
						qrcode.makeCode("${t.link}");
					</script>
				</s:iterator>
		  </s:if>
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