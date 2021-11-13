package com.ctf.service;

import com.ctf.bean.Level;
import com.ctf.bean.Office;

import java.util.List;

public interface SystemDataService {
    //查询office 单位名单
    List<Office> queryOffice();
    //查询level 职级名单
    List<Level> queryLevel();
}
