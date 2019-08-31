package com.gxa.cdut.service.Impl;

import com.gxa.cdut.domain.*;
import com.gxa.cdut.mapper.MailVerifyMapper;
import com.gxa.cdut.mapper.detailMapper;
import com.gxa.cdut.mapper.systemDictionaryMapper;
import com.gxa.cdut.mapper.userInfoMapper;
import com.gxa.cdut.service.IDetailService;
import com.gxa.cdut.service.ISystemDictionaryService;
import com.gxa.cdut.service.IVerifyCodeService;
import com.gxa.cdut.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DetailServiceImpl implements IDetailService {
    @Autowired
    private com.gxa.cdut.mapper.detailMapper detailMapper;

    @Autowired
    private com.gxa.cdut.mapper.userInfoMapper userInfoMapper;

    @Autowired
    private IVerifyCodeService iVerifyCodeService;

    @Autowired
    private MailVerifyMapper mailVerifyMapper;

    @Autowired
    private com.gxa.cdut.mapper.systemDictionaryMapper systemDictionaryMapper;

    @Override
    public Detail selectById(int id) {
        Detail detail = detailMapper.selectById(id);
        detail.setEducationBackgroudItem(systemDictionaryMapper.selectByCurrentId(detail.getEducationBackground_id()));
        detail.setHouseConditionItem(systemDictionaryMapper.selectByCurrentId(detail.getHouseCondition_id()));
        detail.setIncomeGradeItem(systemDictionaryMapper.selectByCurrentId(detail.getIncomeGrade_id()));
        detail.setMarriageItem(systemDictionaryMapper.selectByCurrentId(detail.getMarriage_id()));
        detail.setKidCountItem(systemDictionaryMapper.selectByCurrentId(detail.getKidCount_id()));
        return detail;
    }

    @Override
    public Detail selectByName(String name) {
        UserInfo userInfo = userInfoMapper.selectByUsername(name);
        Detail detail = detailMapper.selectById(userInfo.getId());
        return detail;
    }

    @Override
    public int insert(Detail detail) {
        detailMapper.insert(detail);
        return 0;
    }

    @Override
    public int update(Detail detail) {
        if(detail!=null) {
            detailMapper.update(detail);
        }else{
            throw new RuntimeException("未知错误，参数不能为NULL！");
        }
        return 0;
    }

    @Override
    public Detail selectItems(int id) {

        return detailMapper.selectItems(id);
    }


    @Override
    public void bindPhone(String phoneNumber, String verifyCode) {
        // 先做验证码的校验 (一般关于验证码的都交给验证码相关服务)
        if (iVerifyCodeService.validate(phoneNumber,verifyCode)) {
            //如果校验成功,给当前用户绑定手机号和状态码
            Detail userInfo = selectById(UserContext.getLoginInfo().getId());
            //先判断当前用户是否已经绑定了手机
            if ( !userInfo.getIsBindPhone()) { //表示当前没有绑定手机
                //给当前用户添加状态码和手机号
                userInfo.setPhoneNumber(phoneNumber);
                userInfo.addState(BitStatesUtils.OP_BIND_PHONE);
                update(userInfo);
            }
        }else{
            throw new RuntimeException("绑定失败");
        }
    }

    /**
     * 绑定邮箱的实现
     */
    public void bindEmail(String uuid) {
        //根据uuid查村mailVerify对象
        MailVerify mailVerify = mailVerifyMapper.selectByUUID(uuid);
        if (null != mailVerify
                && DateUtil.getBetweenSecond(mailVerify.getSendTime(), new Date()) < SysConstant.EMAIL_VALID_DAY * 24 * 3600 ) {
            //如果有 且在有效期内 的用户没有绑定邮箱
            Detail userInfo = detailMapper.selectById(mailVerify.getLogininfo_id());
            if ( !userInfo.getIsBindEmail()) {
                //添加邮箱状态码  设置email属性
                userInfo.addState(BitStatesUtils.OP_BIND_EMAIL);
                userInfo.setEmail(mailVerify.getEmail());
                update(userInfo);
            }
        }else{
            throw new RuntimeException("验证邮箱地址错误!");
        }
    }



}
