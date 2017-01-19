package project.caidan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import project.caidan.dao.ITaskDao;
import project.caidan.model.Task;
import project.caidan.model.TaskAnswer;
import project.caidan.model.TaskQuestion;
import project.caidan.model.TaskRecord;

@SuppressWarnings("unchecked")
public class TaskDaoImpl implements ITaskDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private RowMapper taskRowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Task t = new Task();
			
			t.setId(rs.getLong("id"));
			t.setOwner(rs.getLong("owner"));
			t.setTitle(rs.getString("title"));
			t.setImg(rs.getString("img"));
			t.setHydesc(rs.getString("hydesc"));
			t.setContent(rs.getString("content"));
			Date starttime = rs.getTimestamp("starttime");
			if (starttime != null) {
				t.setStarttime(sdf.format(starttime));
			}
			Date endtime = rs.getTimestamp("endtime");
			if (endtime != null) {
				t.setEndtime(sdf.format(endtime));
			}
			t.setType(rs.getInt("type"));
			t.setLink(rs.getString("link"));
			t.setJf(rs.getInt("jf"));
			t.setShownum(rs.getInt("shownum"));
			t.setIdx(rs.getInt("idx"));
			return t;
		}

	};

	private RowMapper taskQuestionRowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			TaskQuestion q = new TaskQuestion();
			q.setId(rs.getLong("id"));
			q.setTid(rs.getLong("tid"));
			q.setQs(rs.getString("qs"));
			q.setType(rs.getInt("type"));
			q.setIdx(rs.getInt("idx"));
			return q;
		}

	};

	private RowMapper taskAnswerRowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			TaskAnswer a = new TaskAnswer();
			a.setId(rs.getLong("id"));
			a.setQsid(rs.getLong("qsid"));
			a.setAns(rs.getString("ans"));
			a.setStatus(rs.getString("status"));
			a.setIdx(rs.getInt("idx"));
			return a;
		}

	};

	private RowMapper taskRecordRowMapper = new RowMapper() {

		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			TaskRecord r = new TaskRecord();
			r.setId(rs.getLong("r.id"));
			r.setTid(rs.getLong("r.tid"));
			r.setWxuid(rs.getLong("r.wxuid"));
			r.setPhone(rs.getString("r.phone"));
			r.setIp(rs.getString("r.ip"));
			r.setArea(rs.getString("r.area"));
			r.setJf(rs.getInt("r.jf"));
			r.setCreatetime(rs.getTimestamp("r.createtime"));
			r.setStatus(rs.getString("r.status"));
			r.setNickname(rs.getString("u.nickname"));
			r.setOpenid(rs.getString("u.openid"));
			return r;
		}

	};

	@Override
	public int getTaskCount(long owner) {
		String sql = "SELECT COUNT(*) FROM caidan.cd_task WHERE owner=?";
		Object[] param = { owner };
		return jdbcTemplate.queryForInt(sql, param);
	}
	
	@Override
	public int getTaskCountWithTime(long owner)
	{
		String sql = "SELECT COUNT(*) FROM caidan.cd_task WHERE owner=? and starttime<=now() and endtime>=now()";
		Object[] param = { owner };
		return jdbcTemplate.queryForInt(sql, param);
	}

	@Override
	public List<Task> getTaskList(long owner, int start, int rows) {
		String sql = "SELECT * FROM caidan.cd_task WHERE owner=? ORDER BY idx DESC LIMIT ?,?";
		Object[] params = { owner, start, rows };
		return jdbcTemplate.query(sql, params, taskRowMapper);
	}
	
	@Override
	public List<Task> getTaskListWithTime(long owner, int start, int rows)
	{
		String sql = "SELECT * FROM caidan.cd_task WHERE owner=? and starttime<=now() and endtime>=now() ORDER BY idx DESC LIMIT ?,?";
		Object[] params = { owner, start, rows };
		return jdbcTemplate.query(sql, params, taskRowMapper);
	}

	@Override
	public Task getTask(long id) {
		String sql = "SELECT * FROM caidan.cd_task WHERE id=?";
		Object[] params = { id };
		List<Task> list = jdbcTemplate.query(sql, params, taskRowMapper);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Task getTask(long owner, int idx, int type) {
		String sql = "SELECT * FROM caidan.cd_task WHERE owner=? AND idx>? order by idx asc  limit 1";
		if (type > 0) {
			sql = "SELECT * FROM caidan.cd_task WHERE owner=? AND idx<? order by idx desc limit 1";
		}
		Object[] params = { owner, idx };
		List<Task> list = jdbcTemplate.query(sql, params, taskRowMapper);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public long addTask(final Task t) {
		Object[] param = { t.getOwner() };
		final int maxidx = jdbcTemplate.queryForInt("SELECT MAX(idx) FROM caidan.cd_task WHERE owner=?", param);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "INSERT INTO caidan.cd_task(owner,title,img,hydesc,content,starttime,endtime,type,link,jf,shownum,idx) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, t.getOwner());
				ps.setString(i++, t.getTitle());
				ps.setString(i++, t.getImg());
				ps.setString(i++, t.getHydesc());
				ps.setString(i++, t.getContent());
				ps.setString(i++, t.getStarttime());
				ps.setString(i++, t.getEndtime());
				ps.setInt(i++, t.getType());
				ps.setString(i++, t.getLink());
				ps.setInt(i++, t.getJf());
				ps.setInt(i++, t.getShownum());
				ps.setInt(i++, maxidx + 1);
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public int updateTask(Task t) {
		String sql = "UPDATE caidan.cd_task SET title=?,img=?,hydesc=?,content=?,starttime=?,endtime=?,link=?,jf=?,shownum=? WHERE id=?";
		Object[] params = { t.getTitle(), t.getImg(), t.getHydesc(), t.getContent(), t.getStarttime(), t.getEndtime(), t.getLink(), t.getJf(), t.getShownum(), t.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public void updateTask(Task t1, Task t2) {
		String sql = "UPDATE caidan.cd_task SET idx=? WHERE id=?";
		jdbcTemplate.update(sql, new Object[] { t1.getIdx(), t2.getId() });
		jdbcTemplate.update(sql, new Object[] { t2.getIdx(), t1.getId() });
	}

	@Override
	public int delTask(long id) {
		String sql = "DELETE FROM caidan.cd_task WHERE id=?";
		Object[] param = { id };
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public List<TaskQuestion> getTaskQuestionList(long tid) {
		String sql = "SELECT * FROM caidan.cd_task_question WHERE tid=? ORDER BY idx";
		Object[] params = { tid };
		return jdbcTemplate.query(sql, params, taskQuestionRowMapper);
	}

	@Override
	public long addTaskQuestion(final TaskQuestion q) {
		final String sql = "INSERT INTO caidan.cd_task_question(tid,qs,type,idx) VALUES(?,?,?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, new String[] { "id" });
				int i = 1;
				ps.setLong(i++, q.getTid());
				ps.setString(i++, q.getQs());
				ps.setInt(i++, q.getType());
				ps.setInt(i++, q.getIdx());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public int delTaskQuestion(long tid) {
		String sql = "DELETE FROM caidan.cd_task_question WHERE tid=?";
		Object[] param = { tid };
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public List<TaskAnswer> getTaskAnswerList(long qsid) {
		String sql = "SELECT * FROM caidan.cd_task_answer WHERE qsid=? ORDER BY idx";
		Object[] params = { qsid };
		return jdbcTemplate.query(sql, params, taskAnswerRowMapper);
	}

	@Override
	public int addTaskAnswer(TaskAnswer a) {
		String sql = "INSERT INTO caidan.cd_task_answer(qsid,ans,status,idx) VALUES(?,?,?,?)";
		Object[] params = { a.getQsid(), a.getAns(), a.getStatus(), a.getIdx() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delTaskAnswer(long qsid) {
		String sql = "DELETE FROM caidan.cd_task_answer WHERE qsid=?";
		Object[] param = { qsid };
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public int getTaskRecordCount(long tid, String status, String stime, String etime) {
		String sql = "SELECT count(*) FROM caidan.cd_task_record WHERE tid=" + tid;
		if (status != null) {
			sql += " AND status='" + status + "'";
		}
		if (stime != null) {
			sql += " AND createtime>'" + stime + "'";
		}
		if (etime != null) {
			sql += " AND createtime<'" + etime + "'";
		}
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public List<TaskRecord> getTaskRecordList(long tid, String status, String stime, String etime, int start, int rows) {
		String sql = "SELECT * FROM caidan.cd_task_record r LEFT JOIN esite.es_wx_user u ON u.id=r.wxuid WHERE r.tid=" + tid;
		if (status != null) {
			sql += " AND r.status='" + status + "'";
		}
		if (stime != null) {
			sql += " AND r.createtime>'" + stime + "'";
		}
		if (etime != null) {
			sql += " AND r.createtime<'" + etime + "'";
		}
		sql += " LIMIT " + start + "," + rows;
		return jdbcTemplate.query(sql, taskRecordRowMapper);
	}

	@Override
	public TaskRecord getTaskRecord(long tid, long wxuid) {
		String sql = "SELECT * FROM caidan.cd_task_record r LEFT JOIN esite.es_wx_user u ON u.id=r.wxuid WHERE r.tid=? AND r.wxuid=?";
		Object[] params = { tid, wxuid };
		List<TaskRecord> list = jdbcTemplate.query(sql, params, taskRecordRowMapper);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public TaskRecord getTaskRecordById(long id)
	{
		String sql = "SELECT * FROM caidan.cd_task_record  WHERE id=?";
		Object[] params = { id };
		List<TaskRecord> list = jdbcTemplate.query(sql, params, new RowMapper(){
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				TaskRecord r = new TaskRecord();
				r.setId(rs.getLong("id"));
				r.setTid(rs.getLong("tid"));
				r.setWxuid(rs.getLong("wxuid"));
				r.setPhone(rs.getString("phone"));
				r.setIp(rs.getString("ip"));
				r.setArea(rs.getString("area"));
				r.setJf(rs.getInt("jf"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setStatus(rs.getString("status"));
				return r;
			}
		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addTaskRecord(TaskRecord r) {
		String sql = "INSERT INTO caidan.cd_task_record(tid,wxuid,phone,ip,area,jf,createtime,status) VALUES(?,?,?,?,?,?,now(),?)";
		Object[] params = { r.getTid(), r.getWxuid(), r.getPhone(), r.getIp(), r.getArea(), r.getJf(),r.getStatus() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int updateTaskRecord(TaskRecord r) {
		String sql = "UPDATE caidan.cd_task_record SET status=? WHERE id=?";
		Object[] params = { r.getStatus(), r.getId() };
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public int delTaskRecord(long id) {
		String sql = "DELETE FROM caidan.cd_task_record WHERE id=?";
		Object[] params = { id };
		return jdbcTemplate.update(sql, params);
	}

}
