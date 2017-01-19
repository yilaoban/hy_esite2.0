<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="toolbar pb10">
    <a href="/${oname }/interact/addSourceIndex.action?lightType=2" class="btn btn-primary">新增二维码</a>
  </div>
  <table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
    <tr>
      <th>二维码</th>
      <th>限粉丝</th>
      <th>应用站点</th>
      <th>创建时间</th>
      <th>关联消费积分</th>
      <th>操作</th>
    </tr>
    <s:iterator value="dto.source" var="p">
      <tr>
        <td>${p.name }</td>
        <td>
          <s:if test='#p.fensi=="Y"'>是</s:if>
          <s:else>否</s:else>
        </td>
        <td>${p.spage.sitename }</td>
        <td>
          <s:date name="#p.createtime" format="yyyy-MM-dd" />
        </td>
        <td>
          <s:if test='#p.type==1'>
            <span>是</span>
            <a href="/${oname}/interact/offCheckJf.action?sourceid=${p.id}">配置</a>
            <a href="javascript:msg('${p.dzpageid }','${p.id}','消费积分二维码');">二维码</a>
          </s:if>
          <s:else>否</s:else>
        </td>
        <td>
          <a href="javascript:msg('${p.fpageid }','${p.id }','签到二维码');">二维码</a>
          <a href="/${oname }/interact/editSourceIndex.action?sourceid=${p.id}">编辑</a>
          <a href="/${oname }/interact/offCheckTemplate.action?sourceid=${p.id}">模板消息</a>
          <a href="javascript:sendResult('${p.id}');">发送记录</a>
          <a href="javascript:delsource(${p.id},'${p.name }')">删除</a>
        </td>
      </tr>
    </s:iterator>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<div id="msg" style="display: none">
  <div id="qrcode" style="float: left"></div>
  <div id="url" style="float: left"></div>
</div>
<div id="templateModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#templateModal').on('loaded.bs.modal', function(e) {
			var html = '';
			html += '<div class="modal-header">';
			html += '  <button type="button" class="close" data-dismiss="modal">';
			html += '    <span aria-hidden="true">&times;</span>';
			html += '    <span class="sr-only">Close</span>';
			html += '  </button>';
			html += '  <h4 class="modal-title">模板消息</h4>';
			html += '</div>';
			$(".modal-content").prepend(html);
		});
		$('#templateModal').on('hidden.bs.modal', function(e) {
			$(this).removeData("bs.modal");
			$(".colpick").remove();
		});
	});

	var qrcode = new QRCode(document.getElementById("qrcode"), {
		width : 200,
		height : 200
	});
	function msg(pid, sourceid, title) {
		if (pid == 0) {
			layer.msg('缺少该功能页面！', {
				icon : 5,
				time : 2000
			});
			return;
		}
		var url = "${pageDomain}/${oname }/user/wxshow/" + pid + "/wxn/" + sourceid + ".html";
		qrcode.clear();
		qrcode.makeCode(url);
		$("#url").text(url);
		layer.msg('正在生成二维码,请稍等...', {
			icon : 16,
			time : 2000
		}, function() {
			layer.alert($("#msg").html(), {
				title : title,
				area : [ '480px', '360px' ]
			});
		});
	}

	function sendResult(entityid) {
		var param = "?template.type=OCT-OCF";
		param += "&template.entityid=" + entityid;
		layer.open({
			type : 2,
			area : [ '800px', '600px' ],
			title : "发送记录",
			content : "/${oname}/template/record.action" + param
		});
	}

	function delsource(id, name) {
		var layerid = layer.confirm('确定将来源[' + name + ']删除吗?', {
			title : "确认删除"
		}, function() {
			$.post("/${oname}/interact/ocSourceDel.action", {
				inajax : 1,
				sourceid : id
			}, function(data) {
				if (data == 0) {
					layer.msg('删除失败！请重试……', {
						icon : 5,
						time : 2000
					});
				} else {
					layer.msg('删除中！请稍等……', {
						icon : 6,
						time : 1500
					}, function() {
						window.location.reload();
					});
				}
			});
		});
	}
</script>
