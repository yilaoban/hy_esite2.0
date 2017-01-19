<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- 
<div>站点名称：<input class='text-medium' id='sitename'/></div>
 -->
 
<div class="wrap_content left_module">
 <div class="template-list clearfix">
 <!-- 微现场 -->
       	<s:iterator value="dto.wSiteGroupList" var="t" status="st1">
       		<s:if test="#st1.first">
			<div class="iphone-frame" >
			   	<div class="rel" id="${t.id }">
				      <img class="template-pic" src="/images/wxc.jpg" />
				      <div class="template-txt">${t.groupname }</div>
					  <div class="template-btn">
						  <a href="javascript:void(0);" onclick="sgsubform(${t.id},'W');$('.layui-layer-input').attr('placeholder','请输入站点名称')"><span class="use" style="width:100%">使用</span></a>
					  </div>
			     </div>
		    </div>
		    </s:if>
		</s:iterator>
		<!-- 微社区 -->
		<s:iterator value="dto.bSiteGroupList" var="t" status="st2">
       		<s:if test="#st2.first">
			<div class="iphone-frame" >
			   	<div class="rel" id="${t.id }">
				      <img class="template-pic" src="/images/wsq.jpg" />
				      <div class="template-txt">${t.groupname }</div>
					  <div class="template-btn">
						  <a href="javascript:void(0);" onclick="sgsubform(${t.id},'B');$('.layui-layer-input').attr('placeholder','请输入站点名称')"><span class="use" style="width:100%">使用</span></a>
					  </div>
			     </div>
		    </div>
		    </s:if>
		</s:iterator>
		<!-- 微传播 
		 -->
		<s:iterator value="dto.cSiteGroupList" var="t" status="st3">
       		<s:if test="#st3.first">
			<div class="iphone-frame" >
			   	<div class="rel" id="${t.id }">
				      <img class="template-pic" src="/images/hhr.jpg" />
				      <div class="template-txt">${t.groupname }</div>
					  <div class="template-btn">
						  <a href="javascript:void(0);" onclick="sgsubform(${t.id},'C');$('.layui-layer-input').attr('placeholder','请输入站点名称')"><span class="use" style="width:100%">使用</span></a>
					  </div>
			     </div>
		    </div>
		    </s:if>
		</s:iterator>
		
		<!-- 微传播 
		 -->
		<s:iterator value="dto.pSiteGroupList" var="t" status="st3">
       		<s:if test="#st3.first">
			<div class="iphone-frame" >
			   	<div class="rel" id="${t.id }">
				      <img class="template-pic" src="/images/hhr.jpg" />
				      <div class="template-txt">${t.groupname }</div>
					  <div class="template-btn">
						  <a href="javascript:void(0);" onclick="sgsubform(${t.id},'C');$('.layui-layer-input').attr('placeholder','请输入站点名称')"><span class="use" style="width:100%">使用</span></a>
					  </div>
			     </div>
		    </div>
		    </s:if>
		</s:iterator>
		
 </div>
</div>
    <script type="text/javascript">
    	function sgsubform(sgid,copytype){
			var url = "/${oname}/page/copySite.action";
			layer.prompt({
				title : '使用模板'
				},function(value){
					if(value==null||value==''){
						   return false;
					}
					$.post(url,{"sitename":value,"copyType":copytype,"groupid":sgid},function(data){
						if(data == 1){
							layer.msg('创建中！请稍等……', {icon: 6, time: 1500}, function(){
								window.location.href = '/${oname}/page/website.action';
							}); 
						}else if(data==-1){
							layer.msg('创建失败！重复的站点名称……', {icon: 5, time: 2000});
						}else{
							layer.msg('创建失败！请重试……', {icon: 5, time: 2000});
						}
					});	             
		    });
		}
		
		$(".iphone-frame").hover(
	     function(){
		    $(this).addClass("hover");
		  },
	     function(){
	     	$(this).removeClass("hover");
		  }
		 );
    </script>