
package com.huiyee.interact.exam.mgr;

import java.util.List;

import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageCard;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpTookit;
import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.exam.dao.IExamDao;
import com.huiyee.interact.exam.dao.IExamQuestionDao;
import com.huiyee.interact.exam.dao.IExamQuestionOptionDao;
import com.huiyee.interact.exam.dto.ExamResultDto;
import com.huiyee.interact.exam.dto.ExamRsDto;
import com.huiyee.interact.exam.dto.IDto;
import com.huiyee.interact.exam.dto.ExamRecordDto;
import com.huiyee.interact.exam.dto.ExamDto;
import com.huiyee.interact.exam.dto.ExamSubDto;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamRecord;
import com.huiyee.interact.exam.model.ExamResult;
import com.huiyee.interact.vote.model.Pager;

import net.sf.json.JSONObject;

public class ExamManagerImpl extends AbstractMgr implements IExamManager
{

	private IExamDao examDao;
	private ITemplateDao templateDao;
	private IExamQuestionDao examQuestionDao;
	private IExamQuestionOptionDao examQuestionOptionDao;

	
	public void setExamQuestionOptionDao(IExamQuestionOptionDao examQuestionOptionDao)
	{
		this.examQuestionOptionDao = examQuestionOptionDao;
	}

	public void setExamQuestionDao(IExamQuestionDao examQuestionDao)
	{
		this.examQuestionDao = examQuestionDao;
	}

	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	public IExamDao getExamDao()
	{
		return examDao;
	}

	public void setExamDao(IExamDao examDao)
	{
		this.examDao = examDao;
	}

	@Override
	public IDto findExamList(long ownerid, int start, int size, long omid)
	{
		ExamDto dto = new ExamDto();
		List<ExamModel> examList = examDao.findVoteList(ownerid, start, size, omid);
		dto.setExamList(examList);
		return dto;
	}

	@Override
	public int findExamListTotal(long ownerid, long omid)
	{
		return examDao.findExamListTotal(ownerid, omid);
	}

	@Override
	public ExamModel findExamModelById(long id, long ownerid)
	{
		ExamModel m = examDao.findExamModelById(id);
		List<ExamQuestionModel> list = examDao.findQuestionsByExamid(id);
		for (ExamQuestionModel q : list)
		{
			q.setOptions(examDao.findOptionsByQuestionid(q.getId()));
		}
		m.setQuestions(list);
		return m;
	}

	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type)
	{
		return examDao.findLotteryByType(ownerid, type);
	}

	@Override
	public long saveExamDesign(long ownerid, ExamDto dto, long omid)
	{
		return examDao.saveExamDesign(ownerid, dto, omid);
	}

	@Override
	public IDto findExamModelById(long examid)
	{
		ExamDto dto = new ExamDto();
		ExamModel exam = examDao.findExamModelById(examid);
		dto.setExam(exam);
		return dto;
	}

	@Override
	public Lottery findLotteryById(long lotteryid)
	{
		return examDao.findLotteryById(lotteryid);
	}

	@Override
	public long updateExamDesign(long ownerid, ExamDto dto, long examid)
	{
		return examDao.updateExamDesign(ownerid, dto, examid);
	}

	@Override
	public IDto findExamRecordList(long searchid, int type, String nickname, String source, int pageId, long owner)
	{

		ExamRecordDto dto = new ExamRecordDto();
		int start = (pageId - 1) * IInteractConstants.VOTE_LIMIT;
		if (type == 0)
		{
			int total = examDao.findExamRecordTotal(searchid, owner);
			if (total > 0)
			{
				List<ExamRecord> list = examDao.findExamRecordList(searchid, start, IInteractConstants.VOTE_LIMIT, owner);
				dto.setList(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
			dto.setPager(pager);
		} else if (type == 1)
		{
			int total = examDao.findWxExamRecordListTotal(searchid, source, owner);
			List<ExamRecord> sourceList = examDao.findExamByRecord(searchid);
			dto.setSourceList(sourceList);
			if (total > 0)
			{
				List<ExamRecord> list = examDao.findExamRecordList(searchid, start, IInteractConstants.VOTE_LIMIT, source, owner);
				dto.setList(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
			dto.setPager(pager);
		} else
		{
			int total = examDao.findExamRecordTotal(searchid, type, owner);
			if (total > 0)
			{
				List<ExamRecord> list = examDao.findNiExamRecordList(searchid, type, start, IInteractConstants.VOTE_LIMIT, owner);
				dto.setList(list);
			}
			Pager pager = new Pager(pageId, total, IInteractConstants.VOTE_LIMIT);
			dto.setPager(pager);
		}
		return dto;
	}

	@Override
	public ExamRsDto saveExamReocrd(ExamSubDto dto, long pageid, VisitUser visit, String ip, String terminal, long relationid, ExamModel exam,long owner)
	{
		ExamRsDto rs = new ExamRsDto();
		rs.setStatus(1);
		rs.setHydesc("谢谢参与！");
		long entityid = visit.getUid();
		int type = visit.getCd();
		long recordid = examDao.saveExamReocrd(pageid, entityid, exam.getId(), ip, terminal, visit.getSource(), type);
		if (recordid > 0)
		{
			this.updateInteractAction(exam, entityid, type);
			if(exam.getBalance()!=0&&visit.getHyUser()!=null)
			{
				this.updateBalance(visit.getHyUser().getId(), exam.getBalance(), "评测获得积分","HUD" , "EXA", exam.getId());
			}
			if (dto.getAnswerStr() != null)
			{
				int score=0;//获得的分数
				String[] str = dto.getAnswerStr().split(";");
				for (int i = 0; i < str.length; i++)
				{
					String[] str1 = str[i].split(":");
					String typeStr = examDao.findExamType(Long.parseLong(str1[0]));
					// 排序
					if ("ORD".equals(typeStr))
					{
						// 类如160：170-1再次分解
						String[] str2 = str1[1].split("-");
						examDao.saveExamAnswerPx(recordid, Long.parseLong(str1[0]), Long.parseLong(str2[0]), Integer.parseInt(str2[1]));
						examDao.updateExamQuestionOption(Long.parseLong(str2[0]));
					}
					// 多选
					if ("MUP".equals(typeStr))
					{
						examDao.saveExamAnswerXZ(recordid, Long.parseLong(str1[0]), Long.parseLong(str1[1]));
						examDao.updateExamQuestionOption(Long.parseLong(str1[1]));
						
						ExamQuestionOption option=examQuestionOptionDao.findOptionById(Long.parseLong(str1[1]));
						score=score+option.getScore();
						
					}
					// 单选
					if ("SGL".equals(typeStr))
					{
						examDao.saveExamAnswerXZ(recordid, Long.parseLong(str1[0]), Long.parseLong(str1[1]));
						examDao.updateExamQuestionOption(Long.parseLong(str1[1]));
						
						ExamQuestionOption option=examQuestionOptionDao.findOptionById(Long.parseLong(str1[1]));
						score=score+option.getScore();
					}
					// 打分
					if ("GAD".equals(typeStr))
					{
						if (str1.length > 1)
						{
							examDao.saveExamAnswerTK(recordid, Long.parseLong(str1[0]), str1[1]);
						} else
						{
							examDao.saveExamAnswerTK(recordid, Long.parseLong(str1[0]), "");
						}
					}
					// 填空
					if ("FIL".equals(typeStr))
					{
						if (str1.length > 1)
						{
							examDao.saveExamAnswerTK(recordid, Long.parseLong(str1[0]), str1[1]);
						} else
						{
							examDao.saveExamAnswerTK(recordid, Long.parseLong(str1[0]), "");
						}
					}
				}
				
				
				//获取结果
				
				List<ExamResult> rList=examDao.findResultList( exam.getId(), owner);
				if(rList.size()>0){
					for (ExamResult examResult : rList)
					{
						if(examResult.getStart()<=score&&examResult.getEnd()>=score){
							rs.setResultid(examResult.getId());
							rs.setContent(examResult.getContent());
							break;
						}
					}
					 
				}
			}
		}
		if (relationid > 0)
		{
			TemplateBlock tb = templateDao.findTemplateBlockByRelationid(relationid);
			if (tb != null)
			{
				PageBlockRelation r = templateDao.findPageBlockRelationByRelationid(relationid);
				String jtype = "N";
				String url = null;
				if (r != null)
				{
					String json = r.getJson();
					JSONObject jo = JSONObject.fromObject(json);
					try
					{
						jo = jo.getJSONObject("obj");
						jo = jo.getJSONObject("redirect");
						jtype = jo.getString("type");
						url = jo.getString("url");
					} catch (Exception e)
					{
						jtype = "N";
					}
				}
				if ("N".equals(jtype))
				{ // 默认跳转
					BlockContext bc = templateDao.findBlockContextid(tb.getId(), "Y");
					if (bc != null)
					{
						Page page = templateDao.findPageByRelationidAndContextid(relationid, bc.getId());
						if (page != null)
						{
							List<PageCard> cardList = templateDao.findTemplateCardByPageid(page.getId());
							if (cardList != null && cardList.size() > 0)
							{ // 有子页面且页面上有卡片则需要跳转
								rs.setId(page.getId());
							}
						}
					}
				} else if ("R".equals(jtype))
				{// 通过http获取跳转地址
					url = HttpTookit.doGet(url, null, "utf-8", true);
				}
				rs.setUrl(url);
			}
		}
		return rs;
	}

	@Override
	public int deleteExam(long id)
	{
		examDao.deleteExam(id);
		return 0;
	}

	@Override
	public int updateRuletypeByLotteryid(long lotteryid)
	{
		return examDao.updateRuletypeByLotteryid(lotteryid);
	}

	@Override
	public ExamModel findModelByIdAndPageId(long examid, long pageid)
	{
		ExamModel exam = examDao.findExamModelById(examid);
		return exam;
	}

	@Override
	public long addExam(long ownerid, String title)
	{
		return examDao.addExam(ownerid, title);
	}

	@Override
	public int updateExamClean(long searchid, long owner)
	{
		ExamModel m = examDao.findExamModelById(searchid);
		if (m != null && m.getOwnerid() == owner)
		{
			return examDao.updateExamClean(searchid);
		}
		return 0;
	}

	@Override
	public IDto findExamResultList(long examid, Account account)
	{
		ExamResultDto dto = new ExamResultDto();
		dto.setList(examDao.findResultList(examid, account.getOwner().getId()));
		int total=0;
		List<ExamQuestionModel> list=examQuestionDao.findExamQuestionList(examid);
		for (ExamQuestionModel m : list)
		{
			//SGL:单选 ;MUP:多选;FIL:填空;GAD:打分
			if("SGL".equals(m.getType())){
				total+=examQuestionDao.findMaxScore(m.getId());
			}else if("MUP".equals(m.getType())){
				total+=examQuestionDao.findSumScore(m.getId());
			}
		}
		dto.setTotal(total);
		return dto;
	}

	@Override
	public long addExamResult(ExamResult examResult, Account account)
	{
		return examDao.addExamResult(examResult, account.getOwner().getId());
	}

	@Override
	public int delExamResult(long resultid, Account account)
	{
		return examDao.delExamResult(resultid, account.getOwner().getId());
	}

	@Override
	public int updateExamResult(ExamResult examResult, Account account)
	{
		return examDao.updateExamResult(examResult, account.getOwner().getId());
	}

	@Override
	public ExamResult findExamResult(long resultid, long ownerid)
	{
		return examDao.findExamResult(resultid, ownerid);
	}

	@Override
	public ExamResult findExamResult(long resultid)
	{
		return examDao.findExamResult(resultid);
	}
}
