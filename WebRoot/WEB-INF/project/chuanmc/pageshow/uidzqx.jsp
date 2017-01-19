<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<meta name="viewport" content="width=320, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "/${oname}/user/dz_qx.action",
			type : "post",
			data : {
				"dz.hyuid" : "${kv}"
			},
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode == 0) {
						var dz = res.dz;
						if (dz) {
							$(".weui_msg .weui_icon_area").html('<img src="'+dz.headimgurl+'" style="width:100px;"/>')
							$(".weui_msg .weui_msg_title").html(dz.nickname);
							if (dz.t1 > 0) {
								$("#t1").prop("checked", true);
							}
							if (dz.t2 > 0) {
								$("#t2").prop("checked", true);
							}
							if (dz.t3 > 0) {
								$("#t3").prop("checked", true);
							}
							if (dz.t4 > 0) {
								$("#t4").prop("checked", true);
							}
							if (dz.t5 > 0) {
								$("#t5").prop("checked", true);
							}
							if (dz.t6 > 0) {
								$("#t6").prop("checked", true);
							}
							if (dz.t7 > 0) {
								$("#t7").prop("checked", true);
							}

							$(".weui_switch").change(function() {
								var type = $(this).attr("id");
								var checked = $(this).prop("checked");
								$.ajax({
									url : "/${oname}/user/dz_edit.action",
									data : {
										"dz.id" : dz.id,
										"dz.type" : type,
										"dz.type_val" : checked ? 1 : 0
									},
									type : "post",
									cache : false,
									success : function(res) {
										console.log(res);
									}
								});
							});
						}
					} else {
						$.alert(res.errmsg, function() {
							closeWindow();
						});
					}
				} else {
					$.alert("网络异常");
				}
			}
		});

	});

	function closeWindow() {
		wx.closeWindow();
	}
</script>
<div class="weui_msg">
  <div class="weui_icon_area"></div>
  <div class="weui_text_area">
    <h2 class="weui_msg_title">操作成功</h2>
  </div>
</div>
<div class="weui_cells weui_cells_form">
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">收营员</div>
    <div class="weui_cell_ft">
      <input id="t1" class="weui_switch" type="checkbox">
    </div>
  </div>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">预约通知</div>
    <div class="weui_cell_ft">
      <input id="t2" class="weui_switch" type="checkbox">
    </div>
  </div>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">服务评价通知</div>
    <div class="weui_cell_ft">
      <input id="t3" class="weui_switch" type="checkbox">
    </div>
  </div>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">商城订单通知</div>
    <div class="weui_cell_ft">
      <input id="t4" class="weui_switch" type="checkbox">
    </div>
  </div>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">消费成功通知</div>
    <div class="weui_cell_ft">
      <input id="t5" class="weui_switch" type="checkbox">
    </div>
  </div>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">充值通知</div>
    <div class="weui_cell_ft">
      <input id="t6" class="weui_switch" type="checkbox">
    </div>
  </div>
  <div class="weui_cell weui_cell_switch">
    <div class="weui_cell_hd weui_cell_primary">管理权限</div>
    <div class="weui_cell_ft">
      <input id="t7" class="weui_switch" type="checkbox">
    </div>
  </div>

</div>