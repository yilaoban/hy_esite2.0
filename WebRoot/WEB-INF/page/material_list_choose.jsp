<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <div class="toolbar">
	 <ul class="c_switch">
	   <s:if test="dto.scategoryList.size() > 0">
		<s:iterator value="dto.scategoryList" var="c" status="st">
		<li <s:if test='scategoryid == #c.id'> class="selected"</s:if> id="li_${c.id}"><a href="javascript:void(0)" onclick="changeSck(${fcategoryid },${c.id },1)">${c.name}</a></li>
	   </s:iterator>
	  </s:if>
	  </ul>
	</div>
</div>
<div class="modal-body">
  <div class="clearfix" style="margin-left:-20px;" id="sck_div">
   <s:iterator value="dto.materiallist" var="t">
    <div class="mtrl-item-c">
      <img src="${t.path}" <s:if test='fcategoryid == 1'>width="238"</s:if><s:if test='scategoryid == 11'>width="238"</s:if> onclick="checkPic(this)" />
     </div>
    </s:iterator>
   </div>
   <s:if test="dto.pager.totalPage > pageId"><a id="a_more" href="javascript:void(0)" onclick="showMoreSck(${fcategoryid },${scategoryid },${pageId+1})">查看更多</a></s:if>
</div>
<div class="modal-footer">
<input type="hidden" id="hy_key" value="${key}">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="selectSck()" data-dismiss="modal">选择</button>
</div>
<script type="text/javascript">
		$(document).ready(function() {  
	      	reSizeModal();
        });
</script>
