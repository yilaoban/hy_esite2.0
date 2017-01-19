<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<dl>
 	<dt>输入文字：</dt>
	<dd>
		<textarea rows="" cols="" name="font_content" id="var_font_content"></textarea>
	</dd>
</dl>
<dl>
 	<dt>颜色：</dt>
	<dd id="ftCl" class="bgAll">
		<input type="text" class="ftCl" name="font_color" id="var_cfont_color" />
	</dd>
</dl>
<dl>
 	<dt>大小：</dt>
	<dd>
		<select onchange="findfontsize();" class="selectbder mt5"   name="font_size" id="var_sefont_size">
			<s:iterator begin="12" end="96" var="i" step="8">
				<option value="${i}">${i }</option>
			</s:iterator>
		</select>
	</dd>
</dl>
<dl>
 	<dt>字体：</dt>
	<dd>
		<s:iterator begin="1" end="7" var="i">
			<span onclick="findfont('f${i }')" class="f${i} fontClass" style="background:url(/font/f${i }.png) no-repeat -3px -3px;"></span>
		</s:iterator>
		
		<input type="hidden" name="font_fm_style" id="var_font_fm_style"/>
		<input type="hidden" name="img" id="var_img"/>
	</dd>
</dl>
<script type="text/javascript">
	$(".fontClass").hover(function() {
        $(this).addClass("fontClassborder");
    }, function() {
        $(this).removeClass("fontClassborder");
    });
        
	$(document).ready(function() {
		var font =$("#var_font").val();
		if(font == null || font == ""){
			$("#var_font").val("/images/zujian/1vm/null.png");
		}
	});
	function findfont(font){
		$(".fontClass").removeClass("fontClassborder fontClassborder2");
		$("."+font).addClass("fontClassborder2");
		$("#var_font_fm_style").val(font);
		var text= $("#var_font_content").val();
		var size= $("#var_sefont_size").val();
		var col= $("#var_cfont_color").val();
		var color = col.substring(1,col.length);
		var relationid = ${relationid};
		var src = "/${oname}/user/hyDraw.action?random="+Math.random()+"&text="+encodeURI(encodeURI(text))+"&size="+size+"&color="+color+"&font="+font+"&relationid="+relationid;
		$("#var_img").val(src);
		$("<img/>").attr("src", src).load(function() {
			imgWidth = this.width;
			imgHeight = this.height;
		});
		$("#img_"+relationid).attr("src",src).load(function(){
			$(this).parent().css("width",imgWidth).css("height",imgHeight);
			click2save();
		});
	}
	
	function findfontsize(){
		var font =$("#var_font_fm_style").val();
		var str = font.indexOf("f");
		if(str == 0){
			findfont(font);
		}
	}
</script>


