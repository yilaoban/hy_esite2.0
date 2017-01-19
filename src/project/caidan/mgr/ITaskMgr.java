package project.caidan.mgr;

import java.util.List;

import com.huiyee.esite.model.VisitUser;

import project.caidan.model.Task;
import project.caidan.model.TaskAnswer;
import project.caidan.model.TaskQuestion;
import project.caidan.model.TaskRecord;

public interface ITaskMgr {

	public int getTaskCount(long owner);

	public List<Task> getTaskList(long owner, int start, int rows);

	public List<Task> getTaskListWithTime(long owner, int start, int rows);
	
	public Task getTask(long id);

	public Task getTask(long owner, int idx, int type);

	public long addTask(Task task);

	public int updateTask(Task task);

	public void updateTask(Task task1, Task task2);

	public int delTask(long id);

	public List<TaskQuestion> getTaskQuestionList(long tid);

	public long addTaskQuestion(TaskQuestion taskQ);

	public int delTaskQuestion(long tid);

	public List<TaskAnswer> getTaskAnswerList(long qsid);

	public int addTaskAnswer(TaskAnswer taskA);

	public int delTaskAnswer(long qsid);

	public int getTaskRecordCount(long tid, String status, String starttime, String endtime);

	public List<TaskRecord> getTaskRecordList(long tid, String status, String starttime, String endtime, int start, int rows);

	public TaskRecord getTaskRecord(long tid, long wxuid);

	public int addTaskRecord(VisitUser vu, Task task, TaskRecord taskR);

	public int updateTaskRecord(TaskRecord taskR, long owner);

	public int delTaskRecord(long id);

	public int getTaskCountWithTime(long owner);

}
