package project.caidan.model;

import java.util.List;

public class TaskQuestion {

	private long id;
	private long tid;
	private String qs;
	private int type;
	private int idx;

	private List<TaskAnswer> taskAList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getQs() {
		return qs;
	}

	public void setQs(String qs) {
		this.qs = qs;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public List<TaskAnswer> getTaskAList() {
		return taskAList;
	}

	public void setTaskAList(List<TaskAnswer> taskAList) {
		this.taskAList = taskAList;
	}

}
