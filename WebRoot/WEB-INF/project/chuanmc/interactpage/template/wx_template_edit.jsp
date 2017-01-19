<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<form class="formview" onsubmit="return false">
  <s:if test="template.showRemark">
    <dl>
      <dt>标题</dt>
      <dd>
        <input type="text" id="title" class="text-medium" value="${template.remark}" />
        <span style="color: red;">*</span>
        <span class="notice">不显示，仅方便用户记忆</span>
      </dd>
    </dl>
  </s:if>

  <dl>
    <dt>模板内容</dt>
    <dd>
      <p id="tcontent">${template.store.content}</p>
    </dd>
  </dl>

  <s:if test="template.showUrl">
    <dl>
      <dt>跳转链接</dt>
      <dd>
        <input type="text" id="url" class="text-long" value="${template.url}" />
      </dd>
    </dl>
  </s:if>

  <s:if test="template.showSend">
    <dl>
      <dt>发送设置</dt>
      <dd>
        <input type="radio" id="r1" name="r" value="r1" onclick="$('.r1').show();$('.r2').hide();" checked="checked" />
        <span>延迟发送</span>
        <input type="radio" id="r2" name="r" value="r2" onclick="$('.r2').show();$('.r1').hide();" />
        <span>指定时间</span>
      </dd>
      <dd class="r1">
        <input type="text" id="delay" class="text-small" />
        <select id="delay_unit">
          <option value="1440">天</option>
          <option value="60">小时</option>
          <option value="1" <s:if test="template.delay"></s:if>>分钟</option>
        </select>
        <span>后发送</span>
      </dd>
      <dd class="r2" style="display: none;">
        <input type="text" id="sendtime" class="Wdate" value="${template.sendtime}" onFocus="WdatePicker({minDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
      </dd>
    </dl>
  </s:if>

  <dl>
    <dt>内容示例</dt>
    <dd>
      <p>${template.store.example}</p>
      <button class="btn btn-primary" onclick="saveTemplate()">保存</button>
    </dd>
  </dl>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		// content
		var data = '${template.data}';
		if (data != '') {
			data = JSON.parse(data);
		}
		var content = $("#tcontent").html();
		var fields = content.match(/\{{2}[^\}]*\}{2}/g);
		for (var i = 0; i < fields.length; i++) {
			var field = fields[i];
			var name = field.split(".")[0].replace(/\{*/g, "");
			var value = data[name] || "";
			content = content.replace(field, '<input name="'+name+'" class="text-long" value="'+value+'" /><span style="color: red;">*</span>');
		}
		$("#tcontent").html(content);

		// systerm generate
		var store_id = "${template.store.id}";
		if (store_id == "1") {
			$("input[name='orderMoneySum'],input[name='orderProductName']").next().remove();
			$("input[name='orderMoneySum'],input[name='orderProductName']").after("<span class='notice'>系统自动生成</span>").remove();
		} else if (store_id == "2") {
			$("input[name='keyword1'],input[name='keyword2'],input[name='keyword3'],input[name='keyword4']").next().remove();
			$("input[name='keyword1'],input[name='keyword2'],input[name='keyword3'],input[name='keyword4']").after("<span class='notice'>系统自动生成</span>").remove();
		} else if (store_id == "3") {
			$("input[name='keyword2']").next().remove();
			$("input[name='keyword2']").after("<span class='notice'>系统自动生成</span>").remove();
		} else if (store_id == "4") {
			$("input[name='FieldName'],input[name='Account'],input[name='change'],input[name='CreditChange'],input[name='CreditTotal']").next().remove();
			$("input[name='FieldName'],input[name='Account'],input[name='change'],input[name='CreditChange'],input[name='CreditTotal']").after("<span class='notice'>系统自动生成</span>").remove();
		} else if (store_id == "5") {
			$("input[name='keyword1'],input[name='keyword2'],input[name='keyword3'],input[name='keyword5']").next().remove();
			$("input[name='keyword1'],input[name='keyword2'],input[name='keyword3'],input[name='keyword5']").after("<span class='notice'>系统自动生成</span>").remove();
		} else if (store_id == "6") {
			$("input[name='keyword1'],input[name='keyword3']").next().remove();
			$("input[name='keyword1'],input[name='keyword3']").after("<span class='notice'>系统自动生成</span>").remove();
		}

		// send radio
		if ($("#sendtime").length > 0 && $("#sendtime").val() != "") {
			$("#r2").click();
		}

		// delay value
		var delay = parseInt('${template.delay}');
		if (isNaN(delay) || delay == 0) {
			$("#delay").val("");
			$("#delay_unit").val("60");
		} else if (delay % 1440 == 0) {
			$("#delay").val(delay / 1440);
			$("#delay_unit").val("1440");
		} else if (delay % 60 == 0) {
			$("#delay").val(delay / 60);
			$("#delay_unit").val("60");
		} else {
			$("#delay").val(delay);
			$("#delay_unit").val("1");
		}
	});

	function saveTemplate() {
		var remark = "${template.remark}";
		if ($("#title").length > 0) {
			remark = $("#title").val().trim();
			if (remark == "") {
				layer.msg('请填写标题!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
		} else if (remark == "") {
			remark = "${template.store.title}";
		}
		var data = new Object();
		var flag = false;
		$("#tcontent").find("input").each(function() {
			var name = $(this).attr("name");
			var value = $(this).val().trim();
			if (value == "") {
				flag = true;
				return false;
			}
			data[name] = value;
		});
		if (flag) {
			layer.msg('模板内容不完整!', {
				icon : 5,
				time : 2000
			});
			return false;
		}
		
		var dtype = 0;
		var va = $("input[name='r']:checked").val();
		if(va == "r2"){
			dtype = 1;
		}
		
		var pos = {
			"template.id" : "${template.id}",
			"template.type" : "${template.type}",
			"template.entityid" : "${template.entityid}",
			"template.remark" : remark,
			"template.store_id" : "${template.store.id}",
			"template.data" : JSON.stringify(data),
			"template.dtype" : dtype,
		}

		// url
		if ($("#url").length > 0) {
			var url = $("#url").val().trim();
			if (url != "") {
				pos['template.url'] = url;
			}
		}
		// delay and sendtime
		if ($("input[name='r']:checked").length > 0) {
			var r = $("input[name='r']:checked").val();
			if (r == "r1") {
				var delay = $("#delay").val().trim();
				var delay_unit = parseInt($("#delay_unit").val());
				if (delay != "") {
					if (delay.match(/^\d+$/) == null) {
						layer.msg('延迟时间只能为整数!', {
							icon : 5,
							time : 2000
						});
						return false;
					}
					pos['template.delay'] = parseInt(delay * delay_unit);
				}
			} else {
				var sendtime = $("#sendtime").val();
				if (sendtime != "") {
					pos['template.sendtime'] = sendtime;
				}
			}
		}

		$.ajax({
			url : "/${oname}/template/save.action",
			data : pos,
			type : "post",
			cache : false,
			success : function(res) {
				if (res) {
					if (res.errcode == 0) {
						layer.alert("保存成功", function(index) {
							parent.location.reload();
						});
					} else {
						layer.alert(res.errmsg);
					}
				} else {
					layer.alert("保存失败");
				}
			}
		});
	}

	
</script>