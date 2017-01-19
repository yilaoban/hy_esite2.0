<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
//实例化编辑器
var um = UE.getEditor('myEditor');
	

	function checkTitle1(){
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			return false;
		}else{
			$("#title_").html("");
			return true;
		}
	}
	function checkTitle2(){
		if($("#startTime").val()==""){
			$("#startTime_").html("请输入开始时间").css("color", "RED");
			return false;
		}else{
			$("#startTime_").html("");
			return true;
		}
	}
	function checkTitle3(){
		if($("#endTime").val()==""){
			$("#endTime_").html("请输入结束时间").css("color", "RED");
			return false;
		}else{
			$("#endTime_").html("");
			return true;
		}
	}
	function checkSub(){
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			window.location.hash="maodian";
		}else if(document.getElementById("startTime").value==""){
			$("#startTime_").html("请输入开始时间").css("color", "RED");
			window.location.hash="maodian";
		}else if(document.getElementById("endTime").value==""){
			$("#endTime_").html("请输入结束时间").css("color", "RED");
			window.location.hash="maodian";
		}else{
			$("#form1").submit();
		}
	}
</script>
<a name="maodian"></a> 
<div class="wrap_content left_module">
  <form action="edit_ggl_sub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  <input type="hidden" name="lid" value="${lid }"/>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑刮刮乐</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10005" class="return" title="返回"></a>
	</div>
	<dl>
	 	<dt>标题</dt>
		<dd>
			<input id="title" onblur="checkTitle1()" type="text" class="text-medium"  name="name" value="${dto.lottery.name }">
			<span class="must">*</span>
			<span id="title_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动开始时间</dt>
		<dd>
			<input id="startTime" onblur="checkTitle2()" type="text" value="<s:date name="dto.lottery.starttime" format="yyyy-MM-dd HH:mm:ss"/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			<span class="must">*</span>
			<span id="startTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束时间</dt>
		<dd>
			<input id="endTime" onblur="checkTitle3()" type="text" value="<s:date name="dto.lottery.endtime" format="yyyy-MM-dd HH:mm:ss"/>" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			<span class="must">*</span>
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖形式</dt>
		<dd>
			<label class="forradio"><input type="radio" name="ruletype" value="G" onclick="$('#ruletype').hide()" <s:if test='dto.lottery.ruletype=="G"'>checked="checked"</s:if>>关联抽奖</label>
			<label class="forradio"><input type="radio" name="ruletype" value="D" onclick="$('#ruletype').show()" <s:if test='dto.lottery.ruletype=="D"'>checked="checked"</s:if>>独立抽奖</label>
		</dd>
	</dl>
	<dl id="ruletype" <s:if test='dto.lottery.ruletype=="G"'>style="display: none"</s:if>>
	 	<dt>抽奖资格</dt>
		<dd>
			<label class="forradio"><input type="radio" name="usertype" value="A" onclick="$('#usertype').hide()" <s:if test='dto.lottery.usertype=="A"'>checked="checked"</s:if>>所有用户</label> 
			<label class="forradio"><input type="radio" name="usertype" value="S" onclick="$('#usertype').show()" <s:if test='dto.lottery.usertype=="S"'>checked="checked"</s:if>>指定用户</label>
		</dd>
	</dl>
	<dl id="usertype" <s:if test='dto.lottery.usertype=="A"'>style="display: none"</s:if>>
	 	<dt></dt>
		<dd>
			<label class="forradio"><input type="checkbox" name="guanzhu" value="Y" <s:if test='dto.usertype.guanzhu=="Y"'>checked="checked"</s:if>>需关注发起人</label>
		</dd>
		<dd>
			<label class="forradio"><input type="checkbox" name="daren" value="Y" <s:if test='dto.usertype.daren=="Y"'>checked="checked"</s:if>>微博达人</label>
		</dd>
		<dd>
			<label class="forradio"><input type="checkbox" name="fensi" value="Y" <s:if test='dto.usertype.fensi>0'>checked="checked"</s:if>>粉丝数人数不少于</label><input type="text" name="fensi_num" <s:if test='dto.usertype.fensi>0'>value="${dto.usertype.fensi }"</s:if>>
		</dd>
	</dl>
	<dl>
	 	<dt>抽奖机会</dt>
		<dd>
			每名用户初始抽奖机会<input type="text" class="text-small" name=usertotal value="${dto.lottery.usertotal }"/>次
		</dd>
		<dd>
			每人每日抽奖次数上限<input type="text" class="text-small" name="userdaynum" value="${dto.lottery.userdaynum }"/>次
		</dd>
	</dl>
	<dl>
	 	<dt>活动中奖率</dt>
		<dd>
			万分之<input type="text" class="text-medium" name="zjl" value="${dto.lottery.zjl }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>中奖用户填写信息</dt>
		<dd>&nbsp;
		</dd>
	</dl>
	<dl>
	 	<dt>姓名</dt>
		<dd>
			<input type="text" class="text-medium" name="userNameValue" value="${dto.lottery.userNameValue }"/><label class="forradio ml10"><input type="checkbox" name="userName" value="Y"  <s:if test='dto.lottery.userName=="Y"'>checked="checked"</s:if>>是否显示</label>
		</dd>
	</dl>
	<dl>
	 	<dt>手机</dt>
		<dd>
			<input type="text" class="text-medium" name="userPhoneValue" value="${dto.lottery.userPhoneValue }"/><label class="forradio ml10"><input type="checkbox" name="userPhone" value="Y"  <s:if test='dto.lottery.userPhone=="Y"'>checked="checked"</s:if>>是否显示</label>
		</dd>
	</dl>
	<dl>
	 	<dt>邮箱</dt>
		<dd>
			<input type="text" class="text-medium" name="userEmailValue" value="${dto.lottery.userEmailValue }"/><label class="forradio ml10"><input type="checkbox" name="userEmail" value="Y" <s:if test='dto.lottery.userEmail=="Y"'>checked="checked"</s:if>>是否显示</label>
		</dd>
	</dl>
	<dl>
	 	<dt>地区</dt>
		<dd>
			<input type="text" class="text-medium" name="userLocationValue" value="${dto.lottery.userLocationValue }"/><label class="forradio ml10"><input type="checkbox" name="userLocation" value="Y" <s:if test='dto.lottery.userLocation=="Y"'>checked="checked"</s:if>>是否显示</label>
		</dd>
	</dl>
	<dl>
	 	<dt>活动说明</dt>
		<dd class="pt10">
		<script type="text/plain" id="myEditor" name="detail" style="width:100%;height:200px;">${dto.lottery.detail}</script>
		</dd>
	</dl>
	<dl>
	 	<dt>活动结束替换图片</dt>
		<dd>
			<input type="file" name="img">
		</dd>
		<dd>
			<img id="img" src="${imgDomain }${dto.lottery.endimg }" style="width: 100px">
			<input type="hidden" name="imgurl" value="${dto.lottery.endimg }">
		</dd>
	</dl>
	<dl>
	 	<dt>&nbsp;</dt>
		<dd>
			<input type="button" value="提交" onclick="checkSub()">
		</dd>
	</dl>
 </form>
 </div>