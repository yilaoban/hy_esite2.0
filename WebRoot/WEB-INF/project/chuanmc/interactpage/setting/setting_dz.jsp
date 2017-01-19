<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="toolbar">
    <button class="btn btn-primary" onclick="dz()">新增管理员</button>
  </div>
  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>头像</th>
        <th>昵称</th>
        <th>收营员</th>
        <th>预约</th>
        <th>服务评价</th>
        <th>商城订单</th>
        <th>消费成功</th>
        <th>充值</th>
        <th>权限</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.dzList" var="dz">
        <tr>
          <td>
            <img src="${dz.headimgurl}" style="width: 50px; height: 50px;" />
          </td>
          <td>${dz.nickname}</td>
          <td>
            <input type="checkbox" id="t1_${dz.id}" <s:if test="t1>0">checked="checked"</s:if> />
          </td>
          <td>
            <input type="checkbox" id="t2_${dz.id}" <s:if test="t2>0">checked="checked"</s:if> />
          </td>
          <td>
            <input type="checkbox" id="t3_${dz.id}" <s:if test="t3>0">checked="checked"</s:if> />
          </td>
          <td>
            <input type="checkbox" id="t4_${dz.id}" <s:if test="t4>0">checked="checked"</s:if> />
          </td>
          <td>
            <input type="checkbox" id="t5_${dz.id}" <s:if test="t5>0">checked="checked"</s:if> />
          </td>
          <td>
            <input type="checkbox" id="t6_${dz.id}" <s:if test="t6>0">checked="checked"</s:if> />
          </td>
          <td>
            <input type="checkbox" id="t7_${dz.id}" <s:if test="t7>0">checked="checked"</s:if> />
          </td>
          <td>
            <a href="javascript:;" onclick="user('${dz.hyuid}')">编辑</a>
            |
            <a href="javascript:;" onclick="delDz('${dz.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<div id="qr" style="display: none;">
  <div id="qrcode" style="margin: 30px;"></div>
</div>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 200,
			height : 200
		});
		qrcode.clear();
		qrcode.makeCode("${pageDomain}/${oname}/user/wxshowp/uidz/kv.html");

		$("input[type='checkbox']").change(function() {
			var slt = $(this).attr("id").split("_");
			var type = slt[0];
			var id = slt[1];
			var checked = $(this).prop("checked");
			$.ajax({
				url : "/${oname}/setting/dz_edit.action",
				data : {
					"dz.id" : id,
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
	});

	function dz() {
		layer.open({
			type : 1,
			area : [ '260px', '307px' ],
			title : "使用微信扫描二维码",
			content : $("#qr").html(),
			end : function() {
				window.location.reload();
			}
		});
	}

	function delDz(id) {
		layer.confirm('确定删除吗？', function() {
			$.ajax({
				url : "/${oname}/setting/dz_del.action",
				data : {
					"dz.id" : id
				},
				type : "post",
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("删除成功", function() {
							window.location.reload();
						});
					} else {
						layer.alert("删除失败");
					}
				}
			});
		});
	}

	function user(id) {
		var index = layer.load();
		$.ajax({
			url : "/${oname}/setting/dz_user.action",
			data : {
				"user.id" : id,
			},
			type : "post",
			cache : false,
			success : function(res) {
				layer.close(index);

				var username = "";
				var password = "";
				if (res) {
					username = res.username || "";
					password = res.password;
				}
				var content = '';
				content += '<form style="padding: 20px;">';
				content += '  <p>';
				content += '    <label style="width: 60px;">用户名：</label>';
				content += '    <input id="username" value="'+username+'"/>';
				content += '  </p>';
				content += '  <p>';
				content += '    <label style="width: 60px;">密码：</label>';
				content += '    <input id="password" value=""/>';
				content += '  </p>';
				content += '  <p>此用户名,密码为收银系统登录的用户名,密码</p>';
				content += '</form>';
				layer.open({
					type : 1,
					title : "账户",
					content : content,
					btn : [ '确定', '取消' ],
					yes : function() {
						editUser(id);
					}
				});
			}
		});

	}

	function editUser(id) {
		var username = $("#username").val().trim();
		var password = $("#password").val().trim();
		$.ajax({
			url : "/${oname}/setting/dz_user_edit.action",
			data : {
				"user.id" : id,
				"user.username" : username,
				"user.password" : password
			},
			type : "post",
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("保存成功", function() {
						window.location.reload();
					});
				} else {
					layer.alert("保存失败");
				}
			}
		});
	}

	
</script>