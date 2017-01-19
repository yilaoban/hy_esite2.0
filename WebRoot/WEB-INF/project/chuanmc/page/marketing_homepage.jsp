<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<style>
#mhp li {margin:10px}
</style>
<script type="text/javascript">
$(document).ready(function() {  
    $("#mhp li").click(function(){
    	$(this).parent("ul").siblings().children("li").children("ul").hide();
		$(this).children("ul").show();
    });
    $("#homepage${marketingSet.homepage}").attr("checked","true");
    $("#homepage${marketingSet.homepage}").parents("ul").show();
    $("#homepage${marketingSet.homepage}").parents("ul").siblings().show();
  
    $('input:radio[name="homepage"]').click(function(){
    	var homepage=$(this).val();
    	$.post("/${oname}/page/updateHomepage.action",{"subtype":'${subtype}',"marketingSet.homepage":homepage},function(data){
			    if(data>0){
			    	layer.msg('设置成功！', {icon: 6, time: 1500}); 
			    }else{
			    	layer.msg('设置失败！', {icon: 5, time: 2000});
			    }
    	});
    });
    
 });
</script>
<div class="wrap_content">
<h3>首页设置</h3>
	<form action="" id="form1">
	<div id="mhp">
		<s:iterator value="list" var="a">
			<ul>
				<li>${a.groupname }
				<s:if test="#a.site.size()==1">
					<s:iterator value="#a.site[0].pages" var="c">
						<ul  style="display: none">
							<li style="border-bottom:1px dashed #666666;"><label style="width: 100%" for="homepage${c.id }" >${c.name }<input style="float: right;" type="radio" name="homepage" id="homepage${c.id }"  value="${c.id }"/></label></li>
						</ul>
					</s:iterator>
				</s:if>
				<s:else>
				<s:iterator value="#a.site" var="b">
					<ul style="display: none">
						<li>${b.name }
						<s:iterator value="#b.pages" var="c">
							<ul  style="display: none">
								<li style="border-bottom:1px dashed #666666;"><label style="width: 100%" for="homepage${c.id }" >${c.name }<input style="float: right;" type="radio" name="homepage" id="homepage${c.id }"  value="${c.id }"/></label></li>
							</ul>
						</s:iterator>
						</li>
					</ul>
				</s:iterator>
				</s:else>
				</li>
			</ul>
		</s:iterator>
	</div>
	</form>
</div>