package service;

import model.Profile;

public interface User {
	Profile login(String userId,String password);
}
