<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">详情</h4>
</div>
<div class="modal-body">
	 <a href="/${oname}/bbs/microRecord.action?type=${type}&utype=${utype}&uid=${uid}" class="button" style="float: right">更多</a>
	 <s:iterator value="map" id="entry">  
        <p>
        	<s:property value="key"/>:<s:property value="value"/>
        </p>
       </s:iterator> 
	
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
