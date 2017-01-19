<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
 	function bigScreenSub(){
		if($("#name").val()==""){
			layer.alert("请输入标题",0);
			return false;
		}if($("#pages").val() == 0){
			layer.alert("请选择一个页面",0);
			return false;
		}else{
			$("#form1").submit();
		}
	}
	
	function switchSite(){
		$.post("/interact/ajax_get_page.action",{"siteid":$("#site").val()},
			   function(data){
					$("#pages").html("");
					$("#pages").append("<option value='0'>选择页面</option>");
					$.each( data, function(i, n){
						$("#pages").append("<option value='"+n.id+"'>"+n.name+"</option>");
					});
			   }, "json");
	}
	
</script>
<a name="maodian"></a>   
<div class="wrap_content left_module">
  <form action="save_xcBigScreen.action?xcid=${xcid}" method="post" enctype="multipart/form-data" id="form1" class="formview">
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li class="selected"><a href="">新增大屏幕</a></li>
	  </ul>
	  <a href="/${oname }/interact/edit_xcBigScreen.action?xcid=${xcid }" class="return" title="返回"></a>
	</div>
	<dl>
	 	<dt>标题</dt>
		<dd>
			<input id="name" type="text" class="text-medium" name="name"/>
			<span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>图片</dt>
		<dd>
			<input type="file" name="img"/>
		</dd>
	</dl>
	<dl>
	 	<dt>站点</dt>
		<dd>
			<select id="site" onchange="switchSite()">
				<option value ="0">选择站点</option>
				<s:iterator value="dto.siteList" var="f">
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dt>页面</dt>
		<dd>
			<select name="pageid" id="pages">
				<option value ="0">选择页面</option>
			</select>
		</dd>
	</dl>
	<dl>
	 	<dt>&nbsp;</dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" onclick="bigScreenSub()">
		</dd>
	</dl>
 </form>
 </div>