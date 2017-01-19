<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <form action="/${oname }/interact/editSourceSub.action" onsubmit="return check()" class="formview">
  	<div class="toolbar pb10">
	  	<ul class="c_switch">
			<li class="selected"><a href="#">编辑二维码</a></li>
		</ul>
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
  
    <dl>
      <dt>名称</dt>
      <dd>
        <input type="text" id="name" name="ofcSource.name" class="text-medium" value="${ofcSource.name}" />
        <span style="color: red;">*</span>
      </dd>
    </dl>
    
    <dl>
      <dt>关联消费积分</dt>
      <dd>
        <input type="radio" name="ofcSource.type" value="1" onclick="$('.xf').show();" <s:if test='ofcSource.type==1'>checked="checked"</s:if>/>
        <span>是</span>
        <input type="radio" name="ofcSource.type" value="0" onclick="$('.xf').hide();" <s:if test='ofcSource.type==0'>checked="checked"</s:if>/>
        <span>否</span>
      </dd>
    </dl>
    <dl class="xf" <s:if test='ofcSource.type==0'>style="display: none;"</s:if>>
      <dt>消费每满</dt>
      <dd>
        <input type="text" id="rmbjf" name="ofcSource.rmbjf" class="text-small" value="${ofcSource.rmbjf}"/>
        <span>元获得1积分</span>
        <span style="color: red;">*</span>
      </dd>
    </dl>

    <dl>
      <dt>仅粉丝可签到</dt>
      <dd>
        <input type="radio" name="ofcSource.fensi" value="Y" onclick="$('.gz').show();" <s:if test='ofcSource.fensi=="Y"'>checked="checked"</s:if> />
        <span>是</span>
        <input type="radio" name="ofcSource.fensi" value="N" onclick="$('.gz').hide();" <s:if test='ofcSource.fensi=="N"'>checked="checked"</s:if> />
        <span>否</span>
      </dd>
    </dl>
    <dl class="gz" <s:if test='ofcSource.fensi=="N"'>style="display: none;"</s:if>>
      <dt>关注引导页链接</dt>
      <dd>
        <input type="hidden" name="gzEvent.id" value="${gzEvent.id}" />
        <input type="text" id="link" name="gzEvent.link" class="text-long" value="${gzEvent.link}" />
        <span style="color: red;">*</span>
        <span class="notice">签到时，非粉丝看到的页面链接</span>
      </dd>
    </dl>
    <dl class="gz" <s:if test='ofcSource.fensi=="N"'>style="display: none;"</s:if>>
      <dt>关注触发图文</dt>
      <dd>
        <span>标题</span>
        <input type="hidden" name="gzEvent.msg.id" value="${gzEvent.msg.id}" />
        <input type="text" id="title" name="gzEvent.msg.title" class="text-medium" value="${gzEvent.msg.title}" />
        <span style="color: red;">*</span>
      </dd>
      <dd>
        <span>描述</span>
        <input id="description" name="gzEvent.msg.description" class="text-long" value="${gzEvent.msg.description}" />
        <span style="color: red;">*</span>
      </dd>
      <dd>
        <span>快照</span>
        <input type="hidden" id="pic" name="gzEvent.msg.pic_url" value="${gzEvent.msg.pic_url}" />
        <img id="img" src="${gzEvent.msg.pic_url}" style="width: 100px; height: 100px; vertical-align: text-top;" />
        <span style="color: red;">*</span>
        <input type="file" id="upload" name="img" onchange="uploadPic()" value="选择图片" style="padding-left: 32px;" />
      </dd>
      <dd>
        <input type="hidden" id="url" name="gzEvent.msg.url" value="${gzEvent.msg.url}" />
      </dd>
    </dl>
    
    <dl>
      <dt>选择应用站点</dt>
      <dd>
        <select id="site">
          <s:iterator value="list" var="sg">
            <s:iterator value="#sg.site" var="s">
              <s:if test="#s.getOffCheckPage()!=null">
                <option value="${s.getOffCheckPage()}">${sg.groupname}</option>
              </s:if>
            </s:iterator>
          </s:iterator>
        </select>
        <span style="color: red;">*</span>
        <input type="hidden" name="ofcSource.id" value="${ofcSource.id}" />
        <input type="hidden" id="fpageid" name="ofcSource.fpageid" />
        <input type="hidden" id="spageid" name="ofcSource.spageid" />
        <input type="hidden" id="dzpageid" name="ofcSource.dzpageid" />
        <input type="hidden" id="dtpageid" name="ofcSource.dtpageid" />
        <input type="hidden" id="jfpageid" name="ofcSource.jfpageid" />
      </dd>
    </dl>
    
    <dl>
      <dt></dt>
      <dd>
        <button class="btn btn-primary">保存</button>
      </dd>
    </dl>
  </form>
</div>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#site").val('${ofcSource.fpageid}-${ofcSource.spageid}-${ofcSource.dzpageid}-${ofcSource.dtpageid}-${ofcSource.jfpageid}');
		$("#site").change(function() {
			var value = $(this).val();
			if (value != null) {
				var f_s = value.split("-");
				$("#fpageid").val(f_s[0]);
				$("#spageid").val(f_s[1]);
				$("#dzpageid").val(f_s[2]);
				$("#dtpageid").val(f_s[3]);
				$("#jfpageid").val(f_s[4]);
				$("#url").val("${pageDomain}/${oname}/user/wxshow/" + f_s[0] + "/wxn/${ofcSource.id}.html");
			}
		}).change();
	});

	function check() {
		var name = $("#name").val().trim();
		if (name == "") {
			layer.msg('请填写名称!', {
				icon : 5,
				time : 2000
			});
			return false;
		}
		var type = $("input:radio[name='ofcSource.type']:checked").val();
		if (type == "1") {
			var rmbjf = $("#rmbjf").val().trim();
			if (rmbjf == "") {
				layer.msg('请填写消费积分比例!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
			if (isNaN(rmbjf)) {
				layer.msg('消费积分比例只能为整数!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
		}
		var fensi = $("input:radio[name='ofcSource.fensi']:checked").val();
		if (fensi == "Y") {
			var link = $("#link").val().trim();
			if (link == "") {
				layer.msg('请填写关注引导页链接!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
			var title = $("#title").val().trim();
			if (title == "") {
				layer.msg('请填写图文标题!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
			var description = $("#description").val().trim();
			if (description == "") {
				layer.msg('请填写图文描述!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
			var pic = $("#pic").val().trim();
			if (pic == "") {
				layer.msg('请上传图文快照!', {
					icon : 5,
					time : 2000
				});
				return false;
			}
		}
		var site = $("#site").val();
		if (site == null) {
			layer.msg('选择应用站点!', {
				icon : 5,
				time : 2000
			});
			return false;
		}
		return true;
	}

	function uploadPic() {
		$.ajaxFileUpload({
			url : '/page/picUpload.action',
			secureuri : false,
			fileElementId : "upload",
			dataType : 'json',
			success : function(data, status) {
				if (status == "success") {
					$("#img").attr("src", "${imgDomain}" + data);
					$("#pic").attr("value", "${imgDomain}" + data);
				}
			},
			error : function(data, status, e) {
				alert(e);
			}
		});
	}

	
</script>