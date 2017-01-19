<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<dl>
	<dt>标签</dt>
	<dd class="showinput">
		<input type="text" class="text-medium showinput1" name="tagJson.tag1" value="${tags.tag1.name }" data-provide="typeahead" 	data-source='${tagsjosn }'/>
		<input type="text" class="text-medium showinput2" name="tagJson.tag2" value="${tags.tag2.name }" data-provide="typeahead"	data-source='${tagsjosn }'/>
		<input type="text" class="text-medium showinput3" name="tagJson.tag3" value="${tags.tag3.name }" data-provide="typeahead"	data-source='${tagsjosn }'/>
		<input type="text" class="text-medium showinput4" name="tagJson.tag4" value="${tags.tag4.name }" data-provide="typeahead"	data-source='${tagsjosn }'/>
		<input type="text" class="text-medium showinput5" name="tagJson.tag5" value="${tags.tag5.name }" data-provide="typeahead"	data-source='${tagsjosn }'/>
		<a onclick="showinput()"><b>+</b>更多</a>
	</dd>
</dl>

<style>
	.showinput a{
		border:1px solid #00a0e9;background-color:#00a0e9;color:#fff;cursor:pointer;
	}
	.showinput3,.showinput4,.showinput5{
		display:none;
	}
	.showinput input{
		margin-top:4px;
	}
</style>
<script type="text/javascript">
	var tag1 = '${tags.tag1.name }';
	var tag2 = '${tags.tag2.name }';
	var tag3 = '${tags.tag3.name }';
	var tag4 = '${tags.tag4.name }';
	var tag5 = '${tags.tag5.name }';
	$(document).ready(function() {
		if(tag1 == ""){
			if(tag2 != "" || tag3 != "" || tag4 != ""|| tag5 != ""){
				$(".showinput1").hide();
			}
		}
		if(tag2 != ""){
			$(".showinput2").show();
		}else{
			$(".showinput2").hide();
		}
		if(tag3 != ""){
			$(".showinput3").show();
		}
		if(tag4 != ""){
			$(".showinput4").show();
		}
		if(tag5 != ""){
			$(".showinput5").show();
		}
		
	});
	
	function showinput(){
		$(".showinput1").show();
		$(".showinput2").show();
		$(".showinput3").show();
		$(".showinput4").show();
		$(".showinput5").show();
	}
</script>
