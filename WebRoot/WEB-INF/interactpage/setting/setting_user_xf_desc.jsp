<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" media="screen,print" href="/js/jqprint/print.css" />
<div class="wrap_content">
  <ul class="c_switch">
    <li>
      <a href="/${oname}/setting/rmbRule.action">充值规则</a>
    </li>
    <li>
      <a href="/${oname}/setting/userLevel.action">会员等级</a>
    </li>
    <li>
      <a href="/${oname}/setting/userRmb.action">用户</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_print.action">打印模板</a>
    </li>
    <li class="selected">
      <a href="/${oname}/setting/user_xf_desc.action">收银系统</a>
    </li>
     <li>
       <a href="/${oname}/setting/user_oc_xf.action">消费积分</a>
     </li>
  </ul>
  <div class="toolbar clearfix">
    <a href="${pageDomain}/${oname}/user/cashierlogin.action" target="_blank" class="btn btn-success">进入收银系统</a>
    <button class="btn btn-primary" onclick="add()">添加商品</button>
  </div>
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>商品</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.xdList" var="x">
        <tr>
          <td>${x.name}</td>
          <td>
            <a href="javascript:;" onclick="edit('${x.id}','${x.name}')">编辑</a>
            |
            <a href="/${oname}/setting/user_xf_zk.action?xfid=${x.id}">折扣</a>
            |
            <a href="javascript:;" onclick="del('${x.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
</div>
<%@include file="/WEB-INF/page/includeAppSites.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function add() {
		layer.prompt({
			value : '',
			title : '消费备注'
		}, function(value, index, elem) {
			$.ajax({
				url : "/${oname}/setting/user_xf_desc_add.action",
				type : "post",
				data : {
					"xd.name" : value
				},
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("添加成功", function() {
							window.location.reload();
						});
					}
				}
			});
		});
	}

	function edit(id, name) {
		layer.prompt({
			value : name,
			title : '消费备注'
		}, function(value, index, elem) {
			$.ajax({
				url : "/${oname}/setting/user_xf_desc_upd.action",
				type : "post",
				data : {
					"xd.id" : id,
					"xd.name" : value
				},
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("修改成功", function() {
							window.location.reload();
						});
					} else {
						layer.alert("修改失败,请查看是否已存在该备注");
					}
				}
			});
		});
	}

	function del(id) {
		layer.confirm('确定删除该备注吗？', function(index) {
			$.ajax({
				url : "/${oname}/setting/user_xf_desc_del.action",
				type : "post",
				data : {
					"xd.id" : id
				},
				cache : false,
				success : function(res) {
					if (res > 0) {
						layer.alert("删除成功", function() {
							window.location.reload();
						});
					}
				}
			});
		});
	}

	
</script>