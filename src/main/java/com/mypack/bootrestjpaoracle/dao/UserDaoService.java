package com.mypack.bootrestjpaoracle.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mypack.bootrestjpaoracle.model.Users_sample;


@Component
public class UserDaoService {
	
	private static List<Users_sample> users = new ArrayList<>();

	private static int usersCount = 3;

	static {
		users.add(new Users_sample(1, "Adam", new Date()));
		users.add(new Users_sample(2, "Eve",  new Date()));
		users.add(new Users_sample(3, "Jack", new Date()));
	}

	public List<Users_sample> findAll() {
		return users;
	}

	public Users_sample save(Users_sample user) {
		if (user.getId() == 0) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public Users_sample findOne(int id) {
		for (Users_sample user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public Users_sample deleteById(int id) {
		Iterator<Users_sample> iterator = users.iterator();
		while (iterator.hasNext()) {
			Users_sample user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
