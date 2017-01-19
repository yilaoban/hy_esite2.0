<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	 $(document).ready(function() {  
           $("#category").change();
           $(".samecheck13").click(function(){
        	   var c =$(this).is(":checked");
        	   var v="N";
				if(c){
					v="Y";
				}
				$("#flg13").val(v);
				$("#req13").val(v);
				$(".samecheck13").attr("checked",c);
           });
        });
        
        function getContent(){
	 	 var obj = document.getElementById("lottery");
	 	 var id = obj.value;
	 	 if(id == "Z"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 	 	$('#Z').show();
	 	 }else if(id == "G"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 	 	$('#G').show();
	 	 }else if(id == "L"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 		$('#L').show();
	 	 }else if(id == "Y"){
	 	 	$('#Z').hide(); $('#G').hide();$('#L').hide();$('#Y').hide();
	 	 	$('#Y').show();
	 	 }
	 }
	 
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
		if($("#start").val()==""){
			$("#start_").html("请输入开始时间").css("color", "RED");
			return false;
		}else{
			$("#start_").html("");
			return true;
		}
	}
	function checkTitle3(){
		if($("#end").val()==""){
			$("#end_").html("请输入结束时间").css("color", "RED");
			return false;
		}else{
			$("#end_").html("");
			return true;
		}
	}
	 
	 function checkAptForm(){
	 	if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}else if(document.getElementById("start").value==""){
			$("#start_").html("请填写开始时间").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}else if(document.getElementById("end").value==""){
			$("#end_").html("请填写结束时间").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}
		if($('#startnote').val().trim() == ""){
			$('#startnote').val("活动还未开始");
		}
		if($('#endnote').val().trim() == ""){
			$('#endnote').val("活动已经结束");
		}
		var islottery=$('input:radio[name="apt.islottery"]:checked').val();
		if(islottery == 'Y'){
			if($('#zlotteryid').val() == 0 && $('#llotteryid').val() == 0 && $('#glotteryid').val() == 0 && $('#ylotteryid').val() == 0){
				$("#lotteryinfo").html("请选择抽奖方式").css("color", "RED");
				location.href = "#md"; 
				return false;
			}
		}
		$("#myform").submit();
	 }
</script> 
   <a name="maodian"></a>        
<div class="wrap_content left_module">
  <form action="add_order_save.action" method="post" id="myform" name="myform" enctype="multipart/form-data" class="formview">
  <div class="jNice">
  <input type="hidden" name="aptid" value="${resultid }">
  <input type="hidden" name="mid" value="${mid }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增</a></li>
	  </ul>
	  <img src="/images/help.jpg" height="20" width="20" onclick="help_appointment()" title="帮助文档" style="cursor:pointer">
	  <a href="/${oname }/interact/index.action?mid=${mid}&omid=${sessionScope.omid }" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" name="apt.title" id="title" onblur="checkTitle()" placeholder="此标题用户不可见，可任意填写">
			<span id="title_" class="must">*</span>
		</dd>
	</dl>
	<dl>
	 	<dt>简介</dt>
		<dd>
			<textarea  maxlength="200" name="apt.content" placeholder="简介内容方便您记忆，不会在页面中显示"></textarea>
	 		<span class="notice">最多不超过200个汉字</span>
		</dd>
	</dl>
 	<dl>
	 	<dt>开始时间</dt>
		<dd>
			<input type="text" placeholder="请选择开始时间" onblur="checkTitle2()" id="start" name="apt.starttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" onblur="checkSdate()" class="Wdate"><span class="must">*</span><span id="start_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束时间</dt>
		<dd>
			<input type="text" placeholder="请选择结束时间" onblur="checkTitle3()" id="end" name="apt.endtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" onblur="checkEdate()" class="Wdate"><span class="must">*</span><span id="end_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>开始前提示语</dt>
		<dd>
			<input type="text" class="text-long" name="apt.startnote" placeholder="活动还未开始" id="startnote"><span class="notice">提示用户该活动还没开始</span>
		</dd>
	</dl>
	<dl>
	 	<dt>结束后提示语</dt>
		<dd>
			<input type="text" class="text-long" name="apt.endnote" placeholder="活动已经结束" id="endnote"><span class="notice">提示用户该活动已结束</span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动上限</dt>
		<dd>
			<input type="text" class="text-small" name="apt.totallimit" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>每日活动上限</dt>
		<dd>
			<input type="text" class="text-small" name="apt.daylimit" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动后关联到抽奖</dt>
		<dd>
 			<label class="forradio"><input type="radio" name="apt.islottery" value="Y"  onclick="$('.type').show()"/>是</label>
			<label class="forradio"><input type="radio" name="apt.islottery" value="N" checked="checked" onclick="$('.type').hide()"/>否</label>
		</dd>
	</dl>
	<dl class="type" style="display: none">
		<dt>抽奖方式</dt>
		<dd>
			<select name="dto.type" id="lottery" onchange="getContent()">
				  <option value ="N">请选择抽奖方式</option>
				  <option value ="Z">大转盘</option>
				  <option value ="L">砸金蛋</option>
				  <!-- 
				  <option value ="G">刮刮乐</option>
				   -->
				  <option value ="Y">摇一摇</option>
			</select>
			<span id="lotteryinfo" class="must"></span><a name="md"></a>
		</dd>
		<dd id="Z">
			<select name="dto.zlotteryid" class="dis" id="zlotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="zlotteryList" var="f">
				<option value ="${f.id  }">${f.name }</option>
				</s:iterator>  
			</select> 
		</dd>
		<dd id="L" style="display:none">
			<select name="dto.llotteryid" class="dis" id="llotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="llotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="G" style="display:none">
			<select name="dto.glotteryid" class="dis" id="glotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="glotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
		<dd id="Y" style="display:none">
			<select name="dto.ylotteryid" class="dis" id="ylotteryid">
				<option value ="0" selected="selected">请选择一项</option>
				<s:iterator value="ylotteryList" var="f" >
				  <option value ="${f.id}">${f.name}</option>
				</s:iterator>  
			</select>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>活动增加抽奖机会</dt>
		<dd>
			<input type="text" class="text-small" name="apt.lotterychance" value="1" id="lotterychance" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>增加抽奖机会上限</dt>
		<dd>
			<input type="text" class="text-small" name="apt.maxlotterychance" value="1" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">次/人
			<span class="notice">（0表示不增加）</span>
		</dd>
	</dl>
	<dl>
	 	<dt>活动后赠送积分</dt>
		<dd>
			<input type="text" class="text-small" name="apt.balance" value="0" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')">积分/人
			<span class="notice">（0表示不赠送）</span>
		</dd>
	</dl>
	</div>

	<dl>
	 	<dt>字段设置</dt>
	 	<dd>
	 	<span class="notice">(填写你要收集的内容，默认选项不可以修改删除。)</span>
	 	<table class="tb_normal tb_high" width="100%"  border="1" cellspacing="1" cellpadding="1" >
		<thead>
			<tr>
				<th>字段类型</th>
				<th>字段名称</th>
				<th>初始内容</th>
				<th>是否显示</th>
				<th>是否必填</th>
				<th>位置</th>
			</tr>
		</thead>
		<tbody>
				<tr align="center" >
					 <td >
					 	固定字段
					 	<input type='hidden' name="subSys[0].name" value='姓名'>
					 	<input type='hidden' name="subSys[0].mapping" value='name'>
					 	<input type='hidden' name="subSys[0].coltype" value="S">
					 	<input type='hidden' name="subSys[0].stype" value="T">
					 </td>
				     <td align="center">姓名</td>
				     <td align="center"><input type="text" class="text-medium" placeholder="请输入您的姓名" name="subSys[0].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[0].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[0].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[0].row">行</span>
				</tr>
				<tr align="center" >
					 <td >
					 	固定字段
					 	<input type='hidden' name="subSys[1].name" value='年龄'>
					 	<input type='hidden' name="subSys[1].mapping" value='age'>
					 	<input type='hidden' name="subSys[1].coltype" value="I">
					 	<input type='hidden' name="subSys[1].stype" value="N">
					 </td>
				     <td align="center">年龄</td>
				     <td align="center"><input type="text" class="text-medium" placeholder="请输入您的年龄" name="subSys[1].defaultvalue"  id="old" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')"></td>
				     <td align="center"><input type="checkbox" name="subSys[1].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[1].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[1].row">行</span>
				</tr> 
				<tr align="center" >
					<td align="center">固定字段
						<input type='hidden' name="subSys[2].name" value='性别'>
						<input type='hidden' name="subSys[2].mapping" value='gender'>
						<input type='hidden' name="subSys[2].coltype" value="S">
						<input type='hidden' name="subSys[2].stype" value="R">
					</td>
				     <td align="center">性别</td>
				     <td align="center"><label class="forradio"><input type="radio" name="subSys[2].defaultvalue" value="男,女" checked>男</label><label class="forradio"><input type="radio" name="subSys[2].defaultvalue" value="女,男">女 </label></td>
				     <td align="center"><input type="checkbox" name="subSys[2].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[2].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[2].row">行</span>
				</tr>
				<tr align="center" >
					 <td align="center">固定字段
					 	<input type='hidden' name="subSys[3].name" value='生日'>
					 	<input type='hidden' name="subSys[3].mapping" value='birthday'>
					 	<input type='hidden' name="subSys[3].coltype" value="D">
					 	<input type='hidden' name="subSys[3].stype" value="D">
					 </td>
				     <td align="center">生日</td>
				     <td align="center"><input type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" class="Wdate" name="subSys[3].defaultvalue"> </td>
				     <td align="center"><input type="checkbox" name="subSys[3].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[3].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[3].row">行</span>
				</tr>
				<tr align="center" >
					 <td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[4].name" value='省份'>
					 	<input type='hidden' name="subSys[4].mapping" value='province'>
					 	<input type='hidden' name="subSys[4].coltype" value="S">
					 	<input type='hidden' name="subSys[4].stype" value="T">
					 </td>
				     <td align="center">省份</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[4].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[4].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[4].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[4].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[5].name" value='城市'>
					 	<input type='hidden' name="subSys[5].mapping" value='city'>
					 	<input type='hidden' name="subSys[5].coltype" value="S">
					 	<input type='hidden' name="subSys[5].stype" value="T">
					 </td>
				     <td align="center">城市</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[5].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[5].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[5].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[5].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[6].name" value='区域'>
					 	<input type='hidden' name="subSys[6].mapping" value='district'>
					 	<input type='hidden' name="subSys[6].coltype" value="S">
					 	<input type='hidden' name="subSys[6].stype" value="T">
					 </td>
				     <td align="center">区域</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[6].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[6].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[6].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[6].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[7].name" value='详细地址'>
					 	<input type='hidden' name="subSys[7].mapping" value='address'>
					 	<input type='hidden' name="subSys[7].coltype" value="S">
					 	<input type='hidden' name="subSys[7].stype" value="T">
					 </td>
				     <td align="center">详细地址</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[7].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[7].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[7].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[7].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[8].name" value='身份证'>
					 	<input type='hidden' name="subSys[8].mapping" value='idcard'>
					 	<input type='hidden' name="subSys[8].coltype" value="S">
					 	<input type='hidden' name="subSys[8].stype" value="T">
					 </td>
				     <td align="center">身份证</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[8].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[8].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[8].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[8].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[9].name" value='邮箱'>
					 	<input type='hidden' name="subSys[9].mapping" value='email'>
					 	<input type='hidden' name="subSys[9].coltype" value="S">
					 	<input type='hidden' name="subSys[9].stype" value="E">
					 </td>
				     <td align="center">邮箱</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[9].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[9].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[9].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[9].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[10].name" value='手机'>
					 	<input type='hidden' name="subSys[10].mapping" value='phone'>
					 	<input type='hidden' name="subSys[10].coltype" value="S">
					 	<input type='hidden' name="subSys[10].stype" value="N">
					 </td>
				     <td align="center">手机</td>
				     <td align="center"><input type="text" class="text-medium" name="subSys[10].defaultvalue" ></td>
				     <td align="center"><input type="checkbox" name="subSys[10].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[10].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[10].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">
					 	固定字段
					 	<input type='hidden' name="subSys[11].name" value='日期'>
					 	<input type='hidden' name="subSys[11].mapping" value='date'>
					 	<input type='hidden' name="subSys[11].coltype" value="S">
					 	<input type='hidden' name="subSys[11].stype" value="D">
					 </td>
				     <td align="center">日期</td>
				     <td align="center"><input type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" name="subSys[11].defaultvalue"></td>
				     <td align="center"><input type="checkbox" name="subSys[11].isshow" value="Y"></td>
				     <td align="center"><input type="checkbox" name="subSys[11].req" value="Y"></td>
				     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[11].row">行</span>
				</tr>
				<tr align="center" >
					<td align="center">固定字段
						<input type='hidden' name="subSys[12].name" value='验证码'>
						<input type='hidden' name="subSys[12].mapping" value='code'>
						<input type='hidden' name="subSys[12].coltype" value="S">
						<input type='hidden' name="subSys[12].stype" value="T"> 
					</td>
					<td align="center">验证码</td>
					<td align="center"><input type="text" class="text-medium" placeholder="请输入验证码" name="subSys[12].defaultvalue"></td>
					<td align="center"><input type="checkbox" class="samecheck13" ><input type="hidden" name="subSys[12].isshow" id="flg13" value="N"></td>
					<td align="center"><input type="checkbox" class="samecheck13" ><input type="hidden" name="subSys[12].req"  id="req13" value="N"></td>
					<td align="center"><span><input type="text" class="text-small" size="3" name="subSys[12].row">行</span></td>
				</tr>
				</tbody>
		</table>
		</dd>
	</dl>
	
	<dl class="mt10">
	 	<dt>自定义字段</dt>
	 	<dd>
		<input type="button" class="btn btn-info" onclick="addRowNew(0,'T')" value="单行文本"/>
		<input type="button" class="btn btn-info" onclick="addRowNew(0,'S')" value="下拉框"/>
		<input type="button" class="btn btn-info" onclick="addRowNew(0,'R')" value="单选"/>
		<input type="button" class="btn btn-info" onclick="addRowNew(0,'C')" value="复选"/>
		<input type="button" class="btn btn-info" onclick="addRowNew(0,'A')" value="多行文字"/>
		<input type="button" class="btn btn-info" onclick="addRowNew(0,'I')" value="图片"/>
		</dd>
	 	<dd class="pt10">
			<table class="tb_normal tb_high" width="100%"  border="1" cellspacing="1" cellpadding="1" id="addtable">
			<thead>
				<tr>
					<th>字段类型</th>
					<th>字段名称</th>
					<th>初始内容(多个内容以换行分开)</th>
					<th>是否必填</th>
					<th>位置</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody >
					
			</tbody>
			</table>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd class="mt10">
			<input type="button" class="btn btn-primary" value="保存" onclick="checkAptForm()">
			<input type="reset" class="btn" value="取消">
		</dd>
	</dl>
  </form>
</div>

