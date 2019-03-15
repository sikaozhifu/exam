package com.school.service.impl;

import com.school.dao.RecordMapper;
import com.school.entity.Record;
import com.school.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;
    @Override
    public Integer insertRecord(Record record) {
        return recordMapper.insert(record);
    }
}
