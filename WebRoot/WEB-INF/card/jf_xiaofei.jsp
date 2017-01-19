<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 消费积分 -->
<link rel="stylesheet" type="text/css" href="/css/shouye/jf/index.css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="cm_title block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">${blocks[0].title}</div>
<div class="cm_inputContent">
  <span class="cm_je">金额</span>
  <input type="number" class="cm_input" />
  <span class="cm_yn">元</span>
</div>
<input type="button" class="cm_button" onclick="consume('X')" value="现金消费" />
<input type="button" class="cm_button2" onclick="consume('H')" value="会员卡消费" />
<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
	var dtpageid = 0;
	$(document).ready(function() {
		var sourceid = $("#hy_kv").val();
		if (!sourceid) {
			return;
		}
		$.ajax({
			url : '/${oname}/user/dz_source.action',
			type : 'get',
			data : {
				'sourceid' : sourceid
			},
			cache : false,
			success : function(data) {
				if (data) {
					$(".cm_input").attr("placeholder", "满" + data.rmbjf + "元得一个积分");
					dtpageid = data.dtpageid;
				}
			}
		});

	});
	
	function consume(type){
		if (dtpageid == 0) {
			$.alert("来源不存在");
			return;
		}
		var rmb = $(".cm_input").val().trim();
		var patt = /^\d+(\.\d{1,2})?$/;
		if (!patt.test(rmb)) {
			$.alert("请输入正确的消费金额");
			return;
		}
		$.ajax({
			url : '/${oname}/user/dz_way.action',
			type : 'get',
			data : {
				'rmb' : rmb
			},
			cache : false,
			success : function(data) {
				if (data) {
					if (data.dzway > 0) {
						window.location.href = "/${oname}/user/wxshow/" + dtpageid + "/wxn/" + type+ "-"+data.dzway + ".html";
					} else {
						$.alert(data.hydesc);
					}
				} else {
					$.alert("错误");
				}
			}
		});
		
	}
	
</script>