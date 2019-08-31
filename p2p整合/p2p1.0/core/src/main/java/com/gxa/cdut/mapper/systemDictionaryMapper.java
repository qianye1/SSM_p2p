package com.gxa.cdut.mapper;

import com.gxa.cdut.domain.SystemDictionary;
import com.gxa.cdut.util.Item;

import java.util.List;

public interface systemDictionaryMapper {

    List<Item> selectById(int id);

    Item selectByCurrentId(int id);

}
