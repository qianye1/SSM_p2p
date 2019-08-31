package com.gxa.cdut.domain;

import com.gxa.cdut.util.BitStatesUtils;
import com.gxa.cdut.util.Item;

public class Detail {

    private int id;
    private int version = 0;
    private long bitState = 0;
    private String realName = "";
    private String idNumber = "";
    private String phoneNumber = "";
    private int incomeGrade_id =2;
    private int marriage_id = 6;
    private int kidCount_id = 8;
    private int educationBackground_id = 4;
    private int authScore = 0;
    private int houseCondition_id = 11;
    private int realauthid = 0;
    private String email = "";

    private Item incomeGradeItem;
    private Item marriageItem;
    private Item kidCountItem;
    private Item educationBackgroudItem;
    private Item houseConditionItem;

    // 添加绑定的状态码
    public void addState(Long state) {

        bitState = BitStatesUtils.addState(this.bitState, state);
    }

    // 移除状态码
    public void  removeState(Long state) {

        bitState = BitStatesUtils.removeState(this.bitState, state);
    }

    // 判断是否已经绑定了手机
    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_PHONE);
    }

    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_BIND_EMAIL);
    }

    public boolean getIsBasicInfo() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_USER_INFO);
    }
    public boolean getIsRealAuth() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_REAL_AUTH);
    }
    public boolean getIsVedioAuth() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_VEDIO_AUTH);
    }
    public boolean getIsBidRequest() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
    }
    public boolean getIsBindBank() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_BIND_BANK);
    }
    public boolean getIsWithRaw() {
        return BitStatesUtils.hasState(this.bitState,
                BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);
    }


    public Item getIncomeGradeItem() {
        return incomeGradeItem;
    }

    public void setIncomeGradeItem(Item incomeGradeItem) {
        this.incomeGradeItem = incomeGradeItem;
    }

    public Item getMarriageItem() {
        return marriageItem;
    }

    public void setMarriageItem(Item marriageItem) {
        this.marriageItem = marriageItem;
    }

    public Item getKidCountItem() {
        return kidCountItem;
    }

    public void setKidCountItem(Item kidCountItem) {
        this.kidCountItem = kidCountItem;
    }

    public Item getEducationBackgroudItem() {
        return educationBackgroudItem;
    }

    public void setEducationBackgroudItem(Item educationBackgroudItem) {
        this.educationBackgroudItem = educationBackgroudItem;
    }

    public Item getHouseConditionItem() {
        return houseConditionItem;
    }

    public void setHouseConditionItem(Item houseConditionItem) {
        this.houseConditionItem = houseConditionItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getBitState() {
        return bitState;
    }

    public void setBitState(long bitState) {
        this.bitState = bitState;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIncomeGrade_id() {
        return incomeGrade_id;
    }

    public void setIncomeGrade_id(int incomeGrade_id) {
        this.incomeGrade_id = incomeGrade_id;
    }

    public int getMarriage_id() {
        return marriage_id;
    }

    public void setMarriage_id(int marriage_id) {
        this.marriage_id = marriage_id;
    }

    public int getKidCount_id() {
        return kidCount_id;
    }

    public void setKidCount_id(int kidCount_id) {
        this.kidCount_id = kidCount_id;
    }

    public int getEducationBackground_id() {
        return educationBackground_id;
    }

    public void setEducationBackground_id(int educationBackground_id) {
        this.educationBackground_id = educationBackground_id;
    }

    public int getAuthScore() {
        return authScore;
    }

    public void setAuthScore(int authScore) {
        this.authScore = authScore;
    }

    public int getHouseCondition_id() {
        return houseCondition_id;
    }

    public void setHouseCondition_id(int houseCondition_id) {
        this.houseCondition_id = houseCondition_id;
    }

    public int getRealauthid() {
        return realauthid;
    }

    public void setRealauthid(int realauthid) {
        this.realauthid = realauthid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
