<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 签到 -->
<link href="/css/hudong/qiandao/index.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="qiandao_141117_box_1" class_data="hy2015050745704">
	<s:if test='blocks[0].display=="Y"'>
	<div class="wdy_top block" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050745563">
		<img src="${blocks[0].img}" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="640*350"/>
	</div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div class="qiandao_141117_box_c" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050745651">
		<s:if test='blocks[1].obj != null && blocks[1].obj.id > 0 '>
			<s:if test='method!="E"'>
				<div class="qiandao_141117_box_c_an" class_data="hy2015050745139">
					<s:if test='blocks[1].obj.ischeck == 0'>
						<a  onclick="onclickGetBalance(${pageid})">点击这里签到赚积分</a>
					</s:if>
					<s:elseif test="blocks[1].obj.ischeck == 1">
						今日已签到
					</s:elseif>
				</div>
			</s:if>
			<s:else>
				<div class="qiandao_141117_box_c_an" class_data="hy2015050745413">
					<s:if test='blocks[1].obj.ischeck == 0'>
						<a>点击这里签到赚积分</a>
					</s:if>
					<s:elseif test="blocks[1].obj.ischeck == 1">
						今日已签到
					</s:elseif>
				</div>
			</s:else>
			<s:set name="obj" value="blocks[1].obj" />
			<div class="qiandao_141117_box_jifen" class_data="hy2015050745813">
				<ul class_data="hy2015050745122">
					<li class_data="hy2015050745167">
					<span class_data="hy2015050745172">总积分</span>
					<p class_data="hy2015050745851">${obj.total }</p>
					</li>
					<li class_data="hy2015050745131">
					<span class_data="hy2015050745382">剩余积分</span>
					<p class_data="hy2015050745496">${obj.remainBalance }</p>
					</li>
					<li class_data="hy2015050745734">
					<span class_data="hy2015050745699">签到积分</span>
					<p class_data="hy2015050745261">${obj.checkinBalance }</p>
					</li>
					<li style="border:0;" class_data="hy2015050745717">
					<span class_data="hy2015050745417">消费积分</span>
					<p class_data="hy2015050745108">${obj.used }</p>
					</li>
				</ul>
			</div>
			<div class="qiandao_141117_box_rili" class_data="hy2015050745205">
	      
				<div class="qiandao_141117_box_rili_bottom" class_data="hy2015050745902">
					<table border="0" cellspacing="0" cellpadding="0" width="100%" style="float:left; text-align:center; line-height:30px; font-size:0.8em;" class_data="hy2015050745430">
						<tr style="background:#eeeeee;border:1px #dcdada solid" class_data="hy2015050745750">
						<td width="433" valign="top" class_data="hy2015050745614">日期</td>
						<td width="310" valign="top" class_data="hy2015050745670">签到情况</td>
						<td width="184" valign="top" class_data="hy2015050745482">积分</td>
						</tr>
						<s:iterator value="#obj.checkinRecord" status="st" var="r">
							<tr style="border-bottom:1px #dcdada solid" class_data="hy2015050745151">
							<td width="433" valign="top" class_data="hy2015050745653">${r.createtimeStr}</td>
							<td width="310" valign="top" class_data="hy2015050745988">签到</td>
							<td width="184" valign="top" class_data="hy2015050745618">+${r.addbalance }</td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="qiandao_141117_box_c_an" class_data="hy2015050745639"><a  onclick="onclickGetBalance(${pageid})">点击这里签到赚积分</a></div>
			<div class="qiandao_141117_box_jifen" class_data="hy2015050745327">
				<ul class_data="hy2015050745657">
					<li class_data="hy2015050745299">
					<span class_data="hy2015050745437">总积分</span>
					<p class_data="hy2015050745248">0</p>
					</li>
					<li class_data="hy2015050745874">
					<span class_data="hy2015050745644">剩余积分</span>
					<p class_data="hy2015050745924">0</p>
					</li>
					<li class_data="hy2015050745195">
					<span class_data="hy2015050745737">签到积分</span>
					<p class_data="hy2015050745948">0</p>
					</li>
					<li style="border:0;" class_data="hy2015050745452">
					<span class_data="hy2015050745566">消费积分</span>
					<p class_data="hy2015050745887">0</p>
					</li>
				</ul>
			</div>
	   
			<div class="qiandao_141117_box_rili" class_data="hy2015050745837">
	      
				<div class="qiandao_141117_box_rili_bottom" class_data="hy2015050745918">
					<table border="0" cellspacing="0" cellpadding="0" width="100%" style="float:left; text-align:center; line-height:30px; font-size:0.8em;" class_data="hy2015050745932">
						<tr style="background:#eeeeee;border:1px #dcdada solid" class_data="hy2015050745237">
						<td width="433" valign="top" class_data="hy2015050745400">日期</td>
						<td width="310" valign="top" class_data="hy2015050745702">签到情况</td>
						<td width="184" valign="top" class_data="hy2015050745599">积分</td>
						</tr>
						<tr style="border-bottom:1px #dcdada solid" class_data="hy2015050745590">
						<td width="433" valign="top" class_data="hy2015050745635">11月1日 星期六</td>
						<td width="310" valign="top" class_data="hy2015050745764">未签到</td>
						<td width="184" valign="top" class_data="hy2015050745327">+0</td>
						</tr>
						<tr style="border-bottom:1px #dcdada solid" class_data="hy2015050745880">
						<td width="433" valign="top" class_data="hy2015050745575">11月2日 星期日</td>
						<td width="310" valign="top" class_data="hy2015050745832">未签到</td>
						<td width="184" valign="top" class_data="hy2015050745231">+0</td>
						</tr>
						<tr style="border-bottom:1px #dcdada solid" class_data="hy2015050745791">
						<td width="433" valign="top" class_data="hy2015050745699">11月3日 星期一</td>
						<td width="310" valign="top" class_data="hy2015050745556">未签到</td>
						<td width="184" valign="top" class_data="hy2015050745757">+0</td>
						</tr>
						<tr style="border-bottom:1px #dcdada solid" class_data="hy2015050745468">
						<td width="433" valign="top" class_data="hy2015050745561">11月4日 星期二</td>
						<td width="310" valign="top" class_data="hy2015050745431">未签到</td>
						<td width="184" valign="top" class_data="hy2015050745991">+0</td>
						</tr>
					</table>
				</div>
			</div>
		</s:else>
	</div>
	</s:if>
</div>
<script type="text/javascript">
	function onclickGetBalance(pageid){
		var id = $("#cardid").val();
		$.post("/${oname}/user/checkin_draw.action",{"pageid":pageid},function(data){
		       	if(data.status == 0){
		       		$.alert(data.hydesc,"");
		       	}else if(data.status == 1){
		       		$.alert(data.hydesc,"");
		       		$("#a_"+id).load('/${oname}/user/showCard.action?pcid='+id+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
		       	}
	       });
	}

</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
