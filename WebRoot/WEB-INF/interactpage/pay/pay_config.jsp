<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <form class="formview" onsubmit="return false">
    <s:if test='subtype=="W"'>
      <dl>
        <dt>积分比例</dt>
        <dd>
          <button class="btn btn-info btn-sm" onclick="bili()">设置</button>
        </dd>
      </dl>
    </s:if>
    <dl>
      <dt>选择应用站点</dt>
      <dd>
        <select id="site">
          <option value="0">选择应用站点</option>
          <s:iterator value="sgList" var="sg">
            <s:iterator value="#sg.site" var="s">
                <option value="${sg.id}">${sg.groupname}</option>
            </s:iterator>
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
	$(document).ready(function() {
		$("#site option").each(function() {
			var pages = $(this).val().split("-");
			var homePage=pages[1];
			if(homePage=='${marketingSet.homepage}'){
				$(this).attr("selected","selected");
				return false;
			}
        });
	});

	function save() {
		var sgid = $("#site").val();
		if(sgid>0){
			$.ajax({
				url : "/${oname}/pay/config_save.action",
				type : "post",
				data : {
					"subtype" : "${subtype}",
					"sgid" : sgid
				},
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("应用成功");
					}
				}
			});
		}
	}

	function bili() {
		var dvalue=100;
		if("${marketingSet.bili}"!=""){
			dvalue='${marketingSet.bili}';
		}
		layer.prompt({
			formType : 0,
			value : dvalue,
			title : '多少积分抵扣一元?'
		}, function(value, index, elem) {
			var reg = /^[0-9]*[1-9][0-9]*$/;
			var re = new RegExp(reg);
			if (!re.test(value)) {
				alert("请输入大于零的整数!");
				return;
			}

			$.post("/${oname}/pay/jifenToPrice.action", {
				"jftoprice" : value
			}, function(res) {
				if (res > 0) {
					layer.alert("设置成功", function() {
						window.location.reload();
					});
				} else {
					layer.alert("设置失败");
				}
			});
			layer.close(index);
		});
	}

	
</script>