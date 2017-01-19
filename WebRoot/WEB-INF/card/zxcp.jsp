<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 在线测评 -->
<link href="/css/hudong/zxcp/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/Sortable.min.js"></script>

<%@include file="/WEB-INF/card/background.jsp"%>
	
	
	<s:if test='blocks[0].display=="Y"'>
	<div class="dh_141217_2box block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050761167">
		<h1 hyvar="name" class_data="hy2015050761706">${blocks[0].name}</h1>
		<div class_data="hy2015050761461"><img src="${blocks[0].img}" class="m_pic_141217" hyvar="img" hydesc="150*150"></div>
	</div>
	</s:if>
	
	<s:if test='blocks[1].display=="Y"'>
	<div hyblk="I" class="block ${blocks[1].ctname}" status="0" hyct="Y" style="width:100%;top:120px;padding-top:20%;${blocks[1].hyct}" hydata="${blocks[1].rid}" class_data="hy2015050761613">
		<s:if test='blocks[1].obj.id > 0'>
			<s:set name="obj" value="blocks[1].obj" />
			<form action="/${oname}/user/exam.action" method="post" id="myform${pcid }" class_data="hy2015050761690">
			<s:iterator value="#obj.questions" var="q" status="st">
				<div id="page${pcid}${st.count}" class="page${pcid} dh_141217_2_box_c question${pcid}${q.id}" class_data="hy2015050761812">
				<span class_data="hy2015050761194">${st.count})、${q.title }</span>
					<s:if test='#q.type=="SGL"'>
						<div class="dh_141217_2_box_c_neirong" class_data="hy2015050761437">
						<ul class_data="hy2015050761316">
							<s:iterator value="#q.options" var="o" status="s">
								<li onclick="changetarget(${st.count },${o.target})" class_data="hy2015050761396">
									<input type="radio"  name="question_${q.id}" value="${o.id}" class="dy_sq_left_141217_1"/>
									<span class_data="hy2015050761411"><label class_data="hy2015050761615">${o.content }</label></span>
								</li>
							</s:iterator>
						</ul>
						</div>
					</s:if>
					
					<s:if test='#q.type=="MUP"'>
						<div class="dh_141217_2_box_c_neirong" class_data="hy2015050761621">
						<ul class_data="hy2015050761827">
							<s:iterator value="#q.options" var="o" status="s">
								<li class_data="hy2015050761836">
								<input  type="checkbox"  name="question_${q.id}" value="${o.id}" class="dy_sq_left_141217_1" />
								<span class_data="hy2015050761387"><label class_data="hy2015050761204">${o.content }</label> </span>
								</li>
							</s:iterator>
						</ul>
						</div>
					</s:if>
					
					<s:if test='#q.type=="FIL"'>
						<div class="dh_141217_2_box_c_neirong" class_data="hy2015050761254">
						<textarea name="question_${q.id}" style="width:91%; line-height:30px;background:#fff; margin-top:10px; margin-left:5px; border:#d5d5d5 1px solid;"></textarea>
						</div>
					</s:if>
					
					<s:if test='#q.type=="GAD"'>
						<div class="dh_141217_2_box_c_neirong" class_data="hy2015050761661">
						<dd class_data="hy2015050761626"><img id="${pcid }${q.id }" idx="1" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050761484"><img id="${pcid }${q.id }" idx="2" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050761622"><img id="${pcid }${q.id }" idx="3" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050761975"><img id="${pcid }${q.id }" idx="4" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<dd class_data="hy2015050761658"><img id="${pcid }${q.id }" idx="5" class="hydf ${pcid }${q.id }" src="/images/hudong/diaoyan3_4/df_05.png" width="30px" height="auto" style="max-width: 100%;"/></dd>
						<input type="hidden" id="df_${pcid}${q.id}" name="question_${q.id}" value="0">
						</div>
					</s:if>
					<s:if test='#q.type=="ORD"'>
						<div class="dh_141217_2_box_c_neirong" class_data="hy2015050761382">
						<ul id="sort${pcid }${q.id }" class_data="hy2015050761969">
							<s:set name="size" value="#q.options.size"></s:set>
							<s:iterator value="#q.options" var="o" status="s">
								<li class_data="hy2015050761615">
									<span class_data="hy2015050761564"><label class_data="hy2015050761543">${o.content }</label></span>
									<input id="px${q.id }${o.id }" type="hidden" name="question_${q.id}" val="${o.id}" value="${o.id}-${s.count }">
								</li>
							</s:iterator>
						</ul>
						</div>
						<script type="text/javascript">
							new Sortable(sort${pcid}${q.id },{
								  onUpdate: function (evt){
								     var idx = jQuery(evt.item).index()+1;
								     var inputs = jQuery("#sort${pcid }${q.id } li input:hidden");
								     for(i = 0 ;i<inputs.length;i++){
								     	var oid = jQuery(inputs[i]).attr("val");
								     	$(inputs[i]).val(oid+"-"+(i+1));
								     }
								  }
								});
						</script>
				</s:if>
					<div style="clear:both"></div>
					<s:if test="#st.last">
						<div class="tj_anniu_141217_tk_1" id="sub_${pcid}" qid="${q.id }" requre="${q.isreq }" class_data="hy2015050761352">提交</div>
					</s:if>
					<s:else>
						<center><div class="link${pcid } tj_anniu_141217_tk_1" id="xyt_${pcid}${st.count}" index="${st.count }" qid="${q.id }" requre="${q.isreq }" class_data="hy2015050761944">下一题</div></center>
					</s:else>
				</div>
			</s:iterator>
			</form>
			<input type="hidden" id ="examid${pcid }" value="${obj.id }"/>
		</s:if>
		<s:else>
			<form action="" method="get" class_data="hy2015050761601">
				<div class="dh_141217_2_box_c" class_data="hy2015050761334">
		
					<span class_data="hy2015050761378">晚上，你在睡梦中醒来，发现自己和床一起飞了起来，飞了好久以后，你觉得你会落在哪里？</span>
		
					<div class="dh_141217_2_box_c_neirong" class_data="hy2015050761810">
		
						<ul hyblk="C" class_data="hy2015050761788">
		
						<!--这里是多选部分-->
						<li class_data="hy2015050761267"><input type="checkbox" name="" class="dy_sq_left_141217_1"/>
						<span hyvar="desc" class_data="hy2015050761501">落在了森林里</span></li>
		
						<li class_data="hy2015050761472"><input type="checkbox" name="" class="dy_sq_left_141217_1"/>
						<span hyvar="desc" class_data="hy2015050761181">落在湖面上</span></li>
		
						<li class_data="hy2015050761238"><input type="checkbox" name="" class="dy_sq_left_141217_1"/>
						<span hhyvar="desc" class_data="hy2015050761329">落在大楼上面</span></li>
		
						<li class_data="hy2015050761265"><input type="checkbox" name="" class="dy_sq_left_141217_1"/>
						<span hyvar="desc" class_data="hy2015050761718">落在法式古堡里</span></li>
						<!--这里是多选部分结束-->
						
						</ul>
					</div>
		
					<div style="clear:both"></div>
		
					<div class="tj_anniu_141217_tk_1" class_data="hy2015050761134">下一题</div>
		
				</div>
		
			</form>
		</s:else>
		<div class="clearfix"></div>
	</div>
	</s:if>
<script type="text/javascript">
	function changetarget(index,target){
		if(target>0){
			$("#xyt_${pcid}"+index).attr("tarindex",target);
		}
	}
			function examQuestionChecked(qid,req){//是否填写该题
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
					if(examQuestionChecked(qid,req)){
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
				if(examQuestionChecked(qid,req)){
					var json = getResult();
					jQuery.post("/${oname}/user/exam.action",{"dto.answerStr":json,"dto.examid":$("#examid${pcid }").val(),"relationid":${blocks[1].rid},"pageid":${pageid}},function(data){
						if(data.status == 1){
							window.location.href="/${oname}/user/show/${blocks[1].pageid}/${sessionScope.visitUser.source}/ce-hy-"+data.resultid+".html";
						}else{
							$.alert(data.hydesc,"");
						}
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
