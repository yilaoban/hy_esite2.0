<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function delRule(id) {
		var layerid = layer.confirm('确定删除吗?', {
			title : "确认删除"
		}, function() {
			$.post("/${oname}/setting/delUserLevel.action", {
				inajax : 1,
				levelid : id
			}, function(data) {
				if (data == 0) {
					layer.msg('删除失败！请重试……', {
						icon : 5,
						time : 2000
					});
				} else {
					layer.msg('删除中！请稍等……', {
						icon : 6,
						time : 1500
					}, function() {
						window.location.reload();
					});
				}
			});
		});
	}
</script>

<div class="wrap_content">
  <ul class="c_switch">
    <li>
      <a href="/${oname}/setting/rmbRule.action">充值规则</a>
    </li>
    <li class="selected">
      <a href="/${oname}/setting/userLevel.action">会员等级</a>
    </li>
    <li>
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

  <div class="toolbar mt20">
    <div class="toolbar pb10">
      <a href="/${oname }/setting/preuserLevel.action" class="btn btn-primary">设置</a>
      <a href="/${oname }/setting/addUserLevel.action?hylevel=${balanceSet.hylevel}" class="btn btn-primary">新增等级</a>
    </div>
  </div>
  <table width="100%" class="tb_normal" border="1" cellspacing="1" cellpadding="1">
    <thead>
      <tr>
        <th>名称</th>
        <th>图片</th>
        <th>
          <s:if test='balanceSet.hylevel == 1'>单次充值额度</s:if>
          <s:elseif test='balanceSet.hylevel == 2'>累积充值额度</s:elseif>
          <s:elseif test='balanceSet.hylevel == 3'>消费额度</s:elseif>
          <s:elseif test='balanceSet.hylevel == 4'>验证码</s:elseif>
        </th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="levelList" var="l">
        <tr>
          <td>${l.name}</td>
          <td>
            <img src="${l.img}" height="67" width="67">
          </td>
          <td>
            <s:if test="balanceSet.hylevel == 1">${l.dannum/100}元</s:if>
            <s:elseif test="balanceSet.hylevel == 2">${l.totalnum/100}元	</s:elseif>
            <s:elseif test="balanceSet.hylevel == 3">${l.usednum/100}元</s:elseif>
            <s:elseif test="balanceSet.hylevel == 4">
              <a href="/${oname}/setting/userLevelCode.action?code.levelid=${l.id}">详情</a>
            </s:elseif>
          </td>
          <td>
            <a href="/${oname }/setting/editUserLevel.action?levelid=${l.id}&hylevel=${balanceSet.hylevel}">编辑</a>
            <a href="javascript:delRule('${l.id}')">删除</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>

</div>