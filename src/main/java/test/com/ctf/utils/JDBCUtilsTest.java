package test.com.ctf.utils; 

import com.ctf.utils.JDBCUtils;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* JDBCUtils Tester. 
* 
* @author <Authors name> 
* @since <pre>11月 19, 2021</pre> 
* @version 1.0 
*/ 
public class JDBCUtilsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getConnection() 
* 
*/ 
@Test
public void testGetConnection() throws Exception { 
//TODO: Test goes here...
    System.out.println(JDBCUtils.getConnection());
} 

/** 
* 
* Method: close(Connection conn) 
* 
*/ 
@Test
public void testClose() throws Exception { 
//TODO: Test goes here... 
} 


} 
