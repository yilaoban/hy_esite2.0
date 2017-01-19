<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content2">
	<div class="row3 clearfix">
		<ul>
			<%--  
			<li>
				<div class="title tt-mbxx">模板消息<a href="/${oname}/interact/template.action">进入</a></div>
				<div class="desc">微信模板消息通知</div>
			</li>
			 <li>
				<div class="title tt-wjl">微记录<a href="/${sessionScope.account.owner.entity}/bbs/microRecord.action">进入</a></div>
				<div class="desc"></div>
			</li>
			<li>
				<div class="title tt-wkq">微卡券<a href="/${oname}/content/weikaquan.action">进入</a></div>
				<div class="desc">优惠券、现金凭证，应有尽有。 线上、线下都互动。</div>
			</li>
			 --%>
			<s:if test="#session.account.owner.contains(1)">
				<li>
					<div class="title tt-wsq">微社区<a href="/${oname }/bbs/index.action">进入</a></div>
					<div class="desc">支持设立多个论坛版块及新闻类评论。所见所得，快速上手。</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(2)">
				<li>
					<div class="title tt-hhr">合伙人<a href="/${sessionScope.account.owner.entity}/interact/marketing_activity.action">进入</a></div>
					<div class="desc">招募传播者， 让您的活动传播更广，更精准。</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(3)">
				<li>
					<div class="title tt-wjf">用户中心<a href="/${sessionScope.account.owner.entity}/setting/jfSetting.action">进入</a></div>
					<div class="desc">针对用户行为，设置各种积分奖励，转发、签到、评论都可积分</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(4)">
				<li>
					<div class="title tt-wds">微电商<a href="/${oname }/content/ebProductList.action?subtype=W">进入</a></div>
					<div class="desc">从前端、到产品 到订单， 一气呵成。 掌握用户全生命周期。</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(5)">
				<li>
					<div class="title tt-jfsc">积分商城<a href="/${oname }/content/ebProductList.action?subtype=J">进入</a></div>
					<div class="desc">从竞拍到兑换， 让积分落到实处。</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(6)">
				<li>
					<div class="title tt-xxqd">线下签到<a href="/${oname }/interact/offCheckIndex.action">进入</a></div>
					<div class="desc">快速创建线下场景二维码，自动维护用户，实现一对一互动沟通</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(7)">
				<li>
					<div class="title tt-wyy">微预约<a href="/${oname }/interact/yuyueIndex.action">进入</a></div>
					<div class="desc">服务、套餐、促销、团购。实现线上预约、手机确认、自动沟通</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(8)">
				<li>
					<div class="title tt-qkgl">潜客管理<a href="/${oname}/interact/leaderIndex.action">进入</a></div>
					<div class="desc">针对目标用户进行针对性的管理、维护、分析、跟踪</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(9)">
				<li>
					<div class="title tt-yhfx">用户分析<a href="">进入</a></div>
					<div class="desc">对用户数据及行为，进行深层次的挖掘、分析，自动推荐</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(10)">
				<li>
					<div class="title tt-znss">站内搜索<a href="/${oname}/interact/site_search.action">进入</a></div>
					<div class="desc">支持对任意微站的模糊查询并记录热点词汇</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(11)">
				<li>
					<div class="title tt-wtf">微投放<a href="/${oname}/interact/adIndex.action">进入</a></div>
					<div class="desc">广告投放的媒体、期刊、版本、着陆页的管理及广告效果分析监测</div>
				</li>
			</s:if>
			<s:if test="#session.account.owner.contains(12)">
				<li>
					<div class="title tt-wtf">服务评价<a href="/${oname}/servicerpj/servicerIndex.action">进入</a></div>
					<div class="desc">线上评价，在线回复，实现商家和用户的及时沟通</div>
				</li>
			</s:if>
						<s:if test="!#session.account.owner.contains(1)">
			<li>
				<div class="title tt-wsq">微社区
				<a href="javascript:tff(1);">开始试用</a>
				</div>
				<div class="desc">支持设立多个论坛版块及新闻类评论。所见所得，快速上手。</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(2)">
			<li>
				<div class="title tt-hhr">合伙人
				<a href="javascript:tff(2);">开始试用</a>
				</div>
				<div class="desc">招募传播者， 让您的活动传播更广，更精准。</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(3)">
			<li>
				<div class="title tt-wjf">用户中心
					<a href="javascript:tff(3);">开始试用</a>
				</div>
				<div class="desc">针对用户行为，设置各种积分奖励，转发、签到、评论都可积分</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(4)">
			<li>
				<div class="title tt-wds">微电商
				<a href="javascript:tff(4);">开始试用</a>
				</div>
				<div class="desc">从前端、到产品 到订单， 一气呵成。 掌握用户全生命周期。</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(5)">
			<li>
				<div class="title tt-jfsc">积分商城
				<a href="javascript:tff(5);">开始试用</a>
				</div>
				<div class="desc">从竞拍到兑换， 让积分落到实处。</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(6)">
			<li>
				<div class="title tt-xxqd">线下签到
				<a href="javascript:tff(6);">开始试用</a>
				</div>
				<div class="desc">快速创建线下场景二维码，自动维护用户，实现一对一互动沟通</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(7)">
			<li>
				<div class="title tt-wyy">微预约
				<a href="javascript:tff(7);">开始试用</a>
				</div>
				<div class="desc">服务、套餐、促销、团购。实现线上预约、手机确认、自动沟通</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(8)">
			<li>
				<div class="title tt-qkgl">潜客管理
				<a href="javascript:tff(8);">开始试用</a>
				</div>
				<div class="desc">针对目标用户进行针对性的管理、维护、分析、跟踪</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(9)">
			<li>
				<div class="title tt-yhfx">用户分析
				<a href="javascript:tff(9);">开始试用</a>
				</div>
				<div class="desc">对用户数据及行为，进行深层次的挖掘、分析，自动推荐</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(10)">
			<li>
				<div class="title tt-znss">站内搜索
				<a href="javascript:tff(10);">开始试用</a>
				</div>
				<div class="desc">支持对任意微站的模糊查询并记录热点词汇</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(11)">
			<li>
				<div class="title tt-wtf">微投放
				<a href="javascript:tff(11);">开始试用</a>
				</div>
				<div class="desc">广告投放的媒体、期刊、版本、着陆页的管理及广告效果分析监测</div>
			</li>
			</s:if>
			<s:if test="!#session.account.owner.contains(12)">
			<li>
				<div class="title tt-wtf">服务评价
				<a href="javascript:tff(12);">开始试用</a>
				</div>
				<div class="desc">线上评价，在线回复，实现商家和用户的及时沟通</div>
			</li>
			</s:if>
		</ul>
	</div>
</div>
<script type="text/javascript">

function tff(id){
	var now = new Date();
	now.setDate(now.getDate()+15);
	var time = now.format("yyyy-MM-dd");
	layer.confirm('您有15天的试用期。 到'+time+'试用结束。 如有问题，请联系客服', {
		    btn: ['马上试用','暂时不'] //按钮
		}, function(index){
			$.post("/${oname}/app/tff.action",{"app_id":id},function(data){
				if(data == 1){
					window.location.href='baseAppIndex.action';
				}else{
					layer.close(index);
				}
			});
		});
}

</script>