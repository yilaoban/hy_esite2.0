<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function() {  
			var um = UE.getEditor('myEditor');
});

function save(){
	var title=$("#title").val();
	if(title==""){
		layer.msg('标题不能为空!', {icon: 5, time: 2000});
		return;
	}
	var index = layer.msg('正在保存,请稍等...', {icon: 16,time:0});
	$.ajax({
        cache: true,
        type: "POST",
        url:"/${oname}/product/updateTabIndex.action",
        data:$('#form1').serialize(),// 你的formid
        async: false,
        error: function(request) {
            layer.alert("Connection error",0);
        },
        success: function(data) {
        	layer.close(index);
            if(data==1){
            	layer.msg('保存成功！', {icon: 6, time: 1500}, function(){
					window.location.reload();
				}); 
            }else{
            	layer.msg('保存失败!', {icon: 5, time: 2000});
            }
        }
    });
}
</script>
<div class="wrap_content">
	<ul class="c_switch">
      <li <s:if test='tabid=="f1"'>class="selected"</s:if>>
      	<a href="/${oname }/product/tabIndex.action?contentId=${contentId }&tabid=f1">
      		<s:if test='tabs.f1.title!=null'>${tabs.f1.title }</s:if><s:else>标签页1</s:else>
      	</a>
      </li>
      <li <s:if test='tabid=="f2"'>class="selected"</s:if>>
      	<a href="/${oname }/product/tabIndex.action?contentId=${contentId }&tabid=f2">
      		<s:if test='tabs.f2.title!=null'>${tabs.f2.title }</s:if><s:else>标签页2</s:else>
      	</a>
      </li>
      <li <s:if test='tabid=="f3"'>class="selected"</s:if>>
      	<a href="/${oname }/product/tabIndex.action?contentId=${contentId }&tabid=f3">
      		<s:if test='tabs.f3.title!=null'>${tabs.f3.title }</s:if><s:else>标签页3</s:else>
      	</a>
      </li>
      <li <s:if test='tabid=="f4"'>class="selected"</s:if>>
      	<a href="/${oname }/product/tabIndex.action?contentId=${contentId }&tabid=f4">
      		<s:if test='tabs.f4.title!=null'>${tabs.f4.title }</s:if><s:else>标签页4</s:else>
      	</a>
      </li>
      <li <s:if test='tabid=="f5"'>class="selected"</s:if>>
      	<a href="/${oname }/product/tabIndex.action?contentId=${contentId }&tabid=f5">
      		<s:if test='tabs.f5.title!=null'>${tabs.f5.title }</s:if><s:else>标签页5</s:else>
      	</a>
      </li>
    </ul>
    <a href="${sessionScope.contentlisturl }" class="return" title="返回"></a>
    <div style="float: left;">
    	<form id="form1">
	    	<input type="hidden" name="tab.id" value="${tabid }"/>
	    	<input type="hidden" name="tab.pid" value="${contentId }"/>
			<p>标题:<input type="text" name="tab.title" value="${tab.title }" id="title"/></p>
			<p>
				<script type="text/plain" id="myEditor" name="tab.content" style="width:100%;height:200px;">${tab.content}</script>
			</p>	
			<s:if test='tabid!=null'>
				<p><input type="button" value="保存" onclick="save()"/></p>
			</s:if>	
    	</form>
    </div>
</div>