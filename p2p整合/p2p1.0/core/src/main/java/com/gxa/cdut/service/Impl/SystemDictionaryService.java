package com.gxa.cdut.service.Impl;

import com.gxa.cdut.domain.SystemDictionary;
import com.gxa.cdut.mapper.systemDictionaryMapper;
import com.gxa.cdut.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemDictionaryService implements ISystemDictionaryService {

    @Autowired
    private com.gxa.cdut.mapper.systemDictionaryMapper systemDictionaryMapper;

    @Override
    public SystemDictionary selectAll() {
        SystemDictionary systemDictionary = new SystemDictionary();
        systemDictionary.setIncomeGrade(systemDictionaryMapper.selectById(1));
        systemDictionary.setEducationBackground(systemDictionaryMapper.selectById(2));
        systemDictionary.setMarriage(systemDictionaryMapper.selectById(3));
        systemDictionary.setKidCount(systemDictionaryMapper.selectById(4));
        systemDictionary.setHouseCondition(systemDictionaryMapper.selectById(5));

        return systemDictionary;
    }
}
