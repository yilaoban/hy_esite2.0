<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
 <script type="text/javascript">
    function sgsubform(sgid,copytype){
			var url = "/${oname}/page/copySite.action";
			layer.prompt({
				title : '创建微现场'
				},function(value){
					if(value==null||value==''){
						   return false;
					}
					var index = layer.msg('正在创建,请稍等...', {icon: 16,time:0});
					$.post(url,{"sitename":value,"copyType":copytype,"groupid":sgid},function(data){
						layer.close(index);
						if(data == 1){
							layer.msg('创建成功！', {icon: 6, time: 1500}, function(){
								window.location.reload();
							}); 
						}else if(data==-1){
							layer.msg('创建失败！重复的站点名称！', {icon: 5, time: 2000});
						}else{
							layer.msg('创建失败！请重试！', {icon: 5, time: 2000});
						}
					});	             
		    });
		}
</script>
<div class="wrap_content">
<div class="toolbar pb10">
	<ul class="general left">
		<li><b>${dto.pager.totalCount }</b>现场总数</li>
		<li style="width:60%;background-image:url(/images/wxc.png);height:60px;"></li>
		<li style="background:#99CBED"><a href="javascript:void(0);" onclick="sgsubform('${dto.xcTemplate[0].id}','W');$('.layui-layer-input').attr('placeholder','请输入站点名称')"/><b><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></b>创建微现场</a></li>
	</ul>
</div>
<s:if test="dto.list.size>0">
	<s:iterator value="dto.list" var="s" status="sta">
	<div class="sitegroup_List sg_${s.id }" style="max-height: 150px">
		<div style="float:left">
			<img  alt="" <s:if test='#s.wps.pic != ""'>src="${s.wps.pic }"</s:if><s:else>src="/images/nopic.png"</s:else> width="150px"/>
		</div>
		<div class="sitegroup_List_left2">
			<div class="title"><b>${s.groupname}<s:if test='#s.istemplate=="Y"'><span class="glyphicon glyphicon-star" aria-hidden="true" title="微现场模板"></span></s:if></b> <a href="javascript:void(0)" onclick="changeSiteName1(${s.id},'${s.groupname }')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></div>
			<div class="updatetime"><s:date name="updatetime" format="yyyy-MM-dd HH:mm"/> 更新</div>
	    	<div>分享标题：${s.wps.title}</div>
	    	<div>分享描述：${s.wps.description}</div>
		    <div class="operations">
	    		<a href="/${oname }/page/xcSite.action?sitegroupid=${s.id}&xcid=${s.entityid}"><span title="编辑" class="glyphicon glyphicon-edit"></span></a>
	    		<i class="split">|</i>
		        <s:if test='#s.istemplate!="Y"'><a href="javascript:void(0)" onclick="delete_site(${s.id})"><span class="glyphicon glyphicon-trash" title="删除"></span></a></s:if>
		    </div>
	    </div>
	</div>
	</s:iterator>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</s:if>
<s:else>
	<p style="font-size:20px;text-align:center;">点击创建微现场，使你的线下活动High起来</p>
</s:else>
</div>