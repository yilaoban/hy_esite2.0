<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">扫一扫</h4>
</div>
<div class="wrap_content">
	<div id="qrcode"></div>
</div>
	<script type="text/javascript" src="/js/qrcode.js"></script> 
			<script type="text/javascript">
			    var qrcode = new QRCode(document.getElementById("qrcode"), {
					width : 200,
					height : 200
				});

				function makeCode () {
					var pageid=$("#pageid").val();
					var elText = "http://site1.huiyee.com/user/preview.action?pageid="+pageid;
					qrcode.makeCode(elText);
				}

makeCode();
			</script>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>