<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">微活动</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">微网站</a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">微现场</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content" style="padding-top:15px;">
    <div role="tabpanel" class="tab-pane active" id="home">
    	<s:iterator value="map['E']" var="g">
			<s:iterator value="#g.site" var="p" status="st">
			<label class="spalash" style="display:inline-block;">
				<input type="radio" name="copysiteid" value="${p.id }" <s:if test='#p.id==siteid'>checked="checked"</s:if> ><span>${g.groupname }</span>
			</label>
			</s:iterator>
		</s:iterator>
		<div class="clearfix"></div> 
    </div>
    <div role="tabpanel" class="tab-pane" id="profile">
    	<s:iterator value="map['W']" var="g">
			<s:iterator value="#g.site" var="p">
			<label class="spalash" style="display:inline-block;">
				<input type="radio" name="copysiteid" value="${p.id }" <s:if test='#p.id==siteid'>checked="checked"</s:if> ><span>${g.groupname }</span>
			</label>
			</s:iterator>
		</s:iterator>
		<div class="clearfix"></div> 
    </div>
    <div role="tabpanel" class="tab-pane" id="messages">
    	<s:iterator value="map['X']" var="g">
			<p style="margin-top:10px;"><b>${g.groupname }</b></p>
			<div class="clearfix">
			<s:iterator value="#g.site" var="p">
			<label class="spalash" style="display:inline-block;">
				<input type="radio" name="copysiteid" value="${p.id }" <s:if test='#p.id==siteid'>checked="checked"</s:if> ><span>${g.id}${p.name }</span>
			</label>
			</s:iterator>
			</Div>
		</s:iterator>
    </div>
  </div>

</div>
		
	<div class="tac">
	<input type="button" class="btn btn-primary" value="确定" onclick="copy_submit(this)">
	</div>
<script type="text/javascript">
function copy_submit(obj){
	if($("input[name='copysiteid']:checked").length == 0){
		layer.msg('请选择一个站点！',{icon: 6, time: 1500});
		return;
	}
	var index = layer.msg('正在努力复制,请稍等...', {icon: 16,time:0});
	$(obj).removeAttr("onclick");
	$.post("save_new_page.action",{"copysiteid":$("input[name='copysiteid']:checked").val(),"pageid":${pageid}},function(data){
		layer.close(index);
		if(data==1){
			layer.msg('复制成功！',{icon: 6, time: 1500},function(){
				parent.window.location.reload();
			});
		}
	});
}
</script>