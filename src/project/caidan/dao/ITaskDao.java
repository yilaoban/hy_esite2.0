package project.caidan.dao;

import java.util.List;

import project.caidan.model.Task;
import project.caidan.model.TaskAnswer;
import project.caidan.model.TaskQuestion;
import project.caidan.model.TaskRecord;

public interface ITaskDao {

	public int getTaskCount(long owner);

	public List<Task> getTaskList(long owner, int start, int rows);
	
	public List<Task> getTaskListWithTime(long owner, int start, int rows);

	public Task getTask(long id);

	public Task getTask(long owner, int idx, int type);

	public long addTask(Task t);

	public int updateTask(Task t);

	public void updateTask(Task t1, Task t2);

	public int delTask(long id);

	public List<TaskQuestion> getTaskQuestionList(long tid);

	public long addTaskQuestion(TaskQuestion q);

	public int delTaskQuestion(long tid);

	public List<TaskAnswer> getTaskAnswerList(long qsid);

	public int addTaskAnswer(TaskAnswer a);

	public int delTaskAnswer(long qsid);

	public int getTaskRecordCount(long tid, String status, String stime, String etime);

	public List<TaskRecord> getTaskRecordList(long tid, String status, String stime, String etime, int start, int rows);

	public TaskRecord getTaskRecord(long tid, long wxuid);

	public int addTaskRecord(TaskRecord r);

	public int updateTaskRecord(TaskRecord r);

	public int delTaskRecord(long id);

	public int getTaskCountWithTime(long owner);

	public TaskRecord getTaskRecordById(long id);

}
