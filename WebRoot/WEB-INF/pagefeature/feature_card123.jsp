<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">互动模块编辑-投票</h4>
  <!-- 
  <button type="button" class="btn btn-primary" onclick="newVote()">新增</button>
   -->
</div>
<div class="popup-body">
<form  action="/page/config_sub.action?featureid=${featureid}" enctype="multipart/form-data" method="post">
<input type="hidden" name="dto.fid" value="${fid}" />
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#tab1" aria-controls="home" role="tab" data-toggle="tab">选择投票</a></li>
    <li role="presentation"><a href="#tab2" aria-controls="profile" role="tab" data-toggle="tab">高级设置</a></li>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="tab1">
	    <p>请选择需要投票的选项：(请至微互动模块处添加投票)</p>
	  <s:iterator value="dto.interactVote" var="apt" status="st">
           <label class="block_label"><input type="radio" value="${apt.id}" count="${st.count}" name="dto.voteid" <s:if test='dto.voteid ==0 && #st.index==0'>checked="checked"</s:if><s:if test="dto.voteid == #apt.id"> checked="checked" </s:if> /> ${apt.title} 
           <a href="/${oname }/interact/to_update_vote_design.action?voteid=${apt.id }&omid=${apt.omid }">编辑</a>
           </label>
      </s:iterator>
           从<input type="number" name="start" min="0" value="${dto.start }" class="text-small"  id="start" onkeyup="this.value = this.value.replace(/\D/g,'');">
       	  至<input type="number" name="end" min="0" value="${dto.end }"  class="text-small" id="end" onkeyup="this.value = this.value.replace(/\D/g,'');">题
    </div>
    <div role="tabpanel" class="tab-pane" id="tab2">
    	<p>投票跳转设置</p>
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
  <button type="button" class="btn btn-primary" onclick="onClikVote(${fid},${featureid},${relationid},'${type }',${pageid },'${oname }')" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});

function addNum(count,total){
	var f = $('#start'+count).val();
	if(f > 0){
		if(f > total){
			alert("超出选项范围，请重新填写");
			$('#start'+count).val(0);
			$('#end'+count).val(0);
		}else{
			var end = f - 0 + 3;
			if(end > total){
				$('#end' + count).val(total);
			}else{
				$('#end' + count).val(end);
			}
		}
	}
}

function editNum(count,total){
	var f = $('#end'+count).val();
	if(f > 0){
		if(f > total){
			alert("该题只有" + total + "个选项");
			var s = $('#start'+count).val();
			if(s > 0){
				var e = s - 0 + 3;
				if(e > total){
					$('#end' + count).val(total);
				}else{
					$('#end' + count).val(e);
				}
			}else{
				$('#end' + count).val(0);
			}
		}
	}
}
</script>