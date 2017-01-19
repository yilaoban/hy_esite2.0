package project.caidan.model;

public class TaskAnswer {

	private long id;
	private long qsid;
	private String ans;
	private String status;
	private int idx;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQsid() {
		return qsid;
	}

	public void setQsid(long qsid) {
		this.qsid = qsid;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

}
