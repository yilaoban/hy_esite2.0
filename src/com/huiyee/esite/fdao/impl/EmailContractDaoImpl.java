package com.huiyee.esite.fdao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IEmailContractDao;
import com.huiyee.esite.model.EmailContract;
import com.huiyee.esite.model.EmailContractRecord;
public class EmailContractDaoImpl extends AbstractDao implements IEmailContractDao {

	
	
	
	private static final String SAVE_FETRUE_CONTRACT = "insert into esite.es_feature_email_contract (pageid,createtime) values(?,now())";

    public long saveEmailContract(final long pageid) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FETRUE_CONTRACT, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
    }
    
    private static final String SAVE_FETRUE_CONTRACT_RECORD = "insert into esite.es_feature_email_contract_record (pageid,contaracid,email,ip,terminal,source,createtime) values(?,?,?,?,?,?,now())";

    public long saveEmailContractRecord(final long pageid,final long contractid,final String email, final String ip,final String terminal,final String source) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
                PreparedStatement ps = arg0.prepareStatement(SAVE_FETRUE_CONTRACT_RECORD, new String[] { "id" });
                int i = 1;
                ps.setLong(i++, pageid);
                ps.setLong(i++, contractid);
                ps.setString(i++, email);
                ps.setString(i++, ip);
                ps.setString(i++, terminal);
                ps.setString(i++, source);
                return ps;
            }
        }, keyHolder);
        long id = keyHolder.getKey().intValue();
        return id;
    }
    
    private static final String FIND_EMAIL_CONTRACT_ID = "select * from esite.es_feature_email_contract where id=? ";

    @Override
    public EmailContract findEmailContractById(long id) {
        Object[] params = { id };
        List<EmailContract> list = getJdbcTemplate().query(FIND_EMAIL_CONTRACT_ID, params, new EmailContractRowmapper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    
    private static final String FIND_EMAIL_CONTRACT_RECORD_ID = "select * from esite.es_feature_email_contract_record where contaracid=? and email=?";

    @Override
    public EmailContractRecord findEmailContractRecordByEmail(long contaracid,String email) {
        Object[] params = { contaracid,email };
        List<EmailContractRecord> list = getJdbcTemplate().query(FIND_EMAIL_CONTRACT_RECORD_ID, params, new EmailContractRecordRowmapper());
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    class EmailContractRecordRowmapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmailContractRecord record=new EmailContractRecord();
            record.setId(rs.getLong("id"));
            record.setEmail(rs.getString("email"));
            record.setContractid(rs.getLong("contractid"));
            record.setIp(rs.getString("ip"));
            record.setPageid(rs.getLong("pageid"));
            record.setSource(rs.getString("source"));
            record.setTerminal(rs.getString("terminal"));
            return record;
        }
    }
    class EmailContractRowmapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmailContract contract=new EmailContract();
            contract.setId(rs.getLong("id"));
            contract.setName(rs.getString("name"));
            return contract;
        }
    }
    
    @Override
    public int updateEmailContractName(String name,long id) {
        String sql="update esite.es_feature_email_contract set name=? where id=?";
        return getJdbcTemplate().update(sql,new Object[]{name,id});
    }
}
