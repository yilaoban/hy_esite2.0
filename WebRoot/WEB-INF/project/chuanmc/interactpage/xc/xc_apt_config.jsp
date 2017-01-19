<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
		$(document).ready(function(){  
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
	           $("#saveBt").click(function(){
		           var index = layer.msg('正在保存,请稍等...', {icon: 16,time:0});
	        	   $.ajax({ 
		       			cache: true,
		       			type: "POST",
		       			url:"/${oname }/page/xcAptSub.action",
		       			data:$('#myform').serialize(),
		       			async: false, 
		       			success: function(data) {
		       				layer.close(index);
		       				if(data>0){
		       					layer.msg("保存成功!");
		       				}
		       			} 
	       			});
	           });
	        });
</script>
<a name="maodian"></a>    
<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<a href="/${oname}/interact/edit_xcLottery.action?xcid=${xcid}" class="return" title="返回"></a>
	</div>
  <form action="" method="post" id="myform" name="myform" enctype="multipart/form-data" class="formview jNice">
  	<input type="hidden" name="apt.id"  value="${apt.id }"/>
  	<input type="hidden" name="apt.title"  value="${apt.title }"/>
  	<input type="hidden" name="refUrl"  value="${refUrl }"/>
  	<input type="hidden" name="xcid"  value="${xcid }"/>
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
			 	<s:set name="mp" value="apt.mappingMap['姓名']"></s:set>
			 	<input type='hidden' name="subSys[0].name" value='姓名'>
			 	<input type='hidden' name="subSys[0].mapping" value='name'>
			 	<input type='hidden' name="subSys[0].coltype" value="S">
			 	<input type='hidden' name="subSys[0].stype" value="T">
			 </td>
		     <td align="center">姓名</td>
		     <td align="center"><input type="text" class="text-medium" placeholder="请输入您的姓名" name="subSys[0].defaultvalue"  value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[0].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[0].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[0].row"  value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			 <td >
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['年龄']"></s:set>
			 	<input type='hidden' name="subSys[1].name" value='年龄'>
			 	<input type='hidden' name="subSys[1].mapping" value='age'>
			 	<input type='hidden' name="subSys[1].coltype" value="I">
			 	<input type='hidden' name="subSys[1].stype" value="N">
			 </td>
		     <td align="center">年龄</td>
		     <td align="center"><input type="text" class="text-medium" placeholder="请输入您的年龄" name="subSys[1].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[1].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[1].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[1].row"  value="${mp.row }">行</span>
		</tr> 
		<tr align="center" >
			<td align="center">
				固定字段
				<s:set name="mp" value="apt.mappingMap['性别']"></s:set>
				<input type='hidden' name="subSys[2].name" value='性别'>
				<input type='hidden' name="subSys[2].mapping" value='gender'>
				<input type='hidden' name="subSys[2].coltype" value="S">
				<input type='hidden' name="subSys[2].stype" value="R">
			</td>
		     <td align="center">性别</td>
		     <td align="center"><label class="forradio"><input type="radio" name="subSys[2].defaultvalue" value="男,女" checked>男</label><label class="forradio"><input type="radio" name="subSys[2].defaultvalue" value="女,男">女 </label></td>
		     <td align="center"><input type="checkbox" name="subSys[2].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[2].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[2].row"  value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			 <td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['生日']"></s:set>
			 	<input type='hidden' name="subSys[3].name" value='生日'>
			 	<input type='hidden' name="subSys[3].mapping" value='birthday'>
			 	<input type='hidden' name="subSys[3].coltype" value="D">
			 	<input type='hidden' name="subSys[3].stype" value="D">
			 </td>
		     <td align="center">生日</td>
		     <td align="center"><input type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" class="Wdate" name="subSys[3].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[3].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[3].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[3].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			 <td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['省份']"></s:set>
			 	<input type='hidden' name="subSys[4].name" value='省份'>
			 	<input type='hidden' name="subSys[4].mapping" value='province'>
			 	<input type='hidden' name="subSys[4].coltype" value="S">
			 	<input type='hidden' name="subSys[4].stype" value="T">
			 </td>
		     <td align="center">省份</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[4].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[4].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[4].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[4].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['城市']"></s:set>
			 	<input type='hidden' name="subSys[5].id" value='${mp.id }'>
			 	<input type='hidden' name="subSys[5].name" value='城市'>
			 	<input type='hidden' name="subSys[5].mapping" value='city'>
			 	<input type='hidden' name="subSys[5].coltype" value="S">
			 	<input type='hidden' name="subSys[5].stype" value="T">
			 </td>
		     <td align="center">城市</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[5].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[5].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[5].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[5].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['区域']"></s:set>
			 	<input type='hidden' name="subSys[6].name" value='区域'>
			 	<input type='hidden' name="subSys[6].mapping" value='district'>
			 	<input type='hidden' name="subSys[6].coltype" value="S">
			 	<input type='hidden' name="subSys[6].stype" value="T">
			 </td>
		     <td align="center">区域</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[6].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[6].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[6].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[6].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['详细地址']"></s:set>
			 	<input type='hidden' name="subSys[7].name" value='详细地址'>
			 	<input type='hidden' name="subSys[7].mapping" value='address'>
			 	<input type='hidden' name="subSys[7].coltype" value="S">
			 	<input type='hidden' name="subSys[7].stype" value="T">
			 </td>
		     <td align="center">详细地址</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[7].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[7].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[7].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[7].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['身份证']"></s:set>
			 	<input type='hidden' name="subSys[8].name" value='身份证'>
			 	<input type='hidden' name="subSys[8].mapping" value='idcard'>
			 	<input type='hidden' name="subSys[8].coltype" value="S">
			 	<input type='hidden' name="subSys[8].stype" value="T">
			 </td>
		     <td align="center">身份证</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[8].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[8].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[8].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[8].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['邮箱']"></s:set>
			 	<input type='hidden' name="subSys[9].name" value='邮箱'>
			 	<input type='hidden' name="subSys[9].mapping" value='email'>
			 	<input type='hidden' name="subSys[9].coltype" value="S">
			 	<input type='hidden' name="subSys[9].stype" value="E">
			 </td>
		     <td align="center">邮箱</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[9].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[9].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[9].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[9].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['手机']"></s:set>
			 	<input type='hidden' name="subSys[10].name" value='手机'>
			 	<input type='hidden' name="subSys[10].mapping" value='phone'>
			 	<input type='hidden' name="subSys[10].coltype" value="S">
			 	<input type='hidden' name="subSys[10].stype" value="T">
			 </td>
		     <td align="center">手机</td>
		     <td align="center"><input type="text" class="text-medium" name="subSys[10].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[10].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[10].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[10].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
			 	固定字段
			 	<s:set name="mp" value="apt.mappingMap['日期']"></s:set>
			 	<input type='hidden' name="subSys[11].name" value='日期'>
			 	<input type='hidden' name="subSys[11].mapping" value='date'>
			 	<input type='hidden' name="subSys[11].coltype" value="S">
			 	<input type='hidden' name="subSys[11].stype" value="T">
			 </td>
		     <td align="center">日期</td>
		     <td align="center"><input type="text" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" name="subSys[11].defaultvalue" value="${mp.defaultvalue }"></td>
		     <td align="center"><input type="checkbox" name="subSys[11].isshow" value="Y" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><input type="checkbox" name="subSys[11].req" value="Y" <s:if test='#mp.req=="Y"'>checked="checked"</s:if>></td>
		     <td align="center"><span><input type="text" class="text-small" size="3" name="subSys[11].row" value="${mp.row }">行</span>
		</tr>
		<tr align="center" >
			<td align="center">
				固定字段
				<s:set name="mp" value="apt.mappingMap['验证码']"></s:set>
				<input type='hidden' name="subSys[12].name" value='验证码'>
				<input type='hidden' name="subSys[12].mapping" value='code'>
				<input type='hidden' name="subSys[12].coltype" value="S">
				<input type='hidden' name="subSys[12].stype" value="T"> 
			</td>
			<td align="center">验证码</td>
			<td align="center"><input type="text" class="text-medium" placeholder="请输入验证码" name="subSys[12].defaultvalue" value="${mp.defaultvalue }"></td>
			<td align="center"><input type="checkbox" class="samecheck13" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>><input type="hidden" name="subSys[12].isshow" id="flg13" value="${mp.isshow }"></td>
			<td align="center"><input type="checkbox" class="samecheck13" <s:if test='#mp.isshow=="Y"'>checked="checked"</s:if>><input type="hidden" name="subSys[12].req"  id="req13" value="${mp.req }"></td>
			<td align="center"><span><input type="text" class="text-small" size="3" name="subSys[12].row" value="${mp.row }">行</span></td>
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
	</dd>
 	<dd class="pt10">
	<table class="tb_normal tb_high" width="100%"  border="1" cellspacing="1" cellpadding="1" id="addtable">
	<thead>
		<tr>
			<th>字段类型</th>
			<th>字段名称</th>
			<th>初始内容</th>
			<th>是否必填</th>
			<th>位置</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="templeteTBody">
			<s:iterator  value="apt.udfList" var="a"  status = "st">
					<s:if test='tag=="N"'>
						<tr align="center" id="tr_${a.mapping}">
							 <td align="center">
							 	<s:if test='stype=="T"'>单行文本</s:if>
							 	<s:if test='stype=="S"'>下拉框</s:if>
							 	<s:if test='stype=="R"'>单选</s:if>
							 	<s:if test='stype=="C"'>复选</s:if>
							 	<s:if test='stype=="A"'>多行文字</s:if>
							 	<input type='hidden' name="subUdf[${a.mappingid }].coltype" value="${a.coltype }">
							 	<input type='hidden' name="subUdf[${a.mappingid }].stype" value="${a.stype }">
							 	<input type='hidden' name="subUdf[${a.mappingid }].mapping" value='${a.mapping }' class="new_mapping">
							 	<input type="hidden" name="subUdf[${a.mappingid }].tag" value="N"></td>
							 	<input type="hidden" name="subUdf[${a.mappingid }].isshow" value="Y"></td>
							 </td>
						     <td align="center"><input type="text" class="text-medium" name="subUdf[${a.mappingid }].name" value="${a.name }"></td>
						      <td align="center"><input type="text" class="text-medium" name="subUdf[${a.mappingid }].defaultvalue" value="${a.defaultvalue }" id="${a.mapping}" onclick="changeContent('${a.mapping}')">
						      <td align="center"><input type="checkbox" <s:if test='#a.req=="Y"'>checked="checked"</s:if>  name="subUdf[${a.mappingid }].req" value="Y"></td>
						      <td align="center"><span><input type="text" class="text-small" size="3" name="subUdf[${a.mappingid }].row" value="${a.row}">行</span></td>
						      <td align="center"><a href="javascript:void(0)" onclick="removeRow('${a.mapping}')">删除</a></td></tr>
						</tr>
					</s:if> 
			</s:iterator>
			</tbody>
	</table>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd class="mt10">
			<input type="button" value="保存" id="saveBt">
		</dd>
	</dl>
 	</form>
</div>