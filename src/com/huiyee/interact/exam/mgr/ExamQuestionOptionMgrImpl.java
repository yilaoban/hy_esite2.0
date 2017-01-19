package com.huiyee.interact.exam.mgr;

import java.util.List;

import com.huiyee.interact.exam.dao.IExamQuestionOptionDao;
import com.huiyee.interact.exam.dto.IDto;
import com.huiyee.interact.exam.dto.ExamQuestionOptionDto;
import com.huiyee.interact.exam.model.ExamVO;


public class ExamQuestionOptionMgrImpl implements IExamQuestionOptionMgr
{
	private IExamQuestionOptionDao examQuestionOptionDao;

	@Override
	public IDto findSurveyResultList(long recordid)
	{
		ExamQuestionOptionDto res = new ExamQuestionOptionDto();
			List<ExamVO> list = examQuestionOptionDao.findAllSurveyresult(recordid);
			for(int i=0;i<list.size();i++){
				if("ORD".equals(list.get(i).getType())){
					list.get(i).setContent(examQuestionOptionDao.setContentbyord(list.get(i).getId(),recordid));
				}
			}
			res.setExamVO(list);
		
		return res;
	}

	public void setExamQuestionOptionDao(IExamQuestionOptionDao examQuestionOptionDao)
	{
		this.examQuestionOptionDao = examQuestionOptionDao;
	}


	
	


}
