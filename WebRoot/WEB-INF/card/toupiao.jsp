<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- ΢投票1 -->
<link href="/css/hudong/toupiao/list.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2_cp block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507126521">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
  <div class="hudong_141111_tp_box clearfix block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="I" hydata="${blocks[1].rid}" class_data="hy20150507126810">
    <ul class_data="hy20150507126884">
		<s:if test='blocks[1].obj.id > 0'>
			<s:set name="obj" value="blocks[1].obj" />
			<form action="/${oname}/user/vote.action" method="post" id="myvoteform" >
				<s:iterator value="#obj.voteOption" var="o" status="st">
					<li>
					  <img src="${imgDomain}${o.pic}" style="width: 146px;height:146px;" hyvar="img" hydesc="300*300"/>
					  <span onclick="voteCount(${o.id },${o.count},${obj.id })"><b hyvar="name">${o.content }</b><strong id="voteCount${o.id}"><img src="/images/hudong/toupiao/zan_17.png" width="20" height="20"/>&nbsp;${o.count }票</strong></span>
					</li>
				</s:iterator>
			</form>
		</s:if>
		<s:else>
			  <a>
		      <li class_data="hy20150507126981">
		        <img src="/images/hudong/toupiao/tu1.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*300"/>
		         <span class_data="hy20150507126969"><b hyvar="name">加菲猫</b><strong><img src="/images/hudong/toupiao/zan_17.png" width="20" height="20" />&nbsp;6424票</strong></span>
		      </li>
		      </a>
		      <a>
		      <li class_data="hy20150507126341">
		        <img src="/images/hudong/toupiao/tu2.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*2300"/>
		         <span class_data="hy20150507126948"><b hyvar="name">樱木花道</b><strong><img src="/images/hudong/toupiao/zan_17.png" width="20" height="20" />&nbsp;8756票</strong></span>
		      </li>
		      </a>
		      <a>
		      <li class_data="hy20150507126611">
		        <img src="/images/hudong/toupiao/tu3.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*300"/>
		         <span class_data="hy20150507126227"><b hyvar="name">卡卡西</b><strong><img src="/images/hudong/toupiao/zan_17.png" width="20" height="20" />&nbsp;51294票</strong></span>
		      </li>
		      </a>
		      <a>
		      <li class_data="hy20150507126567">
		        <img src="/images/hudong/toupiao/tu4.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="300*300"/>
		         <span class_data="hy20150507126705"><b hyvar="name">小熊维尼</b><strong><img src="/images/hudong/toupiao/zan_17.png" width="20" height="20" />&nbsp;2544票</strong></span>
		      </li>
		      </a>
		</s:else>
    </ul>
</div>
</s:if>

<script language="javascript">
		function voteCount(cho,count,voteid){
			$.post("/${oname}/user/vote.action",{"voteid":voteid,"relationid":'${blocks[1].rid}',"pageid":'${pageid}',"cho":cho},function(data){
				if(data.status==1){
					count = count + 1;
					$('#voteCount'+cho).text(count+"票");
				}
				hdCallBack(data,"N");
			});
		}
		
</script>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
