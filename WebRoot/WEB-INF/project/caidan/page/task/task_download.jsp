<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <form onsubmit="return false" class="formview">
    <dl>
      <dt><span style="color: red;">*</span>标题</dt>
      <dd>
        <input id="title" value="${task.title}" class="text-medium" />
      </dd>
    </dl>
    <dl>
      <dt><span style="color: red;">*</span>缩略图</dt>
      <dd>
        <img id="pic" src="${task.img}" height="100px"/>
      </dd>
    </dl>
    <dl>
      <dd>
        <input type="hidden" id="img" value="${task.img}" />
        <input type="file" id="upload" name="img" onchange="uploadPic()" />
      </dd>
      <dd>
      	建议尺寸 640*240
      </dd>
    </dl>
    <dl>
      <dt><span style="color: red;">*</span>活动简介</dt>
      <dd>
        <input id="hydesc" value="${task.hydesc}" class="text-long" />
      </dd>
    </dl>
    <dl>
      <dt><span style="color: red;">*</span>详细内容</dt>
      <dd>
        <script type="text/plain" id="contentEditor">${task.content}</script>
      </dd>
    </dl>
    <dl>
      <dt><span style="color: red;">*</span>下载地址</dt>
      <dd>
        <input id="link" value="${task.link}" class="text-long" />
      </dd>
    </dl>
    <dl>
      <dt>活动时间</dt>
      <dd>
        <input id="starttime" value="${task.starttime}" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}'})" />
        ~
        <input id="endtime" value="${task.endtime}" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}'})" />
      </dd>
    </dl>
    <dl>
      <dt><span style="color: red;">*</span>获得金币</dt>
      <dd>
        <input id="jf" value="${task.jf}" class="text-small" />
      </dd>
    </dl>
    <dl>
      <dt><span style="color: red;">*</span>初始下载量</dt>
      <dd>
        <input id="shownum" value="${task.shownum}" class="text-small" />
      </dd>
    </dl>
    <dl>
      <dd>
        <input type="hidden" value="${task.type}" />
        <button class="btn btn-primary" onclick="saveTask()">保存</button>
      </dd>
    </dl>
  </form>
</div>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	var ue = UE.getEditor('contentEditor');
	$(document).ready(function() {
	});

	function uploadPic() {
		$.ajaxFileUpload({
			url : '/page/picUpload.action',
			secureuri : false,
			fileElementId : "upload",
			dataType : 'json',
			success : function(data, status) {
				if (status == "success") {
					$("#pic").attr("src", "${imgDomain}" + data);
					$("#img").attr("value", "${imgDomain}" + data);
				}
			},
			error : function(data, status, e) {
				layer.alert(e);
			}
		});
	}

	function saveTask() {
		var title = $("#title").val().trim();
		var img = $("#img").val().trim();
		var hydesc = $("#hydesc").val().trim();
		var content = ue.getContent();
		var link = $("#link").val().trim();
		var starttime = $("#starttime").val();
		var endtime = $("#endtime").val();
		var jf = $("#jf").val().trim();
		var shownum = $("#shownum").val().trim();

		if (title == "") {
			layer.msg("标题不能为空！", {
				icon : 5
			});
			return;
		}
		if (img == "") {
			layer.msg("缩略图不能为空！", {
				icon : 5
			});
			return;
		}
		if (hydesc == "") {
			layer.msg("活动简介不能为空！", {
				icon : 5
			});
			return;
		}
		if (content == "") {
			layer.msg("详细内容不能为空！", {
				icon : 5
			});
			return;
		}
		if (link == "") {
			layer.msg("下载地址不能为空！", {
				icon : 5
			});
			return false;
		}
		if (jf == "") {
			layer.msg("积分不能为空！", {
				icon : 5
			});
			return;
		} else if (isNaN(jf)) {
			layer.msg("积分只能为数字！", {
				icon : 5
			});
			return;
		}
		if (shownum == "") {
			layer.msg("初始下载量不能为空！", {
				icon : 5
			});
			return;
		} else if (isNaN(shownum)) {
			layer.msg("初始下载量只能为数字！", {
				icon : 5
			});
			return;
		}

		$.ajax({
			url : "/${oname}/cd-task/save.action",
			type : "post",
			data : {
				"task.id" : "${task.id}",
				"task.title" : title,
				"task.img" : img,
				"task.hydesc" : hydesc,
				"task.content" : content,
				"task.link" : link,
				"task.starttime" : starttime,
				"task.endtime" : endtime,
				"task.type" : 0,
				"task.jf" : jf,
				"task.shownum" : shownum
			},
			cache : false,
			success : function(res) {
				if (res > 0) {
					layer.alert("保存成功", function() {
						window.location.href = "/${oname}/cd-task/index.action";
					});
				} else {
					layer.msg("保存失败！", {
						icon : 5
					});
				}
			}
		});
	}

	
</script>