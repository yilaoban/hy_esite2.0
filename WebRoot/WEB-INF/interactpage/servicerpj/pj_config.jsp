<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <form class="formview" onsubmit="return false">
  	<dl>
      <dt>选择来源</dt>
      <dd>
        <select id="type">
          <option value="0">线下签到</option>
        </select>
      </dd>
    </dl>
    <dl>
      <dt>选择应用站点</dt>
      <dd>
        <select id="site">
          <option value="0">选择应用站点</option>
          <s:iterator value="sgList" var="sg">
              <option value="${sg.id }">${sg.groupname}</option>
          </s:iterator>
        </select>
      </dd>
    </dl>
    <dl>
      <dd>
        <button class="btn btn-primary" onclick="save()">确定</button>
      </dd>
    </dl>
  </form>
</div>
<script type="text/javascript">
	function save() {
		var value = $("#site").val();
		if (value == null) {
			return;
		}
		
		var type = $("#type").val();
		if (type == null) {
			return;
		}

		$.ajax({
			url : "/${oname}/servicerpj/saveConfig.action",
			type : "post",
			data : {
				"sgid" : value,
				"source" : type
			},
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("应用成功");
				}
			}
		});
	}
</script>