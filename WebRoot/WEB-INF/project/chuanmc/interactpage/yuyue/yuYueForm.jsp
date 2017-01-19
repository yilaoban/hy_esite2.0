<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<html>
  <head>
  	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="”width=device-width," initial-scale="1," maximum-scale="1″/" name="”viewport”">
    <title>预约、留言</title>
    
<script type="text/javascript">
	$(document).ready(function() {
		$("#catalog").change(function(){
		   $.post("/${oname}/user/findYuYueServicerListBycatid.action",{"catid":$("#catalog").val()},function(data){
				$("#servicer").html("");
				$("#servicer").append("<option value='-1'>选择医生</option>");
				$('#servicer').prev().children(":first").children(":first").text("选择医生");
				if(data.servicerList){
					$.each(data.servicerList, function(i, n){
						$("#servicer").append("<option value='"+n.id+"' id='s_"+n.id+"'>"+n.name+"</option>");
					});
				}
			});
			   
		});
		
		$.post("/${oname}/user/yuYueFrom.action",function(data){
			if(data.aptRecord){
				if(data.aptRecord.gender == "男"){
					$('input:radio[name="aptRecord.gender"]').eq(0).attr("checked",true);
				}else if(data.aptRecord.gender == "女"){
					$('input:radio[name="aptRecord.gender"]').eq(1).attr("checked",true);
				}
				$('#phone').val(data.aptRecord.phone);
				$('#name').val(data.aptRecord.name);
			}
		});
		
		var kv = '${kv}';
		var kk = kv.split("-");
		var catid = 0;
		var servicerid = 0;
		if(kk.length == 3){
			catid = kk[0];
			var type = kk[1];
			if(type == "U"){
				servicerid = kk[2];
			}
			if(type == "S"){
				var serviceid = kk[2];
				$('#serviceid').val(serviceid);	
			}
			$('#type').val(type);
		}
				
		if(catid > 0){
			$.post("/${oname}/user/findYuYueCatalogById.action",{"catid":catid},function(data){
				$("#catalog").html("");
				if(data.yuYueCatalog){
					$("#catalog").append("<option value='"+data.yuYueCatalog.id+"' id='c_"+data.yuYueCatalog.id+"'>"+data.yuYueCatalog.name+"</option>");
				}
			});
		}else{
			$.post("/${oname}/user/findCatelog.action",function(data){
				$("#catalog").html("");
				$("#catalog").append("<option value='-1'>选择科室</option>");
				$('#catalog').prev().children(":first").children(":first").text("选择科室");
				if(data.catalogList){
					$.each( data.catalogList, function(i, n){
						if(n.id == catid){
							$("#catalog").append("<option value='"+n.id+"' id='c_"+n.id+"'  selected='selected'>"+n.name+"</option>");
						}else{
							$("#catalog").append("<option value='"+n.id+"' id='c_"+n.id+"'>"+n.name+"</option>");
						}
					});
				}
			});
		}
		
		
		$.post("/${oname}/user/findCatelog.action",function(data){
			$("#formcat").html("");
			if(data.catalogList){
				$.each( data.catalogList, function(i, n){
					if(i%3 == 0){
						$('#splitNum').val(n.id);
						$('#formcat').append("<tr><td id='0_"+n.id+"'><a href='/${oname}/user/show/wxn/"+n.id+".html'>"+n.name+"</a></td><td id='1_"+n.id+"'></td><td id='2_"+n.id+"'></td></tr>");
					}else{
						var splitNum = $('#splitNum').val();
						var num = i%3 + "_" + splitNum;
						$('#' + num).append("<a href='/${oname}/user/show/wxn/"+n.id+".html'>"+n.name+"</a>");
					}
				});
			}
		});
		
		
		if(catid > 0){
			if(servicerid > 0){
				$.post("/${oname}/user/findYuYueServicerById.action",{"catid":catid,"serid":servicerid},function(data){
					$("#servicer").html("");
					if(data.yuYueServicer){
						$("#servicer").append("<option value='"+data.yuYueServicer.id+"' id='s_"+data.yuYueServicer.id+"'>"+data.yuYueServicer.name+"</option>");
					}
				});
			}else{
				$.post("/${oname}/user/findYuYueServicerListBycatid.action",{"catid":catid},function(data){
					$("#servicer").html("");
					$("#servicer").append("<option value='-1'>选择医生</option>");
					$('#servicer').prev().children(":first").children(":first").text("选择医生");
					if(data.servicerList){
						$.each(data.servicerList, function(i, n){
							$("#servicer").append("<option value='"+n.id+"' id='s_"+n.id+"'>"+n.name+"</option>");
						});
					}
				});
			}
		}
		
	});

	 function sub(){
	 	var gender=$('input:radio[name="aptRecord.gender"]:checked').val();
	 	var name = $('#name').val().trim();
	 	var phone = $('#phone').val().trim();
	 	if(gender != "男" && gender != "女"){
	 		alert("请选择性别");
	 		return;
	 	}
	 	if(name == ""){
	 		alert("请填写姓名");
	 		return;
	 	}
	 	if(phone == ""){
	 		alert("请填写电话");
	 		return;
	 	}else{
	 		var re = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
			if(!re.test(phone)){
				alert("请填写正确的电话");
		 		return;
			}
	 	}
	 	var catalog = $('#catalog').val();
	 	if(catalog == -1){
	 		alert("请选择科室");
	 		return;
	 	}
	 	var pageid=$("#hy_pageid").val();
	 	pageid = 21;
	 	$('#pageid').val(pageid);
		$.ajax({ 
			cache: true,
			type: "POST",
			url:"/${oname}/user/saveYuYueRecord.action", 
			data:$('#myform').serialize(),
			async: false, 
			error: function(request) { alert("连接服务器失败!"); }, 
			success: function(data) {
				alert(data.desc);
				if(data.status == 0){
					window.history.back();
				}
			} 
		});
	}

</script>
  </head>
  <body> 
    <!--导航-->  
    <!-- CSS reset -->  
    <!-- Resource style -->  
    <!-- Modernizr -->  
    <!--导航-->  
    <header class="cd-main-header"> 
      <div class="home">
        <a href="/${oname}/user/show/39372.html">
          <img src="http://img.huiyee.com/site/2162/2361/images/home.png">
        </a>
      </div>  
      <div class="er_position">预约留言</div>  
      <ul class="cd-header-buttons"> 
        <li style="color:#fff;">
          <a href="/" style="color:#fff;">中文</a> | 
          <a href="/en" style="color:#fff;">EN</a>
        </li>  
        <li>
          <a class="cd-nav-trigger" href="#cd-primary-nav">Menu
            <span></span>
          </a>
        </li> 
      </ul>  
      <!-- cd-header-buttons --> 
    </header>  
    <main class="cd-main-content"> 
      <div style="clear:both;"></div>  
      <div class="yuyue"> 
        <div class="yuyue_title">填写预约表:</div>  
        <div class="yuyue_con">
          <p>请填写一下表格预约明德医院相关科室，如不清楚可以选择未知科室。</p>  
          <p>在您提交预约请求后，科室的医务人员将为您安排就诊。</p>  
          <p>标
            <font style="color:#F00">*</font> 的字段微必填。
          </p>
        </div>  
        <form action="" method="get" id="myform"> 
         <input type="hidden" value="${pageid }" name="pageid" id="pageid">
         <input type="hidden" value="0" id="serviceid">
         <input type="hidden" value="N" id="type">
          <table border="0" cellpadding="0" cellspacing="0" class="yuyue_box" width="100%">
            <tbody>
              <tr>
                <td>称呼：
                  <font style="color:#F00">*</font>
                </td>
              </tr>
              <tr>
                <td>
                  <input name="aptRecord.gender" type="radio" value="男"> 男士    
                  <input name="aptRecord.gender" type="radio" value="女">女士
                </td>
              </tr>
              <tr>
                <td>姓名：
                  <font style="color:#F00">*</font>
                </td>
              </tr>
              <tr>
                <td>
                  <input class="yuyue_input" name="aptRecord.name" type="text" id="name">
                </td>
              </tr>
              <tr>
                <td>电话：
                  <font style="color:#F00">*</font>
                </td>
              </tr>
              <tr>
                <td>
                  <input class="yuyue_input" name="aptRecord.phone" type="text" id="phone">
                </td>
              </tr>
              <tr>
                <td>科室：
                  <font style="color:#F00">*</font>
                </td>
              </tr>
              <tr>
                <td>
                  <select class="yuyue_select" name="catid" size="1" id="catalog">
                    <option>外科</option>
                    <option>全科</option>
                    <option>内科</option>
                    <option>儿科</option>
                    <option>耳鼻咽喉科</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td>首选医生：</td>
              </tr>
              <tr>
                <td>
                  <select class="yuyue_select" name="serid" size="1" id="servicer">
                    <option>医生1</option>
                    <option>医生2</option>
                    <option>医生3</option>
                    <option>医生4</option>
                    <option>医生5</option>
                  </select>
                </td>
              </tr>
              <tr>
                <td>首选日期：
                  <font style="color:#F00">*</font>
                </td>
              </tr>
              <tr>
                <td>
                  <input class="yuyue_input" id="appDateTime" name="yytime" readonly="readonly" type="text" value="2016-01-27 15:42:02">
                </td>
              </tr>
              <tr>
                <td>其他说明:(症状或需求描述)</td>
              </tr>
              <tr>
                <td>
<textarea class="yuyue_textarea" cols="" name="hydesc" rows=""></textarea>
                </td>
              </tr>
            </tbody>
          </table>  
          <div style="clear:both"></div>  
          <div class="yuyue_tijiao">
            <a href="javascript:void(0);" onclick="sub()">提交</a>
          </div> 
        </form> 
      </div>  
      <!--<div class="cd-overlay"></div>--> 
    </main>  
    <nav class="cd-nav"> 
      <ul class="cd-primary-nav is-fixed" id="cd-primary-nav"> 
        <li>
          <a href="/${oname}/user/show/39372.html">首页</a>
        </li>  
        <li class="has-children"> 
          <a href="#">关于明德</a>  
          <ul class="cd-secondary-nav is-hidden"> 
            <li class="go-back">
              <a href="#0">返回</a>
            </li>  
            <li class="see-all">
              <a href="/${oname}/user/show/39380.html">明德宗旨</a>
            </li>  
            <li class="see-all"> 
              <a href="/${oname}/user/show/39373.html">logo释义</a>
            </li>  
            <li class="see-all"> 
              <a href="/${oname}/user/show/39368.html">明德文化</a> 
            </li>  
            <li class="see-all"> 
              <a href="/${oname}/user/show/39371.html">明德团队</a>
            </li> 
          </ul> 
        </li>  
        <li class="has-children"> 
          <a href="#">客户服务</a>  
          <ul class="cd-secondary-nav is-hidden"> 
            <li class="go-back">
              <a href="#0">返回</a>
            </li>  
            <li class="see-all">
              <a href="/${oname}/user/show/39369.html">客服团队</a>
            </li>  
            <li class="see-all"> 
              <a href="/${oname}/user/show/39377.html">客户隐私</a>
            </li>  
            <li class="see-all"> 
              <a href="/${oname}/user/show/39374.html">国际服务</a> 
            </li>  
            <li class="see-all"> 
              <a href="#">贴心问候</a>
            </li>  
            <li class="see-all"> 
              <a href="/${oname}/user/show/39365.html">客户故事</a> 
            </li>  
            <li class="see-all"> 
              <a href="#">联系客服</a>
            </li> 
          </ul> 
        </li>  
        <li class="has-children"> 
          <a href="#">会员计划</a>  
          <ul class="cd-secondary-nav is-hidden"> 
            <li class="go-back">
              <a href="#0">返回</a>
            </li>  
            <li class="see-all">
              <a href="#">会员权益</a>
            </li>  
            <li class="see-all"> 
              <a href="#">会员服务</a>
            </li>  
            <li class="see-all"> 
              <a href="#">会员产品</a> 
            </li> 
          </ul> 
        </li>  
        <li class="has-children"> 
          <a href="#">加入明德</a>  
          <ul class="cd-secondary-nav is-hidden"> 
            <li class="go-back">
              <a href="#0">返回</a>
            </li>  
            <li class="see-all">
              <a href="#">员工文化</a>
            </li>  
            <li class="see-all"> 
              <a href="#">福利待遇</a>
            </li>  
            <li class="see-all"> 
              <a href="#">岗位信息</a> 
            </li> 
          </ul> 
        </li>  
        <li class="has-children"> 
          <a href="#">微信/微博/app</a>  
          <ul class="cd-secondary-nav is-hidden"> 
            <li class="go-back">
              <a href="#0">返回</a>
            </li>  
            <li class="see-all">
              <a href="#">微信</a>
            </li>  
            <li class="see-all"> 
              <a href="#">微博</a>
            </li>  
            <li class="see-all"> 
              <a href="#">APP</a> 
            </li> 
          </ul> 
        </li>  
        <!--<li><a href="#" >个人中心</a></li>--> 
      </ul>  
      <!-- primary-nav --> 
    </nav>  
    <!-- cd-nav -->  
    <div style="clear:both;"></div>  
    <!-- begin-bottom
     -->
<!-- end-bottom --> 
  </body>
</html>
<table border="0" cellpadding="0" cellspacing="0" class="index_keshi" style="margin:0 auto" width="90%">
	 <input type="hidden" id="splitNum">
                <tbody id="formcat">
                  <tr>
                    <td>
                      <a href="/${oname}/user/show/39431.html">外科</a>
                    </td>
                    <td>
                      <a href="/${oname}/user/show/39431.html">全科</a>
                    </td>
                    <td>
                      <a href="/${oname}/user/show/39431.html">内科</a>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <a href="/${oname}/user/show/39431.html">妇产科</a>
                    </td>
                    <td>
                      <a href="/${oname}/user/show/39431.html">儿科</a>
                    </td>
                    <td>
                      <a href="/${oname}/user/show/39431.html">中医科</a>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <a href="/${oname}/user/show/39431.html">药房</a>
                    </td>
                    <td> </td>
                    <td> </td>
                  </tr>
                </tbody>
              </table> 