<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<meta name="viewport" content="width=320, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<script type="text/javascript">
	function dianzhu() {
		$.ajax({
			url : "/${oname}/user/dz_add.action",
			type : "post",
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode == 0) {
						$.alert("恭喜您，添加成功", "", function() {
							wx.closeWindow();
						});
					} else {
						$.alert(res.errmsg);
					}
				} else {
					$.alert("哎呀，加入失败！");
				}
			},
			error : function(e) {
				$.alert("网络异常");
			}
		});

	}

	function closeWindow() {
		wx.closeWindow();
	}
</script>
<div class="weui_msg">
  <div class="weui_icon_area">
    <i class="weui_icon_safe weui_icon_safe_warn"></i>
  </div>
  <div class="weui_text_area">
    <h2 class="weui_msg_title">想要成为管理员吗？</h2>
    <p class="weui_msg_desc">加入管理员行列，步入人生巅峰</p>
  </div>
  <div class="weui_opr_area">
    <p class="weui_btn_area">
      <a href="javascript:dianzhu();" class="weui_btn weui_btn_primary">确定</a>
      <a href="javascript:closeWindow();" class="weui_btn weui_btn_default">取消</a>
    </p>
  </div>
  <div class="weui_extra_area">
    <a href="">查看详情</a>
  </div>
</div>