<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function saveOcxfnum() {
	$.ajax({
		url : "/${oname }/setting/saveOcxfnum.action",
		type : "post",
		data : $("#form").serialize(),
		cache : false,
		success : function(data) {
			if (data > 0) {
				alertMsg("设置成功");
			} else {
				alertMsg("设置失败");
			}
		}
	});
}
function alertMsg(msg) {
	easyDialog.open({
		container : {
			content : msg
		},
		autoClose : 1000
	});
}
</script>
<div class="wrap_content">
	<ul class="c_switch">
    <li>
      <a href="/${oname}/setting/rmbRule.action">充值规则</a>
    </li>
    <li>
      <a href="/${oname}/setting/userLevel.action">会员等级</a>
    </li>
    <li >
      <a href="/${oname}/setting/userRmb.action">用户</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_print.action">打印模板</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_xf_desc.action">收银系统</a>
    </li>
     <li class="selected">
        <a href="/${oname}/setting/user_oc_xf.action">消费积分</a>
      </li>
  </ul>
  <p class="clearfix"></p>

	<form class="formview formview2 jNice" method="post" id="form">
        <dl>
          <dt>线下消费多少元获得1积分</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.ocxfnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.ocxfnum }">
            <span>积分/人</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" class="btn btn-primary" onclick="saveOcxfnum()" value="保存" class="btn btn-default">
          </dd>
        </dl>
      </form>
</div>
