package Test;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.User;
import utils.MyBatisUtil;

import static org.junit.Assert.*;

public class UpdateUserTest {
    //创建一个当前类的日志对象
    private Logger logger=Logger.getLogger(UpdateUserTest.class);
    @Test
    public void getUid() {
        SqlSession sqlSession = null;
        int count = 0;
        try {
            sqlSession = MyBatisUtil.createSqlSession();
            User user = new User();
            user.setUid(2);
            user.setUname("李四");
            user.setUage(28);
            count = sqlSession.update("updateUser",user);
            //模拟异常，进行回滚
            //int i = 2/0;
            sqlSession.commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            sqlSession.rollback();
            logger.warn("向users表修改数据失败");
            count = 0;
        }finally{
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        logger.warn("向users表修改: " + count+"条记录");

    }
}