<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#savedMedia").click(function(){
			var name=$('#name').val();
			if(name==""){
				layer.msg('请填写名称!', {icon: 5, time: 2000});
				return;
			}
			var area=$("#area").val();
			var hydesc=$('#hydesc').val();
			var hztime = $('#hztime').val();
			if(hztime == ""){
				layer.msg('合作时间不能为空!', {icon: 5, time: 2000});
				return;
			}
			if(area==""){
				layer.msg('区域不能为空!', {icon: 5, time: 2000});
				return;
			}else{
				 $.post('/${oname}/cd-media/savedMedia.action', {"area":area,"media.name":name,"media.hydesc":hydesc,"media.hztime":hztime}, function (data) {
					if(data==-1){
						layer.msg('区域不能为空!', {icon: 5, time: 2000});
					}else if(data==-2){
						layer.msg('请选择系统提供的区域!', {icon: 5, time: 2000});
					}else{
						layer.msg('添加成功！', {icon: 6, time: 1500}, function(){
							window.location.href = "/${oname}/cd-media/adMediaList.action";
						}); 
					}
				 });
				
			}
		});
	});
</script>
<div class="wrap_content left_module">
	<form action="/${oname }/cd-media/savedMedia.action" id="form1" class="formview" method="post">
	<div class="toolbar pb10">
	  	<ul class="c_switch">
			<li class="selected"><a href="#">新增媒体</a></li>
		</ul>
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>媒体名称</dt>
			<dd>
				<input type="text" name="media.name" id="name"/>
			</dd>
		</dl>
		<dl>
		 	<dt>媒体区域</dt>
			<dd>
				<input type="text" name="area" id="area"/>
			</dd>
		</dl>
		<dl>
		 	<dt>合作时间</dt>
			<dd>
				<input id="hztime" type="text" name="media.hztime" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			</dd>
		</dl>
		<dl>
		 	<dt>媒体简介</dt>
			<dd>
				<textarea  maxlength="200" name="media.hydesc" id="hydesc"></textarea>
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<button type="button" class="btn btn-primary" id="savedMedia">保存</button>
		</dd>
	</dl>
	</form>
</div>
