<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			</div>
		</div>
		<div id="ft">
		</div>
	<script type="text/javascript">
		$(function(){
			function reloadMenu(){
			topHeight = $(window).scrollTop();
			$("#menu").css("top",topHeight+90);
			}
			$(window).bind("scroll", function(){
			reloadMenu();
			});
			
			setMinHeight();
			});
			
			$(window).resize(function() {
			setMinHeight();
			});
			
			function setMinHeight(){
				var H = $(window).height();
				$("#inside").css("min-height",H-140);
			}
	</script>
	</body>
</html>