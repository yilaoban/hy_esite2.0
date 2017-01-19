<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 我要赚钱-首页 -->
<link href="/css/cb/partner.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="top0608">
	<div class="touxiang"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></div>
	<p class="name">${sessionScope.visitUser.wxUser.nickname}</p>
</div>
<div class="main0608_1">
	<ul> <s:if test='method == "E"'>
		<li><a href="#">
			<div class="left_1"><img src="/images/cb/pic0608_3.png" alt=""></div>
			<div class="right_1"><p class="p_1">立即参加</p><p class="p_2"><span>1111</span>人已参与</p></div>
		</a></li>
        <li><a href="#">
			<div class="left_1"><img src="/images/cb/pic0608_4.png" alt=""></div>
			<div class="right_1"><p class="p_1">立即参加</p><p class="p_2"><span>1111</span>人已参与</p></div>
		</a></li>
		<li><a href="#">
			<div class="left_1"><img src="/images/cb/pic0608_5.png" alt=""></div>
			<div class="right_1"><p class="p_1">立即参加</p><p class="p_2"><span>1111</span>人已参与</p></div>
		</a></li>
              <li><a href="#">
			<div class="left_1"><img src="/images/cb/pic0608_6.png" alt=""></div>
			<div class="right_1"><p class="p_1">立即参加</p><p class="p_2"><span>1111</span>人已参与</p></div>
		</a></li>
		 </s:if>
	</ul>
</div>

<!-- 
<div class="top0526">
  <div class="touxiang">
    <img src="${sessionScope.visitUser.wxUser.headimgurl}" alt="">
  </div>
  <p class="name">${sessionScope.visitUser.wxUser.nickname}</p>
</div>
<div class="main0526_1">
  <ul>
    <s:if test='method == "E"'>
      <li>
        <a href="#">
          <div class="left_1">
            <img src="/images/cb/pic0526_3.png" alt="">
          </div>
          <div class="right_1">
            <p class="p_1">立即参加</p>
            <p class="p_2">
              <span>1111</span>
              人已参与
            </p>
          </div>
        </a>
      </li>
      <li>
        <a href="#">
          <div class="left_1">
            <img src="/images/cb/pic0526_4.png" alt="">
          </div>
          <div class="right_1">
            <p class="p_1">进行中</p>
            <p class="p_2">
              <span>1111</span>
              人已参与
            </p>
          </div>
        </a>
      </li>
      <li>
        <a href="#">
          <div class="left_1">
            <img src="/images/cb/pic0526_5.png" alt="">
          </div>
          <div class="right_2">
            <p class="p_1">已完成</p>
            <p class="p_2">
              <span>1111</span>
              人已参与
            </p>
          </div>
        </a>
      </li>
    </s:if>
  </ul>
</div>
 -->
<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
  <ul>
    <li class="xz1">
      <a href="${blocks[0].link1}">
        <span>${blocks[0].title1}</span>
      </a>
    </li>
    <li class="wxz2">
      <a href="${blocks[0].link2}">
        <span>${blocks[0].title2}</span>
      </a>
    </li>
    <li class="wxz3">
      <a href="${blocks[0].link3}">
        <span>${blocks[0].title3}</span>
      </a>
    </li>
  </ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>

<script type="text/javascript" src="/js/jquery-weui/js/jquery-weui.js"></script>
<script type="text/javascript" src="/js/mustache.js"></script>
<script type="text/javascript">
	var loading = false;
	var pageId = 1;

	$(document).ready(function() {
		if ("${method}" == "E") {
			return;
		}

		$(".main0608_1>ul").empty();
		load();

		$(document.body).infinite().on("infinite", function() {
			if (loading) {
				return;
			}

			loading = true;
			load();
		});
	});

	function load() {
		var html = '';
		html += '<li>';
		html += '  <a href="/${oname}/user/cb_activity.action?source=t-${sessionScope.visitUser.wxUser.id}-{{id}}">';
		html += '    <div class="left_1"><img src="${imgDomain}{{img}}"></div>';
		html += '    <div class="right_1{{^active}} right_2{{/active}}">';
		html += '      <p class="p_1">{{a_status}}</p>';
		html += '      <p class="p_2"><span>{{shared_num}}</span>人参与</p>';
		html += '    </div>';
		html += '  </a>';
		html += '</li>';

		$.ajax({
			url : "/${oname}/user/cb_activities.action",
			type : "get",
			data : {
				"pageId" : pageId
			},
			cache : false,
			success : function(res) {
				if (res.status == 0) {
					var list = res.list;
					if (list.length > 0) {
						for (var i = 0; i < list.length; i++) {
							$(".main0608_1>ul").append(Mustache.render(html, list[i]));
						}
					} else {
						$(document.body).destroyInfinite();
						$(".weui-infinite-scroll").hide();
					}

					pageId = res.pageId + 1;
				} else {
					window.location.href = res.rs;
				}
				loading = false;
			}
		});
	}
</script>
