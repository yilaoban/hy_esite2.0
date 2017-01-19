<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content">
   <dl>
   	 <dt>1、	标题</dt>
   	 	<dd>必填，标题内容用户不可见，填写方便记忆的标题即可。</dd>
   	 <dt>2、	简介</dt>
   	 	<dd>非必填，填写方便记忆的内容即可。</dd>
   	 <dt>3、	活动开始时间/结束时间</dt>
   	 	<dd>必填，系统将根据活动时间判断用户是否可以参与投票。</dd>
   	 <dt>4、	开始前提示语</dt>
   	 	<dd>非必填，可填写“投票还未开始！”，用户在活动开始前参加投票将会提示此内容。</dd>
   	 <dt>5、	结束后提示语</dt>
   	 	<dd>非必填，可填写“活动已结束，感谢关注！”，用户在活动结束后参见投票将会提示此内容。</dd>
   	 <dt>6、	投票上限</dt>
   	 	<dd>必填，是指每名用户最多可投票的次数。</dd>
   	 <dt>7、	每日投票上限</dt>
   	 	<dd>必填，是指每名用户每天可投票的次数。</dd>
   	 <dt>8、	投票后关联抽奖</dt>
   	 	<dd>即参与投票后可以与大转盘、砸金蛋、摇一摇3类互动关联。若选择是，需要设定关联的抽奖方式及其抽奖名称。如设置抽奖方式为：大转盘，抽奖名称为新增大转盘时设置的标题。
		活动增加抽奖机会是指成功参加一次活动可增加的抽奖机会，若设置为1次/人，表示参加1次投票可增加一次大转盘的抽奖机会。
		</dd>
		<dd>增加抽奖机会上限是指每名用户最多可通过投票增加的抽奖机会。若投票上限设置为5次/人，投票增加抽奖机会设置为1次/人，增加抽奖机会上限设置为2次/人。表示用户投票5次，只能增加2次抽奖机会。</dd>
   	 <dt>9、	投票后赠送积分</dt>
   	 	<dd>若设置5积分/人，表示用户参与1次投票赠送其5积分。</dd>
   </dl>
</div>
