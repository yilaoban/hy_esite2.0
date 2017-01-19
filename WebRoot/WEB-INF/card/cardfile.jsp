<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<input id='cardid' type='hidden' value='${pcid}' />
<style type="text/css">
	${dto.cardcss}
</style>
<script type="text/javascript">
	$(document).ready(function(data){
		var class_Data=$("[class_data]");
		$.each(class_Data,function(index,value){
			$(this).addClass($(this).attr("class_data"));
		});
	});
</script>
${divs }
