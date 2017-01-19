package com.huiyee.interact.exam.mgr;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.exam.dao.IExamQuestionDao;
import com.huiyee.interact.exam.dto.OrdData;
import com.huiyee.interact.exam.dto.ExamOptionDto;
import com.huiyee.interact.exam.dto.ExamQuestionDataDto;
import com.huiyee.interact.exam.model.OptionIdx;
import com.huiyee.interact.exam.model.ExamQuestionListModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamQuestionOptionModel;
import com.huiyee.interact.exam.model.ExamQuestionOptionVO;
import com.huiyee.interact.exam.model.ExamRecordAnswer;
import com.huiyee.interact.exam.util.FileUploadService;
public class ExamQuestionMgrImpl implements IExamQuestionMgr
{

	private IExamQuestionDao examQuestionDao;
	
	public IExamQuestionDao getExamQuestionDao()
	{
		return examQuestionDao;
	}

	public void setExamQuestionDao(IExamQuestionDao examQuestionDao)
	{
		this.examQuestionDao = examQuestionDao;
	}
	@Override
	public List<ExamQuestionModel> findExamQuestionList(long searchid,int start,int size)
	{
		return examQuestionDao.findExamQuestionList(searchid,start,size);
	}

	 private String createFileName(String fileName,String type) {
			int index = fileName.lastIndexOf('.');
			// 判断上传文件名是否有扩展名
			if (index != -1) {
				return FileUploadService.getNow()+type+ fileName.substring(index);
			}
			return FileUploadService.getNow()+type;
	}
	 
	@Override
	public long saveExamQuestion(ExamQuestionListModel rqlmodel,ExamQuestionOptionModel rqomodel)
	{
		long resultid=0;
		int result=0;
		long mid=10015;
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		long searchid=rqlmodel.getSearchid();
		String type=rqlmodel.getType();
		String isreq=rqlmodel.getIsreq();
		for(int i=0;i<rqlmodel.getTitle().size();i++){
			String title=rqlmodel.getTitle().get(i);
			String saveResult="";
			if(rqlmodel.getImg()!=null){
				String name=createFileName(rqlmodel.getImgFileName().get(i),"_m");
				String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
				saveResult = FileUploadService.saveFile(rqlmodel.getImg().get(i),path,name);
			}
			resultid=examQuestionDao.saveExamQuestion(searchid, type, title, saveResult,isreq);
		}
		if(rqomodel!=null){
		  for(int i=0;i<rqomodel.getContent().size();i++){
			String content=rqomodel.getContent().get(i);
			String score=rqomodel.getScore().get(i);
			String saveResult ="";
			if(rqomodel.getImg()!=null){
				String name=createFileName(rqomodel.getImgFileName().get(i),"_m");
				String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
				saveResult = FileUploadService.saveFile(rqomodel.getImg().get(i),path,name);
			}
			result=examQuestionDao.saveQuestionContent(content, saveResult, resultid,score);
		  }
		}
		if(resultid>0){
			return resultid;
		}else if(result>0){
			result=1;
		}
		return result;
		
	}
	@Override
	public int delExamQuestion(long id)
	{
		int len=examQuestionDao.delExamQuestion(id);
		if(len>0){
			return len;
		}
		return 0;
	}
	  public List<ExamQuestionDataDto> findExamData(long examid)
	{
		List<ExamQuestionDataDto> questionlist = examQuestionDao.findExamQuestionListOrderByIdex(examid);//根据searchid 得到question的数据
		for (ExamQuestionDataDto examQuestionDataDto : questionlist)  //问题的数量
		{
			ExamOptionDto dto = new ExamOptionDto();
			List<List<Object>> optionobj = new ArrayList<List<Object>>();
			long questionid = examQuestionDataDto.getId();
			List<ExamQuestionOption> optionlist = examQuestionDao.findQuestionOptionsByQuestionid(questionid);//所有的选项 根据questionid 得到option的数据
			int total = examQuestionDao.findExamOptionTotalByQuestionid(questionid);  
			DecimalFormat df2 = new DecimalFormat("##.##%"); //用来格式化数字
			Gson gson = new Gson();
			if("ORD".equals(examQuestionDataDto.getType())){
				List<Map<Integer,Integer>> lists = new ArrayList<Map<Integer,Integer>>();
				List<OrdData> l =new ArrayList<OrdData>();
				for(int i=0;i<optionlist.size();i++){
					Map<Integer,Integer> map = new HashMap<Integer, Integer>();
					List<OptionIdx> list1 = examQuestionDao.countIdx(optionlist.get(i).getId());
					for(OptionIdx o : list1){
						map.putAll(o.getMaps());
					}
					for(int j=0;j<optionlist.size();j++){
						if(map.get(j+1)==null){
							map.put(j+1, 0);
						}
					}
					lists.add(map);
				}
				for(int i= 0 ;i<optionlist.size();i++){
					OrdData od = new OrdData();
					od.setName("第"+(i+1)+"位");
					for(int j= 0 ;j<optionlist.size();j++){
						Map<Integer,Integer>  m = lists.get(j);
						od.getData().add(m.get(i+1));						
					}
					l.add(od);
				}
				dto.setOrdLists(l);
				dto.setOrdStr(gson.toJson(l));
				dto.setLists(lists);
			}
			//String type = examQuestionDataDto.getType();
			if (optionlist != null && optionlist.size() > 0)
			{
				for (ExamQuestionOption option : optionlist)
				{
					int num = option.getCount();
					String content = option.getContent();
					List<Object> votecontents = new ArrayList<Object>();
					if (total != 0)
					{
						double optionnum = (num * 1.00) / (total * 1.00);
						String percent = optionnum != 0 ? df2.format(optionnum) : "0%";
						votecontents.add(content + "=" + percent);
						option.setPercent(percent);
					}
					else
					{
						votecontents.add(content + "=0%");
						option.setPercent("0%");
					}
					votecontents.add(num);
					optionobj.add(votecontents);
				}
			}
			dto.setExamdata(optionobj);
			dto.setTotal(total);
			dto.setList(optionlist);
			examQuestionDataDto.setOptionDto(dto);
		}
		return questionlist;
	}
	  
	@Override
	public int updateExamQuestion(long id, ExamQuestionListModel rqlmodel,long questionid, ExamQuestionOptionModel rqomodel)
	{
		int result=0;
		long mid=10015;
		Account account=(Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid=account.getOwner().getId();
		String isreq=rqlmodel.getIsreq();
		//题目名称
		for(int i=0;i<rqlmodel.getTitle().size();i++){
			String title=rqlmodel.getTitle().get(i);
			//先判断备用图片，默认的为""
			if("".equals(rqlmodel.getPic().get(i))){
				//再判断用户上传的图片
				if(rqlmodel.getImg()!=null){
					String name=createFileName(rqlmodel.getImgFileName().get(i),"_m");
					String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
					String saveResult = FileUploadService.saveFile(rqlmodel.getImg().get(i),path,name);
					result=examQuestionDao.updateExamQuestion(id, title, saveResult,isreq);
				}else{
					String Tpic=rqlmodel.getPic().get(i);
					result=examQuestionDao.updateExamQuestion(id, title, Tpic,isreq);
				}
				//如果用户第一次上传了图片再次编辑没有上传图片来到这里
			}else{
				String Tpic=rqlmodel.getPic().get(i);
				result=examQuestionDao.updateExamQuestion(id, title, Tpic,isreq);
			}
		}
		//选项
		if(rqomodel!=null){
			for(int i=0;i<rqomodel.getContent().size();i++){
				String content=rqomodel.getContent().get(i);
				String score=rqomodel.getScore().get(i);
				//编辑的时候用户新增选项来到这里
				if(rqomodel.getId().get(i)==null){
					//先判断备用图片，默认的为""
					if("".equals(rqomodel.getPic().get(i))){
						//再判断用户上传的图片
						if(rqomodel.getImg()!=null){
							for(int a=0;a<rqomodel.getImg().size();a++){
								String name=createFileName(rqomodel.getImgFileName().get(a),"_m");
								String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
								String saveResult = FileUploadService.saveFile(rqomodel.getImg().get(a),path,name);
								result=examQuestionDao.saveQuestionContent(content, saveResult, questionid,score);
								break;
							}
						}else{
							String Tpic=rqomodel.getPic().get(i);
							result=examQuestionDao.saveQuestionContent(content, Tpic, questionid,score);
						}
						//如果用户第一次上传了图片再次编辑没有上传图片来到这里
					}else{
						String Tpic=rqomodel.getPic().get(i);
						result=examQuestionDao.saveQuestionContent(content, Tpic, questionid,score);
					}
					//编辑的时候用户没有新增选项来到这里
				}else{
					long optionsid=rqomodel.getId().get(i);
					//先判断备用图片，默认的为""
					if("".equals(rqomodel.getPic().get(i))){
						//再判断用户上传的图片
						if(rqomodel.getImg()!=null){
							for(int a=0;a<rqomodel.getImg().size();a++){
								String name=createFileName(rqomodel.getImgFileName().get(a),"_m");
								String path = FileUploadService.getFilePath(IInteractConstants.TYPE_INTERACT, ownerid, mid+"");
								String saveResult = FileUploadService.saveFile(rqomodel.getImg().get(a),path,name);
								result=examQuestionDao.updateExamQuestionOption(optionsid,content, saveResult,score);
								break;
							}
						}else{
							String Tpic=rqomodel.getPic().get(i);
							result=examQuestionDao.updateExamQuestionOption(optionsid, content, Tpic,score);
						}
						//如果用户第一次上传了图片再次编辑没有上传图片来到这里
					}else{
						String Tpic=rqomodel.getPic().get(i);
						result=examQuestionDao.updateExamQuestionOption(optionsid, content, Tpic,score);
					}
				}
			}
		}
		if(result>0){
			result=1;
		}
		return result;
	}
	

	@Override
	public List<ExamQuestionModel> findOneExamQuestion(long questionid)
	{
		return examQuestionDao.findOneExamQuestion(questionid);
	}

	@Override
	public ExamQuestionModel findTypeIds(long id)
	{
		return examQuestionDao.findTypeIds(id);
	}

	@Override
	public int findExamQuestionTotal(long id)
	{
		return examQuestionDao.findExamQuestionTotal(id);
	}

	@Override
	public int updateDownExamQuestion(long id)
	{
		return examQuestionDao.updateDownExamQuestion(id);
	}

	@Override
	public int updateUpExamQuestion(long id)
	{
		return examQuestionDao.updateUpExamQuestion(id);
	}

	@Override
	public int deleteExamOptions(long id)
	{
		return examQuestionDao.deleteExamOptions(id);
	}

	@Override
	public List<ExamQuestionOption> findoptionbyqid(long questionid)
	{
		return examQuestionDao.findoptionbyqid(questionid);
	}

	@Override
	public List<ExamQuestionModel> findquestionbyqid(long questionid,long searchid,int idx)
	{
		return examQuestionDao.findquestionbyqid(questionid,searchid,idx);
	}

	@Override
	public int updateinTarget(long id, long target)
	{
		return examQuestionDao.updateinTarget(id,target);
	}

	@Override
	public ExamQuestionOptionVO selisTar(long id)
	{
		return examQuestionDao.findselisTar(id);
	}

}
