<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 技师列表 -->
<link rel="stylesheet" type="text/css" href="/css/hudong/419_pl/index.css" />
<div class="grey" style="margin-bottom: 40px;">
  <ul></ul>
</div>
<div class="c_bottom" style="position:<s:if test='method=="E"'>absolute</s:if><s:else>fixed</s:else>;bottom:0px;">
  <ul>
    <li>
      <a href="#" id="pjs">技师列表</a>
    </li>
    <li>
      <a href="#" id="pjc">全部评价</a>
    </li>
  </ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "/${oname}/user/pj_servicers.action",
			type : "get",
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode != 0) {
						$.alert(res.errmsg);
						return;
					}
					// link
					var pjp = res.pjp;
					if (pjp) {
						$("#pjs").attr("href", "/${oname}/user/show/" + pjp.pjsid + "/wxn/" + res.kv + ".html");
						$("#pjc").attr("href", "/${oname}/user/show/" + pjp.pjcid + "/wxn/" + res.kv + ".html");
					}
					// servicer list
					var list = res.list;
					for (var i = 0; i < list.length; i++) {
						var ser = list[i];
						var wdList = ser.wdList;
						var html = '';
						html += '<li class="con0607" onclick="showComment(' + ser.id + ')">';
						html += '  <h2>技师：' + ser.name + '</h2>';
						html += '  <ul>';
						for (var j = 0; j < wdList.length; j++) {
							var wd = wdList[j];
							html += '<li>';
							html += '<span style="display:inline-block;width:99px;">' + wd.name + '：</span>';
							for (var k = 0; k < 5; k++) {
								if (k < wd.level) {
									html += '<i class="xz"></i>';
								} else {
									html += '<i></i>';
								}
							}
						}
						html += '</li>';
						$(".grey>ul").append(html);
					}
				}
			}
		});

	});

	function showComment(id) {
		var url = $("#pjc").attr("href");
		if (url != "#") {
			url = url.split(".")[0] + "-" + id + ".html";
			window.location.href = url;
		}
	}

	
</script>