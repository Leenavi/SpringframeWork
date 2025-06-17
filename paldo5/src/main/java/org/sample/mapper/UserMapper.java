package org.sample.mapper;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.UserVO;

public interface UserMapper {
	
	public void insertUser(UserVO user);
	public UserVO readUser(@Param("userid") Long userid);
	public int updateUser(UserVO user);
	public int deleteUser(@Param("userid") Long userid);
	public int getUserById(@Param("userid") Long userid);
}
