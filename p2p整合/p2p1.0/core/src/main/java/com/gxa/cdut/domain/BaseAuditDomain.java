package com.gxa.cdut.domain;

import com.gxa.cdut.util.BaseDomain;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

/**
 * 抽取需要审核对象的基类
 */
public abstract class BaseAuditDomain extends BaseDomain {

    public static final int STATE_NORMAL = 0; // 待审核
    public static final int STATE_AUDIT = 1; // 审核通过
    public static final int STATE_REJECT = 2; // 审核拒绝


    protected int state;     // 审核状态
    protected String remark; // 审核备注
    protected Date auditTime;// 审核时间
    protected Date applyTime;// 申请时间
    protected UserInfo applier; // 申请人
    protected UserInfo auditor;// 审核人

    protected int applier_id;
    protected int auditor_id;


    public String getStateDisplay() {
        switch (state) {
            case STATE_NORMAL:
                return "待审核";
            case STATE_AUDIT:
                return "审核通过";
            case STATE_REJECT:
                return "审核拒绝";
            default:
                return "";
        }
    }

    public int getApplier_id() {
        return applier.getId();
    }

    public void setApplier_id(int applier_id) {
        this.applier_id = applier_id;
    }

    public int getAuditor_id() {
        return auditor.getId();
    }

    public void setAuditor_id(int auditor_id) {
        this.auditor_id = auditor_id;
    }

    public static int getStateNormal() {
        return STATE_NORMAL;
    }

    public static int getStateAudit() {
        return STATE_AUDIT;
    }

    public static int getStateReject() {
        return STATE_REJECT;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public UserInfo getApplier() {
        return applier;
    }

    public void setApplier(UserInfo applier) {
        this.applier = applier;
    }

    public UserInfo getAuditor() {
        return auditor;
    }

    public void setAuditor(UserInfo auditor) {
        this.auditor = auditor;
    }
}
