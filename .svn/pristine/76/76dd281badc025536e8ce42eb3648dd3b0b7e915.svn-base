package org.zbt.testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.IUserService;


@RunWith(SpringJUnit4ClassRunner.class)		//琛ㄧず缁ф壙浜哠pringJUnit4ClassRunner绫�
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	@Resource
//	private IUserService userService;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}
	@Resource
	private IUserService userService;
	@Test
	public void test1() {
		UserInfo uservo = new UserInfo();
		uservo.setMobilephone("18729501216");
		uservo.setOpenid("1");
		uservo = userService.selectUser(uservo);
		if (uservo != null) {
			 System.out.println("yes");
		}
	}
}
