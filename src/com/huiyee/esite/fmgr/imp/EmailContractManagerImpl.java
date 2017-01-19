package com.huiyee.esite.fmgr.imp;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.Feature121Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IEmailContractDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.EmailContract;
import com.huiyee.esite.model.EmailContractRecord;
import com.huiyee.esite.model.UserInfo;
public class EmailContractManagerImpl extends AbstractFeatureManager {
    private IEmailContractDao emailContractDao;
    public void setEmailContractDao(IEmailContractDao emailContractDao) {
        this.emailContractDao = emailContractDao;
    }
    @Override
    public IDto config(long fid) {
        Feature121Dto dto=new Feature121Dto();
       // EmailContract contract=emailContractDao.findEmailContractById(fid);
        dto.setFid(fid);
        return dto;
    }
    @Override
    public IDto config(long fid, Account account) {
        Feature121Dto dto=new Feature121Dto();
        EmailContract contract=emailContractDao.findEmailContractById(fid);
        dto.setName(contract.getName());
        return dto;
    }
    @Override
    public long add(long pageid, long featureid,String featurename) {
        long fid = emailContractDao.saveEmailContract(pageid);
        return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
    }
    @Override
    public String configSub(long featureid, IDto dto, Account account) {
        Feature121Dto d = (Feature121Dto) dto;
        String result = "N";
        int re=emailContractDao.updateEmailContractName(d.getName(), d.getFid());
        if(re>0)
            result = "Y";
        return result;
    }
    @Override
    public String dynamicAction(long uid, DynamicActionDto dto) {
        String result = "N";
        EmailContractRecord record = emailContractDao.findEmailContractRecordByEmail(dto.getContractid(), dto
                .getEmail());
        if (record != null) {
            return "C";
        }
       // UserInfo u = getDynamicActionRecordDao().findUserInofByUid(uid);
        long entityid = emailContractDao.saveEmailContractRecord(dto.getPageid(), dto
                .getContractid(), dto.getEmail(), dto.getIp(), dto.getTerminal(), dto.getSource());
        result = "Y";
        return result;
    }
}
