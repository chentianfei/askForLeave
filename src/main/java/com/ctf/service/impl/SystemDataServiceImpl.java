package com.ctf.service.impl;

import com.ctf.dao.SystemDataDao;
import com.ctf.service.SystemDataService;

import java.util.List;

public class SystemDataServiceImpl implements SystemDataService {

    SystemDataDao systemDataDao = new SystemDataDao();

    @Override
    public List<String> querySelectData(String dataName) {

        return null;
    }
}
