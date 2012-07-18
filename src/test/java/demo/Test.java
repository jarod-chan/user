package demo;

import java.util.List;

import cn.fyg.user.domain.model.User;

public class Test {
	public static void main(String[] args) {
		UserQuery userQuery=null;
		List<User> users=userQuery.username().like("xxx")
				.realname().equal("yyy").list();
	}
}
