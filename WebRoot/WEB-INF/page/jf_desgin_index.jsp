<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".c_switch li a").click(function() {
			$(".c_switch li").removeClass("selected");
			$(this).parent().addClass("selected");
			var rel = $(this).attr("rel");
			console.log(rel);
			$(".tabpanel").hide();
			$("#" + rel).show();
		});
	});

	function zf() {
		$.ajax({
			url : "/${oname }/page/savezfjfDesign.action",
			type : "post",
			data : $("#zfform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}

	function qd() {
		$.ajax({
			url : "/${oname }/page/saveqdjfDesign.action",
			type : "post",
			data : $("#qdform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}

	function sq() {
		$.ajax({
			url : "/${oname }/page/savesqjfDesign.action",
			type : "post",
			data : $("#sqform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}

	function zs() {
		$.ajax({
			url : "/${oname }/page/savezsjfDesign.action",
			type : "post",
			data : $("#zsform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}

	function xq() {
		$.ajax({
			url : "/${oname }/page/savexqjfDesign.action",
			type : "post",
			data : $("#xqform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}

	function yy() {
		$.ajax({
			url : "/${oname }/page/saveyyjfDesign.action",
			type : "post",
			data : $("#yyform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}
	
	function pj() {
		$.ajax({
			url : "/${oname }/page/savepjjfDesign.action",
			type : "post",
			data : $("#pjform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}

	function cz(){
		$.ajax({
			url : "/${oname }/page/saveczDesign.action",
			type : "post",
			data : $("#czform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
				} else {
					alertMsg("设置失败");
				}
			}
		});
	}
	
	function alertMsg(msg) {
		easyDialog.open({
			container : {
				content : msg
			},
			autoClose : 1000
		});
	}
</script>
<div class="wrap_content">
  <div class="toolbar pb10">
    <ul class="c_switch">
      <li class="selected">
        <a href="javascript:void(0);" rel="zf">转发</a>
      </li>
      <li>
        <a href="javascript:void(0);" rel="qd">签到</a>
      </li>
      <li>
        <a href="javascript:void(0);" rel="sq">社区</a>
      </li>
      <li>
        <a href="javascript:void(0);" rel="zs">赠送</a>
      </li>
      <li>
        <a href="javascript:void(0);" rel="xq">线下签到</a>
      </li>
      <li>
        <a href="javascript:void(0);" rel="yy">预约</a>
      </li>
      <li>
        <a href="javascript:void(0);" rel="pj">服务评价</a>
      </li>
       <li>
        <a href="javascript:void(0);" rel="cz">充值</a>
      </li>
    </ul>
  </div>

  <!-- 转发 -->
  <div class="tabpanel" id="zf">
    <form class="formview formview2 jNice" method="post" id="zfform">
      <dl>
        <dt>分享可获得积分</dt>
        <dd>
          <input type="text" class="text-medium" name="balanceSet.sharenum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.sharenum }">
          <span>分/人（每个快照， 24小时内）</span>
        </dd>
      </dl>
      <dl>
        <dt>分享带来点击可获得积分</dt>
        <dd>
          <input type="text" class="text-medium" name="balanceSet.sclicknum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.sclicknum }">
          <span>分/人（24小时内，每个打开人）</span>
        </dd>
      </dl>
      <dl>
        <dt>分享带来分享可获得积分</dt>
        <dd>
          <input type="text" class="text-medium" name="balanceSet.ssharenum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.ssharenum }">
          <span>分/人（24小时内， 每个转发人）</span>
        </dd>
      </dl>
      <dl>
        <dt>分享带来关注可获得积分</dt>
        <dd>
          <input type="text" class="text-medium" name="balanceSet.sgznum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.sgznum }">
          <span>分/人（24小时内有效）</span>
        </dd>
      </dl>
      <dl>
        <dt></dt>
        <dd>
          <input type="button" class="btn btn-primary" onclick="zf()" value="保存" class="btn btn-default">
        </dd>
      </dl>
    </form>
  </div>
  <!-- 签到 -->
  <div class="tabpanel" id="qd" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="qdform">
        <dl>
          <dt>签到获得积分</dt>
          <dd>
            <input type="text" class="text-medium" name="checkin.addnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${checkin.addnum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt>连续签到额外积分</dt>
          <dd>
            <input type="text" class="text-medium" name="checkin.addaddnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${checkin.addaddnum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt>最大连续签到天数</dt>
          <dd>
            <input type="text" class="text-medium" name="checkin.maxday" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${checkin.maxday}">
            <span>天</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <span>
              注 每次签到，会在前一天的签到积分上，增加额外获得的积分。
              <br />
              当连续签到天数达到上限，不再增加额外获得的积分。
              <br />
              当没有连续签到， 系统将从头计算签到积分及连续天数。
            </span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" class="btn btn-primary" onclick="qd()" value="保存" class="btn btn-default">
          </dd>
        </dl>
      </form>

    </div>
  </div>
  <!-- 社区 -->
  <div class="tabpanel" id="sq" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="sqform">
        <dl>
          <dt>发表话题</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.topicnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.topicnum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt>发表评论</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.comnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.comnum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt>话题置顶</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.topnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.topnum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt>顶</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.upnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.upnum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt>踩</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.downum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.downum }">
            <span>分/人</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" class="btn btn-primary" onclick="sq()" value="保存" class="btn btn-default">
          </dd>
        </dl>
      </form>
    </div>
  </div>
  <div class="tabpanel" id="zs" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="zsform">
        <dl>
          <dt>新关注粉丝将赠与</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.newnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.newnum }">
            <span>积分/人</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" class="btn btn-primary" onclick="zs()" value="保存" class="btn btn-default">
          </dd>
        </dl>
      </form>
    </div>
  </div>
  <div class="tabpanel" id="xq" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="xqform">
        <dl>
          <dt>线下签到将赠与</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.ocnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.ocnum }">
            <span>积分/人</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" class="btn btn-primary" onclick="xq()" value="保存" class="btn btn-default">
          </dd>
        </dl>
      </form>
    </div>
  </div>
  <div class="tabpanel" id="yy" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="yyform">
        <dl>
          <dt>预约将赠与</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.yynum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.yynum }">
            <span>积分/人</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" onclick="yy()" value="保存" class="btn btn-primary">
          </dd>
        </dl>
      </form>
    </div>
  </div>
  <div class="tabpanel" id="pj" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="pjform">
        <dl>
          <dt>成功评价后将赠与</dt>
          <dd>
            <input type="text" class="text-medium" name="balanceSet.yypjnum" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${balanceSet.yypjnum }">
            <span>积分/人</span>
          </dd>
        </dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" onclick="pj()" value="保存" class="btn btn-primary">
          </dd>
        </dl>
      </form>
    </div>
  </div>
  <div class="tabpanel" id="cz" style="display: none;">
    <div class="input-group input-group-sm">
      <form class="formview formview2 jNice" method="post" id="czform">
        <dl class="clearfix">
			<input type="hidden" name="balanceSet.hykimg" id="img" value="${balanceSet.hykimg }">
			
			
			<dt>图片</dt>
			<dd>
				<p <s:if test="balanceSet.hykimg == null || balanceSet.hykimg == ''">style="display: none" </s:if>>
					<img src="${balanceSet.hykimg}" id="img1" height="67" width="67"/>
				</p>
				<div id="demo">
					<div id="as" ></div>
					<div id="picker">选择图片</div>
				</div>
			</dd>
		</dl>
        <dl>
		 	<dt>充值描述</dt>
			<dd>
				<textarea  maxlength="200" name="balanceSet.rmbrule">${balanceSet.rmbrule }</textarea>
			</dd>
		</dl>
        <dl>
          <dt></dt>
          <dd>
            <input type="button" onclick="cz()" value="保存" class="btn btn-primary">
          </dd>
        </dl>
      </form>
    </div>
  </div>
  
</div>
<script type="text/javascript">
	//实例化编辑器
	$('#as').diyUpload({
		url:'/${oname}/user/h5UploadPic.action',
		success:function( data ) {
			console.info( data );
			var url = '${imgDomain}' + data.picUrl;
			$('#img').val(url);$('#img1').hide();
			$('#picker').hide();
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			$('#img').val("");
			$('#picker').show();
		},
		auto: true,
		pick: '#picker',
		fileNumLimit:1,
		fileSizeLimit:500000 * 1024,
		fileSingleSizeLimit:50000 * 1024,
		accept:{
					title:"Images",
					extensions:"gif,jpg,jpeg,bmp,png",
					mimeTypes:"image/*"
				}
	});
</script>