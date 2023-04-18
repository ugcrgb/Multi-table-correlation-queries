package Test;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.User;
import utils.MyBatisUtil;

import static org.junit.Assert.*;

public class AddUserTest {
    //创建一个当前类的日志对象
    private Logger logger=Logger.getLogger(AddUserTest.class);

    @Test
    public void getUid() {
        SqlSession sqlSession = null;
        int count = 0;
        try {
            sqlSession = MyBatisUtil.createSqlSession();
            User user = new User();
            user.setUname("test001");
            user.setUage(23);
            count = sqlSession.insert("addUser",user);
            //模拟异常，进行回滚
            //int i = 2/0;
            sqlSession.commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            sqlSession.rollback();
            logger.warn("向users表添加数据失败");
            count = 0;
        }finally{
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        logger.warn("向users表添加: " + count+"条记录");

    }
}