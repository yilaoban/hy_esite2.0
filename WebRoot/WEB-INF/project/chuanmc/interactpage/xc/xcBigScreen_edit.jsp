<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function switchBigScreen(xcid){
		var pageid=$('input:radio[name="pageid"]:checked').val();
		if(pageid > 0){
			$.post("/${oname}/interact/switchBigScreen.action",{"xcid":xcid,"pageid":pageid},function(data){
				if(data == 1){
					layer.alert("切换成功!",1);
				}else{
					layer.alert("切换失败",0);
				}
			});
		}else{
			layer.alert("请选择一项",0);
		}
	}

	function checkdpmComment(xcid,utype,backdrop){
		$('#wideModal').modal({
			backdrop:backdrop,
			remote:"/${oname}/interact/checkdpmComment.action?xcid="+xcid+"&utype=" + utype
		});
	}
	
function checkdpmComment1(xcid,utype){
 	var srcString = "/${oname}/interact/checkdpmComment.action?xcid="+xcid+"&utype=" + utype;
	layer.open({
			type : 2,
			area : ['800px','500px'],
			title : '审核',
			content: srcString
		});
 }
	
	
	function del_xcBigScreen(id){
		var layerid=layer.confirm('确定删除吗?',function(){
		$.post("/${oname}/interact/del_xcBigScreen.action",{"dpmid":id},function(data){
			if(data == 1){
				layer.alert("删除成功",1);
				window.parent.location.reload();
			}else{
				layer.alert("删除失败,请重试!",0);
			}
		});
	},"确认");
	}
	
function startLottery(id,num){
	var html="<div>人数：<input type='text' id='num' value='"+num+"'/><input type='hidden' name='xcid' value='"+id+"' id='xcid'>  </div>";
	layer.open({
		title:'启动需取出的人数', 
		content : html,
		btn : ['确定','取消'],
		yes : function(){
			var xcid = $("#xcid").val();
			if($("#num").val()==''){
				alert("请输入启动需取出的人数!",0);
				return false;
			}
			$.post("/${oname}/interact/restartXcLottery.action",{"xcid":xcid,"num":$("#num").val()},function(data){
				if(data == "N"){
					layer.alert("操作失败,请重试!",0);
				}else{
					layer.alert("操作成功",1);
					window.parent.location.reload();
				}
			});
		}
	});
}
	
	function ajaxFileUpload(imgDomain,id,pageid){
		var val = $("#upd_"+id).val();
		var idx = val.lastIndexOf(".");
		var result =val.substring(idx,val.length);
		if(result != ".jpg" && result != ".JPG" && result != ".jpeg" && result != ".JPEG" && result != ".bmp" && result != ".BMP" && result != ".png" && result != ".PNG"){
			bootbox.alert("文件格式不支持！（仅支持jpg/jpeg/bmp/png等格式）");
			return;
		}
		/*
			prepareing ajax file upload
			url: the url of script file handling the uploaded files
                        fileElementId: the file type of input element id and it will be the index of  $_FILES Array()
			dataType: it support json, xml
			secureuri:use secure protocol
			success: call back function when the ajax complete
			error: callback function when the ajax failed
                */
		$.ajaxFileUpload({
				url:'/${oname}/page/picUpload.action', 
				secureuri:false,
				fileElementId:"upd_"+id,
				dataType: 'json',
				success: function (data, status){
					if(status == "success"){
						$("#img_"+id).attr("src",imgDomain+data);
						$.post("/${oname}/interact/ajaxUpLoad.action",{"imgurl":data,"pageid":pageid});
					}
				},
				error: function (data, status, e){
					bootbox.alert(e);
				}
			})
		return false;
	}
</script>
<div class="wrap_content left_module">
	<div class="switch_tab_div pb10">
	<span><a href="/${oname }/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i> <a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>大屏幕切换</span>
	<p class="clearfix"></p>
	</div>
  <div class="toolbar pb10">
	 	<a href="/${oname}/interact/edit_xcBigScreen.action?xcid=${xcid }" class="button">刷新</a>
	   <a href="/${oname }/page/xcSite.action?xcid=${xcid }" class="return" title="返回"></a>
	</div>
	<s:if test="dto.xcBigScreenList.size()==0">
		只有上线的页面才能在大屏幕上显示!<br><a href="/${oname }/page/pageconfig_new.action?siteid=${dto.siteid }&stype=C">去上线</a>
	</s:if>
	<s:else>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="0" cellpadding="0" >
		<s:iterator value="dto.xcBigScreenList" var="s" status="st">
			<tr align="center" >
				<td align="center">
					<input type="radio" name="pageid" <s:if test='#s.pageid == dto.pageid'> checked="checked" </s:if>  value="${s.pageid }" id="dpm_${st.count}"/>
				</td>
				<td align="center"><label for="dpm_${st.count}">${s.pageName }</label></td>
				<!-- 
				<td align="center"> <label for="dpm_${st.count}"><img id="img_${s.id }" src="${imgDomain }${s.imgurl }" height="100" width="100"></label></td>
				<td align="center">
					
					 <a onclick="del_xcBigScreen(${s.id })" href="javascript:void(0);">删除</a>
					<input type="file" name="img" id="upd_${s.id }" onchange="ajaxFileUpload('${imgDomain }',${s.id },${s.id })" />
				</td>
				 -->
				<s:if test="#st.first">
				  <td align="center" rowspan="${dto.size }">
				  	<a href="javascript:void(0);" onclick="switchBigScreen(${dto.xcid })">切换</a><br/>
				 	<a href="javascript:void(0);" onclick="checkdpmComment1(${dto.xcid },1)">审核</a><br/>
				  	<s:if test="dto.lconfig != null">
						<s:if test="dto.lconfig.started ==1">
							<a href="javascript:void(0);">正在活动中</a>
					    </s:if>
				      	<s:else>
				      		<a href="javascript:void(0);" onclick="startLottery(${dto.xcid },${dto.lconfig.num})">启动抽奖</a>
				      	</s:else>
				    </s:if>
				    <s:else>
				    	请完成微现场抽奖配置启动抽奖
				    </s:else>
				  </td>
				</s:if>
				
			</tr>
		</s:iterator>
	</table>
	</s:else>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="myModal1" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>