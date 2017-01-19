<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form id="myform">
			<div>
				<ul class="lst">
					<s:iterator value="dto.prolist" var="prolist" status="proliststatus">
						<li class="<s:if test='#proliststatus.first'>active</s:if>">
						<a href="javascript:void(0);" onclick="showTab(${proliststatus.index+1 })">${prolist.title }</a><%--showTab()里面的数字从1开始 --%>
						<span style="font-size:12px;font-weight:normal;">(<a href="javascript:void(0);" onclick="delprolist('${prolist.title }',this)"/>删</a>/<a href="javascript:void(0)" onclick="rename(this)">改</a><input type="hidden" name="dto.prolist[${proliststatus.index}].title" value="${prolist.title }"/><input type="hidden" name="dto.prolist[${proliststatus.index}].id" value="${prolist.id }"/><input type="hidden" name="dto.prolist[${proliststatus.index}].content" value="${prolist.content }"/><input type="hidden" name="dto.prolist[${proliststatus.index}].idx" value="${prolist.idx }"/>)</span>
						</li>
					</s:iterator>
					<input type="hidden" value="${dto.fid }" name="dto.fid"/>
					<a href="javascript:void(0);" onclick="addprolist()" style="float:right;margin-top:5px;font-size:12px;"/>增加系列</a>
					<p style="clear: both"></p>
				</ul>
			</div>
			<div id="add">
					<s:iterator value="dto.prolist" var="prolist" status="proliststatus">
					<div id="tab${proliststatus.index+1 }" class="tab_content" style="border-bottom:1px solid #d1d1d1;<s:if test='!#proliststatus.first'>display:none;</s:if>">
						<ul  id="${proliststatus.index }">
							<s:iterator value="dto.product[#proliststatus.index]" var="p" status="ppstatus">
							<li class="existProduct">
								<img height="40" width="40" src="${imgDomain }${p.product.simgurl}"/>
								<input type="checkbox" checked="checked" name="dto.relationStr[${proliststatus.index }]"  value="${prolist.id}~${p.product.id}~${p.zantotal }~${p.idx }"/>
								<input type="text" value="${p.idx }" oninput="addidx(this)" style="width: 20px"/>
							</li>					
							</s:iterator>
							<p style="clear: both"></p>
						</ul>
						<input type="button" value="增加产品" onclick="addprouct(${prolist.id },'${prolist.title }',this)"/>
					</div>
					</s:iterator>
			</div>








<div class="pt10">
				<input  type="button" value="确定" id="save102"/>
				<input  type="button" value="取消" onclick="closeFrame()"/>
</div>
</form>
<div id="addProlist" style="display: none">
	新系列名称:<input class="text-medium" type="text" id="val"/><br>
	系列位置:<input class="text-medium" type="text" id="idx" value="0"/><br>
	新系列描述:<textarea rows="" cols="" id="val1"></textarea>
</div>
<div id="renameProlist" style="display: none">
	新系列名称:<input class="text-medium" type="text" id="newvalue"/><br>
	系列位置:<input class="text-medium" type="text" id="newvalue3"/><br>
	新系列描述:<textarea rows="" cols="" id="newvalue1"></textarea>
</div>
<div id="product1" style="display: none">
	<div id="product" style="height:300px; overflow-y:auto">
	</div>
</div>
<script type="text/javascript">
		$.post("/content/ajax_find_owner_product.action",function(data){
			$.each(data,function(index,value){
				$("#product").append("<input name='prd' type='checkbox' value='"+value.id+"'/>"+value.name+"<img height='40' width='40' src='${imgDomain }"+value.simgurl+"'/>");
			});
		});
		
		$("#save102").click(function(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"/${oname}/page/config_sub.action?featureid=${featureid}",
		        data:$('#myform').serialize(),
		        async: false,
		        success: function(data) {
		        	if(data == "Y"){
						layer.load(2);
						setTimeout('layer.closeAll("loading");parent.layer.msg("操作成功!",{icon: 6, time: 1500},function(){closeFrame()})',2000);
		        	}else{
						parent.layer.msg("保存失败！请重试！",{icon: 6, time: 1500},3);
		        	}
		        }
		    });
		});
</script>
</body>
