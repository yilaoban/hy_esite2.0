<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 九宫格 -->
<link href="/css/hudong/lattice/sfindex.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="nav0427 block" status="0" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct}">
    <ul>
        <li><a href="javascript:void(0);" onclick="introduce()">${blocks[1].title1}</a></li>
        <li><a href="javascript:void(0);" onclick="guizhe()">${blocks[1].title2}</a></li>
        <li><a href="javascript:void(0);" onclick="zhongjiang()">${blocks[1].title3}</a></li>
    </ul>
    <span><em><img src="/images/hudong/lattice/pic_5.png" alt="">今日还剩<i id="chance"></i>次机会</em></span>
</div>
<div class="middle0428 block" status="0" hyct="Y" hydata="${blocks[0].rid}" style="${blocks[0].hyct}">
    <div id="lottery">
    	<s:set name="obj" value="blocks[0].obj"></s:set>
        <table border="0" cellpadding="0" cellspacing="0">
    	<s:if test="#obj.id > 0">
            <tr class="lottery-group">
                <td class="lottery-unit td_1 active" >
                	<s:if test='#obj.lotteryPrizes[0].img !=null && #obj.lotteryPrizes[0].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[0].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_1.png" />
                	</s:else>
                </td>
                <td class="lottery-unit td_2">
                	<s:if test='#obj.lotteryPrizes[1].img !=null && #obj.lotteryPrizes[1].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[1].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_5.png" />
                	</s:else>
                </td>
                <td class="lottery-unit td_3">
                	<s:if test='#obj.lotteryPrizes[2].img !=null && #obj.lotteryPrizes[2].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[2].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_3.png" />
                	</s:else>
                </td>
            </tr>
            <tr class="lottery-group">
                <td class="lottery-unit td_4">
                	<s:if test='#obj.lotteryPrizes[7].img !=null && #obj.lotteryPrizes[7].img !="" '>
	                	<img src="${imgDomain }${obj.lotteryPrizes[7].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_4.png" />
                	</s:else>
                </td>
                <td class="td_5"><a href="#"></a></td>
                <td class="lottery-unit td_6">
                	<s:if test='#obj.lotteryPrizes[3].img !=null && #obj.lotteryPrizes[3].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[3].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_6.png" />
                	</s:else>
                </td>
            </tr>
            <tr class="lottery-group">
                <td class="lottery-unit td_7">
                	<s:if test='#obj.lotteryPrizes[6].img !=null && #obj.lotteryPrizes[6].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[6].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_7.png" />
                	</s:else>
                </td>
                <td class="lottery-unit td_8">
                	<s:if test='#obj.lotteryPrizes[5].img !=null && #obj.lotteryPrizes[5].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[5].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_1.png" />
                	</s:else>
                </td>
                <td class="lottery-unit td_9">
                	<s:if test='#obj.lotteryPrizes[4].img !=null && #obj.lotteryPrizes[4].img !=""'>
	                	<img src="${imgDomain }${obj.lotteryPrizes[4].img }" />
                	</s:if>
                	<s:else>
                		<img src="/images/hudong/lattice/wenzi_9.png" />
                	</s:else>
    	</s:if>
    	<s:else>
    		<tr class="lottery-group">
                <td class="lottery-unit td_1 active" ><img src="/images/hudong/lattice/wenzi_8.png" /></td>
                <td class="lottery-unit td_2"><img src="/images/hudong/lattice/wenzi_3.png" /></td>
                <td class="lottery-unit td_3"><img src="/images/hudong/lattice/wenzi_4.png" /></td>
            </tr>
            <tr class="lottery-group">
                <td class="lottery-unit td_4"><img src="/images/hudong/lattice/wenzi_5.png" /></td>
                <td class="td_5"><a href=""></a></td>
                <td class="lottery-unit td_6"><img src="/images/hudong/lattice/wenzi_6.png" /></td>
            </tr>
            <tr class="lottery-group">
                <td class="lottery-unit td_7"><img src="/images/hudong/lattice/wenzi_7.png" /></td>
                <td class="lottery-unit td_8"><img src="/images/hudong/lattice/wenzi_1.png" /></td>
                <td class="lottery-unit td_9"><img src="/images/hudong/lattice/wenzi_9.png" /></td>
            </tr>
    	</s:else>
        </table>
    </div>
    
     <div class="gonggao">
            <h1><img src="/images/hudong/lattice/pic_6.png"></h1>        
                    <div id="scrollDiv">
                        <ul id="scrollList">
                        	<li><i>落***花</i><em>沙发院线会员1年</em></li>
                            <li><i>开***心</i><em>沙发院线会员3个月</em></li>
							<li><i>落***花</i><em>沙发院线会员1年</em></li>
                            <li><i>开***心</i><em>沙发院线会员3个月</em></li>
                        </ul>
                    </div>
        </div>
</div>

<div class="dialog_form" style="display:none;">
	<table class="info">
		<tr><td style="width:50px;">姓名</td><td><input class="weui-prompt-input" type="text" style="width:90%" id="username"/></td></tr>
		<tr><td style="width:50px;">电话</td><td><input id="userphone" class="weui-prompt-input" type="tel" style="width:90%" /></td></tr>
		<tr><td style="width:50px;">地址</td><td><input class="weui-prompt-input" type="text" style="width:90%" id="userlocation" ></td></tr>
	</table>
</div>

<div id="modal" style="position:fixed;top:0;left:0;right:0;bottom:0;width:100%;height:100%;background:#000;opacity:0.7;z-index:1001;display:none;"></div>
<div id="modal-layer" style="position:fixed;top:0;left:0;right:0;bottom:0;width:100%;height:100%;z-index:1002;display:none;">
<div id="modal-content">
	<div class="modal-head">
		<div class="modal-top"><span class="modal-close"></span></div>
		<div class="modal-title">活动规则</div>
	</div>
	<div class="modal-body">
		
	</div>
</div>
</div>
<script type="text/javascript" src="/js/hudong/lattice/lottery.js"></script>
<script type="text/javascript">

	$(function(){ 
	    //抽奖机会
		$.post("/${oname}/user/lotteryRemain.action",{"lid":'${obj.id}',"pageid":'${pageid}'},function(data){
			$('#chance').html(data.tnum);
		});
	    
	})
	
	findlotteryUserList();
	function findlotteryUserList(){
		if('${method}' =='E'){
			return;
		}
		$.post("/${oname}/user/findPrizeWinnerInfo.action",{"lid":'${obj.id}',"pageid":'${pageid}'},function(data){
			if(data){
				$('#scrollList').empty();
				$.each(data,function(i,n){
					var nickname ="";
					if(n.nickname){
						if(n.nickname.length > 1){
							nickname = n.nickname[0]+"***"+n.nickname[n.nickname.length-1];
						}else{
							nickname = n.nickname;
						}
					}
					$('#scrollList').prepend("<li><i>"+nickname+"</i><em>"+n.name+"</em></li>");
				});
			}
		});
		
		if($("#scrollList li").length >4){
		   // setInterval('autoScroll("#scrollDiv")',1000) ;
		   autoScroll("#scrollDiv");
		}
	}
	setInterval('findlotteryUserList()',3000) ;
	
	function autoScroll(obj){ 
	    $(obj).find("#scrollList").animate({ 
	        marginTop : "-31px" 
	    },500,function(){ 
	        $(this).css({marginTop : "0px"}).find("li:first").appendTo(this); 
	    }) 
	} 

	
	var prizename,status,link,target;
	var run=0;
	var lurid=0;
	var tnumLess;
	$(document).ready(function(){
        lottery.lottery({
            selector:     '#lottery',
            width:        3,
            height:       3,
            index:        0,    // 初始位置
            initSpeed:    500,  // 初始转动速度
             upStep:       50,   // 加速滚动步长
             upMax:        50,   // 速度上限
             downStep:     50,   // 减速滚动步长
             downMax:      500,  // 减速上限
            waiting:      3000, // 匀速转动时长
            beforeRoll: function () { // 重写滚动前事件：beforeRoll
            	if(run != 0)return;
				run =1;
            	$.ajax({
        			url:"/${oname}/user/lottery_draw.action",
        			dataType:"json",
        			async:false,
        			data:{	"lid":'${obj.id}',
        				"pageid":'${pageid}',
        				"random":Math.random()
        			},
        			success:function(data){
        				if(data != null){
        					target = 0;
        					status = data.status;
        					tnumLess= data.tnum;
        					if(status < 0){
				            	lottery.options.canStart=false;
        						run =0;
        						$.alert(data.hydesc,"");
        						return;
        					}else if(status == 1){
        						prizename=data.lotteryPrize.jf;
        					}else if(status==2){
        						var sb = "";
        						if(data.lotteryPrizeCode.code != null && data.lotteryPrizeCode.code != ""){
        							sb += "<br>券号："+data.lotteryPrizeCode.code;
	        						if(data.lotteryPrizeCode.password != null && data.lotteryPrizeCode.password != ""){
	        							sb += "<br>密码："+data.lotteryPrizeCode.password;
	        						}
        						}
        						prizename="恭喜您中了"+data.lotteryPrize.name+sb;
        					}else if(status ==3){
        						link=data.lotteryPrize.link;
        						prizename=data.lotteryPrize.name;
        						lurid=data.lotteryUserRecordid;
        					}else if(status ==4){
        						prizename=data.lotteryPrize.hprice/100;
        					}else if(status==5){
        						prizename = data.hydesc;
        					}else if(status ==6){    //自提商品
								prizename ="恭喜您中奖!请留意微信信息!";
							}
        					if(data.lotteryPrize && data.lotteryPrize.positionid){
	        					target = data.lotteryPrize.positionid;
        					}
        				}else{
        					$.alert("data is null ?","");
        					return false;
        				}
        			}
        		})
            },
			aim: function () {
				this.options.target = target;
				console.log(this.options.target);
			},
			stop:function(){
				run =0;
				$('#chance').html(tnumLess);
				if(status == 0){
					$.alert("谢谢参与!","");
				}else if(status == 1){
					$.alert("恭喜您获得"+prizename+"金币","");
				}else if(status == 2){
					$.alert(prizename,"");
				}else if(status==4){
					$.alert("恭喜您获得"+prizename+"元红包","");
				}else if(status == 5){
					$.alert(prizename,"");
				}else if(status==6){
					$.alert(prizename,"");
				}else if(status==3){
					var html = $(".dialog_form").html();
					$(".dialog_form").empty();
					if(link){
						$.alert2("恭喜您中了"+prizename,"",function(){window.location.href=link;});
					}else{
						$.alert2("恭喜您中了"+prizename,"",function(){
							$.modal({
							  title:"",
							  text: html,
							  buttons: [
							    { text: "取消", className: "default", onClick: function(){$.closeModal(); $(".dialog_form").append(html)}},
							    { text: "确认", onClick: function(){ userCheck(html); }},
							  ],
							  autoClose: false
							});
						});
					}
				}
			}

        });
       
	})
	
	function userCheck(html){  
	  	//实物保存
		var username = $("#username").val();
		var userphone = $("#userphone").val();
		var userlocation = $("#userlocation").val();
		if(userphone == ""){
			$("#userphone").css("border-color","red");
			return false;
		}
		$.closeModal();
		$(".dialog_form").append(html);
		$.post("/${oname}/user/zhuanpan_winner.action",{"dto.lotteryUser.lurid":lurid,"dto.lotteryUser.username":username,"dto.lotteryUser.userphone":userphone,"dto.lotteryUser.userlocation":userlocation},function(data){
			if(data=="Y"){
				$.alert('保存成功!','');
			}
		});
	}
  
 	 function guizhe(){
 	 	var title ="${blocks[1].title2}";
		var desc ="${blocks[1].desc2}";
		 $('.modal-title').html(title);
		 $('.modal-body').html(desc);
         $("#modal").show();
         $("#modal-layer").show();
         var contentH = $("#modal-content").height(); 
         $("#modal-content").css("margin-top",(500-contentH)/2);
	}
	
	function introduce(){
		var title ="${blocks[1].title1}";
		var desc ="${blocks[1].desc1}";
		$('.modal-title').html(title);
		 $('.modal-body').html(desc);
        $("#modal").show();
        $("#modal-layer").show();
        var contentH = $("#modal-content").height(); 
        $("#modal-content").css("margin-top",(500-contentH)/2);
		
	}
	
	function zhongjiang(){
     	var html = "";
   		$.post("/${oname}/user/lottery_prize_winner.action",{"lid":'${obj.id}',"pageid":'${pageid}'},function(data){
		if(data.length > 0){
			 for(var i=0; i<data.length; i++){
				if(data[i].status == 3){
					html = html +  "<p>恭喜您获得"+data[i].name + ";";
					if(data[i].link){
						html = html + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class='weui_btn weui_btn_mini weui_btn_plain_primary' style='line-height:1.3;' href='"+data[i].link+"'>去领奖</a>";
					}
				}else if(data[i].status == 2){
					html = html +"<p>恭喜您获得"+data[i].name +"<br/>券号:" +data[i].code + "<br/>密码："+data[i].password +"";
				}else if(data[i].status == 1){
					html = html +"<p>恭喜您获得"+data[i].jf + "金币;";
				}else{
					html = html +  "<p>恭喜您获得"+data[i].name + ";";
				}
			 }
            $('.modal-body').html("").append(html);
            $('.modal-title').html("中奖纪录");
			$("#modal").show();
            $("#modal-layer").show();
            var contentH = $("#modal-content").height(); 
            $("#modal-content").css("margin-top",(500-contentH)/2);
            
		}else{
			 $.alert("亲，您还未中过奖哦");
		}	
		});
	}
	
	
	$("#modal-layer").click(function(){
		$("#modal-layer").hide();
		$("#modal").hide();
	})
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
