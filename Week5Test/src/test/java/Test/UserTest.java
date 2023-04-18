package Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import pojo.User;
import utils.MyBatisUtil;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    //创建一个当前类的日志对象
    private Logger logger=Logger.getLogger(UserTest.class);


  //  @Test
    public void userFindByIdTest() {
        //读取文件名
        String resources = "mybatis-config.xml";
        //创建流
        Reader reader = null;
        try {
            //读取mybatis-config.xml文件内容到reader对象中
            reader = Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis数据库,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper = new
                SqlSessionFactoryBuilder().build(reader);
        //创建SqlSession实例
        SqlSession session = sqlMapper.openSession();
        //传入参数查询，返回结果

        User user = session.selectOne("findById", 1);
        //输出结果
        logger.info("姓名:"+user.getUname()+",年龄:"+user.getUage());
        //关闭session
        session.close();
    }

    @Test
    public void showUser() {
        //创建SqlSession实例
        SqlSession session = MyBatisUtil.createSqlSession();;
        //传入参数查询，返回结果
        List<User> userList =session.selectList("showAllUser");
        //输出结果
        for(User user: userList){
            logger.debug("ID: " + user.getUid() + " 姓名: " + user.getUname()+" 年龄:"+user.getUage());
        }
        //关闭session
        session.close();
    }
}