<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!--xc投票 -->
<link href="/css/xc/mo/tp/index2.css" rel="stylesheet" type="text/css" />
<div class="hudong_141111_tp" >
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="toupiao_150327_top block ${blocks[0].ctname}" hyblk="S" hydata="${blocks[0].rid}" hyct="Y" status="0" style="${blocks[0].hyct}">
  <img src="${blocks[0].img}" hyvar="img" hydesc="640*365">
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
  <div class="toupiao_150327_center block ${blocks[1].ctname}" hydata="${blocks[1].rid}" hyct="Y" status="0" style="${blocks[1].hyct}">
    <ul>
		<s:if test='method=="E"'>
			<li>
		      <div class="toupiao_150327_prize style1"><img src="/images/xc/mo/tp/1.png"></div>
		      <div class="toupiao_150327_fenmu" style="background: #EEEEEE;">
		        <div class="toupiao_150327_fenzi style11" style="width: 60%;"></div>
		        <span class="span1">颜如玉</span>
         		<span class="span2">颜如玉<br>10票</span>
		      </div>
		      <div class="toupiao_150327_btn style1"><span>投票</span></div>
		    </li>
			 <li>
		      <div class="toupiao_150327_prize style2"><img src="/images/xc/mo/tp/2.png"></div>
		      <div class="toupiao_150327_fenmu" style="background: #EEEEEE;">
		        <div class="toupiao_150327_fenzi style12" style="width: 50%;"></div>
		         <span class="span1">颜如玉</span>
         		<span class="span2">颜如玉<br>10票</span>
		      </div>
		      <div class="toupiao_150327_btn style2"><span>投票</span></div>
		    </li>
		    <li>
		      <div class="toupiao_150327_prize style3"><img src="/images/xc/mo/tp/3.png"></div>
		      <div class="toupiao_150327_fenmu" style="background: #EEEEEE;">
		        <div class="toupiao_150327_fenzi style13" style="width: 40%;"></div>
		        <span class="span1">颜如玉</span>
         		<span class="span2">颜如玉<br>10票</span>
		      </div>
		      <div class="toupiao_150327_btn style3"><span>投票</span></div>
		    </li>
		    <li>
		      <div class="toupiao_150327_prize style4"><img src="/images/xc/mo/tp/4.png"></div>
		      <div class="toupiao_150327_fenmu" style="background: #EEEEEE;">
		        <div class="toupiao_150327_fenzi style14" style="width: 40%;"></div>
		       <span class="span1">颜如玉</span>
         		<span class="span2">颜如玉<br>10票</span>
		      </div>
		      <div class="toupiao_150327_btn style4"><span>投票</span></div>
		    </li>
		    <li>
		      <div class="toupiao_150327_prize style5"><img src="/images/xc/mo/tp/5.png"></div>
		      <div class="toupiao_150327_fenmu" style="background: #EEEEEE;">
		        <div class="toupiao_150327_fenzi style15" style="width: 40%;"></div>
		         <span class="span1">颜如玉</span>
         		<span class="span2">颜如玉<br>10票</span>
		      </div>
		      <div class="toupiao_150327_btn style5"><span>投票</span></div>
		    </li>
		</s:if>
		<s:else>
			<s:set name="obj" value="#session.hy_page_dto['123'].interactVote" />
				<s:iterator value="#obj.voteOption" var="o" status="st">
					<li>
						<div class="toupiao_150327_prize">
							<s:if test='#o.pic !=null && #o.pic != ""'>
						      <img src="${imgDomain}${o.pic}">
							</s:if>
						</div>
				      <div class="toupiao_150327_fenmu" style="background: #818181;">
				        <span class="span2" piao="${o.count }">${o.content}</span>
				        <span class="span3" >${o.count }票</span>
				      </div>
					   <s:if test="#st.index%2 == 0">   
				      	<div class="toupiao_150327_btn" style='background:url("/images/xc/mo/tp/lanse.png");background-position:center;background-repeat: no-repeat;' onclick="voteCount(${o.id },${o.count},${obj.id })"><span>投票</span></div>
				    	</s:if>
				    	<s:else>
				    	<div class="toupiao_150327_btn" style='background:url("/images/xc/mo/tp/lvse.png");background-position:center;background-repeat: no-repeat;' onclick="voteCount(${o.id },${o.count},${obj.id })"><span>投票</span></div>
				    	</s:else>
				    </li>
				</s:iterator>
		</s:else>
    </ul>
</div>
</s:if>
</div>

<script >
		function voteCount(cho,count,voteid){
			var xcid= $("#hy_entity").val();
			$.post("/${oname}/user/vote.action",{"voteid":voteid,"pageid":'${pageid}',"cho":cho},function(data){
				console.log(data);
				hdCallBack(data,"N");
				if(data.status == 1){
					count = count + 1;
					window.location.reload();
				}
			});
		}
		
		
		$(document).ready(function(){
			var maxcount = 0;
			$(".span2").each(function(){
				var count = $(this).attr("piao");
					count = parseInt(count);
				if(count > maxcount){
					maxcount = count;
				};
			});
			$(".span2").each(function(){
				var count = $(this).attr("piao");
					count = parseInt(count);
				$(this).prev().css("width",count/maxcount*100+"%");
			});
		});
</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
<%@include file="/WEB-INF/card/hdcallback.jsp"%>