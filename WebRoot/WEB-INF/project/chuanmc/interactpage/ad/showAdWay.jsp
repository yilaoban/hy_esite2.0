<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<form class="formview jNice">
	<div style="width: 100%">
	    <dl>
	    	<dt>媒&nbsp;&nbsp;&nbsp;&nbsp;体:</dt>
			<dd>
	    	<input type="text" value="${adway.media.name}" class="text-medium" data-provide="typeahead" id="media" autocomplete="off"/>
	    	</dd>
	    </dl>
	    <dl>
	    	<dt>期&nbsp;&nbsp;&nbsp;&nbsp;号:</dt>
			<dd><input type="text" value="${adway.qwd.name}" class="text-medium" data-provide="typeahead" id="qwd"  autocomplete="off"/></dd>
	    </dl>
	    <dl>
	    	<dt>版&nbsp;&nbsp;&nbsp;&nbsp;本:</dt>
			<dd><input type="text" value="${adway.wd.name}" class="text-medium" data-provide="typeahead" id="wd"  autocomplete="off"/></dd>
	    </dl>
	    <dl>
	    	<dt>默认url:</dt>
			<dd><input type="text" value="${adway.url}" class="text-medium" id="wayurl" autocomplete="off"/></dd>
	    </dl>
	    <dl>
	    	<dt>粉丝url:</dt>
			<dd><input type="text" value="${adway.fsurl}" class="text-medium" id="fsurl" autocomplete="off"/><font color="red">注:粉丝url为空跳默认url</font></dd>
	    </dl>
	    <dl>
	    	<dt>选择应用站点:</dt>
	    	<dd>
	    		<s:iterator value="list" var="l">
					<input type="radio" name="gid" <s:if test="groupName == #l.groupname">checked="checked"</s:if> value="${l.id }"/>${l.groupname }
				</s:iterator>
	    	</dd>
	    </dl>
	    <dl>
	    	<dt></dt>
	    	<dd>
				<button type="button" class="btn btn-primary" id="editWay">确认修改</button>
	    	</dd>
	    </dl>
	</div>
</form>
<script type="text/javascript">
	$("#editWay").click(function(){
		var adwayid=${adway.id};
		var qwd=$("#qwd").val();
		var wd=$("#wd").val();
		var media=$("#media").val();
		var gid=$("input:radio[name='gid']:checked").val();
		var url=$("#wayurl").val();
		var fsurl=$("#fsurl").val();
		if(qwd==""||wd==""||media==""){
			layer.msg('参数不能为空!', {icon: 5, time: 2000});
		}else if(!gid){
			layer.msg('没有选择应用站点!', {icon: 5, time: 2000});
		}else{
			 $.post('/${oname}/interact/editAdWay.action', {"wayid":adwayid,"qwd":qwd,"wd":wd,"media":media,"gid":gid,"url":url,"fsurl":fsurl}, function (data) {
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