<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 投票3 -->

<link href="/css/hudong/toupiao3/index.css" rel="stylesheet" type="text/css">
<link href="/css/hudong/toupiao3/animations.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<div class="tou_yk_box block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="I" hydata="${blocks[0].rid}">

	<div>
		<ul>
			<s:if test='blocks[0].obj.id > 0'>
				<s:set name="obj" value="blocks[0].obj" />
				<form action="/${oname}/user/vote.action" method="post" id="myvoteform">
					<s:iterator value="#obj.voteOption" var="o" status="st">
						<li>
							<div class="tou_yk_img">
					            <img width="292" src="${imgDomain}${o.pic}">  
					        </div>  
					        <div class="tou_yk_con"> 
					        	<span>${o.content }</span>
								<div class="dianzan" data="">
					            	<span id="${pcid}voteCount${o.id}">${o.count }</span>
					            	<span onclick="voteCount(${o.id },${o.count},${obj.id })"><img src="/images/hudong/toupiao3/xin_06.png" /></span>
					            </div >
					        </div> 
						</li>
					</s:iterator>
				</form>
			</s:if>
			<s:else>
			<li>
				<div class="tou_yk_img">
		            <img src="/images/hudong/toupiao3/201507241155450353_m.png">  
		        </div>  
		        <div class="tou_yk_con"> 
		        	<h1>杨伟东 &nbsp; 土豆总裁</h1>
		        	<p>他说: 这辈子，一定要做一些酷的事情让自己爽！</p>
					<div class="dianzan" data="">
		            	<img src="/images/hudong/toupiao3/xin_06.png" />
		            	<span id=""></span>
		            </div >
		        </div> 
			</li>
			<li>
				<div class="tou_yk_img">
		            <img src="/images/hudong/toupiao3/201507241156267593_m.png">  
		        </div>  
		        <div class="tou_yk_con"> 
		        	<h1>吴辉 &nbsp; &nbsp; 集团CFO</h1>
		        	<p>他说：和喜欢的人一起做喜欢的事。</p>
					<div class="dianzan" data="">
		            	<img src="/images/hudong/toupiao3/xin_06.png" />
		            	<span id=""></span>
		            </div >
		        </div> 
			</li>
			<li>
				<div class="tou_yk_img">
		            <img src="/images/hudong/toupiao3/201507241157172534_m.png">  
		        </div>  
		        <div class="tou_yk_con"> 
		        	<h1>顾思斌 &nbsp; 集团CPO</h1>
		        	<p>他说：用户第一！</p>
					<div class="dianzan" data="">
		            	<img src="/images/hudong/toupiao3/xin_06.png" />
		            	<span id=""></span>
		            </div >
		        </div> 
			</li>
			</s:else>
		</ul>
	</div>
</div>

<script language="javascript">
		function voteCount(cho,count,voteid){
			$.post("/${oname}/user/vote.action",{"voteid":voteid,"relationid":'${blocks[0].rid}',"pageid":'${pageid}',"cho":cho},function(data){
				if(data.status==1){
					count = count + 1;
					$('#${pcid}voteCount'+cho).text(count);
				}
				hdCallBack(data,"N");
			});
		}
		
		
		function hdCallBack(data,successAlert){
			if(successAlert!="Y"||data.status!=1){
				$.alert(data.hydesc,"");
			}
			
			if(data.url!=null){
				window.location.href=data.url;
			}else if(data.id!=0){
				window.location.href="/${oname}/user/show/"+data.id+".html";
			}
		}
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>