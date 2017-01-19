<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/easyDialog/easydialog.min.js"></script>
<link href="/js/easyDialog/easydialog.css" rel="stylesheet" type="text/css" />
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
  <table class="tb_normal" width="100%">
    <thead>
      <tr>
        <th>会员等级</th>
        <th>折扣</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <s:iterator value="dto.levelList" var="ll">
        <tr>
          <td>${ll.name}</td>
          <td>
           	百分之<input type="text" value="${ll.hyUserXfZk.zk}" id="zk_${ll.id}">
          </td>
          <td>
          	<a href="javascript:void(0);" onclick="savezk(${ll.id},${xfid})">保存</a>
          </td>
        </tr>
      </s:iterator>
    </tbody>
  </table>
</div>
<script type="text/javascript">
	function savezk(levelid,xfid){
		var zk = $('#zk_' + levelid).val();
		$.post("/${oname}/setting/savezk.action",{"levelid":levelid,"xfid":xfid,"zk":zk},function(data){
			if(data > 0){
				alertMsg("设置成功");
			}
		})
	}
	
	function alertMsg(msg) {
		easyDialog.open({
			container : {
				content : msg
			},
			autoClose : 1000
		});
	}
</script>