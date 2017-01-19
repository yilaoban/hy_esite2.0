<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>

<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">互动模块编辑-调研</h4>
  <!-- 
  <button type="button" class="btn btn-primary" onclick="newResearch()">新增</button>
   -->
</div>
<div class="popup-body">
<form enctype="multipart/form-data" method="post">
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#tab1" aria-controls="home" role="tab" data-toggle="tab">选择调研</a></li>
    <li role="presentation"><a href="#tab2" aria-controls="profile" role="tab" data-toggle="tab">高级设置</a></li>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="tab1">
	    <p style="margin-top:15px;">请选择需要调研的选项：(请至微互动模块处添加调研)</p>
		  <s:iterator value="dto.researchList" var="apt">
	           <label class="block_label"><input type="radio" value="${apt.id}" name="dto.researchid" <s:if test="dto.researchid == #apt.id"> checked="checked" </s:if> /> ${apt.title} <a href="/${oname }/interact/to_update_research_design.action?researchid=${apt.id }&omid=${apt.omid }">编辑</a></label>
	      </s:iterator>
    </div>
    <div role="tabpanel" class="tab-pane" id="tab2">
    	<p style="margin-top:15px;">调研跳转设置</p>
		<label class="forradio"><input type="radio" name="dto.redirect.type" value="N" <s:if test='dto.type == "N"'> checked="checked" </s:if> onclick="$('#ruletype1').hide();$('#urlType1').hide();$('#ruletype2').hide();$('#urlType2').hide();$('#toPageid').hide()"/>默认跳转</label> <br>
 		
 		<label class="forradio"><input type="radio" name="dto.redirect.type" value="Z" <s:if test='dto.type == "Z"'> checked="checked" </s:if> onclick="$('#ruletype1').show();$('#urlType1').show();$('#ruletype2').hide();$('#urlType2').hide();$('#toPageid').hide()"/>跳转到指定链接</label>
		<select id="urlType1" <s:if test='dto.type != "Z"'> style="display: none"</s:if>>
			<option value ="http://" <s:if test='dto.urlPre=="http://"'>selected="selected"</s:if>>http://</option>
			<option value ="https://" <s:if test='dto.urlPre=="https://"'>selected="selected"</s:if>>https://</option>
		</select>
		<input <s:if test='dto.type != "Z"'> style="display: none"</s:if> class="text-long" id="ruletype1" type="text" <s:if test='dto.type == "Z"'> value="${dto.urlShow }" </s:if>><br>
		<label class="forradio"><input type="radio" name="dto.redirect.type" value="L" <s:if test='dto.type == "L"'> checked="checked" </s:if> onclick="$('#ruletype1').hide();$('#urlType1').hide();$('#ruletype2').hide();$('#urlType2').hide();$('#toPageid').show()"/>链接到</label> <br>
		<select id="toPageid" <s:if test='dto.type != "L"'> style="display: none"</s:if>>
			<option value="0">请选择跳转页面</option>
			<s:iterator value="dto.pageList" var="p">
			  <option value="${p.id}"  <s:if test="#p.id == dto.toPageid ">selected="selected"</s:if> >${p.name}</option>
			</s:iterator>  
		</select>
    </div>
  </div>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikResearch(${fid},${featureid},${relationid},'${type }',${pageid },'${oname}')" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
	if(${dto.researchid} == 0){
		$('input:radio[name="dto.researchid"]:first').attr('checked', 'checked');
	}
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>