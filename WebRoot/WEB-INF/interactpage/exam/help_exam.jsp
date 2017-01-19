<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content">
   <dl>
   	 <dt>1、	标题</dt>
   	 	<dd>必填，标题内容前台不可见，填写方便记忆的标题即可。</dd>
   	 <dt>2、	简介</dt>
   	 	<dd>非必填，可填写方便记忆的内容。</dd>
   	 <dt>3、	活动开始时间/结束时间</dt>
   	 	<dd>必填，系统将根据时间判断用户是否能参与测评。</dd>
   	 <dt>4、	开始前提示语</dt>
   	 	<dd>非必填，可填写“测评还未开始！”用户在活动开始前参加测评将会提示此内容。</dd>
   	 <dt>5、	结束后提示语</dt>
   	 	<dd>非必填，可填写“测评已结束！”，用户在活动结束后参加测评将会提示此内容。</dd>
   	 <dt>6、	测评上限</dt>
   	 	<dd>指每名用户最多可参加的测评次数，系统默认设置为1次/人。</dd>
   	 <dt>7、	每日测评上限</dt>
   	 	<dd>每名用户每日最多可参加的测评次数。</dd>
	 <dt>8、	测评后关联抽奖</dt>
	 	<dd>即参与测评后可以与大转盘、砸金蛋、摇一摇3类互动关联。若选择是，需要设定关联的抽奖方式及其抽奖名称。如设置抽奖方式为：大转盘，抽奖名称为在大转盘处创建的抽奖名称。活动增加抽奖机会是指成功参加一次活动可增加的抽奖机会，若设置为1次/人，表示参加1次测评可增加一次大转盘的抽奖机会。
		</dd>
	 	<dd>增加抽奖机会上限是指每名用户最多可通过测评增加的抽奖机会。若测评上限设置为5次/人，测评增加抽奖机会设置为1次/人，增加抽奖机会上限设置为2次/人。表示用户测评5次，只能增加2次抽奖机会。</dd>
	 	<dd>若设置关联抽奖，需要先进行抽奖活动的设置，且抽奖活动处设置的用户初始抽奖机会无效，仅能通过测评活动增加抽奖机会。</dd>
   	 <dt>9、	测评后赠送积分</dt>
   	 	<dd>若设置5积分/人，表示用户参与1次测评赠送其5积分。</dd>
   </dl>
</div>