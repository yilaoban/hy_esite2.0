<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
	function cz() {
		$.ajax({
			url : "/${oname }/setting/saveczDesign.action",
			type : "post",
			data : $("#czform").serialize(),
			cache : false,
			success : function(data) {
				if (data > 0) {
					alertMsg("设置成功");
					setTimeout(function() {
						window.location.href = "/${oname}/setting/userLevel.action";
					}, 2000);
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
  <ul class="c_switch">
    <li>
      <a href="/${oname}/setting/rmbRule.action">充值规则</a>
    </li>
    <li class="selected">
      <a href="/${oname}/setting/userLevel.action">会员等级</a>
    </li>
    <li>
      <a href="/${oname}/setting/userRmb.action">用户</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_print.action">打印模板</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_xf_desc.action">收银系统</a>
    </li>
  </ul>
  <p class="clearfix"></p>
  <div class="toolbar pb10">
    <a href="/${oname}/setting/userLevel.action" class="return" title="返回"></a>
  </div>
  <form class="formview formview2 jNice" method="post" id="czform">
    <dl>
      <dt>会员类型</dt>
      <dd>
        <input type="radio" name="balanceSet.hylevel" value="1" id="t1" <s:if test='balanceSet.hylevel == 1'>checked="checked" </s:if>>
        <label for="t1">单次充值会员</label>
        <input type="radio" name="balanceSet.hylevel" value="2" id="t2" <s:if test='balanceSet.hylevel == 2'>checked="checked" </s:if>>
        <label for="t2">累计充值会员</label>
        <input type="radio" name="balanceSet.hylevel" value="3" id="t3" <s:if test='balanceSet.hylevel == 3'>checked="checked" </s:if>>
        <label for="t3">累计消费会员</label>
        <input type="radio" name="balanceSet.hylevel" value="4" id="t4" <s:if test='balanceSet.hylevel == 4'>checked="checked" </s:if>>
        <label for="t4">验证码会员</label>
      </dd>
    </dl>
    <dl>
      <dt>初始会员等级</dt>
      <dd>
        <input type="text" name="balanceSet.hyname" value="${balanceSet.hyname }">
      </dd>
    </dl>
    <dl class="clearfix">
      <input type="hidden" name="balanceSet.hykimg" id="img" value="${balanceSet.hykimg }">
      <dt>图片</dt>
      <dd>
        <s:if test="balanceSet.hykimg == null || balanceSet.hykimg == ''">
          <p>请上传页面初始化的会员卡图片</p>
        </s:if>
        <s:else>
          <p>
            <img src="${balanceSet.hykimg}" id="img1" height="67" width="67" />
          </p>
        </s:else>
        <div id="demo">
          <div id="as"></div>
          <div id="picker">选择图片</div>
        </div>
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
<script type="text/javascript">
	//实例化编辑器
	$('#as').diyUpload({
		url : '/${oname}/user/h5UploadPic.action',
		success : function(data) {
			console.info(data);
			var url = '${imgDomain}' + data.picUrl;
			$('#img').val(url);
			$('#img1').hide();
			$('#picker').hide();
		},
		error : function(err) {
			console.info(err);
		},
		del : function(filename) {
			$('#img').val("");
			$('#picker').show();
		},
		auto : true,
		pick : '#picker',
		fileNumLimit : 1,
		fileSizeLimit : 500000 * 1024,
		fileSingleSizeLimit : 50000 * 1024,
		accept : {
			title : "Images",
			extensions : "gif,jpg,jpeg,bmp,png",
			mimeTypes : "image/*"
		}
	});

	
</script>