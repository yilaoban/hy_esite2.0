<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function checkSub(){
		$("#name_").html("");
		$("#startTime_").html("");
		$("#endTime_").html("");
	 	if($("#addname").val() == null || $("#addname").val() == ""){
			$("#name_").html("标题不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#lid").val() == 0 ){
			$("#lid_").html("抽奖不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else{
			$("#form1").submit();
		}
	 	
		
	}
</script>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <form action="renqiAddSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" name="mid" value="10012">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增集人气</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10012" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" placeholder="此标题仅方便您记忆，请任意填写" id="addname" name="title" value="${title }" onblur="nameonblur()"/>
			<span class="must">*</span>
			<span id="name_"><s:fielderror theme="" fieldName="err1" cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input id="startTime" type="text" value="${startTime }" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="startTimeonblur()" />
			<span class="must">*</span>
			<span id="startTime_"><s:fielderror fieldName="err2" cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" type="text" value="${endTime }" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="endTimeonblur()" />
			<span class="must">*</span>
			<span id="endTime_"><s:fielderror fieldName="err3" cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="startNote"><span class="notice">提示用户该活动还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="endNote"><span class="notice">提示用户该活动已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>选择抽奖</dt>
		<dd>
			<select name="lid" id="lid">
					<option value="0" selected="selected">选择抽奖</option>
				<s:iterator value="dto.lottery" var="l">
					<option value="${l.id }">${l.name }</option>
				</s:iterator>
			</select>
			<span class="must">*</span>
			<span id="lid_"><s:fielderror fieldName="err4" cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-medium" value="${maxlu }" name="maxlu" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖需要人气</dt>
		<dd>
			<input type="text" class="text-medium" value="${cnum }" name="cnum" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>集人气类型</dt>
		<dd>
			<input type="radio" name="utype" value="0" checked="checked" onchange="renqc()">微博
			<input type="radio" name="utype" value="1" onchange="renqc()">微信
		</dd>
	</dl>
	<div id="wb">
		<dl>
		 	<dt>微博分享内容</dt>
			<dd>
				<textarea rows="" cols="" name="content"></textarea>
			</dd>
		</dl>
		<dl>
		 	<dt>集人气链接</dt>
			<dd>
				<input type="text" class="text-medium" value="${link }" name="link"/>
			</dd>
		</dl>
	</div>
	<div id="wx" style="display: none">
		<dl>
		 	<dt>微信标题</dt>
			<dd>
				<input type="text" class="text-medium" value="${link }" name="wxtitle"/>
			</dd>
		</dl>
		<dl>
		 	<dt>微信分享内容</dt>
			<dd>
				<textarea rows="" cols="" name="wxdesc"></textarea>
			</dd>
		</dl>
	</div>
	<dl>
	 	<dt>反馈人气</dt>
		<dd>
			<input type="text" class="text-medium" value="${minjf }" name="minjf"/>至<input type="text" class="text-medium" value="${maxjf }" name="maxjf"/>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="提交" onclick="checkSub()">
		</dd>
	</dl>
 </form>
 </div>
 
