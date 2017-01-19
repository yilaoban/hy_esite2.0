<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
   $(document).ready(function(e) {
		$("#step2").hide();
		$("#step3").hide();
	});
	function showStep(step){
	    $(".step").hide();
	    $("#step"+step).show();
	}
	function publishSub(){
		var pagid = $("#pageid").val();
		var appid = $("#appID").val();
		var appSecret = $("#appSecret").val();
		if(appid ==""){
			parent.layer.alert("应用ID未填!");
			showStep(2);
			return;
		}
		if(appSecret ==""){
			parent.layer.alert("应用密钥未填!");
			showStep(2);
			return;
		}
	    $("#pbform").submit();
	}
	
	function ajaxUpload(imgDomain,id){
		var val = $("#upd_"+id).val();
		var idx = val.lastIndexOf(".");
		var result =val.substring(idx,val.length);
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG"){
			alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png格式）");
			return;
		}
		$.ajaxFileUpload({
				url:'/page/picUpload.action', 
				secureuri:false,
				fileElementId:"upd_"+id,
				dataType: 'json',
				success: function (data, status){
					if(status == "success"){
						$("#img_"+id).attr("src",imgDomain+data);
						$("#var_"+id).attr("value",imgDomain+data);
					}
				},
				error: function (data, status, e){
					bootbox.alert(e);
				}
			})
		return false;
	}
		</script>

<div>
  <form  action="/page/pub_qq.action" id="pbform" enctype="multipart/form-data" method="post" class="jNice">
    <div id="step1" class="step">
    <s:if test='pbdto.plist.size>0'>
      <dl class="bindview">
         <dt>选择页面</dt>
         <dd>
         <select name="pageid" id="pageid" >
            <s:iterator value="pbdto.plist" var="p">
               <option value="${p.id}" >${p.name}</option>
            </s:iterator>
         </select>
         </dd>
      </dl>
      <dl class="bindview"><dt><dt><dd><input type="button" value="下一步"  onclick="showStep(2)"></dd></dl>
      </s:if>
      <s:else>
      	<p>暂时没有能够绑定到微信的页面(上线之后才能绑定)</p>
      </s:else>
      <div class="instruction">
      	<h4>绑定到微信后如何在自定义菜单发布页面</h4><dl><dt>1、编辑自定义菜单</dt><dd>认证为服务号和订阅号的公众号才可以申请开通自定义菜单。有自定义菜单的公众号，点击菜单管理列的 添加一级菜单。也可在一级菜单下添加二级菜单。</dd><dd><img src="/images/sub_weixin_img1.png"></dd><dt>2、设置菜单动作</dt><dd>点击菜单名，在右侧设置动作处选择“跳转到网页”，将链接复制到文本框中，点击“保存”。</dd><dd><img src="/images/sub_weixin_img2.png"></dd><dt>3、菜单发布</dt><dd>点击自定义菜单下方的“发布”，菜单就发布到公众号上啦，快通知你的小伙伴们来参加活动吧！</dd></dl>
      </div>
    </div>
    <div id="step2" class="step">
      <dl class="bindview"><dt>公众号名称</dt><dd><input type="text" class="text-medium" id="name" name="name"><span class="must" id="title_">*</span></dd></dl>
      <dl class="bindview"><dt>AppID(应用ID)</dt><dd><input type="text" class="text-medium" id="appID" name="appID"><span class="must" id="title_">*</span></dd></dl>
      <dl class="bindview"><dt>AppSecret(应用密钥)</dt><dd><input type="text" class="text-medium" id="appSecret" name="appSecret"><span class="must" id="title_">*</span></dd></dl>
      <dl class="bindview"><dt>获取用户信息</dt><dd><input type="radio" name="infoed" value="Y">是 <input type="radio" name="infoed" checked="checked" value="N">否</dd></dl>
      <s:if test="dto.wxlist.size>0">
		已有官微：
		<s:iterator value="dto.wxlist" var="wx">
			<input type="radio" name="appid" value="${wx.id}">${wx.name}
		</s:iterator>      
      </s:if>
      <dl class="bindview">
      <dt></dt>
      <dd>
      <input type="button" value="上一步"  onclick="showStep(1)">
      <input type="button" value="下一步"  onclick="showStep(3)">
      </dd>
      </dl>
      <div class="instruction">
      <h4>AppID和AppSecret获取方法及其作用</h4><dl><dt>1、AppID和AppSecret获取方法</dt><dd>登录微信公众平台，点击“开发者中心”，配置项中开发者ID可获取AppID和AppSecret。</dd><dd>若您的公众号没有AppID和AppSecret，可使用我们会易科技公众号的AppID和AppSecret。</dd><dd>AppID：wxd141e40a0c911492</dd><dd>AppSecret：a91161eb8e2aa6f41ad3e1cf7b1e0d48</dd><dd><img src="/images/sub_weixin_img1.png"></dd><dt>2、是否获取用户信息</dt><dd>若选择是，在用户参加活动前必须授权，可获取用户昵称、性别、区域信息；</dd><dd>若选择否，用户无须授权，以上信息也无法获取。</dd><dd>若使用会易科技的AppID和AppSecret，并且获取用户信息，在授权的界面将显示该网页由会易科技开发。</dd><dd><img src="/images/public_weixin.png"></dd></dl>
      </div>
    </div>
    <div id="step3" class="step">
    	<dl class="bindview"><dt>微信分享快照</dt><dd><input type="file" name="img" id="upd_img" onchange="ajaxUpload('${imgDomain }','img')" >
    	<img id="img_img" height="100" src="/images/nopic.png"/>
    	<input name="pic" type="hidden" id="var_img">
    	</dd>
      </dl>
      <dl class="bindview">
      	<dt></dt>
      	<dd>
         <input type="button" value="上一步"  onclick="showStep(2)">
         <input type="button" value="确定"  onclick="publishSub()">
         </dd>
      </dl>
      <div class="instruction">
      	<h4>微信推送界面如何添加？</h4><dl><dt>1、什么是微信快照？</dt><dd><img src="/images/weixin_pic.png"></dd><dd>如上图所示，红框处即微信快照。上图中，“一句话的slogan，一部iPhone6的事”为标题；“来！开启你的创意模式…”为描述。</dd><dt>2、标题和描述如何添加？</dt><dd>添加标题和描述，在页面编辑处的页面标题中添加。</dd><dd><img src="/images/weixin_pic1.png"></dd><dd>点击“页面标题”，填写标题和描述，加上快照就构成了推送界面啦！</dd><dd><img src="/images/weixin_pic2.png"></dd></dl>
      </div>
     </div>
  </form>
</div>