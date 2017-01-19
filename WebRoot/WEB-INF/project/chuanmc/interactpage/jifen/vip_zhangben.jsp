<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />
<title>消费列表页</title>
<link href="/css/mycards/reset.css" rel="stylesheet" type="text/css" />
<link href="/css/mycards/index_list.css" rel="stylesheet" type="text/css" />
<link href="/css/mycards/index_wu2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/plug-in/scroll/pullToRefresh.css" />
<script src="/plug-in/scroll/iscroll.js"></script>
<script src="/plug-in/scroll/pullToRefresh_div.js"></script>
<script src="/js/mustache.js" type="text/javascript"></script>
<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		  refresher.init({
	            id: "wrapper",
	            pullDownAction: Refresh,
	            pullUpAction: Load1
	        });
	        var generatedCount = 2;
	        
	        function Refresh() {
	        	wrapper.refresh();
	        }
	        loadFrist();
			function loadFrist(){
				$.post("/${oname}/user/searchRecordBySolr.action",{"solrtype":"${solrtype}"},function(data){
		    		if(data&&data.list.length>0){
		    			var html = '';
	    				html +='{{#list}}';
	    				html +='<li>{{#mc}}<img src="/images/mycards/pic_2924.png"><em><b>+<i>{{{scoreYuan}}}</i>元</b>{{/mc}}{{^mc}}<img src="/images/mycards/pic_2924.png"><em><b>-<i>{{{scoreYuan}}}</i>元</b>{{/mc}}<small>{{desc}}</small></em><span>{{createStr}}</span></li>';
						html += '{{/list}}';
						$("#tb").append(Mustache.render(html, data));
					} else {
						$(".wu0503_2").show();
						$(".list0504").remove();
					}
				});
			}

			function Load1() {
				setTimeout(function() {
					$.post("/${oname}/user/searchRecordBySolr.action", {
						"pageId" : generatedCount,
						"solrtype" : "${solrtype}"
					}, function(data) {
						if (data) {
							var html = '';
							html += '{{#list}}';
							html += '<li>{{#mc}}<img src="/images/mycards/pic_2924.png"><em><b>+<i>{{{scoreYuan}}}</i>元</b>{{/mc}}{{^mc}}<img src="/images/mycards/pic_2924.png"><em><b>-<i>{{{scoreYuan}}}</i>元</b>{{/mc}}<small>{{desc}}</small></em><span>{{createStr}}</span></li>';
							html += '{{/list}}';
							$("#tb").append(Mustache.render(html, data));
							++generatedCount;
							wrapper.refresh();
						}
					});

				}, 1000);
			}

		});
	</script>
<body>
  <div class="list0504" id="wrapper">
    <div>
      <ul id="tb">
      </ul>
    </div>
  </div>
  <div class="wu0503_2" style="display: none">
    <span>
      <img src="/images/mycards/pic_2922.png">
    </span>
    <span>暂无账单信息!</span>
  </div>
</body>
</html>