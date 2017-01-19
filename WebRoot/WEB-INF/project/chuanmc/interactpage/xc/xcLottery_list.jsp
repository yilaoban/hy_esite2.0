<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function changexcName(id,oldname){
	var str="<p>名称：<input id='name' value='"+oldname+"'></p>";
	$.layer({
	    shade : [0],
	    area : ['auto','auto'],
		title : '修改名称',
	    dialog : {
	        msg: str,
	        btns : 2, 
	        type : -1,
	        yes : function(){
				var name=$("#name").val();
				if(name==null||name==''){
				   layer.alert("请填写名称");
				   return false;
				}
				$.post("/interact/updatexcName.action",{"xc.title":name,"xc.id":id},function(data){
					if(data == "Y"){
						layer.msg('修改成功!',1,1);
						window.parent.location.reload();
					}else{
						layer.msg('修改失败!',1,3);
					}
				});	             
	        },
	    }
	});
}

 function add_xcLottery1(backdrop){
 	$('#myModal1').modal({
		backdrop:backdrop,
		remote:"/interact/add_xcLottery.action"
	});
 }

function updateBigScreenSite(xcSiteid,siteid,backdrop){
	$('#myModal2').modal({
		backdrop:backdrop,
		remote:"/interact/editBigScreenSite.action?xcSiteid="+xcSiteid+"&siteid=" + siteid
	});
}

function updateXcSite(xcSiteid,siteid,backdrop){
	$('#myModal3').modal({
			backdrop:backdrop,
			remote:"/interact/editXcSite.action?xcSiteid="+xcSiteid+"&siteid=" + siteid
		});
}

function del_xc(id){
	var layerid=layer.confirm('确定删除吗?',function(){
		$.post("/interact/del_xc.action",{"xcid":id},function(data){
			if(data == "N"){
				layer.alert("删除失败,请重试!",0);
			}else{
				layer.alert("删除成功",1);
				window.parent.location.reload();
			}
		});
	},"确认");
}

</script>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="/${oname }/interact/index.action?mid=10014">微现场</a></li>
	  <!-- 
	  <li ><a href="/${oname }/interact/commentAction.action?mid=10014">微上墙</a></li>
	   -->
	  </ul>
	   <a href="javascript:void(0);" onclick="add_xcLottery1(true)" class="button">新增微现场</a>
	</div>
  	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>名称</th>
			<th>创建日期</th>
			<th>绑定大屏幕站点</th>
			<th>绑定微现场站点</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.xcList" var="s">
			<tr align="center" >
				<td align="center">
				<a href="javascript:void(0)" onclick="changexcName(${s.id},'${s.title }')">${s.title}</a>
				</td>
				<td align="center"><s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
				<td align="center">
					<s:iterator value="#s.xcSiteList" var="xcsite">
						<s:if test='#xcsite.type == "D"'>
								<a href="javascript:void(0)" onclick="updateBigScreenSite(${xcsite.id},${xcsite.siteid },true)">${xcsite.name }</a>
					 	</s:if>
					</s:iterator>
				</td>
				<td align="center">
					<s:iterator value="#s.xcSiteList" var="xcsite">
						<s:if test='#xcsite.type == "W"'>
							<a href="javascript:void(0)" onclick="updateXcSite(${xcsite.id},${xcsite.siteid },true)">${xcsite.name }</a>
						</s:if>
					</s:iterator>
				</td>
			    <td align="center"><a href="/${oname }/interact/edit_xcLottery.action?xcid=${s.id }">基础设置</a>
			    <i class="split">|</i><a href="/${oname }/interact/edit_xcBigScreen.action?xcid=${s.id }">大屏幕切换</a>
			    <i class="split">|</i><a href="javascript:void(0);" onclick="del_xc(${s.id })">删除</a></td>
			</tr>
		</s:iterator>
	</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>
<div id="myModal1" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>
<div id="myModal2" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>
<div id="myModal3" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>
