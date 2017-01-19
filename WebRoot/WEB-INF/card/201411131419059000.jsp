<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微调研2 -->
<link href="/css/hudong/diaoyan2/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/Sortable.min.js"></script>


<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>	
  <div class="shinaide1030_wt_top block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050733532"><img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="640*300"/></div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="block ${blocks[1].ctname}" status="0" hyct="Y" style="top:160px;${blocks[1].hyct}" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050733760">  
	<s:if test='blocks[1].obj.id > 0'>
		<s:set name="obj" value="blocks[1].obj" />
		<form id="myform${pcid}" class_data="hy2015050733912">
			<div class="meijimu_1022_wt" class_data="hy2015050733462">
  			<div class="meijimu_1022_wt_center" class_data="hy2015050733166">
			<s:iterator value="#obj.questions" var="q" status="st">
			<div id="page${pcid}${st.count}" class="page${pcid} question${pcid}${q.id}" class_data="hy2015050733532">
			 <div class="meijimu_1022_wt_c_box" class_data="hy2015050733888">
			 <div class="neirong" class_data="hy2015050733246">
					<div class="shinaide1030_wt_title " class_data="hy2015050733169">${st.count}、${q.title }</div>
					<div class="shinaide1030_wt_center" class_data="hy2015050733124">
					<s:if test='#q.type=="SGL"'>
						<s:iterator value="#q.options" var="o" status="s">
						<table border="0" onclick="changetarget(${st.count },${o.target})" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.png) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733747">
						<tr style="width:100%; height:100%;" class_data="hy2015050733864">
							<td width="150" valign="center" style="text-align:center" class_data="hy2015050733321">
							<label class_data="hy2015050733396">
								<input type="radio"  name="question_${q.id}" value="${o.id}"  />
							</label>
							</td>
							<td  width="1023" valign="center" style="line-height:50px;" for="c${q.id}${s.count}" class_data="hy2015050733691">
								<label for="c${pcid }${q.id}${s.count}" class_data="hy2015050733645">${o.content }</label>
							</td>
						</tr>
						</table>
						</s:iterator>
					</s:if>
					<s:if test='#q.type=="MUP"'>
						<s:iterator value="#q.options" var="o" status="st2">
						<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.jpg) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733427">
						<tr style="width:100%; height:100%;" class_data="hy2015050733251">
							<td width="150" valign="center" style="text-align:center" class_data="hy2015050733464">
								<label class_data="hy2015050733500">
									<input type="checkbox" name="question_${q.id }" value="${o.id}" />
								</label>
							</td>
							<td width="1023" valign="center" style="line-height:50px;" class_data="hy2015050733114">
								<label for="c${pcid }${q.id}${st2.count}" class_data="hy2015050733935">${o.content }</label>
							</td>
						</tr>
						</table>
						</s:iterator>
					</s:if>
					<s:if test='#q.type=="FIL"'>
						 <table border="0" cellspacing="0" cellpadding="0" width="100%" style="font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei; margin-top:10px;" class_data="hy2015050733773">
							 <tr style=" width:100%; height:100%;" class_data="hy2015050733525">
							 	<td valign="center" style="text-align:center" class_data="hy2015050733794">
							 		<textarea  name="question_${q.id}"  style="width:90%; height:100px; margin:0 auto;"></textarea>
							 	</td>
							 </tr>
						</table>
					</s:if>
					<s:if test='#q.type=="GAD"'>
						<div class="dh_141217_2_box_c_neirong" class_data="hy2015050733126">
						<dd class_data="hy2015050733539"><img id="${pcid }${q.id }" idx="1" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050733228"><img id="${pcid }${q.id }" idx="2" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050733475"><img id="${pcid }${q.id }" idx="3" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050733345"><img id="${pcid }${q.id }" idx="4" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050733370"><img id="${pcid }${q.id }" idx="5" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<input type="hidden" id="df_${pcid}${q.id}" name="question_${q.id}" value="0">
						</div>
					</s:if>
					<s:if test='#q.type=="ORD"'>
						<div id="sort${pcid }${q.id }" class_data="hy2015050733421">
							<s:set name="size" value="#q.options.size"></s:set>
							<s:iterator value="#q.options" var="o" status="s">
								<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.png) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733692">
								<tr style="width:100%; height:100%;" class_data="hy2015050733141">
									<td  width="1023" valign="center" style="line-height:50px;" class_data="hy2015050733959">
										<label style="margin-left:50px;" class_data="hy2015050733421">${o.content }</label>
									</td>
								</tr>
								<input id="px${q.id }${o.id }" type="hidden" name="question_${q.id}" val="${o.id}" value="${o.id}-${s.count }">
								</table>
							</s:iterator>
						</div>
						
						<script type="text/javascript">
							new Sortable(sort${pcid}${q.id },{
							  onUpdate: function (evt){
							     var inputs = jQuery("#sort${pcid }${q.id } table input:hidden");
							     for(i = 0 ;i<inputs.length;i++){
							     	var oid = $(inputs[i]).attr("val");
							     	$(inputs[i]).val(oid+"-"+(i+1));
							     }
							  }
							});
						</script>
					</s:if>
					</div>
					<s:if test="#st.last">
						 <center><a id="sub_${pcid}" qid="${q.id }" requre="${q.isreq }"><img src="/images/mjm/tijiao_12.jpg" width="auto\9" height="auto" style="max-width: 100%; margin-top:10px; width:120px; height:34px;" "/></a></center>
					</s:if>
					<s:else>
						 <center><a id="xyt_${pcid}${st.count}" class="link${pcid }" index="${st.count }" qid="${q.id }" requre="${q.isreq }"><img src="/images/mjm/xiayiti_12.jpg" width="auto\9" height="auto" style="max-width: 100%; margin-top:10px; width:120px; height:34px;" "/></a></center>
					</s:else>
				</div>
			</div>
			</div>
			</s:iterator>
			</div>
			</div>
		</form>
		<input type="hidden" id ="researchid${pcid }" value="${obj.id }"/>
	</s:if>
	<s:else>
	  <div class="shinaide1030_wt_title " class_data="hy2015050733402">1、你阅读微信文章的时段是?</div>
		<div class="shinaide1030_wt_center" class_data="hy2015050733407">
		<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.jpg) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733949">
			<tr style="width:100%; height:100%;" class_data="hy2015050733368">
				<td width="150" valign="center" style="text-align:center" class_data="hy2015050733439">
				<label class_data="hy2015050733306">
					<input  type="radio"  id="xz_1" name="xz_List" />
				</label>
				</td>
				<td  width="1023" valign="center" style="line-height:50px;" for="c${q.id}${s.count}" class_data="hy2015050733703">
					<label  for="xz_1" class_data="hy2015050733198">上下班路上</label>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.jpg) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733691">
			<tr style="width:100%; height:100%;" class_data="hy2015050733408">
				<td width="150" valign="center" style="text-align:center" class_data="hy2015050733939">
				<label class_data="hy2015050733885">
					<input  type="radio"  id="xz_2" name="xz_List" />
				</label>
				</td>
				<td  width="1023" valign="center" style="line-height:50px;" for="c${q.id}${s.count}" class_data="hy2015050733187">
					<label  for="xz_2" class_data="hy2015050733617">吃饭时/后</label>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.jpg) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733600">
			<tr style="width:100%; height:100%;" class_data="hy2015050733285">
				<td width="150" valign="center" style="text-align:center" class_data="hy2015050733495">
				<label class_data="hy2015050733690">
					<input  type="radio"  id="xz_3" name="xz_List" />
				</label>
				</td>
				<td  width="1023" valign="center" style="line-height:50px;" for="c${q.id}${s.count}" class_data="hy2015050733517">
					<label  for="xz_3" class_data="hy2015050733730">睡觉前</label>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="0" width="100%" style="background:url(/images/mjm/wenti_03.jpg) no-repeat;background-size:100% 100%;  font-size:1.1em;margin-top:3px;color:#006bbb;font-family:Microsoft YaHei;" class_data="hy2015050733795">
			<tr style="width:100%; height:100%;" class_data="hy2015050733417">
				<td width="150" valign="center" style="text-align:center" class_data="hy2015050733311">
				<label class_data="hy2015050733913">
					<input type="radio" id="xz_4" name="xz_List" />
				</label>
				</td>
				<td  width="1023" valign="center" style="line-height:50px;" for="c${q.id}${s.count}" class_data="hy2015050733408">
					<label for="xz_4" class_data="hy2015050733746">工作间隙</label>
				</td>
			</tr>
		</table>
		<center><a href="javascript:void(0);"><img src="/images/mjm/xiayiti_12.jpg" width="auto\9" height="auto" style="max-width: 100%; margin-top:10px; width:120px; height:34px;" "/></a></center>
		</div>
	 </s:else>
</div>
</s:if>

<script type="text/javascript">
	function changetarget(index,target){
		if(target>0){
			$("#xyt_${pcid}"+index).attr("tarindex",target);
		}
	}
			function researchQuestionChecked(qid,req){//是否填写该题
				var result = false;
				if(req=="Y"){
					$("[name='question_"+qid+"']").each(function(i, o){
						var value = $(this).val();
						if($(this).is('textarea')){
							if(value != ""){
								result = true;
							}
						}
						if($(this).is('input')){
						   var type = $(this).attr("type");
						   if(type=="hidden"){
						   		if(value!=""){
						   			result = true;
						   		}
						   }
						   if(type=="checkbox" || type=="radio"){
						   		if($(this).is(":checked")){
						   			result = true;
						   		}
						   }
						}
					});
				}else{
					result=true;
				}
				if(!result){
					$.alert("请完成这一题","");
					return false;
				}
				return true;
			}
			
			jQuery(document).ready(function(){
				jQuery(".page${pcid}").hide();
				jQuery("#page${pcid}1").show();
				
				jQuery(".link${pcid }").click(function(){//控制题目跳转用
					var qid = $(this).attr("qid");
					var req = $(this).attr("requre");
					if(researchQuestionChecked(qid,req)){
						var index = $(this).attr("index");
						var tarindex = $(this).attr("tarindex");//
						if(tarindex > 0){
							jQuery(".page${pcid}").slideUp(1000);
							jQuery(".question${pcid}"+tarindex).slideDown(1000);
						}else{
							index++;
							jQuery(".page${pcid}").slideUp(1000);
							jQuery("#page${pcid}"+index).slideDown(1000);
						}
						$('html, body').animate({scrollTop:0}, 0);
					}
				});
				
				jQuery(".hydf").click(function(){//打分题用
					var count = $(this).attr("idx");
					var id = $(this).attr("id");
					var df = $("."+id);
					for(var i = 0 ; i < df.length ; i ++){
						if(i < count ){
							df[i].src='/images/hudong/diaoyan3_4/df_03.png';
						}else{
							df[i].src='/images/hudong/diaoyan3_4/df_05.png';
						}
					}
					$("#df_"+id).val(count);
				});
				
				jQuery("#sub_${pcid}").click(function(){//表单提交用
				var qid = $(this).attr("qid");
				var req = $(this).attr("requre");
				if(researchQuestionChecked(qid,req)){
					var json = getResult();
					jQuery.post("/${oname}/user/research.action",{"dto.answerStr":json,"dto.researchid":$("#researchid${pcid }").val(),"relationid":${blocks[1].rid},"pageid":${pageid}},function(data){
						hdCallBack(data,"N");
					});		
				}
				});
			});
			function getResult(){
				var pcid = '${pcid}';
				var radios = jQuery("#myform"+pcid+" input:radio:checked");
				var checkboxs = jQuery("#myform"+pcid+" input:checkbox:checked");
				var areas = jQuery("#myform"+pcid+" textarea");
				var hiddens = jQuery("#myform"+pcid+" input[type='hidden']");
				var result = '';
				for(var i=0,len=radios.length;i<len;i++){
					var o = radios[i].value;
					var qid = radios[i].name;
					qid = qid.substring(9,qid.length);
					result += qid+":"+o+";";
				}
				for(var i=0,len=checkboxs.length;i<len;i++){
					var o = checkboxs[i].value;
					var qid = checkboxs[i].name;
					qid = qid.substring(9,qid.length);
					result += qid+":"+o+";";
				}
				for(var i=0,len=hiddens.length;i<len;i++){
					var o = hiddens[i].value;
					var qid = hiddens[i].name;
					qid = qid.substring(9,qid.length);
					result += qid+":"+o+";";
				}
				for(var i=0,len=areas.length;i<len;i++){
					var o = areas[i].value;
					var qid = areas[i].name;
					qid = qid.substring(9,qid.length);
					result += qid+":"+o+";";
				}
				return result;
			}
			
			
</script>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
