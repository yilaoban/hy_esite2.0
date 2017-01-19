<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/rbga.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup"><span>&times;</span></button>
  <ul class="nav nav-tabs" role="tablist">
    <li id="tab_content" role="presentation" class="active"><a aria-controls="home" role="tab" data-toggle="tab">内容</a></li>
    <li id="tab_style" role="presentation"><a aria-controls="profile" role="tab" data-toggle="tab">样式</a></li>
    <li id="tab_donghua" role="presentation"><a aria-controls="profile" role="tab" data-toggle="tab">动画</a></li>
    <li id="tab_redirect" role="presentation"><a aria-controls="profile" role="tab" data-toggle="tab">高级设置</a></li>
  </ul>
</div>
<div class="popup-body">
	<form id="form1" name="form1" action="/page/blockSub.action" method="post" class="blockview clearfix">
	<s:iterator value="dto.tb.attr" var="j" status="st">
		<div class="edit_content">
			<s:if test='#j.value.type=="URL"'>
				<dl>
					<dt>温馨提示</dt>
					<dd style="padding-top:5px;">请将pano2vr软件生成的目录打包上传</dd>
				 	<dt>${j.value.mapping}</dt>
					<dd>
						<div id="as${j.key }"></div>
						<div id="picker${j.key }">点击上传</div>
						<script type="text/javascript">
						/*
						* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
						* 其他参数同WebUploader
						*/
						$('#as${j.key }').diyUpload({
							url:'/${oname}/user/quanjing.action',
							success:function( data ) {
								if(data.xmlPath!=""){
									$("#record${j.key }").val(data.xmlPath);
								}
								$("#picker${j.key }").hide();
							},
							error:function( err ) {
								console.info( err );	
							},
							del:function(filename) {
								$("#picker${j.key }").show();
							},
							auto: true,
							pick: '#picker${j.key }',
							//chunked:true,
							// 分片大小
							//chunkSize:512 * 1024,
							//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
							fileNumLimit:1,
							fileSizeLimit:500000 * 1024,
							fileSingleSizeLimit:50000 * 1024,
							accept:{
								extensions:"zip",
								mimeTypes:"application/zip|zip"
							}
						});
						</script>
					</dd>
					<dt></dt>
					<dd><input type="hidden" name="${j.key }" id="record${j.key }"/></dd>
				</dl>
			</s:if>
			<%--单行 --%>
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
			<s:if test='#j.value.type=="VO"'>
				<dl>
				 	<dt>${j.value.mapping}</dt>
					<dd>
						<textarea oninput="updatevalue(this)" name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<%--多行编辑器 --%>
			<s:if test='#j.value.type=="A"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<textarea class="kindeditor" name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<s:if test='#j.value.type=="HTML"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<textarea name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<s:if test='#j.value.type=="JS"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<textarea name="${j.key }" id="var_${j.key }"></textarea>${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<%--图片 --%>
			<s:if test='#j.value.type=="I"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
				 	<dd>
				 		<div class="btn-group">
						  <button type="button" class="btn btn-default"  onClick="upd_${j.key }.click()">原图上传</button>
						  <button type="button" class="btn btn-default" id="pic_upload" name="pic" onclick="img_flash('${j.key }','${j.value.desc }')">截图上传</button>
						  <button type="button" class="btn btn-default" onclick="sckall(2,'${j.key}')">从素材库选择</button>
						</div>
						<input name="${j.key }" type="hidden" id="var_${j.key }">
						<input type="file" name="img" id="upd_${j.key }" onchange="ajaxFileUpload('${imgDomain }','${j.key }')" style="display:none;" >
				 	</dd>
					<dd>
						<img id="img_${j.key}" src="" style="max-width:100%;max-height:100px;"/>
					</dd>
				</dl>
			</s:if>
			<%--链接 --%>
			<s:if test='#j.value.type=="L"'>
				<dl >
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="text" placeholder="外链请以http://开头" class="text-pop form-control hylink" name="${j.key }" id="var_${j.key }" />${j.value.desc }
					</dd>
					<dd >
						<label style="width:15%;float:left;line-height:35px"><input type="checkbox" id="gl_${j.key}" onclick="usegld('${j.key}','${oname }')">链接到</label>
						<select style="width:85%;float:left" class="text-pop form-control" id="sel_${j.key}" disabled="disabled" name="gld" onchange="gldym('${j.key}',this,'${oname }')">
							<s:iterator value='dto.pages' var="p">
							<option value="${p.id}">${p.name}</option>
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
			<%--视频 --%>
			<s:if test='#j.value.type=="V"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }" />${j.value.desc }
					</dd>
				</dl>
			</s:if>
			<%--地图地址 --%>
			<s:if test='#j.value.type=="D1"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }"/>
						<input type="hidden" class="text-pop form-control" name="position" id="var_position"/>
						<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
						<div id="bdmap_edit" style="width:0%;height:0%;display: none"></div>
						<script type="text/javascript" src="/js/bdlbs.js"></script>
					</dd>
				</dl>
			</s:if>
			<%--地图级别--%>
			<s:if test='#j.value.type=="D2"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<select name="${j.key }" id="var_se${j.key }">
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
						</select>
					</dd>
				</dl>
			</s:if>
			<s:if test='#j.value.type=="FONT"'>
				<s:include value="block_font.jsp"></s:include>
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
					<dd id="ftCl" class="bgAll">
						<input type="text" class="ftCl" name="${j.key }" id="var_c${j.key }" />
					</dd>
				</dl>
			</s:if>
			<!-- bordercolor -->
			<s:if test='#j.value.type=="B"'>   
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="btCl" class="bgAll">
						<input type="text" class="btCl" name="${j.key }" id="var_c${j.key }" />
					</dd>
				</dl>
			</s:if>
			<!-- 小图标 -->
			<s:if test='#j.value.type=="TB"'>   
				<dl class="listyle">
				 	<dt>${j.value.mapping }</dt>
					<dd>
						<ul>
							<input type="hidden" name="${j.key }" id="var_tb${j.key }" />
							<li><a class="tbclass"><span class="nullclass">无</span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-pencil"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-earphone"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-home"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-envelope"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-trash"></span></a></li>
							<li><a style="border:1px solid #00a0e9;background-color:#00a0e9;color:#fff" onclick="showtb()"><b>+</b>更多</a></li>
						</ul>
					</dd>
					<dd id="showtb" style="width:200px;height:100px;word-break : normal | break-all | keep-all ;border:1px solid #474747;display:none;">
						<ul>
							<li><a class="tbclass"><span class="glyphicon glyphicon-envelope"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-music"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-search"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-heart"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-star"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-star-empty"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-user"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-film"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-th-list"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-off"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-signal"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-file"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-download-alt"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-play-circle"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-refresh"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-headphones"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-qrcode"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-font"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-facetime-video"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-picture"></span></a></li>
							<li><a class="tbclass"><span class="glyphicon glyphicon-open"></span></a></li>
						</ul>
						<script type="text/javascript">	
							$(".tbclass").on("click",function(){
								$(".tbclass span").removeAttr("style");
								$("span").removeClass("showcolor"); 
								$(this).find("span").css("color","#00a0e9;");
								var class1 = $(this).find("span").attr("class");
								$("#var_tb${j.key }").val(class1);
							});
						</script>
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
			
			<!-- 透明度 -->
			<s:if test='#j.value.type=="X"'>
				<dl>
				 	<dt>${j.value.mapping }</dt>
					<dd id="bgClrSlt" class="bgAll">
						<div class="User_ratings User_grade" id="div_fraction0">
							<div class="ratings_bars">
								<input type="text" class="title0 bars_10" readonly="readonly" style="border: 1px solid #bfbebe;width:35px;font-size: 14px;" name="${j.key }" id="var_bdc${j.key }" value="${j.value.tm }" />
								<span class="bars_10">0</span>
								<div class="scale" id="bar0${j.key}">
									<div id="bdc${j.key}"></div>
									<span id="btn0${j.key}"></span>
								</div>
								<span class="bars_10">1</span>
								<span class="bars_10" style="margin-left:10px;font-size: 12px;color:red;">注:0为无背景</span>
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
		<div class="edit_donghua" style="display:none">
			<s:if test='#j.key=="hyct"'>
				<dl>
				 	<dt>动画效果</dt>
					<dd>
						<div class="btn-group btn-group-aimation">
						  <button onclick="ctfunction(0,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype == 0 && dto.pbr.jsonObject.cttype !="" '> btn-primary</s:if>" type="button">淡入</button>
						  <button onclick="ctfunction(1,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype ==1'> btn-primary</s:if>" type="button">移入</button>
						  <button onclick="ctfunction(2,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype ==2'> btn-primary</s:if>" type="button">弹入</button>
						  <button onclick="ctfunction(3,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype ==3'> btn-primary</s:if>" type="button">从中心弹入</button>
						  <button onclick="ctfunction(4,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype ==4'> btn-primary</s:if>" type="button">从中心放大</button>
						  <button onclick="ctfunction(5,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype ==5'> btn-primary</s:if>" type="button">抖动</button>
						  <button onclick="ctfunction(6,this)" class="btn btn-default<s:if test='dto.pbr.jsonObject.cttype ==null || dto.pbr.jsonObject.cttype =="" || dto.pbr.jsonObject.cttype ==6'> btn-primary</s:if>" type="button">无</button>
						</div>
					</dd>
				</dl>
				<dl class="fx-hide" <s:if test='dto.pbr.jsonObject.cttype!=1 && dto.pbr.jsonObject.cttype !=2'>style="display: none"</s:if> >
					<dt>动画方向</dt>
					<dd>
							<select id="fx_name" onchange="fxname(this)" class="text-pop form-control">
								<option value="0" <s:if test='dto.pbr.jsonObject.ctfx ==null || dto.pbr.jsonObject.ctfx =="" || dto.pbr.jsonObject.ctfx ==0'>selected="selected"</s:if> >从左向右</option>
								<option value="1" <s:if test='dto.pbr.jsonObject.ctfx ==1'>selected="selected"</s:if>>从上到下</option>
								<option value="2" <s:if test='dto.pbr.jsonObject.ctfx ==2'>selected="selected"</s:if>>从右向左</option>
								<option value="3" <s:if test='dto.pbr.jsonObject.ctfx ==3'>selected="selected"</s:if>>从下到上</option>
							</select>
					</dd>
				</dl>
				<div style="float:left;width:400px;">
				<dl> 
					<dt>动画时间</dt>
					<dd>
						<input type="text" id="ct_duration" class="text-pop form-control" style="width:50px;float:left;" <s:if test='dto.pbr.jsonObject.ctduration==null'>value="3"</s:if><s:else>value="${dto.pbr.jsonObject.ctduration}"</s:else> name="ctduration"><p style="float:left;margin:3px 0 0 5px;">秒</p>
					</dd>
				</dl>
				<dl>
					<dt>延迟时间</dt>
					<dd>
						<input type="text" id="ct_delay" class="text-pop form-control" style="width:50px;float:left;" style <s:if test='dto.pbr.jsonObject.ctdelay==null'>value="0"</s:if><s:else>value="${dto.pbr.jsonObject.ctdelay}"</s:else>  name="ctdelay"><p style="float:left;margin:3px 0 0 5px;">秒</p>
					</dd>
				</dl>
				</div>
				<div  class="pet_preview" >
				<div style="animation-duration: 3s; animation-delay: 0s;" id="div_pet" class="" >
					<img style="width: 80px;" src="/images/tuboshu.jpg">
				</div>
				</div>
				<input id="ct_name" name="ctname" type="hidden" <s:if test='dto.pbr.jsonObject.ctname == null'>value=""</s:if><s:else>value="${dto.pbr.jsonObject.ctname}"</s:else> >
				<input type="hidden" id="cttype" name="cttype" value="${dto.pbr.jsonObject.cttype}">
				<input type="hidden" id="ctfx" name="ctfx" value="${dto.pbr.jsonObject.ctfx}"> 
			</s:if>
			</div>
		</s:iterator>
		<input type="hidden" id="ct_value" name="hyct">
	</form> 
		<div role="tabpanel" class="edit_redirect" style="display: none">
		<s:if test="dto.tb.json.contains('redirect')">
			<form id="re_form">
			<div class="forradio">
				<label>
					<input type="radio" name="redirect" value="N" <s:if test='dto.pbr.jsonObject.obj.redirect.type == "N" || redirect==null'> checked="checked" </s:if> onclick="$('#ruletype1').hide();$('#urlType1').hide();$('#ruletype2').hide();$('#urlType2').hide();$('#toPageid').hide()"/>默认跳转
				</label>
			</div>
	 		<div class="forradio">
	 			<label>
	 				<input type="radio" name="redirect" value="Z" <s:if test='dto.pbr.jsonObject.obj.redirect.type == "Z"'> checked="checked" </s:if> onclick="$('#ruletype1').show();$('#urlType1').show();$('#ruletype2').hide();$('#urlType2').hide();$('#toPageid').hide()"/>跳转到指定链接
	 			</label>
	 		</div>
			<select id="urlType1" name="urlPre" <s:if test='dto.pbr.jsonObject.obj.redirect.type != "Z"'> style="display: none"</s:if>>
				<option value ="http://" <s:if test='dto.pbr.jsonObject.obj.redirect.urlPre=="http://"'>selected="selected"</s:if>>http://</option>
				<option value ="https://" <s:if test='dto.pbr.jsonObject.obj.redirect.urlPre=="https://"'>selected="selected"</s:if>>https://</option>
			</select>
			<input <s:if test='dto.pbr.jsonObject.obj.redirect.type != "Z"'>style="display: none"</s:if> class="text-long" id="ruletype1" type="text" name="urlShow" <s:if test='dto.pbr.jsonObject.obj.redirect.type == "Z"'> value="${dto.pbr.jsonObject.obj.redirect.urlShow }" </s:if>>
			<div class="forradio">
				<label>
					<input type="radio" name="redirect" value="L" <s:if test='dto.pbr.jsonObject.obj.redirect.type == "L"'> checked="checked" </s:if> onclick="$('#ruletype1').hide();$('#urlType1').hide();$('#ruletype2').hide();$('#urlType2').hide();$('#toPageid').show()"/>链接到
				</label>
			</div>
			<select id="toPageid" name="toPageid" <s:if test='dto.pbr.jsonObject.obj.redirect.type != "L"'> style="display: none"</s:if>>
				<option value="0">请选择跳转页面</option>
				<s:iterator value="dto.pageList" var="p">
				  <option value="${p.id}"  <s:if test="#p.id == dto.toPageid ">selected="selected"</s:if> >${p.name}</option>
				</s:iterator>  
			</select>
			</form>
		</s:if>
	    </div>
    </div>
    <div class="popup-footer">
<input type="hidden" id="relationid" value="${relationid}">
  <button type="button" class="btn btn-default closePopup">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClik(${dto.pbr.pcid})">保存</button>
</div>

<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<script type="text/javascript">
$(document).ready(function() {
         sdata(sedit);//数据
	     showCT();//动画
	     $("#tab_content").click(function(){
	     	$(".edit_content").show();
	     	$(".edit_style").hide();
	     	$(".edit_donghua").hide();
	     	$(".edit_redirect").hide();
	     });
	     $("#tab_style").click(function(){
	    	$(".edit_content").hide();
	     	$(".edit_style").show();
	     	$(".edit_donghua").hide();
	     	$(".edit_redirect").hide();
	     });
	      $("#tab_donghua").click(function(){
	    	$(".edit_content").hide();
	     	$(".edit_style").hide();
	     	$(".edit_donghua").show();
	     	$(".edit_redirect").hide();
	     });
	      $("#tab_redirect").click(function(){
		    	$(".edit_content").hide();
		     	$(".edit_style").hide();
		     	$(".edit_donghua").hide();
		     	$(".edit_redirect").show();
		     });
	      
	     $(".closePopup").click(function(){
			$("#rightPopup").animate({width:'hide'});
		});
		
		//处理样式按钮
		var hyct = '<s:property value="dto.tb.json"/>';
		if(hyct.indexOf("hyct") == -1){
			$("#tab_donghua").hide();
		}
		if(hyct.indexOf("redirect") == -1){
			$("#tab_redirect").hide();
		}
		
});
        
function sdata(callback) {    
	<s:iterator value="dto.pbr.jsonObject" var="j">
   		var id = '${j.key}';
   		var v = '${j.value}';
   		v = v.replace(/&apos;/g,"'");
   		$("#var_"+id).val(v).text(v).attr("hydata",v);
   		$("#var_se"+id).val(v);
	   	if($("#var_font_fm_style").length>0){
	   		var str = v.indexOf("f");
			if(str == 0){
		   		$("."+v).addClass("fontClassborder fontClassborder2");
			}
   		}
   		if($("#var_bc"+id).length>0){
	   		var bgcol="RGB("+v+")";
	   		$("#var_bc"+id).val(v).text(v).attr("style","margin-top:5px;width:80px;border:1px solid "+bgcol.colorHex()+";border-right:20px solid "+bgcol.colorHex()+";");
   		}
   		if($("#var_tb"+id).length>0){
   			if(v == "" || v == "nullclass"){
   				$(".nullclass").addClass("showcolor");
   			}else{
	   			var str ="."+v.substring(10,v.length);
	   			$(".listyle "+str).addClass("showcolor");
   			}
   		}
   		if($("#var_b1").length>0){
	   		$("#var_b1").attr("style","margin-top:5px;width:80px;border:1px solid "+$("#var_b1").val()+";border-right:20px solid "+$("#var_b1").val()+";");
   		}
   		if($("#var_bds"+id).length>0){
   			$("#bds"+id).attr("style","width:"+v*7+"px;");
   			$("#btn0"+id).attr("style","left:"+v*7+"px;");
   			$("#var_bds"+id).val(v).text(v);
   		}
   		if($("#var_bdc"+id).length>0){
   			$("#bdc"+id).attr("style","width:"+v*140+"px;");
   			$("#btn0"+id).attr("style","left:"+v*140+"px;");
   			$("#var_bdc"+id).val(v).text(v);
   		}
   		if($("#var_c"+id).length>0){
   			//字体
   			$("#var_c"+id).val(v).text(v).attr("style","margin-top:5px;width:80px;border:1px solid "+v+";border-right:20px solid "+v+";");
   		}
   		if($("#img_"+id).length>0){
   			//图片
	   		$("#img_"+id).attr("src",v);
   		}
   		if(v.indexOf("/user/show/")>0){
   			//关联到页面
   			var reg = /\/show\/[1-9][0-9]*/g;  
       		var numList = v.match(reg);
       		var pageid = numList[0].replace("/show/","");
			var s = document.getElementById("sel_"+id);
			if(s){
				var ops = s.options;
				for(var i=0;i<ops.length; i++){
					var tempValue = ops[i].value;
					if(tempValue == pageid) {
						ops[i].selected = true;
						$("#gl_"+id).attr("checked","checked");
			   			$("#sel_"+id).attr("disabled",false);
						$("#var_"+id).attr("disabled",true);
						break;
					}
				}
			}
   		}else {
   			$("#gl_"+id).attr("checked",false);
   			$("#sel_"+id).attr("disabled",true);
   		}
   	</s:iterator>
    callback(); 
} 
function sedit(){
	var s = $(".kindeditor");
   	for(var i=0;i<s.length;i++){
   		edit($(s[i]).attr("id"));
   	};
   	if($("#searchResultPanel").length>0){
	   	bdrun();//百度地图
   	}
} 

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
	
function updatevalue(arg){
	var value = arg.value;
	var width = $(value).attr("width");
	var height = $(value).attr("height");
	var nvalue = value.replace(width,"100%").replace(height,"100%");
	arg.value=nvalue;
} 

function tbonclick(key,arg){
	var class1 = $(arg).find("span").attr("class");
	$("#var_"+key).val(class1);
} 

function showtb(){
	if($('#showtb').is(':hidden')){
		$("#showtb").show();
	}else{
		$("#showtb").hide();
	}
} 
</script>