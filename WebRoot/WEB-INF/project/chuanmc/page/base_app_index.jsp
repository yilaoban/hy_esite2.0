<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
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
			<li>
				<div class="title tt-wsq">微社区
				<s:if test="#session.account.owner.contains(1)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(1);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">支持设立多个论坛版块及新闻类评论。所见所得，快速上手。</div>
			</li>
			<li>
				<div class="title tt-hhr">合伙人
				<s:if test="#session.account.owner.contains(2)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(2);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">招募传播者， 让您的活动传播更广，更精准。</div>
			</li>
			<li>
				<div class="title tt-wjf">用户中心
				<s:if test="#session.account.owner.contains(3)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(3);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">针对用户行为，设置各种积分奖励，转发、签到、评论都可积分</div>
			</li>
			<li>
				<div class="title tt-wds">微电商
				<s:if test="#session.account.owner.contains(4)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(4);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">从前端、到产品 到订单， 一气呵成。 掌握用户全生命周期。</div>
			</li>
			<li>
				<div class="title tt-jfsc">积分商城
				<s:if test="#session.account.owner.contains(5)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(5);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">从竞拍到兑换， 让积分落到实处。</div>
			</li>
			<li>
				<div class="title tt-xxqd">线下签到
				<s:if test="#session.account.owner.contains(6)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(6);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">线下签到线下签到线下签到</div>
			</li>
			<li>
				<div class="title tt-wyy">微预约
				<s:if test="#session.account.owner.contains(7)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(7);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">微预约</div>
			</li>
			<li>
				<div class="title tt-qkgl">潜客管理
				<s:if test="#session.account.owner.contains(8)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(8);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">潜客</div>
			</li>
			<li>
				<div class="title tt-yhfx">用户分析
				<s:if test="#session.account.owner.contains(9)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(9);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">用户分析</div>
			</li>
			<li>
				<div class="title tt-znss">站内搜索
				<s:if test="#session.account.owner.contains(10)">
					<a href="#">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(10);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">站内搜索</div>
			</li>
			<li>
				<div class="title tt-wtf">微投放
				<s:if test="#session.account.owner.contains(11)">
					<a  onclick="tff(11)">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(11);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">微投放</div>
			</li>
			<li>
				<div class="title tt-wtf">服务评价
				<s:if test="#session.account.owner.contains(12)">
					<a  onclick="tff(12)">已购买</a>
				</s:if>
				<s:else>
					<a href="javascript:tff(12);">立即试用</a>
				</s:else>
				</div>
				<div class="desc">服务评价</div>
			</li>
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