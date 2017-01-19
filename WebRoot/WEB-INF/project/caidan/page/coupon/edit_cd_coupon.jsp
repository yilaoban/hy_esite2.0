<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	//实例化编辑器
	 $(document).ready(function() {  
			var um = UE.getEditor('myEditor');
			var um2 = UE.getEditor('myEditor1');
			
			getway();
			
			$('select[name="coupon.caid"]').each(function(){
				var catalog=$(this).val();
				var waySel=$(this).next("select");
				var oldWay=$(this).next("select").val();
		    	 if(catalog>0){
		    		 $.post("/${oname}/cd-coupon/getWay.action",{"catalog":catalog},function(data){
		    			 	$(waySel).empty();
							$.each(data,function(index,value){
								if(oldWay==value.id){
									$(waySel).append("<option value='"+value.id+"' selected='selected'>"+value.name+"</option>");			
								}else{
									$(waySel).append("<option value='"+value.id+"'>"+value.name+"</option>");			
								}
							});
		    		 });
		    	 }else{
		    		 $(waySel).empty();
		    		 $(waySel).append("<option value='-1'>-请选择-</option>");	;
		    	 }
			}); 
			
        }); 
        
     function checkSub(){
     	var title = $.trim($('#title').val());
     	var wayid=$("#wayid").val();
     	if(title == "" || title == null){
     		$("#title_").html("请输入名称").css("color", "RED");
     		window.location.hash="maodian";
			return false;
     	}
     	if(wayid==-1){
     		layer.msg('请选择渠道', {icon: 5, time: 2000});
			return false;
     	}
		$("#form1").submit();
	}
     
     var getway=function(){
			$('select[name="coupon.caid"]').change(function(){
				var catalog=$(this).val();
				var waySel=$(this).next("select");
		    	 if(catalog>0){
		    		 $.post("/${oname}/cd-coupon/getWay.action",{"catalog":catalog},function(data){
		    			 	$(waySel).empty();
							$.each(data,function(index,value){
								$(waySel).append("<option value='"+value.id+"'>"+value.name+"</option>");			
							});
		    		 });
		    	 }else{
		    		 $(waySel).empty();
		    		 $(waySel).append("<option value='-1'>-请选择-</option>");	;
		    	 }
				
			});
		}
     
     function addcata(){
     	var html="";
     	html+="<dl class='waycata'><dt>&nbsp;</dt>";
     	html+="<dd>";
     	html+="<select name='coupon.caid'>";
 		html+=$('select[name="coupon.caid"]:first').html();
     	html+='</select>';
 		html+='<select name="coupon.wayid"><option value="-1">-请选择分类-</option>	</select></dd></dl>';
     	$(".waycata :last").after(html);
     	getway();
     	
     }
</script>
<a name="maodian"></a> 
<div  class="wrap_content">
<form action="/${oname }/cd-coupon/editSub.action" method="post" enctype="multipart/form-data" id="form1" class="formview">
<input type="hidden" value="${dto.coupon.product.id }" name="product.id"/>
<input type="hidden" value="${dto.coupon.product.catid }" name="product.catid"/>
<input type="hidden" value="${dto.coupon.product.type }" name="product.type"/>
<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div id="contentTag">
		<script type="text/javascript">
		$("#contentTag").load('/${oname}/cd-news/tags.action?pid=${dto.coupon.product.id}&type=T');	
		</script>
	</div>
	<dl>
	 	<dt><span id="title_" class="must">*</span>名称</dt>
		<dd>
			<input type="text" class="text-long" name="product.name" value="${dto.coupon.product.name }" id="title"/>
		</dd>
	</dl>
	<dl>
	 	<dt>小图片</dt>
		<dd>
			<s:if test="dto.coupon.product.simgurl!=null">
				<span id="hsimg" style="display: none"><input type="file" name="simg" id="fsimg" /></span>
				<img id="simg" src="${imgDomain }${dto.coupon.product.simgurl }" height="80">
				<input type="hidden" name="product.simgurl" value="${dto.coupon.product.simgurl }">
				<a id="simgtext" href='javascript:imgchange("simg")'>我要上传</a>
			</s:if>
			<s:else>
				<input type="file" name="simg"/>
			</s:else>		
		</dd>
		<dd>
			建议尺寸 320*267
		</dd>
	</dl>
	<dl>
	 	<dt>大图片</dt>
		<dd>
			<s:if test="dto.coupon.product.bimgurl!=null">
				<span id="hbimg" style="display: none"><input type="file" name="bimg" id="fbimg" /></span>
				<img id="bimg" src="${imgDomain }${dto.coupon.product.bimgurl }"  height="100">
				<input type="hidden" name="product.bimgurl" value="${dto.coupon.product.bimgurl }">
				<a id="bimgtext" href='javascript:imgchange("bimg")'>我要上传</a>
			</s:if>
			<s:else>
				<input type="file" name="bimg" id="fbimg" />
			</s:else>		
		</dd>
		<dd>
			建议尺寸 637*277
		</dd>
	</dl>
	<dl>
	 	<dt>类别</dt>
		<dd>
			<label class="forradio"><input type="radio" <s:if test='dto.coupon.product.subtype == "C"'>checked="checked"</s:if> name="product.subtype" value="C">外部券</label>
			<label class="forradio"><input type="radio" <s:if test='dto.coupon.product.subtype == "K"'>checked="checked"</s:if> name="product.subtype" value="K">内部券</label>
			<label class="forradio"><input type="radio" <s:if test='dto.coupon.product.subtype == "S"'>checked="checked"</s:if> name="product.subtype" value="S">无券码</label>
		</dd>
	</dl>
	<dl>
	 	<dt>链接地址</dt>
		<dd>
			<input type="text" class="text-long" name="product.linkurl" placeholder="地址请以http://开头" value="${dto.coupon.product.linkurl }"/>
		</dd>
	</dl>
	<dl>
	 		<dt>原需金币</dt>
	 		<dd>
			<input type="text" class="text-long" name="product.price" value="${dto.coupon.product.price }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
			</dd>
	</dl>
	<dl>
		 	<dt>现需金币</dt>
		 	<dd>
			<input type="text" class="text-long" name="product.salesprice" value="${dto.coupon.product.salesprice }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
			</dd>
	</dl>
	<dl>
	 	<dt>产品总数</dt>
		<dd>
			<input type="text" class="text-long" name="product.total" value="${dto.coupon.product.total }"  onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>已使用数</dt>
		<dd>
			<input type="text" class="text-long" name="product.used" value="${dto.coupon.product.used }"  onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<!-- 
	<dl>
	 	<dt>展示使用数</dt>
		<dd>
			<input type="text" class="text-medium" name="product.shownum" value="${dto.coupon.product.shownum }" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	-->
	<s:if test="dto.coupon.ways.size>0">
		<s:iterator value="dto.coupon.ways" var="w" status="st">
		<dl  class="waycata">
		 	<dt><s:if test="#st.first">选择渠道</s:if><s:else>&nbsp;</s:else></dt>
			<dd>
				<select name="coupon.caid">
					<s:iterator value="dto.catalogs" var="c">
						<option value="${c.id }" <s:if test="#c.id==#w.caid">selected="selected"</s:if>>${c.name }</option>
					</s:iterator>
				</select>
				<select name="coupon.wayid">
						<option value="${w.id }" selected="selected">${w.name }</option>
				</select>
				<s:if test="#st.first"><a onclick="addcata()"><b>+</b>更多渠道</a></s:if>
			</dd>
		</dl>
		</s:iterator>
	</s:if>
	<s:else>
		<dl class="waycata">
	 	<dt>选择渠道</dt>
		<dd>
			<select name="coupon.caid">
				<option value="-1">-请选择分类-</option>
				<s:iterator value="dto.catalogs" var="c">
				<option value="${c.id }">${c.name }</option>
				</s:iterator>
			</select>
			<select name="coupon.wayid">
				<option value="-1">-请选择渠道-</option>
			</select>
			<a onclick="addcata()"><b>+</b>更多渠道</a>
		</dd>
	</dl>
	</s:else>
	<dl>
	 	<dt>抵扣现金</dt>
		<dd>
			<input type="text" class="text-medium" name="coupon.rmb" value="${dto.coupon.rmb }" onkeyup="this.value = this.value.replace(/\D/g,'');">(单位:分)
		</dd>
	</dl>
	<dl>
	 	<dt>彩票站可获佣金</dt>
		<dd>
			<input type="text" class="text-medium" name="coupon.zdrmb" value="${dto.coupon.zdrmb }" onkeyup="this.value = this.value.replace(/\D/g,'');">(单位:分)
		</dd>
	</dl>
	<dl>
	 	<dt>县级代理可获佣金</dt>
		<dd>
			<input type="text" class="text-medium" name="coupon.xjrmb" value="${dto.coupon.xjrmb }" onkeyup="this.value = this.value.replace(/\D/g,'');">(单位:分)
		</dd>
	</dl>
	<dt>兑换券有效期至</dt>
		<dd>
			<input type="text" name="coupon.endtime" class="Wdate" value="<s:date name="dto.coupon.endtime" format="yyyy-MM-dd HH:mm:ss"/>"   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<script type="text/plain" id="myEditor" name="product.tag" style="width:100%;height:200px;">${dto.coupon.product.tag }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>详情</dt>
		<dd>
			<script type="text/plain" id="myEditor1" name="product.detail" style="width:100%;height:200px;">${dto.coupon.product.detail }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>提交审核</dt>
		<dd>
			<input type="radio" name="product.status" value="DSH" <s:if test='dto.coupon.product.status=="DSH"'>checked="checked"</s:if>>是&nbsp;
			<input type="radio" name="product.status" value="EDT" <s:if test='dto.coupon.product.status=="EDT"'>checked="checked"</s:if>>下次编辑&nbsp;
			<input type="radio" name="product.status" value="CMP" <s:if test='dto.coupon.product.status=="CMP"'>checked="checked"</s:if>>直接发布
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" onclick="checkSub()" value="确定"/>
		</dd>
	</dl>
</form>
</div>