<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<script src="/js/jquery.swipe.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$.post("/${oname}/user/findHbRecordList.action",
		   function(data){
		   		if(data.status == -1 || data.status == -2){
		   			window.location.href = data.rs;
		   		}else if(data.status == 1){
					$("#content").html("");
					$("#pageId").val(data.pageId);
					$("#content").append("<tr style='background:#c6dbf1;'><td style='color:#666;'>提取时间</td><td>提取金额</td></tr>");
					$('#total').html(data.total);
					$('#used').html(data.balance);
					$.each( data.cbHbRecordList, function(i, n){
						$("#content").append("<tr><td class='table_left'>"+n.createtimeStr+"</td><td>"+n.money+"</td></tr>");
					});
		   		}
		   });
	});
	
	function getMoneyNum(){
		var url = "/${oname}/user/tixian.action";
		var num = $('#num').val().trim();
		if(num == ""){
			alert("请输入金额");
			return;
		}else{
			num = num * 100;
			$.post(url,{"num":num},function(data){
				if(data.status == -3){
					alert(data.rs);
				}else if(data.status == -1 || data.status == -2){
		   			window.location.href = data.rs;
		   		}else if(data.status == 1){
					alert("提取成功");
					window.location.reload();
		   		}
			});	             
		}
	}
	
</script>


<!-- 提取记录 -->
<link href="/css/cb/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<!--header开始
<div class="cb_header"><img src="/images/cb/tx.jpg"/> 用户名称</div>
-->
<div class="cb_header"><img src="${sessionScope.visitUser.wxUser.headimgurl}" width="60px">${sessionScope.visitUser.wxUser.nickname}</div>
<!--header结束-->
<div id="triangle-up"></div>

<div class="cb_title">
  <ul>
    <li><strong>总额</strong><br/>
    	￥<span id="total"></span></li>
    <li><strong>余额</strong><br/>
    	￥<span id="used"></span></li>
    <li style=" color:#159eff;">
     <span class="jlbutton" onclick="tiqu()">提取</span>
     </li>
      
  </ul>
</div>
<div class="box">
  <table width="90%" border="1" cellspacing="0" cellpadding="0" class="box_table" id="content">
  </table>
</div>
<input type="hidden" id="pageId" value="">
<!--bottom开始-->
<div style="width:100%; float:left; height:65px;"></div>
<s:if test='blocks[0].display=="Y"'>
<div class="cb_bottom block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[0].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:0;" hydata="${blocks[0].rid}"><a href="${blocks[0].link}"><img src="${blocks[0].img}"/><br/>${blocks[0].title}</a></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="cb_bottom block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[1].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:33.3%;" hydata="${blocks[1].rid}"><a href="${blocks[1].link}"><img src="${blocks[1].img}"/><br/>${blocks[1].title}</a></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="cb_bottom block ${blocks[2].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[2].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:66.6%;" hydata="${blocks[2].rid}"><a href="${blocks[2].link}" style="color:#0099ff;"><img src="${blocks[2].img}"/><br/>${blocks[2].title}</a></div>
</s:if>
<!--bottom结束-->

<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
			$('#content').swipe({
				up: function(){
					var pageid = $('#pageId').val();
					pageid = pageid + 1;
					$.post("/${oname}/user/findHbRecordList.action",{"pageid":pageid},
					   function(data){
					   		if(data.status == -1 || data.status == -2){
					   			window.location.href = data.rs;
					   		}else if(data.status == 1){
								$("#content").html("");
								$("#pageId").val(data.pageId);
								$("#content").append("<tr style='background:#c6dbf1;'><td style='color:#666;'>提取时间</td><td>提取金额</td></tr>");
								$('#total').html(data.cbSender.hbtotal);
								$('#used').html(data.balance);
								$.each( data.cbHbRecordList, function(i, n){
									$("#content").append("<tr><td class='table_left'>"+n.createtimeStr+"</td><td>"+n.money+"</td></tr>");
								});
					   		}
					   });
				},
				down: function(){
					var pageid = $('#pageId').val();
					pageid = pageid - 1;
					if(pageid == 0){
						pageid = 1;
					}
					$.post("/${oname}/user/findHbRecordList.action",{"pageid":pageid},
					   function(data){
					   		if(data.status == -1 || data.status == -2){
					   			window.location.href = data.rs;
					   		}else if(data.status == 1){
								$("#content").html("");
								$("#pageId").val(data.pageId);
								$("#content").append("<tr style='background:#c6dbf1;'><td style='color:#666;'>提取时间</td><td>提取金额</td></tr>");
								$('#total').html(data.cbSender.hbtotal);
								$('#used').html(data.balance);
								$.each( data.cbHbRecordList, function(i, n){
									$("#content").append("<tr><td class='table_left'>"+n.createtimeStr+"</td><td>"+n.money+"</td></tr>");
								});
					   		}
					   });
				}
			});
			
			function tiqu(){
				$('#myJLModal').modal();
			}
		</script>
<div class="modal fade" id="myJLModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="position:absolute;top:20%;">
   <div class="modal-dialog" style="width:300px;">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               提取金额
            </h4>
         </div>
         <div class="modal-body">
            <input style="border:0;" type="text" name="num" placeholder="输入提取金额" id="num" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">元
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" onclick="getMoneyNum()">
              确定
            </button>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>