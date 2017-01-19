<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<html>
<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
<meta content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;" name="viewport">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="width=device-width," initial-scale="1," maximum-scale="1" name="viewport">
<link href="/res/2363/css/reset.css" rel="stylesheet" type="text/css">
<link href="/res/2363/css/index.css" rel="stylesheet" type="text/css">
<link href="/res/2363/css/style.css" rel="stylesheet">
<script src="/res/2363/js/modernizr.js"></script>
<script src="/res/2363/js/jquery.min.js" type="text/javascript"></script>
<script src="/res/2363/js/jquery.mobile.custom.min.js" type="text/javascript"></script>
<script src="/res/2363/js/main.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		if ("${catid}" == "1330") {
			$("#text").attr("placeholder", "保险");
		}
	});
	function search() {
		var text = $("#text").val().trim();
		if (text == "") {
			return;
		}
		window.location.href = "/${oname}/user/search.action?catid=${catid}&wd=" + text;
	}
</script>
<title>搜索结果页</title>
</head>
<body>
  <!--导航-->
  <!-- CSS reset -->
  <!-- Resource style -->
  <!-- Modernizr -->
  <!--导航-->
  <header class="cd-main-header">
    <div class="home">
      <a href="/${oname}/user/show/39426.html">
        <img src="http://img.huiyee.com/site/2162/2363/images/home.png">
      </a>
    </div>
    <div class="er_position">站内搜索</div>

    <!-- begin-header_erji -->
    <s:include value='public/header_erji.jsp' />
    <!-- end-header_erji -->

  </header>
  <main class="cd-main-content">
  <div style="clear: both;"></div>
  <div class="search">
    <form action="" method="get">
      <input id="text" class="qz_search" placeholder="医生、 科室、保险、套餐、新闻" type="text" value="${wd}" />
      <span onclick="search()">
        <img src="http://img.huiyee.com/site/2162/2363/images/search_14.png">
      </span>
    </form>
  </div>
  <div style="width: 100%; float: left; height: 30px;"></div>

  <s:iterator value="dto.docs" var="d">
    <s:if test='#d.type=="C" || #d.type=="S" || #d.type=="U"'>
      <div class="ys_final_top">
        <a href="${d.url}">
          <s:if test="#d.simgurl_s!=null">
            <img src="${d.simgurl_s}">
          </s:if>
        </a>
        <div class="ys_list_title">
          <a href="${d.url}">${d.titlecn}${d.titleen}</a>
        </div>
        <s:iterator value="#d.desccn" var="desc">
          <p>${desc}</p>
        </s:iterator>
        <s:iterator value="#d.descen" var="desc">
          <p>${desc}</p>
        </s:iterator>
        <s:iterator value="#d.cntcn" var="cnt">
          <p>${cnt}</p>
        </s:iterator>
        <s:iterator value="#d.cnten" var="cnt">
          <p>${cnt}</p>
        </s:iterator>
        <div class="ys_anniu">
          <ul>
            <li>
              <a href="${d.url}">预约/留言</a>
            </li>
            <li>
              <a href="#">在线咨询</a>
            </li>
          </ul>
        </div>
      </div>
    </s:if>
    <div class="tabBlock-pane">
      <ul>
        <li>
          <a href="${d.url}">
            <s:if test="#d.simgurl_s!=null">
              <img src="${d.simgurl_s}">
            </s:if>
          </a>
          <span>
            <a href="${d.url}" style="color: #db584e;">${d.titlecn}${d.titleen}</a>
          </span>
          <s:iterator value="#d.desccn" var="desc">
            <p>${desc}</p>
          </s:iterator>
          <s:iterator value="#d.descen" var="desc">
            <p>${desc}</p>
          </s:iterator>
          <s:iterator value="#d.cntcn" var="cnt">
            <p>${cnt}</p>
          </s:iterator>
          <s:iterator value="#d.cnten" var="cnt">
            <p>${cnt}</p>
          </s:iterator>
        </li>
      </ul>
    </div>
  </s:iterator> <%@include file="/WEB-INF/pagechip/pageBar.jsp"%> <!-- 
 <div class="ys_final_top">
    <a href="/${oname}/user/show/39421.html">
      <img src="http://img.huiyee.com/site/2162/2363/images/1308241307518281cvet.png">
    </a>
    <div class="ys_list_title">
      <a href="/${oname}/user/show/39421.html">聂源欣</a>
      <font>
        <a href="/${oname}/user/show/39421.html">外科医生</a>
      </font>
    </div>
    <span>
      <a href="/${oname}/user/show/39421.html">
        <img src="http://img.huiyee.com/site/2162/2363/images/gj_03.png">
      </a>
    </span>
    <p>
      <a href="/${oname}/user/show/39421.html">国籍：英国    语言：中|英文</a>
    </p>
    <div class="ys_anniu">
      <ul>
        <li>
          <a href="#">预约/留言</a>
        </li>
        <li>
          <a href="#">在线咨询</a>
        </li>
      </ul>
    </div>
  </div>
  <div class="sousuo_jg">
    <a href="#">某某某某保险公司</a>
  </div>
  <div class="tabBlock-pane">
    <ul>
      <li>
        <a href="/${oname}/user/show/39434.html">
          <img src="http://img.huiyee.com/site/2162/2363/images/hd_05.png">
        </a>
        <span>
          <a href="/${oname}/user/show/39434.html" style="color: #db584e;">市场活动市动活动活动1</a>
        </span>
        <p>
          <a href="/${oname}/user/show/39434.html">市场活动详情市场活动详情市场活动详情市场活动详情市场活...</a>
        </p>
      </li>
    </ul>
  </div> 
 
  <div class="sousuo_jg">
    <img src="http://img.huiyee.com/site/2162/2363/images/404.png">
    <a href="#">返回首页</a>
  </div>
   --> <!--<div class="cd-overlay"></div>--></main>


  <!-- begin-nav -->
  <s:include value='public/nav.jsp' />
  <!-- end-nav -->
  <div style="clear: both;"></div>
  <!-- begin-bottom -->
  <s:include value='public/bottom.jsp' />
  <!-- end-bottom -->

</body>
</html>