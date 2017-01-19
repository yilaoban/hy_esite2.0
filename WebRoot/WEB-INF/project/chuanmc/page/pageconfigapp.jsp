<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	function pageconfig(url){
		$(".wrap_content").load(url);
	}
	function wxpublish(url){
		$(".wrap_content").load(url);
	}
</script>

<div class="wrap_content">
	<script type="text/javascript">
		$(".wrap_content").load("/${oname }/page/pageconfig_iframe.action?siteid=${siteid }&stype=${stype }");
	</script>
</div>