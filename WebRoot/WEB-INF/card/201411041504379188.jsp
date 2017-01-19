<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 新闻列表3 -->
<link href="/css/liebiao/10/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%><s:if test='blocks[0].display=="Y"'>
<div class="liebiao_1023_6 block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};position:relative" hyblk="I" hydata="${blocks[0].rid}" class_data="hy2015050725434">
		<div class="telnav" class_data="hy2015050725592">
			<s:if test='blocks[0].list.size == 0'>
		    	 <a href="#" target="_blank" hyvar="link">
				    <div class="telbtn" class_data="hy2015050725380">
				      <div class="pic" class_data="hy2015050725948"><img src="/images/liebiao/10/9deec13b6b142042877d39.jpg" hyvar="img" hydesc="333*265"></div>
				      <div class="t_content" class_data="hy2015050725306">
				        <div class="t_t1" hyvar="title" class_data="hy2015050725420">沕沕水</div>
				        <div class="t_t2" hyvar="introduction" class_data="hy2015050725668">沕沕水旅游攻略</div>
				      </div>
				      <div class="icon" class_data="hy2015050725262"><img src="/images/liebiao/10/small.png" hyvar="img" hydesc="18*18"></div>
				    </div>
				    </a> 
					<a href="#" target="_blank" hyvar="link">
				    <div class="telbtn" class_data="hy2015050725526">
				      <div class="pic" class_data="hy2015050725871"><img src="/images/liebiao/10/7aaff58dbe7419dbd7ca12.jpg" hyvar="img" hydesc="333*265"></div>
				      <div class="t_content" class_data="hy2015050725815">
				        <div class="t_t1" hyvar="title" class_data="hy2015050725470">海南</div>
				        <div class="t_t2" hyvar="introduction" class_data="hy2015050725606">海南旅游攻略</div>
				      </div>
				      <div class="icon" class_data="hy2015050725427"><img src="/images/liebiao/10/small.png" hyvar="img" hydesc="18*18"></div>
				    </div>
				    </a> 
					<a href="#" target="_blank" hyvar="link">
				    <div class="telbtn" class_data="hy2015050725300">
				      <div class="pic" class_data="hy2015050725923"><img src="/images/liebiao/10/60d03c5e9218dcb948076e.jpg" hyvar="img" hydesc="333*265"></div>
				      <div class="t_content" class_data="hy2015050725975">
				        <div class="t_t1" hyvar="title" class_data="hy2015050725801">九寨</div>
				        <div class="t_t2" hyvar="introduction" class_data="hy2015050725619">九寨旅游攻略</div>
				      </div>
				      <div class="icon" class_data="hy2015050725990"><img src="/images/liebiao/10/small.png" hyvar="img" hydesc="18*18"></div>
				    </div>
				    </a> 
					<a href="#" target="_blank" hyvar="link">
				    <div class="telbtn" class_data="hy2015050725692">
				      <div class="pic" class_data="hy2015050725511"><img src="/images/liebiao/10/86af5a451ac584df818fdb.jpg" hyvar="img" hydesc="333*265"></div>
				      <div class="t_content" class_data="hy2015050725407">
				        <div class="t_t1" hyvar="title" class_data="hy2015050725373">旅游的意义</div>
				        <div class="t_t2" hyvar="introduction" class_data="hy2015050725501">您心中的旅游意义</div>
				      </div>
				      <div class="icon" class_data="hy2015050725786"><img src="/images/liebiao/10/small.png" hyvar="img" hydesc="18*18"></div>
				    </div>
				    </a> 
					<a href="#" target="_blank" hyvar="link">
				    <div class="telbtn" class_data="hy2015050725767">
				      <div class="pic" class_data="hy2015050725381"><img src="/images/liebiao/10/5990c475e57b47f2e2489f.jpg" hyvar="img" hydesc="333*265"></div>
				      <div class="t_content" class_data="hy2015050725187">
				        <div class="t_t1" hyvar="title" class_data="hy2015050725344">夏季出游</div>
				        <div class="t_t2" hyvar="introduction" class_data="hy2015050725452">夏季出游必备攻略</div>
				      </div>
				      <div class="icon" class_data="hy2015050725167"><img src="/images/liebiao/10/small.png" hyvar="img" hydesc="18*18"></div>
				    </div>
				    </a> 
		    </s:if>
		    <s:else>
				<s:iterator status='st' value='blocks[0].list' var='item'>
					<a <s:if test='#item.islink =="N"'> href="/${oname}/user/show/${blocks[0].pageid}/${sessionScope.visitUser.source}/n-${item.id}.html" </s:if><s:else>href="${item.linkurl}"</s:else> target="_blank" hyvar="link">
						<div class="telbtn" class_data="hy2015050725359">
								<div class="pic" class_data="hy2015050725444"><img <s:if test='#item.simgurl ==""'>src="/images/xw.jpg"</s:if><s:else>src="${imgDomain}${item.simgurl}"</s:else> hyvar="img" hydesc="333*265"></div>
								<div class="t_content" class_data="hy2015050725272">
							  		<div class="t_t1" hyvar="title" class_data="hy2015050725908">${item.title}</div>
									<div class="t_t2" hyvar="introduction" class_data="hy2015050725886">${item.shortdesc}</div>
								</div>
								<div class="icon" class_data="hy2015050725783"><img src="/images/liebiao/10/small.png" hyvar="img" hydesc="18*18"></div>
							</div>
					</a>
				</s:iterator>
		    </s:else>
</div>
	</div>
</s:if>


<%@include file="/WEB-INF/card/cardfile.jsp"%>
