<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link href="/css/hudong/lattice/sfindex.css" rel="stylesheet" type="text/css" />
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">奖品设置</h4>
</div>
<div class="modal-body">
	<div style="width: 290px;float:left;">
		<div id="lottery">
	          <table border="0" cellpadding="0" cellspacing="0">
	              <tr class="lottery-group">
	                  <td class="lottery-unit td_1 active" ><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	                  <td class="lottery-unit td_2"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	                  <td class="lottery-unit td_3"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	              </tr>
	              <tr class="lottery-group">
	                  <td class="lottery-unit td_4"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	                  <td class="td_5"><a href="#"></a></td>
	                  <td class="lottery-unit td_6"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	              </tr>
	              <tr class="lottery-group">
	                  <td class="lottery-unit td_7"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	                  <td class="lottery-unit td_8"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	                  <td class="lottery-unit td_9"><img src="/images/hudong/lattice/weixiao.png" /><p>谢谢参与</p></td>
	              </tr>
	          </table>
	      </div>
	      <div>
		      <img alt="" src="/images/hudong/lattice/t_order_1.png">
	      </div>
	</div>
	<form id="prize_form">
	<div style="width: 480px;float: right">
		<table class="tb_normal" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td colspan="3"></td>
					<td>奖品名称</td>
					<td>是否中奖</td>
					<td>奖品数量</td>
					<td>奖品类型</td>
					<td>奖品价值</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="prizes" var="p" status="st">
					<tr>
						<td>${p.positionid + 1}</td>
						<td onclick="upd_${p.id}.click();">
							<s:if test='#p.img != null && #p.img != ""'>
								<img alt="" src="${imgDomain }${p.img}" width="30px" id="img${p.id}"/>
							</s:if>
							<s:else>
								<img alt="" src="/images/hudong/lattice/weixiao.png" width="30px" id="img${p.id}"/>
							</s:else>
							<input type="file" name="img" id="upd_${p.id }" onchange="upload('${p.id}')" style="display:none;" >
						</td>
						<td>
							<span id="color${p.id}" class="picker" data="${p.id}" style="${p.style }"><s:property value="#p.name.substring(0,1)" /> </span>
						</td>
						<td>
							<input name="prizes[${st.index}].name" data="${p.id}" type="text" class="pname" value="${p.name}" style="width:60px" placeholder="奖品名称">
						</td>
						<td>
							<select name="prizes[${st.index}].hydefault" class="mydefault" data="${p.id }">
								<option value="N" <s:if test='#p.hydefault=="N"'>selected="selected"</s:if>>是</option>
								<option value="Y" <s:if test='#p.hydefault=="Y"'>selected="selected"</s:if>>否</option>
							</select>
						</td>
						<td>
							<input id="mytotal${p.id }" <s:if test='#p.hydefault=="Y"'>disabled="disabled"</s:if> name="prizes[${st.index}].total" type="text" value="${p.total}" style="width:50px;" placeholder="奖品数量"></td>
						<td>
							<select name="prizes[${st.index}].type" class="mytype" data="${p.id }">
								<option value="J" <s:if test='#p.type=="J"'>selected="selected"</s:if>>积分</option>
								<option value="S" <s:if test='#p.type=="S"'>selected="selected"</s:if>>实物</option>
								<option value="M" <s:if test='#p.type=="M"'>selected="selected"</s:if>>微信红包</option>
							</select>
						</td>
						<td>
							<input id="myvalue${p.id }" <s:if test='#p.type=="S"'>disabled="disabled"</s:if> name="prizes[${st.index}].value" type="text" value="${p.value}" style="width:50px;">
						</td>
					</tr>
					<input type="hidden" name="prizes[${st.index}].id" value="${p.id}">
					<input type="hidden" name="prizes[${st.index}].img" id="myimg${p.id}" value="${p.img }">
					<input type="hidden" name="prizes[${st.index}].style" id="style${p.id}" value="${p.style}">
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div class="clearfix"></div>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default"  data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" id="saveprize161" data-dismiss="modal">保存</button>
</div>
<link rel="stylesheet" href="/js/colpick/colpick.css" type="text/css" />
<script type="text/javascript" src="/js/colpick/colpick.js"></script>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	$(function(){
		$('.picker').colpick({
				layout:'hex',
				submit:0,
				onChange:function(hsb,hex,rgb,el,bySetColor) {
					$(el).css('color','#'+hex);
					var pid = $(el).attr("data");
					$("#style"+pid).val("color:#"+hex);
					if(!bySetColor) $(el).val('#'+hex);
				}
			}).keyup(function(){
				$(this).colpickSetColor(this.value);
			});
		
		$(".pname").change(function(){
			var pid = $(this).attr("data");
			$("#color"+pid).text($(this).val().substring(0,1));
		})
		
		$("#saveprize161").click(function(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"/${oname}/interact/save_nine_prize.action",
		        data:$('#prize_form').serialize(),
		        async: false,
		        success: function(data) {
		        	if(data > 0){
		        	}else{
		        		bootbox.alert("保存失败！请重试…");
		        	}
		        }
		    });	
		})
		
		$(".mydefault").change(function(){
			var pid = $(this).attr("data");
			var value = $(this).val();
			if(value=='Y'){
				$("#mytotal"+pid).val(0).attr('disabled',true);
			}else{
				$("#mytotal"+pid).attr('disabled',false);
			}
		})
		
		$(".mytype").change(function(){
			var pid = $(this).attr("data");
			var value = $(this).val();
			if(value=='S'){
				$("#myvalue"+pid).val(0).attr('disabled',true);
			}else{
				$("#myvalue"+pid).attr('disabled',false);
			}
		})
		
	});
	
	function upload(id){
		var val = $("#upd_"+id).val();
		var idx = val.lastIndexOf(".");
		var result =val.substring(idx,val.length);
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG" && result != ".gif" && result != ".GIF"){
			bootbox.alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png等格式）");
			return;
		}
		$.ajaxFileUpload({
				url:'picUpload.action', 
				secureuri:false,
				fileElementId:"upd_"+id,
				dataType: 'json',
				success: function (data, status){
					if(status == "success"){
						$("#myimg"+id).val(data);
						$("#img"+id).attr("src",'${imgDomain}' + data);
					}
				}
			})
		return false;
	}
</script>