
package com.huiyee.interact.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.huiyee.interact.lottery.model.Lottery;
import com.huiyee.interact.exam.dto.ExamDto;
import com.huiyee.interact.exam.model.ExamModel;
import com.huiyee.interact.exam.model.ExamQuestionModel;
import com.huiyee.interact.exam.model.ExamQuestionOption;
import com.huiyee.interact.exam.model.ExamRecord;
import com.huiyee.interact.exam.model.ExamResult;

public class ExamDaoImpl implements IExamDao
{

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findExamListTotal(long ownerid, long omid)
	{
		String sql = "select count(*) from esite.es_interact_exam where ownerid=? and omid=? and status !='DEL'";
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ ownerid, omid });
	}

	@Override
	public List<ExamModel> findVoteList(long ownerid, int start, int size, long omid)
	{
		String sql = "select id,ownerid,title,starttime,endtime from esite.es_interact_exam where ownerid=? and omid=? and status !='DEL' order by id desc limit ?,?";
		return getJdbcTemplate().query(sql, new Object[]
		{ ownerid, omid, start, size }, new ExamMapper());
	}

	class ExamMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			ExamModel model = new ExamModel();
			model.setId(rs.getLong("id"));
			model.setOwnerid(rs.getLong("ownerid"));
			model.setTitle(rs.getString("title"));
			model.setStarttimeDate(rs.getTimestamp("starttime"));
			model.setEndtimeDate(rs.getTimestamp("endtime"));
			return model;
		}
	}

	class LotteryMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			Lottery lottery = new Lottery();
			lottery.setId(rs.getLong("id"));
			lottery.setName(rs.getString("name"));
			lottery.setType(rs.getString("type"));
			return lottery;
		}
	}

	private static final String FIND_LOTTERY_BY_TYPE = "select es.id,es.name,es.type from esite.es_interact_lottery es where es.owner = ? and type = ? and status !='D' order by es.id desc";

	@Override
	public List<Lottery> findLotteryByType(long ownerid, String type)
	{
		Object[] params =
		{ ownerid, type };
		return getJdbcTemplate().query(FIND_LOTTERY_BY_TYPE, params, new LotteryMapper());
	}

	private static final String SAVE_EXAM_DESIGN = "insert into esite.es_interact_exam(ownerid,title,content,starttime,endtime,totallimit,daylimit,islottery,lotterychance"
			+ ",lotteryid,balance,startnote,endnote,maxlotterychance,omid,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";

	@Override
	public long saveExamDesign(final long ownerid, final ExamDto dto, final long omid)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_EXAM_DESIGN, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, dto.getExam().getTitle());
				ps.setString(i++, dto.getExam().getContent());
				if (dto.getExam().getStarttime() != null)
				{
					ps.setTimestamp(i++, new Timestamp(dto.getExam().getStarttime().getTime()));
				} else
				{
					ps.setTimestamp(i++, null);
				}
				if (dto.getExam().getEndtime() != null)
				{
					ps.setTimestamp(i++, new Timestamp(dto.getExam().getEndtime().getTime()));
				} else
				{
					ps.setTimestamp(i++, null);
				}
				ps.setInt(i++, dto.getExam().getTotallimit());
				ps.setInt(i++, dto.getExam().getDaylimit());
				ps.setString(i++, dto.getExam().getIslottery());
				ps.setInt(i++, dto.getExam().getLotterychance());
				ps.setLong(i++, dto.getExam().getLotteryid());
				ps.setInt(i++, dto.getExam().getBalance());
				ps.setString(i++, dto.getExam().getStartnote());
				ps.setString(i++, dto.getExam().getEndnote());
				ps.setInt(i++, dto.getExam().getMaxlotterychance());
				ps.setLong(i++, omid);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	private static final String FIND_EXAM_MODEL_BY_ID = "select * from esite.es_interact_exam where id = ? and status !='DEL'";

	@Override
	public ExamModel findExamModelById(long examid)
	{
		Object[] params =
		{ examid };
		List<ExamModel> list = getJdbcTemplate().query(FIND_EXAM_MODEL_BY_ID, params, new FeatureRowmapper());
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	class FeatureRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ExamModel iv = new ExamModel();
			iv.setId(rs.getLong("id"));
			iv.setTitle(rs.getString("title"));
			iv.setContent(rs.getString("content"));
			iv.setStarttimeDate(rs.getTimestamp("starttime"));
			iv.setEndtimeDate(rs.getTimestamp("endtime"));
			iv.setTotallimit(rs.getInt("totallimit"));
			iv.setDaylimit(rs.getInt("daylimit"));
			iv.setIslottery(rs.getString("islottery"));
			iv.setLotterychance(rs.getInt("lotterychance"));
			iv.setLotteryid(rs.getLong("lotteryid"));
			iv.setBalance(rs.getInt("balance"));
			iv.setMaxlotterychance(rs.getInt("maxlotterychance"));
			iv.setStartnote(rs.getString("startnote"));
			iv.setEndnote(rs.getString("endnote"));
			iv.setOwnerid(rs.getLong("ownerid"));
			iv.setOmid(rs.getLong("omid"));
			return iv;
		}

	}

	private static final String FIND_LTTERY_BY_ID = "select es.id,es.name,es.type from esite.es_interact_lottery es where id = ?";

	@Override
	public Lottery findLotteryById(long lotteryid)
	{
		Object[] params =
		{ lotteryid };
		List<Lottery> list = getJdbcTemplate().query(FIND_LTTERY_BY_ID, params, new LotteryMapper());
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public List<ExamRecord> findExamRecordList(long searchid, int start, int size, long owner)
	{
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		list.add(searchid);
		String sql = "select r.*,u.username,wb.nickname nickname1 from es_feature_interact_exam_record r left join es_sina_user wb on r.wbuid = wb.wbuid left join es_hy_user u on r.wbuid = u.wbuid and u.owner=? where r.searchid=? and r.type=0 ";
		sql += " order by r.id desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new ResearRecordMapper());
	}

	public int findExamRecordTotal(long searchid, long owner)
	{
		List<Object> list = new ArrayList<Object>();
		list.add(owner);
		list.add(searchid);
		String sql = "select count(r.id) from es_feature_interact_exam_record r left join es_sina_user wb on r.wbuid = wb.wbuid left join es_hy_user u on r.wbuid = u.wbuid and u.owner=? where r.searchid=? and r.type=0 ";
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	class ResearRecordMapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int arg) throws SQLException
		{
			ExamRecord record = new ExamRecord();
			record.setId(rs.getLong("id"));
			record.setCreatetime(rs.getTimestamp("createtime"));
			record.setIsshare(rs.getString("isshare"));
			record.setWbuid(rs.getLong("wbuid"));
			record.setSearchid(rs.getLong("searchid"));
			record.setIp(rs.getString("ip"));
			record.setNickname(rs.getString("nickname1"));
			record.setUsername(rs.getString("u.username"));
			return record;
		}
	}

	private static final String UPDATE_EXAM_DESIGN = "update esite.es_interact_exam set title=?,content=?,starttime=?,endtime=?,totallimit=?"
			+ ",daylimit=?,islottery=?,lotterychance=?,lotteryid=?,balance=?,createtime = now(),maxlotterychance=?,startnote=?,endnote=? where id=?";

	@Override
	public long updateExamDesign(long ownerid, ExamDto dto, long examid)
	{
		ExamModel r = dto.getExam();
		Object[] params =
		{ r.getTitle(), r.getContent(), r.getStarttime(), r.getEndtime(), r.getTotallimit(), r.getDaylimit(), r.getIslottery(), r.getLotterychance(), r.getLotteryid(),
				r.getBalance(), r.getMaxlotterychance(), r.getStartnote(), r.getEndnote(), examid };
		return getJdbcTemplate().update(UPDATE_EXAM_DESIGN, params);
	}

	private static final String FIND_QUESTION_BY_EXAMID = "select * from esite.es_interact_exam_question where searchid = ? and status != 'DEL'";

	@Override
	public List<ExamQuestionModel> findQuestionsByExamid(long examid)
	{
		return getJdbcTemplate().query(FIND_QUESTION_BY_EXAMID, new Object[]
		{ examid }, new QuestionRowmapper());
	}

	class QuestionRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ExamQuestionModel q = new ExamQuestionModel();
			q.setId(rs.getLong("id"));
			q.setSearchid(rs.getLong("searchid"));
			q.setType(rs.getString("type"));
			q.setTitle(rs.getString("title"));
			q.setPic(rs.getString("pic"));
			q.setIdx(rs.getInt("idx"));
			q.setCreatetime(rs.getTimestamp("createtime"));
			q.setStatus(rs.getString("status"));
			return q;
		}

	}

	private static final String FIND_OPTION_BY_QUESTIONID = "select * from esite.es_interact_exam_question_option where questionid = ? and status != 'DEL'";

	@Override
	public List<ExamQuestionOption> findOptionsByQuestionid(long questionid)
	{
		Object[] params =
		{ questionid };
		return getJdbcTemplate().query(FIND_OPTION_BY_QUESTIONID, params, new OptionsRowmapper());
	}

	class OptionsRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ExamQuestionOption o = new ExamQuestionOption();
			o.setId(rs.getLong("id"));
			o.setQuestionid(rs.getLong("questionid"));
			o.setContent(rs.getString("content"));
			o.setPic(rs.getString("pic"));
			o.setIdx(rs.getInt("idx"));
			o.setCreatetime(rs.getTimestamp("createtime"));
			o.setStatus(rs.getString("status"));
			return o;
		}

	}

	private static final String SAVE_EXAM_RECORD = "insert into esite.es_feature_interact_exam_record(pageid,wbuid,searchid,createtime,ip,terminal,source,type)values(?,?,?,now(),?,?,?,?)";

	@Override
	public long saveExamReocrd(final long pageid, final long wbuid, final long examid, final String ip, final String terminal, final String source, final int type)
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(SAVE_EXAM_RECORD, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, pageid);
				ps.setLong(i++, wbuid);
				ps.setLong(i++, examid);
				ps.setString(i++, ip);
				ps.setString(i++, terminal);
				ps.setString(i++, source);
				ps.setInt(i++, type);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		return id;
	}

	private static final String SAVE_ANSWER_XZ = "insert into esite.es_feature_interact_exam_record_answer(recordid,questionid,answer,createtime)values(?,?,?,now())";

	@Override
	public int saveExamAnswerTK(long recordid, long questionid, String answer)
	{
		Object[] params =
		{ recordid, questionid, answer };
		return getJdbcTemplate().update(SAVE_ANSWER_XZ, params);
	}

	private static final String SAVE_ANSWER_TK = "insert into esite.es_feature_interact_exam_record_answer(recordid,questionid,optionid,createtime)values(?,?,?,now())";

	@Override
	public int saveExamAnswerXZ(long recordid, long questionid, long option)
	{
		Object[] params =
		{ recordid, questionid, option };
		return getJdbcTemplate().update(SAVE_ANSWER_TK, params);
	}

	private static final String FIND_EXAM_COUNT = "select count(id) from esite.es_feature_interact_exam_record where wbuid = ? and type=0 and searchid = ?";

	@Override
	public int findExamCount(long wbuid, long examid)
	{
		Object[] params =
		{ wbuid, examid };
		return getJdbcTemplate().queryForInt(FIND_EXAM_COUNT, params);
	}

	private static final String FIND_EXAM_COUNT_BY_WXUID = "select count(id) from esite.es_feature_interact_exam_record where wbuid = ? and type=1 and searchid = ?";

	@Override
	public int findExamCountByWxuid(long wxuid, long examid)
	{
		Object[] params =
		{ wxuid, examid };
		return getJdbcTemplate().queryForInt(FIND_EXAM_COUNT_BY_WXUID, params);
	}

	private static final String ADD_LOTTERY_CHANCE = "insert into esite.es_interact_lottery_user(wbuid,lid,totalnum)values(?,?,?) on duplicate key update totalnum = totalnum + ? ";

	@Override
	public int addLotteryChance(long wbuid, long lid, int chance)
	{
		Object[] params =
		{ wbuid, lid, chance, chance };
		return getJdbcTemplate().update(ADD_LOTTERY_CHANCE, params);
	}

	@Override
	public int deleteExam(long id)
	{
		String sql = "update es_interact_exam set status='DEL' where id=?";
		return getJdbcTemplate().update(sql, new Object[]
		{ id });
	}

	private static final String UPDATE_EXAM_QUESTION_OPTION = "update es_interact_exam_question_option set count = count + 1 where id = ?";

	@Override
	public int updateExamQuestionOption(long option)
	{
		return getJdbcTemplate().update(UPDATE_EXAM_QUESTION_OPTION, new Object[]
		{ option });
	}

	@Override
	public List<ExamRecord> findExamRecordList(long searchid, int start, int size, String source, long owner)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select r.*,u.username,wx.nickname nickname1 from es_feature_interact_exam_record r left join es_wx_user wx on r.wbuid = wx.id left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.searchid=? and r.type=1 ";
		list.add(owner);
		list.add(searchid);
		if (StringUtils.isNotEmpty(source))
		{
			sql += " and r.source =? ";
			list.add(source);
		}
		sql += " order by r.id desc limit ?,? ";
		list.add(start);
		list.add(size);
		return getJdbcTemplate().query(sql, list.toArray(), new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				ExamRecord record = new ExamRecord();
				record.setId(rs.getLong("id"));
				record.setCreatetime(rs.getTimestamp("createtime"));
				record.setIsshare(rs.getString("isshare"));
				record.setWbuid(rs.getLong("wbuid"));
				record.setSearchid(rs.getLong("searchid"));
				record.setIp(rs.getString("ip"));
				record.setSource(rs.getString("source"));
				record.setUsername(rs.getString("u.username"));
				record.setNickname(rs.getString("nickname1"));
				return record;
			}
		});
	}

	@Override
	public List<ExamRecord> findExamByRecord(long searchid)
	{
		String sql = "select distinct r.source from es_feature_interact_exam_record r left join es_wx_user u on u.id=r.wbuid where r.searchid=? and r.type=1 and r.source is not null";
		return getJdbcTemplate().query(sql, new Object[]
		{ searchid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				ExamRecord record = new ExamRecord();
				record.setSource(rs.getString("source"));
				return record;
			}
		});
	}

	class ExamRecordRowmapper implements RowMapper
	{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			ExamRecord record = new ExamRecord();
			record.setId(rs.getLong("id"));
			record.setCreatetime(rs.getTimestamp("createtime"));
			record.setIsshare(rs.getString("isshare"));
			record.setWbuid(rs.getLong("wbuid"));
			record.setSearchid(rs.getLong("searchid"));
			record.setIp(rs.getString("ip"));
			record.setSource(rs.getString("source"));
			record.setNickname(rs.getString("nickname"));
			record.setOpenid(rs.getString("openid"));
			return record;
		}
	}

	@Override
	public int findWxExamRecordListTotal(long searchid, String source, long owner)
	{
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(r.id) from es_feature_interact_exam_record r left join es_wx_user wx on r.wbuid = wx.id left join es_hy_user u on r.wbuid = u.wxuid and u.owner=?  where r.searchid=? and r.type=1";
		list.add(owner);
		list.add(searchid);
		if (StringUtils.isNotEmpty(source))
		{
			sql += " and r.source =? ";
			list.add(source);
		}
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	private static final String UPDATE_RULETYPE_BY_LOTTERYID = "update es_interact_lottery set ruletype = 'G' where id = ?";

	@Override
	public int updateRuletypeByLotteryid(long lotteryid)
	{
		Object[] params =
		{ lotteryid };
		return getJdbcTemplate().update(UPDATE_RULETYPE_BY_LOTTERYID, params);
	}

	@Override
	public int findExamRecordTotal(long searchid, int type, long owner)
	{
		String sql = "";
		if (type == 2)
		{
			sql = "select count(r.id) from es_feature_interact_exam_record r left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.searchid=? and r.type=2 ";
		} else if (type == -1)
		{
			sql = "select count(r.id) from es_feature_interact_exam_record r left join es_hy_user u on r.wbuid = u.wxuid  and u.owner=? where r.searchid=? and r.type=-1";
		}
		return getJdbcTemplate().queryForInt(sql, new Object[]
		{ owner, searchid });
	}

	@Override
	public List<ExamRecord> findNiExamRecordList(long searchid, int type, int start, int voteLimit, long owner)
	{
		String sql = "";
		if (type == 2)
		{
			sql = "select r.*,u.username,wb.nickname nickname1,wx.nickname wxnickname from es_feature_interact_exam_record r left join es_hy_user u on r.wbuid = u.id and u.owner=? left join es_sina_user wb on u.wbuid = wb.wbuid left join es_wx_user wx on u.wxuid = wx.id where r.searchid=? and r.type=2 order by r.id desc limit ?,? ";
		} else if (type == -1)
		{
			sql = "select r.*,u.username,'' nickname1,'' wxnickname from es_feature_interact_exam_record r left join es_hy_user u on r.wbuid = u.wxuid and u.owner=? where r.searchid=? and r.type=-1 order by r.id desc limit ?,? ";
		}
		return getJdbcTemplate().query(sql, new Object[]
		{ owner, searchid, start, voteLimit }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ExamRecord record = new ExamRecord();
				record.setId(rs.getLong("id"));
				record.setCreatetime(rs.getTimestamp("createtime"));
				record.setIsshare(rs.getString("isshare"));
				record.setWbuid(rs.getLong("wbuid"));
				record.setSearchid(rs.getLong("searchid"));
				record.setIp(rs.getString("ip"));
				record.setSource(rs.getString("source"));
				record.setNickname(rs.getString("nickname1"));
				int type = rs.getInt("r.type");
				if (type == 2)
				{
					record.setWxnickname(rs.getString("wxnickname"));
				}
				record.setUsername(rs.getString("u.username"));
				return record;
			}
		});
	}

	@Override
	public long addExam(final long ownerid, final String title)
	{
		final String sql = "insert into es_interact_exam(ownerid,title,createtime) values(?,?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, ownerid);
				ps.setString(i++, title);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int saveExamAnswerPx(long recordid, long questionid, long optionid, int index)
	{
		String sql = "insert into es_feature_interact_exam_record_answer(recordid,questionid,optionid,idx,createtime)values(?,?,?,?,now())";
		Object[] params =
		{ recordid, questionid, optionid, index };
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public String findExamType(long questionid)
	{
		String sql = "select type from esite.es_interact_exam_question where id=?";
		List<String> list = getJdbcTemplate().query(sql, new Object[]
		{ questionid }, new RowMapper()
		{

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				return rs.getString("type");
			}
		});
		if (list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateExamClean(long searchid)
	{
		String[] sql =
		{ "update  es_interact_exam_question_option  set count=0  where  questionid in (select id from es_interact_exam_question  where searchid=" + searchid + ")",
				"delete o from  es_feature_interact_exam_record  r left outer join es_feature_interact_exam_record_answer o on o.recordid=r.id where r.searchid=" + searchid,
				"delete from es_feature_interact_exam_record where searchid=" + searchid, "delete from es_interact_user_data where featureid=153 and hdid=" + searchid, };
		int[] rs = getJdbcTemplate().batchUpdate(sql);
		int sum = 0;
		for (int i : rs)
		{
			sum = sum + i;
		}
		return sum;
	}

	@Override
	public List<ExamResult> findResultList(long examid, long id)
	{
		return getJdbcTemplate().query("select * from es_interact_exam_result where ownerid=? and examid=? order by start asc,end asc", new Object[]
		{ id, examid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ExamResult r = new ExamResult();
				r.setId(rs.getLong("id"));
				r.setStart(rs.getInt("start"));
				r.setEnd(rs.getInt("end"));
				r.setContent(rs.getString("content"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setExamid(rs.getLong("examid"));
				return r;
			}
		});
	}

	@Override
	public long addExamResult(final ExamResult examResult, final long owner)
	{
		final String sql = "insert into es_interact_exam_result(examid,ownerid,start,end,content,createtime) values(?,?,?,?,?,now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator()
		{

			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException
			{
				PreparedStatement ps = arg0.prepareStatement(sql, new String[]
				{ "id" });
				int i = 1;
				ps.setLong(i++, examResult.getExamid());
				ps.setLong(i++, owner);
				ps.setInt(i++, examResult.getStart());
				ps.setInt(i++, examResult.getEnd());
				ps.setString(i++, examResult.getContent());
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().intValue();
		return id;
	}

	@Override
	public int delExamResult(long resultid, long id)
	{
		return getJdbcTemplate().update("delete from es_interact_exam_result where id=? and ownerid=?", new Object[]
		{ resultid, id });
	}

	@Override
	public int updateExamResult(ExamResult examResult, long id)
	{
		return getJdbcTemplate().update("update es_interact_exam_result set start=?,end=?,content=?,createtime=now() where id=? and ownerid=?", new Object[]
		{ examResult.getStart(), examResult.getEnd(), examResult.getContent(), examResult.getId(), id });
	}

	@Override
	public ExamResult findExamResult(long resultid, long id)
	{
		List<ExamResult> list = getJdbcTemplate().query("select * from es_interact_exam_result where ownerid=? and id=? order by start asc,end asc", new Object[]
		{ id, resultid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ExamResult r = new ExamResult();
				r.setId(rs.getLong("id"));
				r.setStart(rs.getInt("start"));
				r.setEnd(rs.getInt("end"));
				r.setContent(rs.getString("content"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setExamid(rs.getLong("examid"));
				return r;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public ExamResult findExamResult(long resultid)
	{
		List<ExamResult> list = getJdbcTemplate().query("select * from es_interact_exam_result where id=?", new Object[]{resultid }, new RowMapper()
		{

			public Object mapRow(ResultSet rs, int arg1) throws SQLException
			{
				ExamResult r = new ExamResult();
				r.setId(rs.getLong("id"));
				r.setStart(rs.getInt("start"));
				r.setEnd(rs.getInt("end"));
				r.setContent(rs.getString("content"));
				r.setCreatetime(rs.getTimestamp("createtime"));
				r.setExamid(rs.getLong("examid"));
				return r;
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}
}
