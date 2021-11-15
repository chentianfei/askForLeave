package com.ctf.service.impl;

import com.ctf.bean.Level;
import com.ctf.bean.Nation;
import com.ctf.bean.Office;
import com.ctf.dao.SystemDataDao;
import com.ctf.service.SystemDataService;

import java.util.List;

public class SystemDataServiceImpl implements SystemDataService {

    SystemDataDao systemDataDao = new SystemDataDao();

    @Override
    public List<Office> queryOffice() {
        return systemDataDao.queryOffice();
    }

    @Override
    public List<Level> queryLevel(){
        return systemDataDao.queryLevel();
    }

    @Override
    public List<Nation> queryNaiton() {
        return systemDataDao.queryNation();
    }
}
