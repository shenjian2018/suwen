package com.sj_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
	SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

	@Before
	public void addUser() {
		simpleAccountRealm.addAccount("sj", "123","admin","user");
	}

	@Test
	public void testAuthentication() {
		// 1. 构建secutityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);

		// 2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();

		// 3.usernamepasswordtoken
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("sj", "123");
		subject.login(usernamePasswordToken);
		subject.checkRoles("admin","user");

		System.out.println("isAuthenticated:" + subject.isAuthenticated());
		
		
		subject.logout();
		System.out.println("isAuthenticated:" + subject.isAuthenticated());
		
		
	}

}
