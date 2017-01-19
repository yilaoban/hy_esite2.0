package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import project.caidan.dto.TaskDto;
import project.caidan.mgr.ITaskMgr;
import project.caidan.model.Task;
import project.caidan.model.TaskAnswer;
import project.caidan.model.TaskQuestion;
import project.caidan.model.TaskRecord;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.Pager;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.opensymphony.xwork2.ActionContext;

public class TaskAction extends AbstractAction {

	private static final long serialVersionUID = 259741663721876643L;
	private ITaskMgr taskMgr;

	private int pageId = 1;
	private int rows = 10;
	private TaskDto dto;
	private Task task;
	private TaskRecord taskR;
	
	private String code; //验证码
	@Override
	public String execute() throws Exception {
		this.lightType = 1;

		int start = (pageId - 1) * rows;
		int total = taskMgr.getTaskCount(this.getOwner());
		List<Task> list = taskMgr.getTaskList(this.getOwner(), start, rows);

		dto = new TaskDto();
		dto.setList(list);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}

	public String editTask() throws Exception {
		if (task == null) {
			return null;
		}
		// add or edit
		long id = task.getId();
		if (id > 0) {
			task = taskMgr.getTask(id);
			if (task == null) {
				return null;
			}
			List<TaskQuestion> taskQList = taskMgr.getTaskQuestionList(id);
			for (TaskQuestion taskQ : taskQList) {
				List<TaskAnswer> taskAList = taskMgr.getTaskAnswerList(taskQ.getId());
				taskQ.setTaskAList(taskAList);
			}
			task.setTaskQList(taskQList);
		}

		int type = task.getType();
		if (type == 0) {
			return "download";
		} else if (type == 1) {
			return "qa";
		} else {
			return null;
		}
	}

	public String saveTask() throws Exception {
		if (task == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			String link = task.getLink();
			if (link != null && !link.startsWith("<") && !link.startsWith("http")) {
				link = "http://" + link;
				task.setLink(link);
			}

			int res = 0;
			long tid = task.getId();
			if (tid > 0) {
				res = taskMgr.updateTask(task);
			} else {
				task.setOwner(this.getOwner());
				tid = taskMgr.addTask(task);
				if (tid > 0) {
					res = 1;
				}
			}
			// delete old record
			List<TaskQuestion> taskQList = taskMgr.getTaskQuestionList(tid);
			for (TaskQuestion taskQ : taskQList) {
				taskMgr.delTaskAnswer(taskQ.getId());
			}
			taskMgr.delTaskQuestion(tid);
			// add new record
			String qa = task.getQa();
			if (StringUtils.isNotBlank(qa)) {
				JSONArray jsonQList = JSONArray.fromObject(qa);
				for (int i = 0; i < jsonQList.size(); i++) {
					JSONObject jsonQ = jsonQList.getJSONObject(i);

					TaskQuestion taskQ = new TaskQuestion();
					taskQ.setTid(tid);
					taskQ.setQs(jsonQ.getString("qs"));
					taskQ.setType(jsonQ.getInt("type"));
					taskQ.setIdx(i);
					long qsid = taskMgr.addTaskQuestion(taskQ);

					JSONArray jsonAList = jsonQ.getJSONArray("taskAList");
					for (int j = 0; j < jsonAList.size(); j++) {
						JSONObject jsonA = jsonAList.getJSONObject(j);

						TaskAnswer taskA = new TaskAnswer();
						taskA.setQsid(qsid);
						taskA.setAns(jsonA.getString("ans"));
						taskA.setStatus(jsonA.getString("status"));
						taskA.setIdx(j);
						taskMgr.addTaskAnswer(taskA);
					}
				}
			}
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String delTask() throws Exception {
		if (task == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = taskMgr.delTask(task.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String sortTask() throws Exception {
		if (task == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			Task task2 = taskMgr.getTask(this.getOwner(), task.getIdx(), task.getType());
			if (task2 != null) {
				taskMgr.updateTask(task, task2);
				out.print(1);
			} else {
				out.print(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String record() throws Exception {
		if (taskR == null) {
			return null;
		}

		long tid = taskR.getTid();
		String status = taskR.getStatus();
		String starttime = taskR.getStarttime();
		String endtime = taskR.getEndtime();
		int rows = 10;
		int start = (pageId - 1) * rows;
		int total = taskMgr.getTaskRecordCount(tid, status, starttime, endtime);
		List<TaskRecord> recordList = taskMgr.getTaskRecordList(tid, status, starttime, endtime, start, rows);

		dto = new TaskDto();
		dto.setRecordList(recordList);
		dto.setPager(new Pager(pageId, total, rows));
		return SUCCESS;
	}

	public String updateRecord() throws Exception {
		if (taskR == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = taskMgr.updateTaskRecord(taskR,this.getOwner());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String delRecord() throws Exception {
		if (taskR == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			int res = taskMgr.delTaskRecord(taskR.getId());
			out.print(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String tasks() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null || vu.getWxUser() == null) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			long wxuid = vu.getWxUser().getId();
			int start = (pageId - 1) * rows;
			int total = taskMgr.getTaskCountWithTime(this.getOwner());
			List<Task> list = taskMgr.getTaskListWithTime(this.getOwner(), start, rows);
			for (Task t : list) {
				// num = shownum + record count
				int shownum = t.getShownum();
				int count = taskMgr.getTaskRecordCount(t.getId(), null, null, null);
				t.setShownum(shownum + count);
				// check if done
				TaskRecord r = taskMgr.getTaskRecord(t.getId(), wxuid);
				if (r != null) {
					t.setDone(true);//NTP-未审核；CMP-审核通过；ERR-审核不通过
					if("NTP".equals(r.getStatus())){
						t.setStatus("进行中");
					}else if("CMP".equals(r.getStatus())){
						t.setStatus("已完成");
						t.setSdone(true);
					}else if("ERR".equals(r.getStatus())){
						t.setStatus("下载失败");
					}
					
				}
			}

			JSONObject json = new JSONObject();
			json.put("list", list);
			json.put("pageId", pageId);
			json.put("total", total);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String task() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null || vu.getWxUser() == null) {
			return null;
		}
		
		if (task == null || task.getId() == 0) {
			return null;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		try {
			long id = task.getId();
			task = taskMgr.getTask(id);
			if (task != null) {
				int shownum = task.getShownum();
				int count = taskMgr.getTaskRecordCount(id, null, null, null);
				task.setShownum(shownum + count);

				List<TaskQuestion> taskQList = taskMgr.getTaskQuestionList(id);
				for (TaskQuestion taskQ : taskQList) {
					List<TaskAnswer> taskAList = taskMgr.getTaskAnswerList(taskQ.getId());
					taskQ.setTaskAList(taskAList);
				}
				task.setTaskQList(taskQList);
				long wxuid = vu.getWxUser().getId();
				TaskRecord r = taskMgr.getTaskRecord(task.getId(), wxuid);
				if (r != null) {
					task.setDone(true);//NTP-未审核；CMP-审核通过；ERR-审核不通过
					if("NTP".equals(r.getStatus())){
						task.setStatus("进行中");
					}else if("CMP".equals(r.getStatus())){
						task.setStatus("已完成");
						task.setSdone(true);
					}else if("ERR".equals(r.getStatus())){
						task.setStatus("下载失败");
					}
					
				}
			}

			JSONObject json = new JSONObject();
			json.put("task", task);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public String addRecord() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if (vu == null || vu.getWxUser() == null || taskR == null) {
			return null;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Boolean flag = true;
		try {
			Task t = taskMgr.getTask(taskR.getTid());
			if(t != null){
				if(t.getType() == 0){//下载任务
					String sRand = (String) request.getSession().getAttribute("checkCode");
					if (sRand != null)
					{
						if (sRand.equalsIgnoreCase(code))
						{
							ActionContext.getContext().getSession().put("checkCode", null);
						} else
						{
							flag = false;
							out.print(-2);//验证码填写错误
						}
					} else
					{
						flag = false;
						out.print(-1);//验证码失效,请重新获取
					}
				}
				if(flag){
					long wxuid = vu.getWxUser().getId();
					TaskRecord r = taskMgr.getTaskRecord(taskR.getTid(), wxuid);
					if (r != null) {
						out.print(-1);
						return null;
					}
					
					taskR.setWxuid(wxuid);
					
					String ip = ClientUserIp.getIpAddr(request);
					taskR.setIp(ip);
					taskR.setJf(t.getJf());
					int res = taskMgr.addTaskRecord(vu, t, taskR);
					out.print(res);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	public void setTaskMgr(ITaskMgr taskMgr) {
		this.taskMgr = taskMgr;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public TaskDto getDto() {
		return dto;
	}

	public void setDto(TaskDto dto) {
		this.dto = dto;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public TaskRecord getTaskR() {
		return taskR;
	}

	public void setTaskR(TaskRecord taskR) {
		this.taskR = taskR;
	}

	
	public String getCode()
	{
		return code;
	}

	
	public void setCode(String code)
	{
		this.code = code;
	}

}
