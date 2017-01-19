<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑签到规则</a></li>
	  </ul>
	  <a href="/${oname }/interact/index.action?mid=10008" class="return" title="返回"></a>
	</div>
  <form action="checkinEditSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview jNice">
  	<input type="hidden" name="ownerwbuid" value="${ownerwbuid }"/>
  	<input type="hidden" name="utype" value="${utype }"/>
  	 <dl>
	 	<dt>初次签到得分</dt>
		<dd>
			<input type="text" class="text-medium"  name="addnum" value="${dto.checkin.addnum }" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>连续签到增加积分</dt>
		<dd>
			<input type="text" class="text-medium"  name="addaddnum" value="${dto.checkin.addaddnum }" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>最多连续签到天数</dt>
		<dd>
			<input type="text" class="text-medium"  name="maxday" value="${dto.checkin.maxday }" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
 	<dl>
	 	<dt></dt>
		<dd>
			<input type="submit" value="提交">
		</dd>
	</dl>
 </form>
 </div>