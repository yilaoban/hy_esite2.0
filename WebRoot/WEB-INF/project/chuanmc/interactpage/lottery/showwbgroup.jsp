<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div id="content">
		<s:iterator value="sinaGroupList" var="u" status="st">
			<label><input type="radio" name="gid" value="${u.id }" onclick="selectWbGroup(${u.id},'${u.groupName }')"/>${u.groupName }</label> ${u.userNum }
			<s:if test="(#st.index+1)%2==0"><br/></s:if> 
		</s:iterator>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
</div>
<script type="text/javascript">
	function selectWbGroup(gid,groupName){
		$("#wbgroupid").val(gid);
		$("#wbgroup").val(groupName);
	}
</script>
