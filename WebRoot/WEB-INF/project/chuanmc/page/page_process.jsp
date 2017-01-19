<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">页面流程</h4>
</div>
<div class="modal-body">
	<div class="headline clearfix">
		<div class="tlz"></div>
		<div class="item-list tac">卡片顺序</div>
	</div>
	<div class="linediv clearfix">
	<div class="tlz">
		<p class="title">入口</p>
		<p>页面效果:</p>
		<label><input type="radio" name="cardMoveStyle" <s:if test='dto.page.jspstyle=="L"'>checked="checked"</s:if> onclick='cardMoveStyle("L",${pageid})'>连续式</label>
		<label><input type="radio" name="cardMoveStyle" <s:if test='dto.page.jspstyle=="Q"'>checked="checked"</s:if> onclick='cardMoveStyle("Q",${pageid})'>切换式</label>
		<label><input type="radio" name="cardMoveStyle" <s:if test='dto.page.jspstyle !="L" && dto.page.jspstyle !="Q"'>checked="checked"</s:if> onclick='cardMoveStyle("K",${pageid})'>卡片式</label>
	</div>
	<ul id="item-list0" class="item-list">
	<s:iterator value="dto.checkedpc" var="pc">
		<li>
			<div class="card" style="background-image:url(${pc.cardbimg });background-size:100%,100%;">
				<span id="${pc.id}" class="order" title="1">${pc.cardname }
				<s:iterator value="#pc.tb">-${relationid }</s:iterator>
				</span>
				<div class="oprt <s:if test='isshow=="Y"'>menued</s:if>" title="在菜单上显示"><img src="/images/menu-choose.png"/></div> <%-- onclick="isshowmenu(${pc.id});"--%>
				<input id="isshow_${pc.id}" name="${pc.id}" type="checkbox" <s:if test='isshow=="Y"'> checked="checked"</s:if> style="display:none"/>
			</div>
		</li>
	</s:iterator>  
	</ul>
	</div>
	<s:iterator value="dto.pages" var="p" status="st">
	<div class="linediv clearfix">
	<div class="tlz">
		<p class="title">${ p.name}-${p.relationid }</p>
		<p>页面效果:</p>
		<label><input type="radio" name="cardMoveStyle${p.id }" <s:if test='#p.jspstyle=="L"'>checked="checked"</s:if> onclick='cardMoveStyle("L",${p.id})'>连续式</label>
		<label><input type="radio" name="cardMoveStyle${p.id }" <s:if test='#p.jspstyle=="Q"'>checked="checked"</s:if> onclick='cardMoveStyle("Q",${p.id})'>切换式</label>
		<label><input type="radio" name="cardMoveStyle${p.id }" <s:if test='#p.jspstyle !="L" && #p.jspstyle !="Q"'>checked="checked"</s:if> onclick='cardMoveStyle("K",${p.id})'>卡片式</label>
	</div>
	<ul id="item-list${st.count }" class="item-list">
	<s:iterator value="#p.pageCards" var="pc">
		<li>
			<div class="card" style="background-image:url(${pc.cardbimg });background-size:100%,100%;">
				<span id="${pc.id}" class="order" title="1">${pc.cardname }
				<s:iterator value="#pc.tb">-${relationid }</s:iterator>
				</span>
				<div class="oprt <s:if test='isshow=="Y"'>menued</s:if>" title="在菜单上显示"><img src="/images/menu-choose.png"/></div> <%-- onclick="isshowmenu(${pc.id});"--%>
				<input id="isshow_${pc.id}" name="${pc.id}" type="checkbox" <s:if test='isshow=="Y"'> checked="checked"</s:if> style="display:none"/>
			</div>
		</li>
	</s:iterator>  
	</ul>
	</div>
	</s:iterator>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
<script type="text/javascript" src="/js/jquery.dragsort-0.5.2.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		$(".oprt").click(function(){
			var isshowpcname = $(this).next().attr("name");
			var ischecked = $("#isshow_"+isshowpcname).attr("checked");
	   		if(ischecked){  
			    $(this).removeClass("menued");
				$(this).next().removeAttr("checked");  
			    $.post("/page/updateisshowmenu.action",{"isshow":"N","pcid":isshowpcname});
	       }else{
	       		$(this).addClass("menued");
				$(this).next().attr("checked","checked");
	          $.post("/page/updateisshowmenu.action",{"isshow":"Y","pcid":isshowpcname});
	       }
		})
	});

	var size ='<s:property value="dto.pages.size"/>';
	for(var j = 0;j<size+1;j++ ){
	 	$('#item-list'+j).dragsort({ dragSelector: "div", dragBetween: true, dragEnd: function(){
	 		var order = $(this).parent().find(".order");
			var str = "";
			for(var i= 0;i<order.length;i++){
			str += order[i].id +"," +(i+1) + ";";
			}
			$.post("/page/moveCard.action",{"cardMoveStr":str});
	 	}, placeHolderTemplate: "<li class='placeHolder'><div></div></li>"});
	}
	
		
		function cardMoveStyle(jspstyle,pageid){
			$.post("/page/cardMoveStyle.action",{"jspstyle":jspstyle,"pageid":pageid});
		};
</script>