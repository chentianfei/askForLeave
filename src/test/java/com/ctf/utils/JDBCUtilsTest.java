package com.ctf.utils;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JDBCUtilsTest {

    @Test
    public void getConnection() {
        System.out.println(JDBCUtils.getConnection());
    }

    @Test
    public void close() {
        Connection con =  JDBCUtils.getConnection();
        JDBCUtils.close(con);
    }
}
