<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="popup-header">
  <button type="button" class="close closePopup"><span>&times;</span></button>
  <a class="btn btn-primary" id="tab_content">内容</a>
  <a class="btn btn-default" id="tab_style">样式</a>
</div>
<div class="popup-body">
<form id="form1" name="form1" action="/${oname}/page/blockSub.action" method="post">
	<div class="clearfix">
	<ul id="json_list">
		<s:iterator value="dto.pbr.jsonObject.list" var ="p" status="st">
		<li class="celement" id="list_${st.count}">
			<input type="hidden" id="json_${st.count }" class="hy_yuansu" name="list" value='${p}'/>
			<a id="xs_${st.count}" href="javascript:void(0)" onclick="showJson(${st.count},'${oname }')">元素${st.count}</a>
			<a id="sc_${st.count}" href="javascript:void(0)" onclick="del_json(${st.count },'${oname }')" class="closeTab">删除</a>
		</li>
		</s:iterator>
	</ul>
		<a href="javascript:void(0);" onclick="add_list('${oname}')" class="addTab">增加</a>
	</div>
	<input type="hidden" id="ct_value" name="hyct">
</form>
	
<div class="list_div blockview mt20" id="newDiv">
<form id="newform">
	<s:iterator value="dto.tb.attr" var="j">
	<div class="edit_content">
		<s:if test='#j.value.type=="T" || #j.value.type=="S"'>
			<dl>
			 	<dt>${j.value.mapping}</dt>
				<dd>
					<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }" />${j.value.desc }
				</dd>
			</dl>
		</s:if>
		<s:if test='#j.value.type=="NUM"'>
				<dl>
				 	<dt>${j.value.mapping}</dt>
					<dd>
						<input type="number" class="text-pop form-control" name="${j.key }" id="var_${j.key }" />${j.value.desc }
					</dd>
				</dl>
			</s:if>
		<s:if test='#j.value.type=="A"'>
			<dl>
			 	<dt>${j.value.mapping }</dt>
				<dd>
					<textarea class="kindeditor" name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
				</dd>
			</dl>
		</s:if>
		<s:if test='#j.value.type=="I"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
				 	<dd>
				 		<div class="btn-group">
						  <button type="button" class="btn btn-default"  onClick="upd_${j.key }.click()">原图上传</button>
						  <button type="button" class="btn btn-default" id="pic_upload" name="pic" onclick="img_flash('${j.key }','${j.value.desc }')">截图上传</button>
						  <button type="button" class="btn btn-default" onclick="sckall(2,'${j.key}')">从素材库选择</button>
						</div>
						${j.value.desc }
						<input name="${j.key }" type="hidden" id="var_${j.key }">
						<input type="file" name="img" id="upd_${j.key }" onchange="ajaxFileUpload('${imgDomain }','${j.key }')" style="display:none;" >
				 	</dd>
					<dd>
						<img id="img_${j.key}" src="" style="max-width:100%;max-height:100px;"/>
					</dd>
				</dl>
		</s:if>
		<s:if test='#j.value.type=="L"'>
			<dl>
			 	<dt>${j.value.mapping }</dt>
				<dd>
					<input type="text" class="text-pop form-control hylink" name="${j.key }" id="var_${j.key }"/>
				</dd>
				<dd>
					<label><input type="checkbox" id="gl_${j.key}" onclick="usegld('${j.key}','${oname }')">链接到</label>
					<select class="text-pop form-control" id="sel_${j.key}" disabled="disabled" name="gld" onchange="gldym('${j.key}',this,'${oname}')">
						<s:iterator value='dto.pages' var="p">
						<option value="${p.id }">${p.name}</option>
						</s:iterator>
					</select>
				</dd>
				<dd>
					<div>
						<a href="javascript:void(0);" onclick="chct('var_${j.key}','${oname}')">从内容素材中挑选</a>
					</div>
				</dd>
			</dl>
		</s:if>
		<s:if test='#j.value.type=="V"'>
			<dl>
			 	<dt>${j.value.mapping }</dt>
				<dd>
					<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }"/>
				</dd>
			</dl>
		</s:if>
		</div>
		<div class="edit_style" style="display:none">
		<!-- bgcolor -->
			<s:if test='#j.value.type=="Z"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="bgClrSlt" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
						<input type="text" class="zpicker" name="${j.key }" id="var_bc${j.key }" />
					</dd>
				</dl>
			</s:if>
			<!-- font-color -->
			<s:if test='#j.value.type=="F"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="ftCl" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
						<input type="text" class="ftCl" name="${j.key }" id="var_c${j.key }" />
					</dd>
				</dl>
			</s:if>
			<!-- bordercolor -->
			<s:if test='#j.value.type=="B"'>   
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="btCl" class="bgAll" <s:if test='dto.flag=="P"'>style="display:none;"</s:if>>
						<input type="text" class="btCl" name="${j.key }" id="var_c${j.key }" />
					</dd>
				</dl>
			</s:if>
			<!-- 边框像素 -->
			<s:if test='#j.value.type=="BPX"'>   
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="btCl" class="bgAll">
						<input type="text" class="btCl"  name="b1" id="var_b1" />
					</dd>
				</dl>
				<dl>
				 	<dt>边框宽度</dt>
					<dd id="btCl" class="bgAll">
						<select class="selectbder"  name="b2" id="var_seb2">
							<s:iterator begin="0" end="20" var="i">
								<option value="${i}px">${i }px</option>
							</s:iterator>
						</select>
					</dd>
				</dl>
				<dl>
				 	<dt>边框样式</dt>
					<dd id="btCl" class="bgAll">
						<select class="selectbder" name="b3" id="var_seb3">
								<option value="solid">实线</option>
								<option value="dashed">虚线</option>
						</select>
					</dd>
				</dl>
			</s:if>
			<!-- 边框圆角 -->
			<s:if test='#j.value.type=="BYJ"'>   
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="bdradius" class="bgAll">
						<div class="User_ratings User_grade" id="div_fraction0">
							<div class="ratings_bars">
								<input type="text" class="title0 bars_10" readonly="readonly" style="border: 1px solid #bfbebe;width:35px;font-size: 14px;" name="${j.key }" id="var_bds${j.key }" value="${j.value.tm }" />
								<span class="bars_10">0</span>
								<div class="scale" id="bar0${j.key}">
									<div id="bds${j.key}"></div>
									<span id="btn0${j.key}"></span> 
								</div>
								<span class="bars_10">20</span>
							</div>
						</div>
					</dd>
				</dl>
				<script type="text/javascript" src="/js/scale.js"></script>
				<script type="text/javascript">
					new scale('btn0${j.key}', 'bar0${j.key}', 'var_bds${j.key }',5);
				</script>
			</s:if>
			<s:if test='#j.value.type=="X"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="bgClrSlt" class="bgAll">
						<div class="User_ratings User_grade" id="div_fraction0">
							<div class="ratings_bars">
								<input type="text" class="title0 bars_10" readonly="readonly" style="border: 1px solid #bfbebe;width:35px;font-size: 14px;" name="${j.key }" id="var_bdc${j.key }" value="${j.value.desc }" />
								<span class="bars_10">0</span>
								<div class="scale" id="bar0${j.key}">
									<div id="bdc${j.key}"></div>
									<span id="btn0${j.key}"></span>
								</div>
								<span class="bars_10">1</span>
								<span class="bars_10" style="margin-left:10px;font-size: 12px;color:red;">注:0为最透明</span>
							</div>
						</div>
					</dd>
				</dl>
				<script type="text/javascript" src="/js/scale.js"></script>
				<script type="text/javascript">
					new scale('btn0${j.key}', 'bar0${j.key}', 'var_bdc${j.key }',100);
				</script>
			</s:if>
		</div>
		</s:iterator>
</form>
</div>
<div class="clearfix"></div>
 </div>
    <div class="popup-footer">
<input type="hidden" id="relationid" value="${relationid}">
<input type="hidden" id="show_index">
  <button type="button" class="btn btn-default closePopup">关闭</button>
  <button type="button" class="btn btn-primary" id="block_list_btn" onclick="onClik2(${dto.pbr.pcid})">保存</button>
</div>

<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<script type="text/javascript" src="/js/rbga.js"></script>
<script type="text/javascript">
$(document).ready(function() {  
         	showJson(1,'${oname}');
         	 $("#tab_content").click(function(){
	     	$(".popup-header .btn-primary").removeClass("btn-primary").addClass("btn-default");
	     	$(this).removeClass("btn-default").addClass("btn-primary");
	     	$(".edit_content").show();
	     	$(".edit_style").hide();
	     });
	     $("#tab_style").click(function(){
	     	$(".popup-header .btn-primary").removeClass("btn-primary").addClass("btn-default");
	     	$(this).removeClass("btn-default").addClass("btn-primary");
	    	$(".edit_content").hide();
	     	$(".edit_style").show();
	     });
     	     $(".closePopup").click(function(){
				$("#rightPopup").animate({width:'hide'});
			});
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
$('.btCl').colpick({
	layout:'hex',
	submit:0,
	onChange:function(hsb,hex,rgb,el,bySetColor) {
		$(el).css('border-color','#'+hex);
		if(!bySetColor) $(el).val('#'+hex);
	}
}).keyup(function(){
		$(this).colpickSetColor(this.value);
	});	

</script>

