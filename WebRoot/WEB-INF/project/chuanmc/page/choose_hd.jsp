<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/card.js"></script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">模块编辑</h4>
</div>
<div class="modal-body">
	<input type="radio" name="hd_type" value="N" checked="checked">新建<br>
	<input type="radio" name="hd_type" value="M">选择
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClik1(${dto.tb.fid })" data-dismiss="modal">保存</button>
</div>