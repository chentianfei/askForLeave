package com.ctf.dao;
import com.ctf.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来执行：Insert\Update\Delete语句
     *
     * @return 如果返回-1,说明执行失败<br/>返回其他表示影响的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            int updateCounts = queryRunner.update(connection, sql, args);
            connection.commit();
            return updateCounts;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
        return -1;
    }

   /**
     * 查询返回一个javaBean的sql语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection =JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
        return null;
    }

    //用于查询某一行的某一个数据
    public Object[] queryForANumber(String sql, Object... args) {
        Connection connection =JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            return queryRunner.query(connection, sql, new ArrayHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
        return null;
    }


    /**
     * 查询返回多个javaBean的sql语句
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = JDBCUtils.getConnection();
        try {
            con.setAutoCommit(false);
            return queryRunner.query(con, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con);
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql   执行的sql语句
     * @param args  sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql, Object... args){

        Connection conn = JDBCUtils.getConnection();

        try {
            conn.setAutoCommit(false);
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return null;

    }
    
    /*
     * @Description :执行返回一列多行的sql语句
     * @param: sql 执行的sql语句
     * @param: args sql对应的参数值
     * @return java.util.List<java.lang.Object>
     * @Author tianfeichen
     * @Date 2021/12/1 11:34
     **/
    public List<Object> queryForOneCol(String sql,Object... args){

        Connection conn = JDBCUtils.getConnection();

        try {
            conn.setAutoCommit(false);
            return queryRunner.query(conn,sql, new ColumnListHandler("person_id"),args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn);
        }
        return null;

    }
    
}
