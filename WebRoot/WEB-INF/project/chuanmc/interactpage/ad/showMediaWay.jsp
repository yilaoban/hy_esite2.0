<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div style="width: 100%">
    <dd>媒&nbsp;&nbsp;&nbsp;&nbsp;体:<input type="text" value="${adway.media.name}" disabled="disabled" class="text-medium" data-provide="typeahead" id="media" autocomplete="off"/></dd>
	<dd>期&nbsp;&nbsp;&nbsp;&nbsp;号:<input type="text" value="${adway.qwd.name}" class="text-medium" data-provide="typeahead" id="qwd"  autocomplete="off"/></dd>
	<dd>版&nbsp;&nbsp;&nbsp;&nbsp;本:<input type="text" value="${adway.wd.name}" class="text-medium" data-provide="typeahead" id="wd"  autocomplete="off"/></dd>
	<dd>默认url:<input type="text" value="${adway.url}" class="text-medium" id="wayurl" autocomplete="off"/></dd>
	<dd>粉丝url:<input type="text" value="${adway.fsurl}" class="text-medium" id="fsurl" autocomplete="off"/><font color="red">注:粉丝url为空跳默认url</font></dd>
</div>
<div style="width: 100%;height: 100%">
	选择应用站点:
	<s:iterator value="list" var="l">
		<input type="radio" name="gid" <s:if test="groupName == #l.groupname">checked="checked"</s:if> value="${l.id }"/>${l.groupname }
	</s:iterator>
</div>
<button type="button" class="btn btn-primary" id="editWay">确认修改</button>
<script type="text/javascript">
	$("#editWay").click(function(){
		var adwayid=${adway.id};
		var qwd=$("#qwd").val();
		var wd=$("#wd").val();
		var gid=$("input:radio[name='gid']:checked").val();
		var url=$("#wayurl").val();
		var fsurl=$("#fsurl").val();
		if(qwd==""||wd==""||media==""){
			layer.msg('参数不能为空!', {icon: 5, time: 2000});
		}else if(!gid){
			layer.msg('没有选择应用站点!', {icon: 5, time: 2000});
		}else{
			 $.post('/${oname}/interact/editMediaWay.action', {"wayid":adwayid,"qwd":qwd,"wd":wd,"gid":gid,"url":url,"fsurl":fsurl}, function (data) {
				if(data==1){
					layer.msg('保存成功!', {icon: 5, time: 2000});
					window.parent.location.reload();
				}else{
					layer.msg('保存失败!', {icon: 5, time: 2000});
				}
			 });
			
		}
	});
</script>