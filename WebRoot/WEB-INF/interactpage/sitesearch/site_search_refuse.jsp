<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <span>未开通站内搜索功能</span>
  <a href="javascript:;" onclick="openSiteSearch()">点此开通</a>
</div>
<script type="text/javascript">
	function openSiteSearch() {
		$.ajax({
			url : "/${oname}/interact/site_search_open.action",
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("开通成功", function() {
						window.location.reload();
					});
				} else {
					layer.alert("开通失败",0);
				}
			}
		});
	}

	
</script>