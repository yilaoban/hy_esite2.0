<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" media="screen,print" href="/js/jqprint/print.css" />
<div class="wrap_content">
  <ul class="c_switch">
    <li>
      <a href="/${oname}/setting/rmbRule.action">充值规则</a>
    </li>
    <li>
      <a href="/${oname}/setting/userLevel.action">会员等级</a>
    </li>
    <li>
      <a href="/${oname}/setting/userRmb.action">用户</a>
    </li>
    <li class="selected">
      <a href="/${oname}/setting/user_print.action">打印模板</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_xf_desc.action">收银系统</a>
    </li>
     <li>
          <a href="/${oname}/setting/user_oc_xf.action">消费积分</a>
        </li>
  </ul>
  <div class="toolbar clearfix">
    <button class="btn btn-primary" onclick="vm()">选择模板</button>
  </div>
  <s:if test="print">
    <div class="example" style="float: left; padding: 18px; border: 2px solid #ddd;">${vm}</div>
    <div style="float: right;">
      <p>
        <label style="width: 85px;">模板：</label>
        <span>${print.name}</span>
      </p>
      <p>
        <label style="width: 85px;">上传logo：</label>
        <input type="file" id="upload" name="img" style="display: inline;" />
        <input type="hidden" id="upload_val" value="${print.logo}" />
      </p>
      <p>
        <label style="width: 85px;">二维码地址：</label>
        <input id="url" value="${print.url}" />
      <p>
        <button class="btn btn-primary" onclick="save('${print.id}')">保存</button>
        <button class="btn btn-primary" onclick="print()">预览</button>
    </div>
  </s:if>
  <div class="clearfix"></div>
</div>
<%@include file="/WEB-INF/page/includeAppSites.jsp"%>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script language="javascript" src="/js/jqprint/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#upload").change(function() {
			$.ajaxFileUpload({
				url : '/page/picUpload.action',
				secureuri : false,
				fileElementId : "upload",
				dataType : 'json',
				success : function(data, status) {
					if (status == "success") {
						$(".example .logo").attr("src", "${imgDomain}" + data);
						$("#upload_val").val("${imgDomain}" + data);
					}
				}
			});
		});
	});

	function vm() {
		layer.open({
			type : 2,
			area : [ '900px', '700px' ],
			title : "选择模板",
			content : "/${oname}/setting/user_print_vm.action"
		});
	}

	function save(id) {
		var logo = $("#upload_val").val();
		var url = $("#url").val().trim();
		$.ajax({
			url : "/${oname}/setting/user_print_update.action",
			type : "post",
			data : {
				"print.id" : id,
				"print.logo" : logo,
				"print.url" : url
			},
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("保存成功", function() {
						window.location.reload();
					});
				}
			}
		});
	}

	function print() {
		$(".example>div").jqprint();
	}

	
</script>