<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<style>
.mhp li {margin:10px}
</style>
<script type="text/javascript">
$(document).ready(function() {  
    $(".mhp li").click(function(){
    	$(this).parent("ul").siblings().children("li").children("ul").hide();
		$(this).children("ul").show();
    });
    $("#homepage${marketingSet.homepage}").attr("checked","true");
    $("#homepage${marketingSet.homepage}").parents("ul").show();
    $("#homepage${marketingSet.homepage}").parents("ul").siblings().show();
    
    
    $("#userpage${marketingSet.userpage}").attr("checked","true");
    $("#userpage${marketingSet.userpage}").parents("ul").show();
    $("#userpage${marketingSet.userpage}").parents("ul").siblings().show();
  
    $('input:radio[name="userpage"],input:radio[name="homepage"]').click(function(){
    	var homepage=$('input:radio[name="homepage"]:checked').val();
    	var userpage=$('input:radio[name="userpage"]:checked').val();
    	$.post("/${oname}/page/updateHomepage.action",{"subtype":'${subtype}',"marketingSet.userpage":userpage,"marketingSet.homepage":homepage},function(data){
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
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">首页设置</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">个人中心</a></li>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
    		<form action="" id="form1">
			<div class="mhp">
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
    <div role="tabpanel" class="tab-pane" id="profile">
			<form action="" id="form2">
			<div class="mhp">
				<s:iterator value="list" var="a">
					<ul>
						<li>${a.groupname }
						<s:if test="#a.site.size()==1">
							<s:iterator value="#a.site[0].pages" var="c">
								<ul  style="display: none">
									<li style="border-bottom:1px dashed #666666;"><label style="width: 100%" for="userpage${c.id }" >${c.name }<input style="float: right;" type="radio" name="userpage" id="userpage${c.id }"  value="${c.id }"/></label></li>
								</ul>
							</s:iterator>
						</s:if>
						<s:else>
						<s:iterator value="#a.site" var="b">
							<ul style="display: none">
								<li>${b.name }
								<s:iterator value="#b.pages" var="c">
									<ul  style="display: none">
										<li style="border-bottom:1px dashed #666666;"><label style="width: 100%" for="userpage${c.id }" >${c.name }<input style="float: right;" type="radio" name="userpage" id="userpage${c.id }"  value="${c.id }"/></label></li>
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
  </div>
	
</div>