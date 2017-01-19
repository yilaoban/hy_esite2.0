<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div>
		<select  id="appselect">
			<option value="-1">请选择</option>
		</select>
		<script type="text/javascript">
			$(document).ready(function(){
				$.post("/${oname}/page/baseAppLeft.action",function(data){
						$.each(data,function(index,value){
							if(value.type=="B"){
								$("#appselect").append("<option value='1' atype='B'>社区管理</option>");
							}else{
								$("#appselect").append("<option value='"+value.entity+"' atype='"+value.type+"'>"+value.name+"</option>");
							}
						});
				});
				
				
				$("#appselect").bind("change",function(){
					var type=$(this).find("option:selected").attr("atype");
					if(type=="B"){
						window.location="/${oname}/bbs/index.action";
					}else if(type=="C"){
						window.location="/${oname}/interact/cbSender.action?cbid="+$(this).val();
					}
				});
			});
		</script>
	</div>