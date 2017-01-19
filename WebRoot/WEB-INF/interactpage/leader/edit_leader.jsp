<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function updateLeader(){
		$('#myfrom2').submit();
	}
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">编辑</h4>
</div>
<div class="modal-body">
	<form action="/${oname}/interact/updateLeader.action" method="post" id="myfrom2">
		<input type="hidden" name="dto.leader.id" value="${id }">
		<p>
			昵称：
		    <input type="text" name="dto.leader.nickname" id="nickname" value="${dto.leader.nickname }">
		</p>
		<p>
			用户名：
			<input type="text" name="dto.leader.username" id="username" value="${dto.leader.username }">
		</p>
		<p>
			性别：
			<input type="radio" name="dto.leader.gender" value="0" <s:if test='dto.leader.gender == 0'>checked="checked"</s:if>>未知 
			<input type="radio" name="dto.leader.gender" value="1" <s:if test='dto.leader.gender == 1'>checked="checked"</s:if>>男        
			<input type="radio" name="dto.leader.gender" value="2" <s:if test='dto.leader.gender == 2'>checked="checked"</s:if>>女   
		</p>
		<p>
			邮箱：
		    <input type="text" name="dto.leader.email" id="email" value="${dto.leader.email }">
		</p>
		<p>
			公司名：
			<input type="text" name="dto.leader.company" id="company" value="${dto.leader.company }">        
		</p>
		<p>
			手机号：
			<input type="text" name="dto.leader.telphone" id="telphone" value="${dto.leader.telphone }">        
		</p>
		<p>
			座机号：
			<input type="text" name="dto.leader.tel" id="tel" value="${dto.leader.tel }">        
		</p>
		<p>
			区域：
			<input type="text" name="dto.leader.area" id="area" value="${dto.leader.area }">
		</p>
		<p>
			潜客状态：
			<input type="radio" name="dto.leader.status" value="NOL" <s:if test='dto.leader.status == "NOL"'>checked="checked"</s:if>>有效潜客        
			<input type="radio" name="dto.leader.status" value="LED" <s:if test='dto.leader.status == "LED"'>checked="checked"</s:if>>潜客        
		</p>
		<p>
			是否客户：
			<input type="radio" name="dto.leader.kehued" value="Y" <s:if test='dto.leader.kehued == "Y"'>checked="checked"</s:if>>是        
			<input type="radio" name="dto.leader.kehued" value="N" <s:if test='dto.leader.kehued == "N"'>checked="checked"</s:if>>否       
		</p>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="updateLeader()">保存</button>
</div>
