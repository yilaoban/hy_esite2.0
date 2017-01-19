<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 投票2 已废弃-->
<link href="/css/hudong/toupiao2/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
	<div class="wdy_top2_cp block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507127238"><img src="${blocks[0].img}" width="100%" height="100%" hyvar="img" hydesc="720*300"/></div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div hyblk="I" class="block tpclearfix ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy20150507127117">
<s:if test='blocks[1].obj.id > 0'>
<s:set name="obj" value="blocks[1].obj" />
<div class="hudong_141211_tp_box" class_data="hy20150507127506">
	<form action="/${oname}/user/vote.action" method="post" id="myvoteform${pcid}" class_data="hy20150507127583">
		<input type="hidden" name="voteid" value="${obj.id }"/>
		<input type="hidden" name="relationid" value="${blocks[1].rid}">
		<input type="hidden" name="pageid" value="${pageid }"/>
		<ul class_data="hy20150507127468">
			<s:if test='#obj.questiontype == "R"'>
				<s:iterator value="#obj.voteOption" var="o" status="st">
					<li class_data="hy20150507127969">
					 	 <label class_data="hy20150507127359">
						 	 <input name="cho" type="checkbox" value="${o.id}"  class="tp_141211_dx" onclick="checkOption(1)"/>
	            			 <span hyvar="name" class_data="hy20150507127162">${o.content}</span>
            			 </label>
					</li>
				</s:iterator>
			</s:if>
			<s:elseif test='#obj.questiontype == "C"'>
				<s:iterator value="#obj.voteOption" var="o" status="st">
					<li class_data="hy20150507127784">
					 	 <label class_data="hy20150507127164">
						 	 <input name="cho" type="checkbox" value="${o.id}"  class="tp_141211_dx" onclick="checkOption(${obj.maxnum })"/>
	            			 <span hyvar="name" class_data="hy20150507127648">${o.content}</span>
            			 </label>
					</li>
				</s:iterator>
			</s:elseif>
			 <div class="tp_an_141211" class_data="hy20150507127505">
	            <center>
	              <a href="javascript:void(0);" id="toupiaosub_${pcid}"><img src="/images/hudong/toupiao2/tp_an.png" hyvar="img" hydesc="50*30"/></a>
	            </center>
	          </div>
		</ul>
	</form>
</div>
</s:if>
<s:else>
<div class="hudong_141211_tp_box" class_data="hy20150507127743">
     <form action="" method="get" class_data="hy20150507127834">
       <ul class_data="hy20150507127106">
         <h1 hyvar="title" class_data="hy20150507127122">“青春正能量”—选出你心中的先进集体</h1>
         <a>
         <li class_data="hy20150507127902">
           <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
           <span hyvar="name" class_data="hy20150507127330">日本语学院13级3班</span> </li>
        </a> 
  <a>
        <li class_data="hy20150507127978">
          <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
          <span hyvar="name" class_data="hy20150507127515">俄语系13级1班</span> </li>
        </a> 
  <a>
        <li class_data="hy20150507127729">
          <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
          <span hyvar="name" class_data="hy20150507127743">软件学院12级18班</span> </li>
        </a> 
  <a>
        <li class_data="hy20150507127866">
          <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
          <span hyvar="name" class_data="hy20150507127274">文化传播学院11级1班</span> </li>
        </a>
   <a>
        <li class_data="hy20150507127409">
          <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
          <span hyvar="name" class_data="hy20150507127371">软件学院12级18班</span> </li>
        </a>
   <a>
        <li class_data="hy20150507127850">
          <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
          <span hyvar="name" class_data="hy20150507127438">文化传播学院11级1班</span> </li>
        </a> 
  <a>
        <li class_data="hy20150507127677">
          <input name="" type="checkbox" value=""  class="tp_141211_dx"/>
          <span hyvar="name" class_data="hy20150507127946">西葡语系11级3班</span> </li>
        </a>
        <div class="tp_an_141211" class_data="hy20150507127993">
          <center>
            <a><img src="/images/hudong/toupiao2/tp_an.png" hyvar="img" hydesc="50*30"/></a>
          </center>
        </div>
      </ul>
    </form>
 
  </div>
</s:else>
</div>
</s:if>

<script language="javascript">
		$(document).ready(function() {  
			 $("#toupiaosub_${pcid}").click(function(){
			 	var pcid = '${pcid}';
			 	var inputs = $("#myvoteform"+pcid+" input[type='checkbox']:checked");
			 	if(inputs.length < 1){
					$.alert("请选择投票的选项!","");
					return;
				}
				var questiontype = '${blocks[1].obj.questiontype}';
				var num = '${blocks[1].obj.maxnum}';
				if(questiontype == "R"){
					if(inputs.length > 1){
						$.alert("最多选择1个，请去掉多余的选项","");
						return;
					}
				}else if(questiontype == "C"){
					if(inputs.length > num){
						$.alert("最多选择"+ num +"个，请去掉多余的选项");
						return;
					}
				}
				$.ajax({
		                cache: true,
		                type: "POST",
		                url:"/${oname}/user/vote.action",
		                data:$('#myvoteform${pcid}').serialize(),
		                async: false,
		                error: function(request) {
		                    console.log("Connection error");
		                },
		                success: function(data) {
		                	hdCallBack(data,"Y");
		                }
		            });	
				 });
		})
		
		function checkOption(num){
			var pcid = '${pcid}';
			var inputs = $("#myvoteform"+pcid+" input[type='checkbox']:checked");
			if(inputs.length > num){
				$.alert("最多选择"+ num +"个，请去掉多余的选项");
			}
		}
		
		
</script>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
