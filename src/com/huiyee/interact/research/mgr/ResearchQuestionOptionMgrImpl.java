package com.huiyee.interact.research.mgr;

import java.util.List;

import com.huiyee.interact.research.dao.IResearchQuestionOptionDao;
import com.huiyee.interact.research.dto.IDto;
import com.huiyee.interact.research.dto.ResearchQuestionOptionDto;
import com.huiyee.interact.research.model.ResearchVO;


public class ResearchQuestionOptionMgrImpl implements IResearchQuestionOptionMgr
{
	private IResearchQuestionOptionDao researchQuestionOptionDao;

	@Override
	public IDto findSurveyResultList(long recordid)
	{
		ResearchQuestionOptionDto res = new ResearchQuestionOptionDto();
			List<ResearchVO> list = researchQuestionOptionDao.findAllSurveyresult(recordid);
			for(int i=0;i<list.size();i++){
				if("ORD".equals(list.get(i).getType())){
					list.get(i).setContent(researchQuestionOptionDao.setContentbyord(list.get(i).getId(),recordid));
				}
			}
			res.setResearchVO(list);
		
		return res;
	}

	public void setResearchQuestionOptionDao(IResearchQuestionOptionDao researchQuestionOptionDao)
	{
		this.researchQuestionOptionDao = researchQuestionOptionDao;
	}


	
	


}
