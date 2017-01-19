<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function checkSub(){
	 	if($("#addname").val() == null || $("#addname").val() == ""){
			$("#name_").html("标题不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#startTime").val() == null || $("#startTime").val() == ""){
			$("#startTime_").html("开始时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#endTime").val() == null || $("#endTime").val() == ""){
			$("#endTime_").html("结束时间不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else if($("#lid").val() == null || $("#lid").val() == ""){
			$("#lid_").html("抽奖不能为空!").css("color", "RED");
			window.location.hash="maodian";
		}else{
			$("#form1").submit();
		}
	 	
		
	}
</script>
<a name="maodian"></a>
<div class="wrap_content left_module">
  <form action="renqiEditSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" name="mid" value="10012">
  <input type="hidden" name="id" value="${id }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑集人气</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10012" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" placeholder="此标题仅方便您记忆，请任意填写" id="addname" name="title" value="${dto.renqi.title }" onblur="nameonblur()"/>
			<span class="must">*</span>
			<span id="name_"><s:fielderror fieldName="err1"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input id="startTime" type="text" value="<s:date name='dto.renqi.startStr' format='yyyy-MM-dd HH:mm:ss'/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="startTimeonblur()" />
			<span class="must">*</span>
			<span id="startTime_"><s:fielderror fieldName="err2"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" type="text" value="<s:date name='dto.renqi.endStr' format='yyyy-MM-dd HH:mm:ss'/>" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" onblur="endTimeonblur()" />
			<span class="must">*</span>
			<span id="endTime_"><s:fielderror fieldName="err3"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="startNote" value="${dto.renqi.startnote }"><span class="notice">提示用户该申请还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="endNote" value="${dto.renqi.endnote }"><span class="notice">提示用户该申请已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>选择抽奖</dt>
		<dd>
			<select name="lid" id="lid">
				<s:iterator value="dto.lottery" var="l">
					<option value="${l.id }" <s:if test="dto.renqi.lotteryid==id">selected="selected"</s:if>>${l.name }</option>
				</s:iterator>
			</select>
			<span id="lid_"><s:fielderror fieldName="err4"  cssStyle="color:red"/></span>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-medium" value="${dto.renqi.maxlu }" name="maxlu" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖需要人气</dt>
		<dd>
			<input type="text" class="text-medium" value="${dto.renqi.cnum }" name="cnum" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>集人气类型</dt>
		<dd>
			<input type="radio" name="utype" value="0" onchange="renqc()" <s:if test="dto.renqi.utype==0">checked="checked"</s:if>>微博
			<input type="radio" name="utype" value="1" onchange="renqc()" <s:if test="dto.renqi.utype==1">checked="checked"</s:if>>微信
		</dd>
	</dl>
	<div id="wb" <s:if test="dto.renqi.utype==1">style="display: none"</s:if>>
		<dl>
		 	<dt>微博分享内容</dt>
			<dd>
				<textarea rows="" cols="" name="content">${dto.renqi.content }</textarea>
			</dd>
		</dl>
		<dl>
		 	<dt>集人气链接</dt>
			<dd>
				<input type="text" class="text-medium" value="${dto.renqi.link }" name="link"/>
			</dd>
		</dl>
	</div>
	<div id="wx" <s:if test="dto.renqi.utype==0">style="display: none"</s:if>>
		<dl>
		 	<dt>微信标题</dt>
			<dd>
				<input type="text" class="text-medium" value="${dto.renqi.wxtitle }" name="wxtitle"/>
			</dd>
		</dl>
		<dl>
		 	<dt>微信分享内容</dt>
			<dd>
				<textarea rows="" cols="" name="wxdesc">${dto.renqi.wxdesc }</textarea>
			</dd>
		</dl>
	</div>
	<dl>
	 	<dt>反馈人气</dt>
		<dd>
			<input type="text" class="text-medium" value="${dto.renqi.minjf }" name="minjf"/>至<input type="text" class="text-medium" value="${dto.renqi.maxjf }" name="maxjf"/>
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
 
