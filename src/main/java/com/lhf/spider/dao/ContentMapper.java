package com.lhf.spider.dao;

import com.lhf.spider.entity.po.Content;
import java.util.List;

public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    Content selectByPrimaryKey(Long id);

    List<Content> selectAll();

    int updateByPrimaryKey(Content record);
}