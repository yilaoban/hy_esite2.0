<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="toolbar">
    <a href="javascript:window.history.back()" class="return" title="返回"></a>
  </div>
  <form id="myfrom1" class="formview" onsubmit="return false">
    <dl>
      <dt>标题</dt>
      <dd>
        <input type="text" class="text-medium" name="cbActivity.title" id="title" />
        <span id="title_" class="must">*</span>
      </dd>
    </dl>
    <dl>
      <dt>图标</dt>
      <dd>
        <img id="img_img" style="width: 262px; height: 85px;" />
        <input type="hidden" id="img_val" name="cbActivity.img" />
        <input type="file" id="upload" name="img" onchange="uploadImg()" />
        <span>（建议尺寸 262px * 85px）</span>
      </dd>
    </dl>
    <dl>
      <dt>简介</dt>
      <dd>
        <textarea name="cbActivity.content"></textarea>
      </dd>
    </dl>
    <dl>
      <dt>活动页面</dt>
      <dd>
        <input type="hidden" name="cbActivity.type" value="${type}" />
        <input type="hidden" id="enid" name="cbActivity.enid" />
        <span id="m_title"></span>
        <button class="btn btn-default" onclick="findMaterial('${type}')">选择</button>
      </dd>
    </dl>
    <dl>
      <dt>开始时间</dt>
      <dd>
        <input type="text" placeholder="请选择开始时间" d id="start" name="cbActivity.starttimeStr" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
        <span class="must">*</span>
        <span id="start_"></span>
      </dd>
    </dl>
    <dl>
      <dt>结束时间</dt>
      <dd>
        <input type="text" placeholder="请选择结束时间" id="end" name="cbActivity.endtimeStr" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
        <span class="must">*</span>
        <span id="end_"></span>
      </dd>
    </dl>
    <dl>
      <dt>带来的转发</dt>
      <dd>
        <input type="text" class="text-small" id="zhuanfa" onblur="baocun('zhuanfa','resentjf')"
          onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;" />
        元/人
        <input type="hidden" name="cbActivity.zhuanfa" id="zhuanfa_1">
        或
        <input type="text" class="text-small" name="cbActivity.zhuanfajf" onblur="baocun('resentjf','zhuanfa')" id="resentjf" onkeyup="this.value = this.value.replace(/\D/g,'');" />
        积分
        <span class="must">(注：精确到2位小数)</span>
      </dd>
    </dl>
    <dl>
      <dt>带来的点击</dt>
      <dd>
        <input type="text" class="text-small" id="click" onblur="baocun('click','clickjf')"
          onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;" />
        元/人
        <input type="hidden" name="cbActivity.click" id="click_1">
        或
        <input type="text" class="text-small" name="cbActivity.clickjf" id="clickjf" onblur="baocun('clickjf','click')" onkeyup="this.value = this.value.replace(/\D/g,'');" />
        积分
      </dd>
    </dl>
    <dl>
      <dt>关注</dt>
      <dd>
        <input type="text" class="text-small" onblur="baocun('guanzhu','guanzhujf')" id="guanzhu"
          onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;" />
        元/人
        <input type="hidden" name="cbActivity.guanzhu" id="guanzhu_1">
        或
        <input type="text" class="text-small" onblur="baocun('guanzhujf','guanzhu')" name="cbActivity.guanzhujf" id="guanzhujf" onkeyup="this.value = this.value.replace(/\D/g,'');" />
        积分 需关注时长
        <input type="text" class="text-small" name="cbActivity.guanzhudays" id="guanzhudays" onkeyup="this.value = this.value.replace(/\D/g,'');" />
        天
      </dd>
    </dl>
    <dl>
      <dt>互动</dt>
      <dd>
        <input type="text" class="text-small" onblur="baocun('hudong','hudongjf')" id="hudong"
          onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;" />
        元/人
        <input type="hidden" name="cbActivity.hudong" id="hudong_1">
        或
        <input type="text" class="text-small" onblur="baocun('hudongjf','hudong')" name="cbActivity.hudongjf" id="hudongjf" onkeyup="this.value = this.value.replace(/\D/g,'');" />
        积分
      </dd>
    </dl>
    <dl>
      <dt>外部</dt>
      <dd>
        <input type="text" class="text-small" onblur="baocun('waibu','waibujf')" id="waibu"
          onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;" />
        元/人
        <input type="hidden" name="cbActivity.waibu" id="waibu_1">
        或
        <input type="text" class="text-small" onblur="baocun('waibujf','waibu')" name="cbActivity.waibujf" id="waibujf" onkeyup="this.value = this.value.replace(/\D/g,'');" />
        积分
      </dd>
    </dl>
    <dl>
      <dt></dt>
      <dd>
        <button type="button" class="btn btn-primary" onclick="saveMarketActivity()">保存</button>
        <button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
      </dd>
    </dl>
  </form>
</div>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function uploadImg() {
		$.ajaxFileUpload({
			url : '/page/picUpload.action',
			secureuri : false,
			fileElementId : "upload",
			dataType : 'json',
			success : function(data, status) {
				if (status == "success") {
					$("#img_img").attr("src", "${imgDomain}" + data);
					$("#img_val").val(data);
				}
			}
		});
	}

	function findMaterial(type) {
		var url;
		if (type == 0) {
			url = "/${oname}/interact/findMaterial.action";
		} else if (type == 1) {
			url = "/${oname}/interact/findNewsMaterial.action";
		}
		layer.open({
			type : 2,
			area : [ '800px', '600px' ],
			title : "活动页面",
			content : url
		});
	}

	function saveMarketActivity() {
		var title = $('#title').val().trim();
		if (title == "") {
			$("#title_").html("请输入标题").css("color", "RED");
			return;
		} else if (document.getElementById("start").value == "") {
			$("#start_").html("请填写开始时间").css("color", "RED");
			return false;
		} else if (document.getElementById("end").value == "") {
			$("#end_").html("请填写结束时间").css("color", "RED");
			return false;
		}
		var zf = $('#zhuanfa').val();
		var cl = $('#click').val();
		var gz = $('#guanzhu').val();
		var hd = $('#hudong').val();
		var wb = $('#waibu').val();
		$('#zhuanfa_1').val(zf * 100);
		$('#click_1').val(cl * 100);
		$('#guanzhu_1').val(gz * 100);
		$('#hudong_1').val(hd * 100);
		$('#waibu_1').val(wb * 100);
		$.ajax({
			cache : true,
			type : "POST",
			url : '/${oname}/interact/saveMarketActivity.action',
			data : $('#myfrom1').serialize(),// 你的formid
			success : function(data) {
				if (data > 0) {
					layer.alert("保存成功", function() {
						window.location.href = "/${oname}/interact/marketing_activity.action";
					});
				} else {
					layer.alert("操作失败!", 0);
				}
			}
		});
	}

	function baocun(id, id2) {
		if ($('#' + id2).val() != 0) {
			$('#' + id).val(0);
		}
	}

	
</script>