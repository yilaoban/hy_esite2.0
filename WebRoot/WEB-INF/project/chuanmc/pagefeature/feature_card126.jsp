<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<script type="text/javascript" src="/js/block.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">互动模块编辑-竞拍</h4>
  <!-- 
  <button type="button" class="btn btn-primary" onclick="newAuction()">新增</button>
   -->
</div>
<div class="popup-body">
	<form enctype="multipart/form-data" method="post">
	  请选择需要竞拍的选项：(请至微互动模块处添加竞拍)<br/>
	  <p id="label"></p>
       <s:iterator value="dto.auctionList" var="apt" status="st">
           <label class="forradio"><input type="radio" value="${apt.id}" name="dto.auctionid" <s:if test="dto.auctionid == #apt.id"> checked="checked" </s:if> /> ${apt.title}  </label><a href="/${oname }/interact/editAuction.action?auid=${apt.id }&mid=10007&omid=${apt.omid }">编辑</a>
     	   <s:if test="(#st.index+1)%3==0"><br /></s:if>
      </s:iterator>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikAuction(${fid},${featureid},${relationid},'${type }',${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
	if(${dto.auctionid} == 0){
		$('input:radio[name="dto.auctionid"]:first').attr('checked', 'checked');
	}
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>
