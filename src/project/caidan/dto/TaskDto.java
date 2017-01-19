package project.caidan.dto;

import java.util.List;

import com.huiyee.esite.dto.Pager;

import project.caidan.model.Task;
import project.caidan.model.TaskRecord;

public class TaskDto {

	private List<Task> list;
	private List<TaskRecord> recordList;
	private Pager pager;

	public List<Task> getList() {
		return list;
	}

	public void setList(List<Task> list) {
		this.list = list;
	}

	public List<TaskRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<TaskRecord> recordList) {
		this.recordList = recordList;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
