package project.caidan.model;

import java.util.List;

public class Task {

	private long id;
	private long owner;
	private String title;
	private String img;
	private String hydesc;
	private String content;
	private String starttime;
	private String endtime;
	private int type;
	private String link;
	private int jf;
	private int shownum;
	private int idx;

	private List<TaskQuestion> taskQList;
	private String qa;
	private boolean done;
	private boolean sdone;
	private String status;//NTP-未审核；CMP-审核通过；ERR-审核不通过
	
	
	public String getStatus()
	{
		return status;
	}

	
	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getHydesc() {
		return hydesc;
	}

	public void setHydesc(String hydesc) {
		this.hydesc = hydesc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getJf() {
		return jf;
	}

	public void setJf(int jf) {
		this.jf = jf;
	}

	public int getShownum() {
		return shownum;
	}

	public void setShownum(int shownum) {
		this.shownum = shownum;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public List<TaskQuestion> getTaskQList() {
		return taskQList;
	}

	public void setTaskQList(List<TaskQuestion> taskQList) {
		this.taskQList = taskQList;
	}

	public String getQa() {
		return qa;
	}

	public void setQa(String qa) {
		this.qa = qa;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}


	
	public boolean isSdone()
	{
		return sdone;
	}


	
	public void setSdone(boolean sdone)
	{
		this.sdone = sdone;
	}

}
