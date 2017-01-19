<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微调研1 -->
<script type="text/javascript" src="/js/Sortable.min.js"></script>
<link href="/css/hudong/diaoyan3/list.css" rel="stylesheet" type="text/css" />
<link href="/css/hudong/diaoyan3_2/index.css" rel="stylesheet" type="text/css" />
<link href="/css/hudong/diaoyan3_3/index.css" rel="stylesheet" type="text/css" />
<link href="/css/hudong/diaoyan3_4/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
<div class="wdy_top block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050734313"><img src="${blocks[0].img}" width="100%" height="100%" hyvar="img" hydesc="720*300"/></div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="box_hudong_141112_xg_1 block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050734579">
	<s:if test='blocks[1].obj.id > 0'>
	<s:set name="obj" value="blocks[1].obj" />
	<form id="myform${pcid }" class_data="hy2015050734779">
		<s:iterator value="#obj.questions" var="q" status="st">
		<div id="page${pcid}${st.count}" class="page${pcid} box_hudong_141112_dx_1 question${pcid}${q.id}" class_data="hy2015050734750">
		<s:if test='#q.type=="SGL"'>
				<div class="dy_tatle_141112_dx_1" class_data="hy2015050734529">${st.count} 、${q.title }</div>
				<ul class_data="hy2015050734807">
					<s:iterator value="#q.options" var="o" status="s">
						<label class_data="hy2015050734690" style="display:block;">
						<li class="dy_wt_141112_dx_1" onclick="changetarget(${st.count },${o.target})" class_data="hy2015050734737">
							<input type="radio" name="question_${q.id}" value="${o.id}" class="dy_wt_left_141112_dx_1" />
							<span class_data="hy2015050734312">${o.content }</span>
						</li>
						</label> 
					</s:iterator>
				</ul>
		</s:if>
			
		<s:if test='#q.type=="MUP"'>
			<div class="dy_tatle_141112_xg_1" class_data="hy2015050734970">${st.count} 、${q.title }</div>
			<ul class_data="hy2015050734780">
				<s:iterator value="#q.options" var="o" status="s">
					<label class_data="hy2015050734252">
					<li class="dy_wt_141112_xg_1" class_data="hy2015050734110">
						<input type="checkbox" name="question_${q.id }" value="${o.id}" class="dy_wt_left_141112_xg_1" />
						<span class_data="hy2015050734107">${o.content } </span>
					</li>
					</label>
				</s:iterator>
			</ul>
		</s:if>
							
		<s:if test='#q.type=="FIL"'>
			<div class="dy_tatle_141112_tk_1" hyvar="title" class_data="hy2015050734552">${st.count}、${q.title }</div>
			<span class="dy_wt_141112_tk_1" class_data="hy2015050734641">
				<textarea name="question_${q.id}" style="width:90%; height:100px; margin:0 auto;"></textarea>
			</span>
		</s:if>
		
		<s:if test='#q.type=="GAD"'>
			<div class="dy_box_141114_dafen clearfix" class_data="hy2015050734505">
				<div class="dy_tatle_141112_tk_1" class_data="hy2015050734608">${st.count} 、${q.title }</div>
				<div class="box_dafen_fenzhi" class_data="hy2015050734413">
					<ul class_data="hy2015050734315"> 
					 <li class_data="hy2015050734987"><img id="${pcid }${q.id }" idx="1" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></li>
					 <li class_data="hy2015050734874"><img id="${pcid }${q.id }" idx="2" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></li>
					 <li class_data="hy2015050734528"><img id="${pcid }${q.id }" idx="3" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></li>
					 <li class_data="hy2015050734214"><img id="${pcid }${q.id }" idx="4" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></li>
					 <li class_data="hy2015050734990"><img id="${pcid }${q.id }" idx="5" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></li>
					</ul>
					<input type="hidden" id="df_${pcid}${q.id}" name="question_${q.id}" value="0">
				</div>
			</div>
		</s:if>
		
		<s:if test='#q.type=="ORD"'>
				<div class="dy_tatle_141112_dx_1" class_data="hy2015050734336">${st.count} 、${q.title }</div>
				<ul id="sort${pcid }${q.id }" class_data="hy2015050734706">
					<s:iterator value="#q.options" var="o" status="s">
						<li class="dy_wt_141112_dx_1" class_data="hy2015050734567">
						<span class_data="hy2015050734914"><label class_data="hy2015050734882">${o.content }</label> </span>
						<input id="px${q.id }${o.id }" type="hidden" name="question_${q.id}" val="${o.id}" value="${o.id}-${s.count }">
						</li>
					</s:iterator>
				</ul>
				<script type="text/javascript">
					new Sortable(sort${pcid}${q.id },{
						  onUpdate: function (evt){
						     var inputs = jQuery("#sort${pcid }${q.id } li input:hidden");
						     for(i = 0 ;i<inputs.length;i++){
						     	var oid = $(inputs[i]).attr("val");
						     	$(inputs[i]).val(oid+"-"+(i+1));
						     }
						  }
						});
				</script>
		</s:if>
		
		<s:if test="#st.last">
			  <div class="tj_anniu_141112_xg_1" id="sub_${pcid}" qid="${q.id }" requre="${q.isreq }" class_data="hy2015050734241">提交</div>
		</s:if>
		<s:else>
			<center><div class="link${pcid } tj_anniu_141112_xg_1" id="xyt_${pcid}${st.count}" index="${st.count }" qid="${q.id }" requre="${q.isreq }" class_data="hy2015050734215">下一题</div></center>
		</s:else>
		</div>
		</s:iterator>
		</form>
		<input type="hidden" id ="researchid${pcid }" value="${obj.id }"/>
	</s:if>
	<s:else>
		<div class_data="hy2015050734417">
		<ul class_data="hy2015050734576">
			<div class="dy_tatle_141112_xg_1" hyvar="title" class_data="hy2015050734774">1.请问你喜欢什么样的智能手机？</div>
				<a><li class="dy_wt_141112_xg_1" class_data="hy2015050734690">
				<input name="" type="checkbox" value=""  class="dy_wt_left_141112_xg_1"/>
				<span hyvar="name" class_data="hy2015050734396">苹果（Apple）</span>
				</li></a>

				<a><li class="dy_wt_141112_xg_1" class_data="hy2015050734236">
				<input type="checkbox" name="radio" class="dy_wt_left_141112_xg_1"/>
				<span hyvar="name" class_data="hy2015050734700">三星（samsung）</span>
				</li></a>

				<a><li class="dy_wt_141112_xg_1" class_data="hy2015050734185">
				<input name="" type="checkbox" value=""  class="dy_wt_left_141112_xg_1"/>
				<span hyvar="name" class_data="hy2015050734186">锤子（Smartisan T1）</span>
				</li></a>

				<a><li class="dy_wt_141112_xg_1" class_data="hy2015050734563">
				<input name="" type="checkbox" value=""  class="dy_wt_left_141112_xg_1"/>
				<span hyvar="name" class_data="hy2015050734442">小米（MI）</span>
				</li></a>

				<a><li class="dy_wt_141112_xg_1" class_data="hy2015050734567">
				<input name="" type="checkbox" value=""  class="dy_wt_left_141112_xg_1"/>
				<span hyvar="name" class_data="hy2015050734646">联想（Lenovo）</span>
				</li></a>
		</ul>
		<div class="tj_anniu_141112_xg_1" class_data="hy2015050734141">下一题</div>
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
