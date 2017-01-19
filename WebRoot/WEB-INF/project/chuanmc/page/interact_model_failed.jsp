<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="switch_tab_div pb10">
		<span>不存在的互动模块!</span>
		<input type="button" onclick="interact_config_index('${oname}')" value="选择所需互动"/>
		<p class="clearfix"></p>
	</div> 
</div>
<script type="text/javascript">
		$(document).ready(function(){
			interact_config_index('${oname}');
		});
</script>
