<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function ajaxUpload(){
		var val = $("#upd_img").val();
		var idx = val.lastIndexOf(".");
		var result =val.substring(idx,val.length);
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG"){
			alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png格式）");
			return;
		}
		$.ajaxFileUpload({
				url:'/${oname}/weixin/pic_upload.action', 
				secureuri:false,
				fileElementId:"upd_img",
				dataType: 'json',
				success: function (data, status){
					if(status == "success"){
						$("#img_img").attr("src", data);
						$("#var_img").attr("value", data);
					}
				},
				error: function (data, status, e){
					bootbox.alert(e);
				}
			})
		return false;
	}
	
	function publish(){
		var pageid = $("#pageid").val();
		if(pageid == 0){
			parent.layer.alert("请选择起始页面");
			return;
		}
		var var_img = $("#var_img").val();
		if(var_img == ""){
			parent.layer.alert("分享快照不能为空");
			return;
		}
		var title = $("#title").val();
		if(title == ""){
			parent.layer.alert("分享标题不能为空");
			return;
		}
		var desc = $("#desc").val();
		if(desc == ""){
			parent.layer.alert("分享描述不能为空");
			return;
		}
	    $.ajax({
	    	url:"/${oname}/weixin/updateWxPageShow.action",
			type:"post",
			data: $("#pbform").serialize(),
			dataType:"json",
			success:function(data){
				layer.msg("设置成功！",{icon: 6, time: 1500},function(){
					parent.window.location.reload();
				});
			},
			error:function(){
				parent.layer.alert("网络异常，请稍后再试");
			}
		});
	}
</script>
<div class="frame_content">
	<form id="pbform">
		<input type="hidden" name="siteid" value="${siteid }">
		<input type="hidden" name="shareid" value="${shareid }">
			<dl class="bindview">
				<dt>选择起始页面</dt>
				<dd>
					<select name="pageid" id="pageid" class="form-control" style="width:300px">
						<option value="0" >选择一项</option>
						<s:iterator value="pdto.pageList" var="p">
						<option value="${p.id}" <s:if test="#p.id == pdto.wxPageShow.pageid">selected="selected"</s:if>>${p.name}</option>
						</s:iterator>
					</select>
					<s:if test='pdto.pageList.size==0'>
						<span class="must">目前没有能使用的页面(已上线页面才能使用)</span>
					</s:if>
					<s:else>
						<span class="must">此页面为点击分享快照进入的第一个页面</span>
					</s:else>
				</dd>
			</dl>
			<dl class="bindview">
				<dt>微信分享图片</dt>
				<dd>
					<input type="file" name="img" id="upd_img" onchange="ajaxUpload()">
					<img id="img_img" height="100" <s:if test='pdto.wxPageShow.pic != ""'>src="${pdto.wxPageShow.pic }"</s:if><s:else>src="/images/error.gif"</s:else>>
					<input name="pic" type="hidden" id="var_img" value="${pdto.wxPageShow.pic }">
				</dd>
			</dl>
			<dl class="bindview">
				<dt>
					微信分享标题
				</dt>
				<dd>
					<input id="title" name="title" type="text" class="text-long" maxlength="32" placeholder="15字以内" value="${pdto.wxPageShow.title }">
				</dd>
			</dl>
			<dl class="bindview">
				<dt>
					微信分享描述
				</dt>
				<dd>
					<textarea id="desc" name="desc" maxlength="64" placeholder="30字以内">${pdto.wxPageShow.description }</textarea>
				</dd>
			</dl>
			<dl class="bindview">
				<dt>获取用户信息</dt>
				<dd>
					<input type="radio" <s:if test='shareid == 0 || pdto.wxPageShow.infoed == "N"'>checked="checked"</s:if>  name="infoed" value="N" />否
					<input type="radio" <s:if test='pdto.wxPageShow.infoed == "Y"'>checked="checked"</s:if> name="infoed" value="Y" />是
					<input type="hidden" name="updateseconds" value="7">
					<br/>
					<p class="must">*若选择是，点击分享快照将弹出用户授权界面，可以获取用户微信昵称等信息</p>
				</dd>
			</dl>
			<dl class="bindview">
				<dt>分享成功跳转页面</dt>
				<dd>
					<select name="spageid" id="spageid" class="form-control" style="width:300px">
						<option value="0" >不需跳转</option>
						<s:iterator value="pdto.pageList" var="p">
						<option value="${p.id}" <s:if test="#p.id == pdto.wxPageShow.spageid">selected="selected"</s:if>>${p.name}</option>
						</s:iterator>
					</select>
					<br/>
					<a type="button" class="btn btn-primary" onclick="publish()">保存</a>
				</dd>
			</dl>
	</form>
</div>