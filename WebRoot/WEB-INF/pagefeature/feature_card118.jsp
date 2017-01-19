<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<script type="text/javascript" src="/js/rbga.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">微互动-表单</h4>
</div>
<div class="popup-body">
<form action="/page/config_sub_new.action?featureid=${featureid}" enctype="multipart/form-data" method="post">
	
	
	<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#tab1" aria-controls="home" role="tab" data-toggle="tab">选择表单</a></li>
    <li role="presentation"><a href="#tab3" aria-controls="profile" role="tab" data-toggle="tab">整体布局</a></li>
    <li role="presentation"><a href="#tab2" aria-controls="profile" role="tab" data-toggle="tab">高级设置</a></li>
  </ul>

  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="tab1">
	    <p>请选择表单：(请至微互动模块处添加表单内容)</p>
	<input type="hidden" name="dto.fid" value="${fid}" />
	<input type="hidden" name="dto.relationid" value="${relationid}" />
	<input type="hidden" id="color118" value="${dto.color}" />
	<input id="name" type="hidden" name="dto.name" value="${dto.name}">
	  <s:iterator value="dto.interactApt" var="apt">
           <label class="block_label"><input type="radio" value="${apt.id}" name="dto.aptid" namevalue="${apt.title}" <s:if test="dto.aptid == #apt.id"> checked="checked" </s:if> /> ${apt.title} <a href="/${oname }/interact/update_order_pre.action?id=${apt.id }&omid=${apt.omid}">编辑</a></label>
      </s:iterator>
    </div>
    <div role="tabpanel" class="tab-pane" id="tab3">
    	<p><b>内容样式</b></p>
    	<dl>
		 	<dt>字体颜色：</dt>
			<dd id="ftCl" class="bgAll">
				<input value="${dto.color}" type="text" class="ftCl mt5" name="font_color" id="var_cfont_color" />
			</dd>
		</dl>
		<dl>
		 	<dt>字体大小：</dt>
			<dd>
				<select class="selectbder mt5"   name="font_size" id="var_sefont_size">
					<s:iterator begin="8" end="26" var="i" step="2">
						<option <s:if test="dto.size == #i">selected="selected"</s:if> value="${i}">${i }</option>
					</s:iterator>
				</select>
				px
			</dd>
		</dl>
		<dl>
		 	<dt>间距：</dt>
			<dd>
				<select class="selectbder mt5"   name="font_size" id="var_bottom_size">
					<s:iterator begin="5" end="25" var="i">
						<option <s:if test="dto.bottom == #i">selected="selected"</s:if> value="${i}">${i }</option>
					</s:iterator>
				</select>
				px
			</dd>
		</dl>
		<p><b>按钮样式</b></p>
		<dl>
		 	<dt>按钮：</dt>
			<dd>
				<input value="${dto.btcontent}" type="text" class="text-pop form-control" name="bt_content" id="var_bt_content" />
			</dd>
		</dl>
		<dl>
		 	<dt>字体颜色：</dt>
			<dd id="ftCl" class="bgAll">
				<input value="${dto.btcolor}"  type="text" class="ftCl mt5" name="font_color" id="var_bt_font_color" />
			</dd>
		</dl>
		<dl>
		 	<dt>字体大小：</dt>
			<dd>
				<select class="selectbder mt5"   name="font_size" id="var_bt_font_size">
					<s:iterator begin="8" end="26" var="i" step="2">
						<option <s:if test="dto.btsize == #i">selected="selected"</s:if> value="${i}">${i }</option>
					</s:iterator>
				</select>
				px
			</dd>
		</dl>
		<dl>
		 	<dt>按钮背景</dt>
			<dd id="bgClrSlt" class="bgAll">
				<input value="${dto.btbg}" type="text" class="zpicker" id="var_bt_bg" />
			</dd>
		</dl>
		<dl>
		 	<dt>透明度</dt>
			<dd id="bgClrSlt" class="bgAll">
				<div class="User_ratings User_grade" id="div_fraction0">
					<div class="ratings_bars">
						<input type="text" class="title0 bars_10" readonly="readonly" style="border: 1px solid #bfbebe;width:35px;font-size: 14px;"  id="var_bt_tm" value="${dto.bttm}"  />
						<span class="bars_10">0</span>
						<div class="scale" id="bar0">
							<div id="bdc"></div>
							<span id="btn0"></span>
						</div>
						<span class="bars_10">1</span>
						<span class="bars_10" style="margin-left:10px;font-size: 12px;color:red;">注:0为无背景</span>
					</div>
				</div>
			</dd>
		</dl>
		<script type="text/javascript" src="/js/scale.js"></script>
		<script type="text/javascript">
			new scale('btn0', 'bar0', 'var_bt_tm',100);
		</script>
    </div>
    <div role="tabpanel" class="tab-pane" id="tab2">
    	<p>申请跳转设置</p>
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
  <button type="button" class="btn btn-primary" onclick="onClik1(${fid},${featureid},${relationid},'${type }',${pageid },'${oname }')" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
	if(${dto.aptid} == 0){
		$('input:radio[name="dto.aptid"]:first').attr('checked', 'checked');
	}
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
	var color = $("#color118").val();
	if(color != null && color != ""){
		$("#var_cfont_color").attr("style","border:1px solid "+color+";border-right:20px solid "+color+";");
	}
	$("#var_bt_font_color").attr("style","border:1px solid ${dto.btcolor};border-right:20px solid ${dto.btcolor};");
  	var bgcol="RGB(${dto.btbg})";
 	$("#var_bt_bg").attr("style","margin-top:5px;width:80px;border:1px solid "+bgcol.colorHex()+";border-right:20px solid "+bgcol.colorHex()+";")
 	$("#bdc").attr("style","width:"+${dto.bttm}*140+"px;");
   	$("#btn0").attr("style","left:"+${dto.bttm}*140+"px;");
   	$("#tab3 p").attr("style","color:#00a0e9");
});
$('.ftCl').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		if(!bySetColor) $(el).val('#'+hex);
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});
$('.bgClrSlt').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		if(!bySetColor) $(el).val('#'+hex);
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});
$('.zpicker').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		var sb=('#'+hex).colorRgb();
		if(!bySetColor) $(el).val(sb.substring(sb.indexOf('(')+1,sb.indexOf(')')));
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});
</script>
