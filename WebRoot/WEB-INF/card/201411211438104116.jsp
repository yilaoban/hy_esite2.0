<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 竞拍 -->
<link rel="stylesheet" href="/css/hudong/jingpai/index.css" type="text/css"></link>

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="jingpai_141113_box" class_data="hy2015050742702">

	<s:if test='blocks[0].display=="Y"'>
	<div class="wdy_top block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050742436">
		<img src="${blocks[0].img}" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*350"/>
	</div>
	</s:if>   
	   
	<s:if test='blocks[1].display=="Y"'>   
	<div class="jingpai_141113_box_c block clearfix" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050742871">
		<s:if test='blocks[1].obj.id > 0'>
			<s:set name="obj" value="blocks[1].obj" />
			<s:set name="ua" value="blocks[1].ua" />
			<s:set name="relation" value="blocks[1].relation" />
			<s:set name="jf" value="blocks[1].jf" />
			<div class="jingpai_141114_cp" class_data="hy2015050742937">
				<img src="${imgDomain }${obj.simg }" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="250*350"/>
			</div>
			<div class="jingpai_141113_box_c_left" class_data="hy2015050742715">
				<h3 class_data="hy2015050742520">${obj.title }</h3>
				<div class="jingpai_141113_box_c_l_1" class_data="hy2015050742126">起拍积分：<s:if test="#obj.currentmax==0">${obj.startbalance}</s:if><s:else>${obj.currentmax}</s:else></div>
				<div class="jingpai_141113_box_c_l_time" class_data="hy2015050742345">
					<p class_data="hy2015050742773">剩余时间：</p>
					<s:if test='#obj.timeStatus=="B"'>
						<span class_data="hy2015050742254"><s:if test='#obj.startnote!=null'>${obj.startnote}</s:if><s:else>竞拍还未开始</s:else></span>
					</s:if>
					<s:elseif test='#obj.timeStatus=="E"'>
						<span class_data="hy2015050742647"><s:if test='#obj.endnote!=null'>${obj.endnote}</s:if><s:else>竞拍已经结束</s:else></span>
					</s:elseif>
					<s:elseif test='#obj.timeStatus=="N"'>
						<span class_data="hy2015050742996">
							<strong id="day${pcid}"><s:number name="#obj.dateSub/(60*60*24)%1"/></strong>天
							<strong id="hour${pcid}"><s:number name="#obj.dateSub%(24*60*60)/(60*60)"/></strong>小时
							<strong id="minute${pcid}"><s:number name="#obj.dateSub%(60*60)/60" maximumIntegerDigits="2"/></strong>分钟
							<strong id="second${pcid}"><s:number name="#obj.dateSub%60" maximumIntegerDigits="2"/></strong>秒
						</span>
					</s:elseif>
				</div>
				<div class="jingpai_141113_box_c_l_qp" class_data="hy2015050742956"><p class_data="hy2015050742229">我要竞拍：</p><span id="subBalance${pcid}" class_data="hy2015050742694">${obj.currentmax+obj.addbalance}</span>
					<img src="/images/hudong/jingpai/jia_03.png" width="26" height="26" id="addb${pcid }"/>
					<img src="/images/hudong/jingpai/2_04_05.jpg" width="26" height="26"  id="subb${pcid }"/> 
				</div>
			
				<div class="jingpai_141113_box_c_l_time" class_data="hy2015050742651"><p class_data="hy2015050742445">当前积分：</p><span class_data="hy2015050742823">${jf}</span></div>
			
				<div class="anniu_141114_cj" class_data="hy2015050742940">
					<img id="auctionSub${pcid }" src="/images/hudong/jingpai/an_03.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="80*30"/>
				</div>
			</div>
			
			
			<script type="text/javascript">
								function timer(intDiff,pcid){
									var timeid=window.setInterval(function(){
									var day=0,
										hour=0,
										minute=0,
										second=0;	
									if(intDiff > 0){
										day = Math.floor(intDiff / (60 * 60 * 24));
										hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
										minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
										second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
									}else{
										window.clearInterval(timeid);
										$("#a_${pcid}").load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
									}
									if (minute <= 9) minute = '0' + minute;
									if (second <= 9) second = '0' + second;
									$("#day${pcid}").text(day);
									$("#hour${pcid}").text(hour);
									$("#minute${pcid}").text(minute);
									$("#second${pcid}").text(second);
									intDiff--;
									}, 1000);
									timerArr.push(timeid);
									$('#djs'+pcid).after("<input type='hidden' id='timeid"+timeid+"' value='1'/>");
								} 
								
								$(document).ready(function(){
									for(var i=0;i<timerArr.length;i++){
											var timeid=timerArr[i];
											if($("#timeid"+timeid).val()!=1){
												window.clearInterval(timerArr[i]);
											}
										}
										var intDiff = ${obj.dateSub};
										if(intDiff>0){
											timer(intDiff,${pcid});
										}
										var timeStatus='${obj.timeStatus}';
										if(timeStatus=="E"){
											var type='${obj.type}';
											var entityid='${obj.entityid}';
											if(entityid!=0){
												var isuserName='${obj.userName}';
												var isuserPhone='${obj.userPhone}';
												var isuserEmail='${obj.userEmail}';
												var isuserLocation='${obj.userLocation}';
												if(isuserName=="Y"||isuserPhone=="Y"||isuserEmail=="Y"||isuserLocation=="Y"){
													if(type==0){
														var wbuid='${visitUser.wbuid}';
														if(wbuid==entityid){
															usermsg('${obj.id}',isuserName,isuserPhone,isuserEmail,isuserLocation);
														}
													}else if(type==1){
														var wxuid='${visitUser.wxuid}';
														if(wxuid==entityid){
															usermsg('${obj.id}',isuserName,isuserPhone,isuserEmail,isuserLocation);
														}
													}	
												}
											}
										}
										
									$("#auctionSub${pcid}").click(function(){
										var auid='${obj.id}';
										var timeCheck='${obj.timeStatus}';
										var minBalance=parseInt('${obj.currentmax+obj.addbalance}');
										var addBalance=parseInt($("#subBalance${pcid}").text());
										var maxBalance=parseInt('${jf}');
										if(timeCheck=="N"&&addBalance>=minBalance&&addBalance<=maxBalance){
											$.ajax({
												url:"/${oname}/user/auction_draw.action",
												dataType:"json",
												data:{	"auid":'${obj.id}',
														"pageid":'${pageid}',
														"addnum":addBalance,
														"random":Math.random()
													},
												success:function(data){
														if(data !=null){
															if(data.status==1){
																hyalert("竞价成功",2000);
																$("#a_${pcid}").load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
															}else if(data.status==0){
																hyalert(data.hydesc,2000);
															}
														}
													},
												timeout:4000
											})
										}else if(timeCheck=="B"){
											hyalert('${obj.startnote}',2000);										
										}else if(timeCheck=="E"){
											hyalert('${obj.endnote}',2000);
										}else if(addBalance<minBalance){
											hyalert('竞拍出价不得低于最低竞拍价格',2000);
										}else if(addBalance>maxBalance){
											hyalert('积分不足',2000);
										}
									});
									
									
									$("#addb${pcid}").click(function(){
										var balance=parseInt($("#subBalance${pcid}").text());
										var jifen=parseInt('${jf}');
										if(jifen>balance){
											$("#subBalance${pcid}").text(parseInt(balance)+1);	
										}else{
											hyalert("积分不足",2000);
										}
									});
									
									$("#subb${pcid }").click(function(){
										var minbalance=parseInt('${obj.currentmax+obj.addbalance}');
										var currentbalance=parseInt($("#subBalance${pcid}").text());
										if(currentbalance>minbalance){
											$("#subBalance${pcid}").text(currentbalance-1);
										}else{
											hyalert("不能低于最低出价哦",2000);
										}
									});
								});
								
								
								function usermsg(auid,name,phone,email,location){
										var str='<div style="padding:10px; width: 250px;" class_data="hy2015050742697"><table class="info" class_data="hy2015050742892"><tr class_data="hy2015050742373"><td colspan="2" class_data="hy2015050742786">恭喜您赢得此次竞拍，请填写联系信息。</td></tr>';
										if(name=="Y"){
											str+='<tr class_data="hy2015050742711"><td width="25%" class_data="hy2015050742701">姓名</td><td class_data="hy2015050742110"><input type="text" id="username"/>';
										}
										if(phone=="Y"){
											str+='</td><tr class_data="hy2015050742739"><td class_data="hy2015050742674">电话</td><td class_data="hy2015050742636"><input type="text" id="userphone" /></td></tr>';
										}
										if(email=="Y"){
											str+='<tr class_data="hy2015050742628"><td class_data="hy2015050742779">邮箱</td><td class_data="hy2015050742331"><input type="text" id="useremail" /></td></tr>';
										}
										if(location=="Y"){
											str+='<tr class_data="hy2015050742702"><td class_data="hy2015050742855">地址</td><td class_data="hy2015050742189"><input type="text" id="userlocation" /></td></tr>';
										}
										str+='</tr><tr class_data="hy2015050742911"><td colspan="2" align="center" style="line-height:40px;" class_data="hy2015050742897"><input type="button" value="提交" onclick="userCheck('+auid+')"/></td></tr></table></div>';
										easyDialog.open({
											  container : {
											    content : str
											  }
											});
									}
									
								function userCheck(auid){
										var username = $("#username").val();
										var userphone = $("#userphone").val();
										var useremail = $("#useremail").val();
										var userlocation = $("#userlocation").val();
										if(userphone == ""){
											alert("请填写联系电话");
											return;
										}
										$.post("/${oname}/user/auction_winer_sub.action",{"auid":auid,"username":username,"userphone":userphone,"userlocation":userlocation},function(data){
											if(data=="Y"){
												hyalert('保存成功!',2000)
											}else{
												hyalert('保存失败!',2000)
											}
										});
									}
						</script>
			
		</s:if>
		<s:else>
			<div class="jingpai_141114_cp" class_data="hy2015050742719">
				<img src="/images/hudong/jingpai/2_04.jpg" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="250*350"/>
			</div>
			<div class="jingpai_141113_box_c_left" class_data="hy2015050742287">
				<h3 class_data="hy2015050742985">超萌动物LED台灯</h3>
				<div class="jingpai_141113_box_c_l_1" class_data="hy2015050742487">起拍积分：115</div>
				<div class="jingpai_141113_box_c_l_time" class_data="hy2015050742746"><p class_data="hy2015050742574">剩余时间：</p><span class_data="hy2015050742297">5小时30分</span></div>
				<div class="jingpai_141113_box_c_l_qp" class_data="hy2015050742220"><p class_data="hy2015050742106">我要竞拍：</p><span class_data="hy2015050742499">3655</span>
					<img src="/images/hudong/jingpai/jia_03.png" width="26" height="26" />
					<img src="/images/hudong/jingpai/2_04_05.jpg" width="26" height="26" /> 
				</div>
			
				<div class="jingpai_141113_box_c_l_time" class_data="hy2015050742311"><p class_data="hy2015050742752">当前积分：</p><span class_data="hy2015050742218">2541</span></div>
			
				<div class="anniu_141114_cj" class_data="hy2015050742490">
					<img src="/images/hudong/jingpai/an_03.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="80*30"/>
				</div>
			
			</div>
		</s:else>
	</div>
	</s:if>   
	
	
	<div style="clear:both"></div>
	<s:if test='blocks[2].display=="Y"'>   
	<div class="jp_141114_xq block" hyblk="S" hydata="${blocks[2].rid}" class_data="hy2015050742656">
		<span class="zxf_span1" hyvar="title" class_data="hy2015050742838">${blocks[2].title}</span>
		<span class="zxf_span2" hyvar="desc" class_data="hy2015050742586">${blocks[2].desc}</span>
	</div>
	</s:if>   
	   
 
   
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
