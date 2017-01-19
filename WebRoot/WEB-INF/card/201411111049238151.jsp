<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表4 -->
<link href="/css/liebiao/15/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top2 block ${blocks[0].ctname}" status="0" hyct="Y" style="width:320px;left:0;${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy2015050729936">
	<img src="${blocks[0].img}" width="100%" hyvar="img" hydesc="720*300"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="block ${blocks[1].ctname}" status="0" hyct="Y" style="top:133px;width:100%;${blocks[1].hyct};" hyblk="I" hydata="${blocks[1].rid}" class_data="hy2015050729808">
	<s:if test='blocks[1].list.size == 0'>
	   <a href="http://www.huiyee.com" target="_blank" hyvar="link">
            <div class="ul01" class_data="hy2015050729345">
                 <div class="ul01_left" class_data="hy2015050729343">
                     <img src="/images/liebiao/15/wz_05_13.png" width="11" height="95">
                 </div>
                 <div class="ulmain" class_data="hy2015050729118">
                     <div class="ul01_cont" class_data="hy2015050729907">
                          <div class="circle" class_data="hy2015050729733">
                               <div class="imgchange" class_data="hy2015050729199"><img src="/images/liebiao/15/tu1.jpg" hyvar="img"  hydesc="75*75"></div>
                          </div><!-- end of circle 圆内容-->
                          <div class="ul01_cont_nr" class_data="hy2015050729749">
                              <h4 hyvar="title" class_data="hy2015050729655">国外创意家居设计</h4>
                              <p hyvar="text" class_data="hy2015050729260">灯具设计-其他-其他设计... </p>
                          </div>
                   </div>
              </div>
                 <div class="ul01_right" class_data="hy2015050729212">
                    <img src="/images/liebiao/15/wz_05_16.png" width="11" height="95">
                 </div>
                 <div class="arrow_right" class_data="hy2015050729437"><img src="/images/liebiao/15/right_01n.png"></div>	
                 <div class="clr"></div>
            </div>
        </a>
        <a href="#" target="_blank" hyvar="link">
            <div class="ul01" class_data="hy2015050729230">
                 <div class="ul01_left" class_data="hy2015050729178">
                     <img src="/images/liebiao/15/wz_05_013.png" width="11" height="95">
                 </div>
                 <div class="ulmain" class_data="hy2015050729447">
                     <div class="ul01_cont" class_data="hy2015050729727">
                          <div class="circle" class_data="hy2015050729529">
                               <div class="imgchange" class_data="hy2015050729369"><img src="/images/liebiao/15/tu2.jpg" hyvar="img"  hydesc="75*75"></div>
                          </div><!-- end of circle 圆内容-->
                          <div class="ul01_cont_nr" class_data="hy2015050729333">
                               <h4 class="m_text" hyvar="title" class_data="hy2015050729731">灰白色的简约家居设计</h4>
                              <p class="m_desc" hyvar="text" class_data="hy2015050729715">独特的崇尚原木的现代...</p>
                          </div>
                     </div>
                 </div>
                 <div class="ul01_right" class_data="hy2015050729740">
                    <img src="/images/liebiao/15/wz_05_016.png" width="11" height="95">
				</div>
                 <div class="arrow_right" class_data="hy2015050729150"><img src="/images/liebiao/15/right_01n.png"></div>	
                 <div class="clr"></div>
            </div>
        </a>
		<a href="#" target="_blank" hyvar="link">
            <div class="ul01" class_data="hy2015050729210">
                 <div class="ul01_left" class_data="hy2015050729561">
                     <img src="/images/liebiao/15/wz_05_13.png" width="11" height="95">
                 </div>
                 <div class="ulmain" class_data="hy2015050729249">
                     <div class="ul01_cont" class_data="hy2015050729871">
                          <div class="circle" class_data="hy2015050729954">
                               <div class="imgchange" class_data="hy2015050729399"><img src="/images/liebiao/15/tu3.jpg" hyvar="img"  hydesc="75*75"></div>
                          </div><!-- end of circle 圆内容-->
                          <div class="ul01_cont_nr" class_data="hy2015050729249">
                              <h4 class="m_text" hyvar="title" class_data="hy2015050729217">顶尖创意家居</h4>
                              <p class="m_desc" hyvar="text" class_data="hy2015050729570">小空间的小格局...</p>
                          </div>
                     </div>
                 </div>
                 <div class="ul01_right" class_data="hy2015050729279">
                    <img src="/images/liebiao/15/wz_05_16.png" width="11" height="95">
                 </div>
                 <div class="arrow_right" class_data="hy2015050729259"><img src="/images/liebiao/15/right_01n.png"></div>	
                 <div class="clr"></div>
            </div>
        </a>
   <a href="#" target="_blank" hyvar="link">
      <div class="ul01" class_data="hy2015050729796">
        <div class="ul01_left" class_data="hy2015050729806">
          <img src="/images/liebiao/15/wz_05_013.png" width="11" height="95">
        </div>
        <div class="ulmain" class_data="hy2015050729323">
          <div class="ul01_cont" class_data="hy2015050729558">
            <div class="circle" class_data="hy2015050729297">
              <div class="imgchange" class_data="hy2015050729744"><img src="/images/liebiao/15/tu4.jpg" hyvar="img"  hydesc="75*75"></div>
            </div><!-- end of circle 圆内容-->
            <div class="ul01_cont_nr" class_data="hy2015050729546">
              <h4 class="m_text" hyvar="title" class_data="hy2015050729670">温馨小情调-HDA家居</h4>
              <p class="m_desc" hyvar="text" class_data="hy2015050729321">它由木头碎屑混以水和粘合...</p>
            </div>
          </div>
        </div>
        <div class="ul01_right" class_data="hy2015050729702">
          <img src="/images/liebiao/15/wz_05_016.png" width="11" height="95">
        </div>
        <div class="arrow_right" class_data="hy2015050729600"><img src="/images/liebiao/15/right_01n.png"></div>	
        <div class="clr"></div>
    </div>
    </a>
	</s:if>
	<s:else>
		<s:iterator status='st' value='blocks[1].list' var='item'>
		<a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[1].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
	      <div class="ul01" class_data="hy2015050729636">
	        <div class="ul01_left" class_data="hy2015050729348">
	          <s:if test='#st.count%2 == 0'>
	          	<img src="/images/liebiao/15/wz_05_013.png" width="11" height="95">
	          </s:if>
	          <s:else>
	         	 <img src="/images/liebiao/15/wz_05_13.png" width="11" height="95">
	          </s:else>
	        </div>
	        <div class="ulmain" class_data="hy2015050729387">
	          <div class="ul01_cont" class_data="hy2015050729781">
	            <div class="circle" class_data="hy2015050729662">
	              <div class="imgchange" class_data="hy2015050729976"><img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else> width="75" height="75" /></div>
	            </div><!-- end of circle 圆内容-->
	            <div class="ul01_cont_nr" class_data="hy2015050729280">
	              <h4 class="m_text" hyvar="title" class_data="hy2015050729661">${item.title}</h4>
	              <span class="m_desc" hyvar="text" class_data="hy2015050729399">${item.shortdesc}</span>
	            </div>
	          </div>
	        </div>
	        <div class="ul01_right" class_data="hy2015050729216">
	        <s:if test='#st.count%2 == 0'>
	        	<img src="/images/liebiao/15/wz_05_016.png" width="11" height="95">
	        </s:if>
	        <s:else>
	        	<img src="/images/liebiao/15/wz_05_16.png" width="11" height="95">
	        </s:else>
	        </div>
	        <div class="arrow_right" class_data="hy2015050729427"><img src="/images/liebiao/15/right_01n.png"></div>	
	        <div class="clr"></div>
	    </div>
	    </a>
	    </s:iterator>
	</s:else>
</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>