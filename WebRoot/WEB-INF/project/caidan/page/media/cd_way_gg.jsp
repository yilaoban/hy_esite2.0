<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if(!$.cookie('ggCookie')){
		$.cookie('ggCookie','${dto.getWgIds()}');
	}
	
	$("input[name='ggids']").each(function(){	
		var val=$(this).val();
		if($.cookie('ggCookie').split(",").indexOf(val)!=-1){
			$(this).prop("checked",true);
		}
	});
	$("input[name='ggids']").click(function(){
			var val=$(this).val();
			var arr=$.cookie('ggCookie').split(",");
			var idx=arr.indexOf(val);
			if($(this).prop("checked")){
				if(idx==-1){
					if(arr.length==1&&arr[0]==''){
						arr[0]=val;
					}else{
						arr[arr.length]=val;
					}
				}
			}else{
				if(idx!=-1){
					arr.splice(idx,1);
				}
			}
			if(arr.length>=1){
				$.cookie('ggCookie',arr.join(","));
			}else{
				$.cookie('ggCookie','');
			}
	});
});


	function wgSave(){
		var wayid=$("#wayid").val();
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname}/cd-media/adajax_saveWaygg.action',
	        data:{"wayid":wayid,"ggids":$.cookie('ggCookie')},// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data=="Y"){
	            	$.cookie('ggCookie', '');
	            	layer.msg('保存成功！请稍等……', {icon: 6, time: 1500}, function(){
						window.parent.location.reload();
					}); 
	            }else{
	            	layer.alert("操作失败!",0);
	            }
	        }
	    });
	}	
</script>
<div>
	<form action="" id="form1">
 		<input type="hidden" id="wayid" value="${wayid }"/>
 		<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
 			<tr>
 				<th>广告名称</th>
 				<th>开始时间</th>
 				<th>结束时间</th>
 			</tr>
			<s:iterator value="dto.ggList" var="g">
				<tr>
					<td style="text-align: left;">
						<input type="checkbox" value="${g.id }" name="ggids"/>${g.title }
					</td>
					<td><s:date name="#g.starttime" format="yyyy-MM-dd"/></td>
					<td><s:date name="#g.endtime" format="yyyy-MM-dd"/></td>
				</tr>
			</s:iterator>
 		</ul>
 		</table>
 		<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
		<div style="margin-bottom: 0">
			<a class="btn btn-primary" href="javascript:wgSave()">保存设置</a>
			<a class="btn btn-primary" href="/${oname }/cd-guanggao/addGG.action" target="_blank" title="已新增的广告未显示,请点击刷新!">新增</a>
			<a class="btn btn-primary" href="javascript:window.location.reload();tabshow();">刷新</a>
		</div>
	</form>
 </div>