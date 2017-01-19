<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <div class="toolbar pb10">
    <a href="/${oname}/interact/createMarketActivity.action?type=0" class="btn btn-primary">新增H5活动</a>
    <a href="/${oname}/interact/createMarketActivity.action?type=1" class="btn btn-primary">新增新闻活动</a>
  </div>
  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>标题</th>
        <th>类型</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.cbActivityList" var="f">
        <tr align="center">
          <td>${f.title}</td>
          <td>
            <s:if test="#f.type == 0">H5</s:if>
            <s:elseif test="#f.type == 1">新闻</s:elseif>
          </td>
          <td>
            <s:date name="starttime" format="yyyy-MM-dd" />
          </td>
          <td>
            <s:date name="endtime" format="yyyy-MM-dd" />
          </td>
          <td>
            <s:if test="#f.status == 0">未发布</s:if>
            <s:elseif test="#f.status == 1">已发布</s:elseif>
          </td>
          <td>
            <a href="/${oname}/interact/editMarketActivity.action?aid=${f.id}">编辑</a>
            |
            <a href="javascript:;" onclick="checkMoney('${f.id}')">预算</a>
            |
            <s:if test="#f.status == 0">
              <a href="javascript:;" onclick="cbActivityPublish('${f.id}',1)">发布</a>
              |
            </s:if>
            <s:elseif test="#f.status == 1">
              <a href="javascript:;" onclick="cbActivityNoPublish('${f.id}',0)">下线</a>
              |
            </s:elseif>
            <a href="/${oname}/interact/senderEffect.action?cbaid=${f.id}">效果</a>
            |
            <a href="javascript:;" onclick="delMarketActivity('${f.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<script type="text/javascript">
	function editMarketActivity(id) {
		var srcString = '/${oname}/interact/editMarketActivity.action?cbid=${cbid}&aid=' + id;
		var title = "编辑活动";
		layer.open({
			type : 2,
			area : [ '600px', '500px' ],
			title : [ title, true ],
			content : srcString
		});
	}

	function delMarketActivity(id) {
		var srcString = '/${oname}/interact/delMarketActivity.action';
		var layerid = layer.confirm('确定删除?', {
			icon : 2
		}, function() {
			$.post(srcString, {
				"aid" : id
			}, function(data) {
				if (data == "Y") {
					layer.msg('删除中！请稍等……', {
						icon : 6,
						time : 1500
					}, function() {
						window.parent.location.reload();
					});
				} else {
					layer.msg('删除失败！请重试……', {
						icon : 5,
						time : 2000
					});
				}
			});
		});
	}

	function checkMoney(aid) {
		var srcString = '/${oname}/interact/checkMoney.action?aid=' + aid;
		var title = "提交审核";
		layer.open({
			type : 2,
			area : [ '450px', '400px' ],
			title : [ title, true ],
			content : srcString
		});
	}

	function cbActivityPublish(aid, status) {
		var srcString = '/${oname}/interact/cbActivityPublish.action';
		var layerid = layer.confirm('确定发布?', {
			icon : 3
		}, function() {
			$.post(srcString, {
				"aid" : aid,
				"status" : status
			}, function(data) {
				if (data == "Y") {
					layer.msg('发布中！请稍等……', {
						icon : 6,
						time : 1500
					}, function() {
						window.location.reload();
					});
				} else {
					layer.msg('发布失败！请重试……', {
						icon : 5,
						time : 2000
					});
				}
			});
		});
	}

	function cbActivityNoPublish(aid, status) {
		var srcString = '/${oname}/interact/cbActivityPublish.action';
		var layerid = layer.confirm('确定取消发布?', {
			icon : 3
		}, function() {
			$.post(srcString, {
				"aid" : aid,
				"status" : status
			}, function(data) {
				if (data == "Y") {
					layer.msg('取消发布中！请稍等……', {
						icon : 6,
						time : 1500
					}, function() {
						window.location.reload();
					});
				} else {
					layer.msg('取消发布失败！请重试……', {
						icon : 5,
						time : 2000
					});
				}
			});
		});
	}

	
</script>