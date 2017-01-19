<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function saveWxGroupJob(){
	var type = $('#type').val();
	if(type == 1){
		var asigngid = $('#asigngid').val();
		var name = $('#'+asigngid).html();
		$("#name").val(name);
	}else if(type == 2){
		var groupname = $('#groupname').val();
		if(groupname == ""){
			alert("请填写组名");
			return;
		}
		$("#name").val(groupname);
	}
	$('#myform').submit();
	
}
</script>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			//parent.layer.close(i); 
			setTimeout("window.parent.location.reload()",3000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
<div>
<form action="/${oname}/interact/saveWxGroupJob.action" enctype="multipart/form-data" method="post" class="formview jNice" id="myform">
	<input type="hidden" name="dto.id" value="${dto.id}"> 
	<input type="hidden" name="dto.type" value="${dto.type}"> 
	<input type="hidden" name="dto.fensi" value="${dto.fensi}"> 
	<input type="hidden" name="dto.area" value="${dto.area}"> 
	<input type="hidden" name="mpid" value="${mpid}"> 
	<input type="hidden" name="name" value="" id="name">
	<input type="hidden" name="type" value="1" id="type"/>
	<input type="hidden" name="dto.xcid" value="${xcid }" id="xcid"/>
	
	<div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
    <ul id="myTabs" class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active">
      	<a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true" onclick="$('#type').val(1)">移至已存在组</a>
      </li>
      <li role="presentation" class="">
      	<a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false" onclick="$('#type').val(2)">移至新建组</a>
      </li>
      <li role="presentation" class="">
      	<a href="#leader" role="tab" id="leader-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false" onclick="$('#type').val(3)">移至潜客</a>
      </li>
    </ul>
    <div id="myTabContent" class="tab-content">
      <div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
	       	<div class="form-group form-inline">
	        	<select name="dto.asigngid" id="asigngid">
					<s:iterator value="wxGroupList" var="g">
					  <option value="${g.id}" id="${g.id}">${g.name }</option>
					</s:iterator>  
				</select>
			</div>
      </div>
      <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
        <div class="input-group input-group-sm">
        	<input type="text" placeholder="新建组名" id="groupname" class="form-control">
        </div>
      </div>
       <div role="tabpanel" class="tab-pane fade" id="leader" aria-labelledby="leader-tab">
	       	<div class="form-group form-inline">
	       		<br/>
	       		至用户洞察可进行潜客管理
			</div>
      </div>
      <input type="button" value="确定" onclick="saveWxGroupJob()" class="btn btn-default">
    </div>
  </div>
</form>
</div>
