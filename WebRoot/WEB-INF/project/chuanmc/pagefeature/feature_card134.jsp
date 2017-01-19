<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<div class="popup-header">
  <button type="button" class="close closePopup"><span>&times;</span></button>
  <h4 class="modal-title" id="myModalLabel">互动模块编辑-口碑</h4>
  <!-- 
  <button type="button" class="btn btn-primary" onclick="newSpread()">新增</button>
   -->
</div>
<div class="popup-body">
<form action="/page/config_sub.action?featureid=${featureid}&dto.fid=${fid}" method="post" id="form1">
<div class="frame_content">
	 			<p>选择口碑:(请至微互动模块处添加口碑)
	 				<table width="80%">
	 				<p id="label"></p>
		 				<s:iterator value="dto.list" var="f" status="st">
		 					<s:if test="#st.count==1">
		 						<tr>
		 					</s:if>
								<td><label class="forradio"><input type="radio" name="dto.spreadid" value="${f.id }" <s:if test='dto.spreadid==id'>checked="checked"</s:if> />${f.title }</label>
									<span class="notice"><s:if test='#f.type == "SRE"'>内容随机直发</s:if><s:elseif test='#f.type=="FWD"'>选择内容直发</s:elseif><s:elseif test='#f.type=="FAC"'>转发并评论</s:elseif></span><a href="/${oname }/interact/to_update_spread_design.action?spreadid=${f.id }&omid=${f.omid }" >编辑</a>
								</td>
							<s:if test="((#st.count+1)%2==1)||#st.last">
								</tr>
							</s:if>
		 				</s:iterator>
	 				</table>
	 			</p>
</div>
</form>
</div>
<div class="popup-footer">
  <button type="button" class="btn btn-default closePopup" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClikSpread(${fid},${featureid},${relationid},'${type }',${pageid })" data-dismiss="modal">保存</button>
</div>
<script type="text/javascript">
$(document).ready(function() {
    $(".closePopup").click(function(){
		$("#rightPopup").animate({width:'hide'});
	});
});
</script>