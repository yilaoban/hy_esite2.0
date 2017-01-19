<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">选择卡片</h4>
</div>
<div class="wrap_content">
<form action="/page/addCardSub.action" method="post">
<div>
<s:iterator value="dto.cards" var="c" status="st">
<label><img src="${imgDomain}c.bimg"><input type="radio" name="cardid" value="${c.id}" <s:if test="#st.index==0"> checked="checked"</s:if>>${c.name }</label>
<s:if test="#st.count%4==0"><br></s:if>
</s:iterator>
</div>
<input type="hidden" name="pageid" value="${pageid }">
<input type="hidden" name="fartherPageid" value="${fartherPageid }">
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <s:if test="dto.cards.size() > 0">
  <button type="button" class="btn btn-primary" onclick="sub(document.forms[0])" data-dismiss="modal">确定</button>
  </s:if>
</div>
