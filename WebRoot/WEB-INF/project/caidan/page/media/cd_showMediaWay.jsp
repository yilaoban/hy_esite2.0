<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<form class="formview jNice">
	<div style="width: 100%">
		<dl>
	    	<dt>媒体名称:</dt>
			<dd>
    			<input type="text" value="${adway.media.name}" disabled="disabled" class="text-medium" data-provide="typeahead" id="media" autocomplete="off"/></dd>
	    	</dd>
	    </dl>
		<dl>
	    	<dt>印刷日期:</dt>
			<dd><input type="text" value="${adway.qwd.name}" class="text-medium" data-provide="typeahead" id="qwd"  autocomplete="off"/></dd>
	    </dl>
		<dl>
	    	<dt>画&nbsp;&nbsp;&nbsp;&nbsp;面:</dt>
			<dd><input type="text" value="${adway.wd.name}" class="text-medium" data-provide="typeahead" id="wd"  autocomplete="off"/></dd>
	    </dl>
	    <dl>
   			<dt>印刷类型:</dt>
			<dd>
				<label><input type="radio" name="type" value="定量"  <s:if test='adway.type == "定量"'>checked="checked"</s:if> onclick="$('#shownum').show()">定量</label>
				<label><input type="radio" name="type" value="不定量" <s:if test='adway.type == "不定量"'>checked="checked"</s:if> onclick="$('#shownum').hide();$('#num').val(0)">不定量</label>
			</dd>
   		</dl>
   		<dl id="shownum" <s:if test='adway.type == "不定量"'>style="display: none;"</s:if> >
   			<dt>印刷数量:</dt>
			<dd><input type="number" value="${adway.num }" class="text-medium" data-provide="typeahead" id="num"  autocomplete="off"/></dd>
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
    		<dt></dt>
    		<dd>
				<button type="button" class="btn btn-primary" id="editWay">修改</button>
    		</dd>
    	</dl>
	</div>
</form>


<div style="width: 100%">
</div>
<script type="text/javascript">
	$("#editWay").click(function(){
		var adwayid=${adway.id};
		var qwd=$("#qwd").val();
		var wd=$("#wd").val();
		var media=$("#media").val();
		var url=$("#wayurl").val();
		var fsurl=$("#fsurl").val();
		var type=$('input:radio[name="type"]:checked').val();
		var num = $('#num').val();
		if(type == "定量"){
			if(num == 0){
				layer.msg('请填写印刷数量!', {icon: 5, time: 2000});
				return;
			}
		}
		if(qwd==""||wd==""||media==""){
			layer.msg('参数不能为空!', {icon: 5, time: 2000});
		}else{
			 $.post('/${oname}/cd-media/editAdWay.action', {"wayid":adwayid,"qwd":qwd,"wd":wd,"media":media,"url":url,"fsurl":fsurl,"type":type,"num":num}, function (data) {
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