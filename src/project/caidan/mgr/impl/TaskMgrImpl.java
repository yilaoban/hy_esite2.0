package project.caidan.mgr.impl;

import java.util.List;

import com.huiyee.esite.dao.IHyUserDao;
import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.VisitUser;

import project.caidan.dao.ITaskDao;
import project.caidan.mgr.ITaskMgr;
import project.caidan.model.Task;
import project.caidan.model.TaskAnswer;
import project.caidan.model.TaskQuestion;
import project.caidan.model.TaskRecord;

public class TaskMgrImpl extends AbstractMgr implements ITaskMgr {

	private ITaskDao taskDao;
	private IHyUserDao hyUserDao;

	
	public void setHyUserDao(IHyUserDao hyUserDao)
	{
		this.hyUserDao = hyUserDao;
	}

	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public int getTaskCount(long owner) {
		return taskDao.getTaskCount(owner);
	}
	
	@Override
	public int getTaskCountWithTime(long owner)
	{
		return taskDao.getTaskCountWithTime(owner);
	}
	

	@Override
	public List<Task> getTaskList(long owner, int start, int rows) {
		return taskDao.getTaskList(owner, start, rows);
	}
	
	@Override
	public List<Task> getTaskListWithTime(long owner, int start, int rows)
	{
		return taskDao.getTaskListWithTime(owner, start, rows);
	}

	@Override
	public Task getTask(long id) {
		return taskDao.getTask(id);
	}

	@Override
	public Task getTask(long owner, int idx, int type) {
		return taskDao.getTask(owner, idx, type);
	}

	@Override
	public long addTask(Task t) {
		return taskDao.addTask(t);
	}

	@Override
	public int updateTask(Task t) {
		return taskDao.updateTask(t);
	}

	@Override
	public void updateTask(Task t1, Task t2) {
		taskDao.updateTask(t1, t2);
	}

	@Override
	public int delTask(long id) {
		return taskDao.delTask(id);
	}

	@Override
	public List<TaskQuestion> getTaskQuestionList(long tid) {
		return taskDao.getTaskQuestionList(tid);
	}

	@Override
	public long addTaskQuestion(TaskQuestion q) {
		return taskDao.addTaskQuestion(q);
	}

	@Override
	public int delTaskQuestion(long tid) {
		return taskDao.delTaskQuestion(tid);
	}

	@Override
	public List<TaskAnswer> getTaskAnswerList(long qsid) {
		return taskDao.getTaskAnswerList(qsid);
	}

	@Override
	public int addTaskAnswer(TaskAnswer a) {
		return taskDao.addTaskAnswer(a);
	}

	@Override
	public int delTaskAnswer(long qsid) {
		return taskDao.delTaskAnswer(qsid);
	}

	@Override
	public int getTaskRecordCount(long tid, String status, String stime, String etime) {
		return taskDao.getTaskRecordCount(tid, status, stime, etime);
	}

	@Override
	public List<TaskRecord> getTaskRecordList(long tid, String status, String stime, String etime, int start, int rows) {
		return taskDao.getTaskRecordList(tid, status, stime, etime, start, rows);
	}

	@Override
	public TaskRecord getTaskRecord(long tid, long wxuid) {
		return taskDao.getTaskRecord(tid, wxuid);
	}

	@Override
	public int addTaskRecord(VisitUser vu, Task t, TaskRecord r) {
		String stype = null;
		String desc = null;
		if (t.getType() == 0) {
			r.setStatus("NTP");
			stype = "TDL";
			desc = "下载任务获得积分";
		} else if (t.getType() == 1) {
			r.setStatus("CMP");
			stype = "TQA";
			desc = "答题任务获得积分";
			this.updateBalance(vu.getHyUserId(), t.getJf(), desc, "TAK", stype, t.getId());
		}
		r.setArea(this.findAreaByIp(r.getIp()));
		return taskDao.addTaskRecord(r);
	}

	@Override
	public int updateTaskRecord(TaskRecord r, long owner) {
		int rs= taskDao.updateTaskRecord(r);
		if(rs==1&&"CMP".equalsIgnoreCase(r.getStatus())){
			TaskRecord record=taskDao.getTaskRecordById(r.getId());
			Task task=taskDao.getTask(record.getTid());
			HyUser hyuser=hyUserDao.findHyUserBywxuid(record.getWxuid(), owner);
			this.updateBalance(hyuser.getId(), task.getJf(), "下载任务获得积分", "TAK", "TDL", task.getId());
		}
		return rs;
	}

	@Override
	public int delTaskRecord(long id) {
		return taskDao.delTaskRecord(id);
	}

}
