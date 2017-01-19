<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function formSub(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"content_form_update_sub.action",
		        data:$('#form1').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.alert("Connection error",0);
		        },
		        success: function(data) {
		            if(data=="Y"){
		            	alert('操作成功');
		            	window.parent.location="/${oname}/content/content_form_index.action?ccid="+${dto.current.id};
		            }else{
		            	alert('操作失败');
		            }
		        }
		    });
		}
</script>
<a name="maodian"></a>    
<div class="wrap_content left_module">
  <form action="" method="post" id="form1" name="myform" class="formview">
  <div>
  <input type="hidden" name="contentform.id" value="${dto.contentform.id}">
  <input type="hidden" id="trindex" value="1">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑表单</a></li>
	  </ul>
	  <a href="content_form_index.action?ccid=${dto.contentform.catid }" class="return" title="返回"></a>
	</div>

	<dl>
 	<dt>表单字段设置</dt>
 	<dd>
 	<span class="notice">(填写你要收集的内容，默认选项不可以修改删除。)</span>
 	<table class="tb_normal tb_high" width="100%"  border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>字段类型</th>
			<th>字段名称</th>
			<th>初始内容</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator  value="dto.contentform.list" var="a"  status = "st">
						<tr align="center" id="tr_${a.mapping}">
							 <td align="center">
							 	固定字段
							 	<input type="hidden" name="contentform.list[${st.index }].mapping" value="${a.mapping }"/>
							 	<input type="hidden" name="contentform.list[${st.index }].coltype" value="${a.coltype}"/>
							 	<input type="hidden" name="contentform.list[${st.index }].stype" value="${a.stype }"/>
							 </td>
						     <td align="center"><input type="text" class="text-medium" name="contentform.list[${st.index }].name" value="${a.name }"></td>
							 <td align="center"><input type="text" class="text-medium" name="contentform.list[${st.index }].defaultvalue" value="${a.defaultvalue}"></td>
						      <td align="center"><span>位置<input type="text" class="text-small" size="3" name="contentform.list[${st.index }].row" value="${a.row}">行</span></td>
						</tr>
		</s:iterator>	
	</tbody>
	</table>
	</dd>
	</dl>
	
	<dl class="mt10">
 	<dt>自定义字段</dt>
 	<dd>
		<input type="button" class="btn btn-info" onclick="addCfRow('T')" value="单行文本"/>
		<!-- 
		<input type="button" onclick="addCfRow('S')" value="下拉框"/>
		<input type="button" onclick="addCfRow('R')" value="单选"/>
		<input type="button" onclick="addCfRow('C')" value="复选"/>
		 -->
		<input type="button" class="btn btn-info" onclick="addCfRow('A')" value="多行文字"/>
		<input type="button" class="btn btn-info" onclick="addCfRow('P')" value="百度位置"/>
		<input type="button" class="btn btn-info" onclick="addCfRow('B')" value="图片"/>
		<input type="button" class="btn btn-info" onclick="addCfRow('Q')" value="360全景"/>
	</dd>
 	<dd class="pt10">
	<table class="tb_normal tb_high" width="100%"  border="1" cellspacing="1" cellpadding="1" id="addtable">
	<thead>
		<tr>
			<th>字段类型</th>
			<th>字段名称</th>
			<th>初始内容(多个内容以换行分开)</th>
			<th>位置</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="templeteTBody">
				<s:iterator  value="dto.contentform.flist" var="a" status="st">
						<tr align="center" id="tr_${a.mapping}">
							 <td align="center" >
							 	<s:if test='stype=="T"'>单行文本</s:if>
							 	<s:if test='stype=="S"'><select><option>下拉框</option></select></s:if>
							 	<s:if test='stype=="R"'><input type="radio">单选</s:if>
							 	<s:if test='stype=="C"'><input type="checkbox">复选</s:if>
							 	<s:if test='stype=="A"'>多行文字</s:if>
							 	<s:if test='stype=="P"'>百度位置</s:if>
							 	<s:if test='stype=="B"'>图片</s:if>
							 	<s:if test='stype=="Q"'>360全景</s:if>
							 	<input type="hidden" name="contentform.flist[${st.index }].mapping" value="${a.mapping }"/>
							 	<input type="hidden" name="contentform.flist[${st.index }].coltype" value="S"/>
							 	<input type="hidden" name="contentform.flist[${st.index }].stype" value="${a.stype }"/>
							 </td>
						     <td align="center"><input type="text" class="text-medium" name="contentform.flist[${st.index }].name" value="${a.name }"></td>
						     <s:if test='stype=="S" || stype=="R" || stype=="C"'>
						     <td align="center"><input type="text" class="text-medium" name="contentform.flist[${st.index }].defaultvalue" value="${a.defaultvalue}" id="${a.mapping}"  onclick="changeContent('${a.mapping}')" <s:if test='stype=="S"||stype=="R"||stype=="C"'>placeholder="必须初始内容"</s:if>></td>
						     </s:if>
						     <s:else>
						     <td align="center"><input type="text" class="text-medium" name="contentform.flist[${st.index }].defaultvalue" value="${a.defaultvalue}" <s:if test='stype=="S"||stype=="R"||stype=="C"'>placeholder="必须初始内容"</s:if>>	</td>
						     </s:else>
						     <td><span>位置<input type="text" class="text-small" size="3" name="contentform.flist[${st.index }].row" value="${a.row}">行</span></td>
						      <td align="center" id="tr1">
						      	<a href="javascript:void(0)" onclick="removeRow('${a.mapping}')">删除</a>
						      </td>
						</tr>
				</s:iterator>
			</tbody>
	</table>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd class="mt10">
			<input type="button" class="btn btn-primary" value="保存" onclick="formSub()">
			<input type="reset" class="btn" value="重置">
		</dd>
	</dl>
 	</form>
</div>
