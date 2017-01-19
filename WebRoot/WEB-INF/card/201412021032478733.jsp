<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 口碑1 -->
<link href="/css/hudong/koubei/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="dgl_1106_box" class_data="hy2015050744249">
	<s:if test='blocks[0].display=="Y"'>
	<div class="dgl_1106_b_top block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050744519"><img src="${blocks[0].img}" hyvar="img" hydesc="640*460"/></div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div hyblk="I" class="block" hydata="${blocks[1].rid}" class_data="hy2015050744872">
		<s:if test='blocks[1].obj.id > 0'>
			<s:set name="obj" value="blocks[1].obj" />
			<div class="biaodan_tj_dgl_1106" class_data="hy2015050744307">
				<input id="content${pcid }"  type="text" value="赶快在这里填写你创意的Slogan哦~~" onfocus="if(value=='赶快在这里填写你创意的Slogan哦~~') {value=''}" onblur="if
				(value=='') {value='赶快在这里填写你创意的Slogan哦~~'}"  style="width:99%; height:40px; color:#b0b0b0; text-align:center;font-family:Microsoft YaHei;font-size:1.0em;"/>
			</div>
		   
			<div class="dgl1031_pc_c_haoyou" class_data="hy2015050744677">
				<ul class_data="hy2015050744167">
					<li style="margin:0" class_data="hy2015050744861">
					<input id="nickname1${pcid }" name="nickname1" type="text"  style=" margin-left:18px; height:30px; width:85%;"/>
					</li>
					<li class_data="hy2015050744489">
					<input id="nickname2${pcid }" name="nickname2" type="text"  style=" margin-left:18px; height:30px; width:85%;"/>
					</li>
					<li class_data="hy2015050744478">
					<input id="nickname3${pcid }" name="nickname3" type="text"  style=" margin-left:18px; height:30px; width:85%;"/>
					</li>
	
				</ul>
			</div>
			<div class="dgl_1106_anniu" class_data="hy2015050744175"><img src="/images/hudong/koubei/tj.png" id="tijiao${pcid }" width="130px" hyvar="img" hydesc="130*50"/></div>
		</s:if>
		<s:else>
			<div class="biaodan_tj_dgl_1106" class_data="hy2015050744601">
				<input name="" type="text" value="赶快在这里填写你创意的Slogan哦~~" onfocus="if(value=='赶快在这里填写你创意的Slogan哦~~') {value=''}" onblur="if
				(value=='') {value='赶快在这里填写你创意的Slogan哦~~'}"  style="width:99%; height:40px; color:#b0b0b0; text-align:center;font-family:Microsoft YaHei;font-size:1.0em;"/>
			</div>
		   
			<div class="dgl1031_pc_c_haoyou" class_data="hy2015050744102">
				<ul class_data="hy2015050744101">
					<li style="margin:0" class_data="hy2015050744722">
					<input name="Input" type="text"  style=" margin-left:18px; height:30px; width:85%;"/>
					</li>
					<li class_data="hy2015050744228">
					<input name="Input" type="text"  style=" margin-left:18px; height:30px; width:85%;"/>
					</li>
					<li class_data="hy2015050744310">
					<input name="Input" type="text"  style=" margin-left:18px; height:30px; width:85%;"/>
					</li>
	
				</ul>
			</div>
	
		   
			<div class="dgl_1106_anniu" class_data="hy2015050744390"><img src="/images/hudong/koubei/tj.png"  width="130px" hyvar="img" hydesc="130*50"/></div>
		</s:else>
	</div>
	</s:if>
	<s:if test='blocks[2].display=="Y"'>
	<div class="dgl_1106_dzp_hdsm block" hyblk="S" hydata="${blocks[2].rid}" class_data="hy2015050744852">
		<span hyvar="desc" class_data="hy2015050744141">${blocks[2].desc}</span>
	</div>
	</s:if>
</div>

<script type="text/javascript">
			$(document).ready(function(){
				$("#tijiao${pcid }").click(function(){
					var nick1=$("#nickname1${pcid }").val();
					var nick2=$("#nickname2${pcid }").val();
					var nick3=$("#nickname3${pcid }").val();
					var content = $("#content${pcid}").val();
					if(nick1=="" || nick2=="" || nick3==""){
						$.alert("请@三位好友","");
						if(nick1 == "") $("#nickname1${pcid }").focus();
						if(nick2 == "") $("#nickname2${pcid }").focus();
						if(nick3 == "") $("#nickname3${pcid }").focus();
						return;
					}
				var nicknames='["'+nick1+'","'+nick2+'","'+nick3+'"]';
						$.post("/${oname}/user/spread.action",{"content":content,"spreadid":'${blocks["1"].obj.id}',"pageid":'${pageid}',"nicknames":nicknames},function(data){
							if(data == 1){
								$.alert("提交成功","");
							}else if(data == -2){
								$.alert("提交失败","");
							}else if(data == -3){
							    $.alert("新浪接口繁忙,请稍后再试","");
							}
						});
				});
			});
	
</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>