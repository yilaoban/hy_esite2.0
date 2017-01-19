<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/js/slick/slick.css" />
<link rel="stylesheet" type="text/css" media="screen,print" href="/js/jqprint/print.css" />
<div class="slick">
  <s:iterator value="vmList" var="vm">${vm}</s:iterator>
</div>
<div class="toolbar mt20">
  <button class="btn btn-primary" onclick="select()">选择</button>
</div>
<script language="javascript" src="/js/slick/slick.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.slick').slick({
			infinite : false,
			slidesToShow : 3,
			slidesToScroll : 3
		});

		$(".slick-slide").click(function() {
			$(".slick-slide").removeClass("slick-selected");
			$(this).addClass("slick-selected");
		});

	});

	function select() {
		if ($(".slick-selected").length == 0) {
			return;
		}
		var vm = $(".slick-selected").attr("id");
		var name = $(".slick-selected").find(".slick-title").html();
		$.ajax({
			url : "/${oname}/setting/user_print_add.action",
			type : "post",
			data : {
				"print.vm" : vm,
				"print.name" : name
			},
			cache : false,
			success : function(res) {
				if (res > 0) {
					window.parent.location.reload();
				}
			}
		});
	}

	
</script>