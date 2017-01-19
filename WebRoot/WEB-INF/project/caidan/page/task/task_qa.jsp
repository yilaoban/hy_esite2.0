<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<style>
.qsort>li {
	position: relative;
	margin-bottom: 20px;
	background-color: #f5f5f5;
	border: 1px solid #e3e3e3;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .05);
}

.asort {
	width: 350px;
}
</style>
<div class="wrap_content">
  <form onsubmit="return false" class="formview">
    <dl>
      <dt>
        <span style="color: red;">*</span>
        标题
      </dt>
      <dd>
        <input id="title" value="${task.title}" class="text-medium" />
      </dd>
    </dl>
    <dl>
      <dt>
        <span style="color: red;">*</span>
        缩略图
      </dt>
      <dd>
        <img id="pic" src="${task.img}" height="100px" />

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
      <dt>
        <span style="color: red;">*</span>
        活动简介
      </dt>
      <dd>
        <input id="hydesc" value="${task.hydesc}" class="text-long" />
      </dd>
    </dl>
   	<dl style="display: none">
      <dt>详细内容</dt>
      <dd>
        <script type="text/plain" id="contentEditor">${task.content}</script>
      </dd>
    </dl>
    <dl>
      <dt>视频地址</dt>
      <dd>
        <textarea oninput="updatevalue(this)" id="link">${task.link}</textarea>
      </dd>
    </dl>
    <dl>
      <dt>
        <span style="color: red;">*</span>
        答题设置
      </dt>
      <dd>
        <button class="btn btn-success btn-sm" onclick="addQustion()">加一题</button>
        <span class="notice">空的问题将不会被保存</span>
      </dd>
      <dd>
        <ul class="qsort">
          <s:iterator value="task.taskQList" var="q">
            <li>
              <dl>
                <dd>&nbsp;</dd>
              </dl>
              <dl>
                <dt>问题:</dt>
                <dd>
                  <input class="qs text-long" value="${q.qs}" />
                </dd>
              </dl>
              <dl>
                <dt>选项:</dt>
                <dd>
                  <ul class="asort">
                    <s:iterator value="#q.taskAList" var="a">
                      <li>
                        <input type="checkbox" <s:if test='#a.status=="Y"'>checked</s:if> />
                        <input class="ans text-medium" value="${a.ans}" />
                        <a class="glyphicon glyphicon-plus" onclick="addAnswer(this)"></a>
                        <a class="glyphicon glyphicon-minus" onclick="removeAnswer(this)"></a>
                      </li>
                    </s:iterator>
                  </ul>
                </dd>
                <dd>
                  <span class="notice">请为正确选项打勾，支持多选题</span>
                </dd>
              </dl>
              <a href="javascript:;" style="position: absolute; right: 0; top: 0;" onclick="removeQuestion(this)">
                <img src="/images/close.png">
              </a>
            </li>
          </s:iterator>
        </ul>
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
      <dt>
        <span style="color: red;">*</span>
        获得金币
      </dt>
      <dd>
        <input id="jf" value="${task.jf}" class="text-small" />

      </dd>
    </dl>
    <dl>
      <dt>
        <span style="color: red;">*</span>
        初始答题人数
      </dt>
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
<div id="template" style="display: none">
  <li>
    <dl>
      <dd>&nbsp;</dd>
    </dl>
    <dl>
      <dt>问题:</dt>
      <dd>
        <input class="qs text-long" />
      </dd>
    </dl>
    <dl>
      <dt>选项:</dt>
      <dd>
        <ul class="asort">
          <li>
            <input type="checkbox" />
            <input class="ans text-medium" />
            <a class="glyphicon glyphicon-plus" onclick="addAnswer(this)"></a>
            <a class="glyphicon glyphicon-minus" onclick="removeAnswer(this)"></a>
          </li>
        </ul>
      </dd>
      <dd>
        <span class="notice">请为正确选项打勾，支持多选题</span>
      </dd>
    </dl>
    <a href="javascript:;" style="position: absolute; right: 0; top: 0;" onclick="removeQuestion(this)">
      <img src="/images/close.png">
    </a>
  </li>
</div>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript">
	var ue = UE.getEditor('contentEditor');
	$(document).ready(function() {
		$("ul").sortable({
			helper : "clone",
			axis : "y"
		});

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

	function addAnswer(obj) {
		$(obj).parent().after($("#template").find(".asort").html());
	}

	function removeAnswer(obj) {
		$(obj).parent().remove();
	}

	function addQustion() {
		$(".qsort").append($("#template").html());
	}

	function removeQuestion(obj) {
		$(obj).parent().remove();
	}

	function saveTask() {
		var title = $("#title").val().trim();
		var img = $("#img").val().trim();
		var hydesc = $("#hydesc").val().trim();
		//var content = ue.getContent();
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
		var taskQList = new Array();
		$(".qsort>li").each(function() {
			var qs = $(this).find(".qs").val().trim();
			if (qs == "") {
				return;
			}
			var taskAList = new Array();
			var checked = 0;
			$(this).find("li").each(function() {
				var ans = $(this).find(".ans");
				if (ans.length == 0 || ans.val().trim() == "") {
					return;
				}
				var taskA = new Object();
				taskA.ans = ans.val().trim();
				if ($(this).find("input[type='checkbox']").attr("checked")) {
					taskA.status = "Y";
					checked++;
				} else {
					taskA.status = "N";
				}

				taskAList.push(taskA);
			});

			var taskQ = new Object();
			taskQ.qs = qs;
			taskQ.taskAList = taskAList;
			if (checked > 1) {
				taskQ.type = 1;//多选
			} else {
				taskQ.type = 0;//单选
			}

			taskQList.push(taskQ);
		});
		if (taskQList.length == 0) {
			layer.msg("至少需要一道题目！", {
				icon : 5
			});
			return;
		}

		var pos = {
			"task.id" : "${task.id}",
			"task.title" : title,
			"task.img" : img,
			"task.hydesc" : hydesc,
			//"task.content" : content,
			"task.link" : link,
			"task.starttime" : starttime,
			"task.endtime" : endtime,
			"task.type" : 1,
			"task.jf" : jf,
			"task.shownum" : shownum,
			"task.qa" : JSON.stringify(taskQList)
		};
		$.ajax({
			url : "/${oname}/cd-task/save.action",
			type : "post",
			data : pos,
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

	function updatevalue(arg){
		var value = arg.value;
		var width = $(value).attr("width");
		var height = $(value).attr("height");
		var nvalue;
		if(width == undefined){
			nvalue = value.replace("<iframe","<iframe width=100% height=100%");
		}else{
			nvalue = value.replace(width,"100%").replace(height,"100%");
		}
		arg.value=nvalue;
	} 
	
</script>