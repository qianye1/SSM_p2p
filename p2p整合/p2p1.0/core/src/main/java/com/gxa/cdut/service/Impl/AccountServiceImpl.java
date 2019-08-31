package com.gxa.cdut.service.Impl;

import com.gxa.cdut.domain.Acount;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.mapper.acountMapper;
import com.gxa.cdut.service.IAccountService;
import com.gxa.cdut.service.IVerifyCodeService;
import com.gxa.cdut.util.BitStatesUtils;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private com.gxa.cdut.mapper.acountMapper acountMapper;

    @Override
    public Acount selectById(int id) {
        Acount acount = acountMapper.selectById(id);
        return acount;
    }

    @Override
    public int insert(Acount acount) {
        acountMapper.insert(acount);
        return 0;
    }

    @Override
    public void updateById(long id,String money){
        BigDecimal moneyv =new BigDecimal(money);
        Acount accounts = acountMapper.selectById((int)id);
        Acount account = new Acount();
        account.setUsableAmount(accounts.getUsableAmount().add(moneyv));
        account.setId(accounts.getId());

        int res=acountMapper.updateById(account);
        if (0 == res) {
            throw new RuntimeException("充值失败");
        }
    }



}
