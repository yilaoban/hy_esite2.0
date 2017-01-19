<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div style="float: right;">
    <a href="/${oname}/setting/userLevel.action" class="return" title="返回"></a>
    <input type="file" id="upload" name="media" onchange="uploadCode()" style="display: none;" />
    <button class="btn btn-primary" onmouseover="showTips(this)" onmouseout="closeTips()" onclick="file()">上传验证码</button>
  </div>

  <form action="/${oname}/setting/userLevelCode.action" class="pb10">
    <input type=hidden " name="code.levelid" value="${code.levelid}" />
    <label>状态：</label>
    <select name="code.status" style="margin-right: 20px;">
      <option value="" <s:if test="code.status==null">selected="selected"</s:if>>全部</option>
      <option value="0" <s:if test="code.status==0">selected="selected"</s:if>>未使用</option>
      <option value="1" <s:if test="code.status==1">selected="selected"</s:if>>已使用</option>
    </select>
    <label>验证码：</label>
    <input name="code.code" value="${code.code}" />
    <input type="submit" class="btn btn-success btn-sm" value="筛选" />
  </form>

  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>验证码</th>
        <th>状态</th>
        <th>更新时间</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.codeList" var="c">
        <tr>
          <td>${c.code}</td>
          <td>
            <s:if test="status==0">未使用</s:if>
            <s:elseif test="status==1">已使用</s:elseif>
          </td>
          <td>
            <s:date name="updatetime" format="yyyy-MM-dd HH:mm:ss"></s:date>
          </td>
          <td>
            <a href="javascript:;" onclick="editCode('${code}','${c.id}')">编辑</a>
            |
            <a href="javascript:;" onclick="delCode('${c.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
</div>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	var tipsIndex;
	function showTips(el) {
		var content = '';
		content += '<p>温馨提示：目前只支持<b>.txt</b>文本和<b>.xls/.xlsx</b>表格2种文件格式</p>';
		content += '<p><b>.txt</b>：一行一个验证码，不要添加其他分隔符</p>';
		content += '<p><b>.xls/.xlsx</b>：一行一个验证码，请写入第一列，不要添加表头</p>';
		tipsIndex = layer.tips(content, el, {
			area : '360px',
			time : 60000
		});
	}
	function closeTips() {
		layer.close(tipsIndex);
	}
	function file() {
		$("#upload").click();
	}
	function uploadCode() {
		var file = $("#upload").val().trim();
		if (file == "") {
			return;
		}
		var index = layer.load();
		$.ajaxFileUpload({
			url : "/${oname}/setting/uploadLevelCode.action?code.levelid=${code.levelid}",
			secureuri : false,
			fileElementId : "upload", //文件上传域的ID
			type : "post",
			dataType : "json",
			success : function(data, status) {
				if (data.errcode == 0) {
					layer.alert("本次成功上传 " + data.count + " 个验证码", function() {
						window.location.reload();
					});
				} else if (data.errcode == -1) {
					layer.alert("只支持txt文本和Excel表格");
				} else if (data.errcode == -2) {
					layer.alert("未找到验证码，请注意文件格式");
				}
				console.log(data);
				layer.close(index);
			},
			error : function(data, status, e) {
				console.log(e);
			}
		});
	}

	function editCode(code, id) {
		layer.prompt({
			formType : 0,
			value : code,
			title : '验证码'
		}, function(value, index, elem) {
			$.ajax({
				url : '/${oname}/setting/editLevelCode.action',
				type : 'post',
				data : {
					"code.id" : id,
					"code.levelid" : '${code.levelid}',
					"code.code" : value
				},
				success : function(data) {
					if (data == -1) {
						layer.alert("验证码不得重复");
					} else if (data > 0) {
						layer.alert("保存成功", function() {
							window.location.reload();
						});
					} else {
						layer.alert("保存失败");
					}
				}
			});
			//layer.close(index);
		});
	}

	function delCode(id) {
		layer.confirm("确定删除吗，该操作不可恢复", function(res) {
			if (res) {
				$.ajax({
					url : '/${oname}/setting/delLevelCode.action',
					type : 'post',
					data : {
						"code.id" : id
					},
					success : function(data) {
						if (data > 0) {
							layer.alert("删除成功", function() {
								window.location.reload();
							});
						} else {
							layer.alert("删除失败");
						}
					}
				});
			}
		});
	}

	
</script>