package com.gxa.cdut.domain;

import com.gxa.cdut.util.Item;

import java.util.List;

public class SystemDictionary {

    private List<Item> incomeGrade;
    private List<Item> educationBackground;
    private List<Item> marriage;
    private List<Item> kidCount;
    private List<Item> houseCondition;
    private List<Item> userFileType = null;

    public List<Item> getIncomeGrade() {
        return incomeGrade;
    }

    public void setIncomeGrade(List<Item> incomeGrade) {
        this.incomeGrade = incomeGrade;
    }

    public List<Item> getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(List<Item> educationBackground) {
        this.educationBackground = educationBackground;
    }

    public List<Item> getMarriage() {
        return marriage;
    }

    public void setMarriage(List<Item> marriage) {
        this.marriage = marriage;
    }

    public List<Item> getKidCount() {
        return kidCount;
    }

    public void setKidCount(List<Item> kidCount) {
        this.kidCount = kidCount;
    }

    public List<Item> getHouseCondition() {
        return houseCondition;
    }

    public void setHouseCondition(List<Item> houseCondition) {
        this.houseCondition = houseCondition;
    }

    public List<Item> getUserFileType() {
        return userFileType;
    }

    public void setUserFileType(List<Item> userFileType) {
        this.userFileType = userFileType;
    }
}
