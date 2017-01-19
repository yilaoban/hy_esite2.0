<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
  <ul class="c_switch">
    <li>
      <a href="/${oname}/setting/rmbRule.action">充值规则</a>
    </li>
    <li>
      <a href="/${oname}/setting/userLevel.action">会员等级</a>
    </li>
    <li class="selected">
      <a href="/${oname}/setting/userRmb.action">用户</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_print.action">打印模板</a>
    </li>
    <li>
      <a href="/${oname}/setting/user_xf_desc.action">收银系统</a>
    </li>
     <li>
          <a href="/${oname}/setting/user_oc_xf.action">消费积分</a>
        </li>
  </ul>
  <p class="clearfix"></p>

  <form action="/${oname}/setting/userRmb.action" method="post" id="myform" style="padding: 10px;">
    <div>
      <label>会员号:</label>
      <input type="text" name="hyuid" value="<s:if test='hyuid>0'>${hyuid}</s:if>" />
      <label>用户昵称:</label>
      <input type="text" name="nickname" value="${nickname}">
    </div>
    <div>
      <label>类型:</label>
      <select name="type">
        <option value="0" <s:if test='type == 0'>selected="selected"</s:if>>全部</option>
        <option value="5" <s:if test='type == 5'>selected="selected"</s:if>>充值用户</option>
        <option value="6" <s:if test='type == 6'>selected="selected"</s:if>>消费用户</option>
        <option value="7" <s:if test='type == 7'>selected="selected"</s:if>>未充值用户</option>
        <option value="8" <s:if test='type == 8'>selected="selected"</s:if>>未消费用户</option>
      </select>
      <label>时间段：</label>
      <input id="startTime" type="text" value="<s:date name="startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="startStr" class="Wdate"
        onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
      <span>~</span>
      <input id="endTime" type="text" value="<s:date name="endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endStr" class="Wdate"
        onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
      <input type="button" onclick="doSearchUser()" value="搜索" class="btn btn-info btn-sm" />
    </div>
  </form>
  <table width="100%" class="tb_normal">
    <thead>
      <tr>
        <th>会员号</th>
        <th>姓名</th>
        <th>昵称</th>
        <th>会员等级</th>
        <th>余额(元)</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.userBalanceList" var="r">
        <tr>
          <td>${r.hyuser.id }</td>
          <td>${r.hyuser.username }</td>
          <td>${r.nickname }</td>
          <td>${r.hyuser.levelname }</td>
          <td>
            <a href="javascript:void(0)" onclick="showUserRmbDetail('${r.hyuid }')">${(r.rmbtotal - r.rmbused)/100}</a>
          </td>
          <td>
            <a href="javascript:void(0)" onclick="addUserRmb('${r.hyuid }')"> 充值</a>
            |
            <a href="javascript:void(0)" onclick="upUserLevel('${r.hyuid }')">提升会员</a>
            |
            <a href="javascript:void(0)" onclick="chooseSource('${r.hyuser.wxuid}')">邀请评价</a>
            |
            <a href="javascript:void(0)" onclick="showDetail('${r.hyuser.username}','${r.hyuser.gender}','${r.hyuser.telphone}','${r.hyuser.birthday}')">查看详情</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
  <%@include file="/WEB-INF/pagechip/pageBar.jsp"%>

</div>
<script type="text/javascript">
	function doSearchUser() {
		$('#myform').submit();
	}

	function showUserRmbDetail(hyuid) {
		var url = "/${oname}/setting/userRmbDetail.action?hyuid=" + hyuid + "&solrtype=RMB";
		layer.open({
			type : 2,
			area : [ '800px', '550px' ],
			title : '资金变动',
			content : url
		});
	}

	function addUserRmb(hyuid) {
		var url = "/${oname}/setting/addUserRmb.action";
		layer.prompt({
			title : '充值'
		}, function(value) {
			if (value == null || value == '') {
				return false;
			}
			$.post(url, {
				"hyuid" : hyuid,
				"jf" : value
			}, function(data) {
				if (data > 0) {
					layer.msg('充值成功!', {
						icon : 6,
						time : 1500
					}, function() {
						window.parent.location.reload();
					});
				}
			});
		});
	}

	function upUserLevel(hyuid) {
		var srcString = '/${oname}/setting/upUserLevel.action?hyuid=' + hyuid;
		var title = "修改会员等级";
		layer.open({
			type : 2,
			area : [ '450px', '235px' ],
			title : [ title, true ],
			content : srcString
		});
	}
	function showDetail(username, gender, telphone, birthday) {
		html = "";
		html += "<p>姓名:" + username + "</p>";
		if (gender == 1) {
			html += "<p>性别:男</p>";
		} else if (gender == 2) {
			html += "<p>性别:女</p>";
		}
		html += "<p>电话:" + telphone + "</p>";
		html += "<p>生日:" + birthday + "</p>";
		layer.alert(html);
	}

	function chooseSource(wxuid) {
		layer.open({
			type : 2,
			area : [ '800px', '600px' ],
			title : '选择二维码',
			content : '/${oname}/setting/user_offcheck_source.action?wxuid='+wxuid
		});
	}

	
</script>