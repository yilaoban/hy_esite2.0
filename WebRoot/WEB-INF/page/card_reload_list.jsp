<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    <div class="inner">
      <div class="view-port">
        <div id="temp-slct" class="slider-container huge">
        	<s:iterator value="tDto.cardlist" var="c" status="st">
		          <div class="item">
		            <div class="img-block">
		            	<label>
		              		<img width="220px" src="${c.bimg }">
							<p><input type="radio" name="cardid" value="${c.id}" <s:if test="#st.index==0"> checked="checked"</s:if>>
		              		${c.name }</p>
						</label>
		            </div>
		          </div>    
			</s:iterator>
        </div>
      </div>

  

