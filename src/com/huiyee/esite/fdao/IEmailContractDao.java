package com.huiyee.esite.fdao;

import com.huiyee.esite.model.EmailContract;
import com.huiyee.esite.model.EmailContractRecord;

public interface IEmailContractDao {
    public long saveEmailContract(final long pageid);
    public EmailContract findEmailContractById(long id);
    public int updateEmailContractName(String name,long id);
    public long saveEmailContractRecord(long pageid,final long contractid,final String email, final String ip,final String terminal,final String source);
    public EmailContractRecord findEmailContractRecordByEmail(long contaracid,String email);
}
