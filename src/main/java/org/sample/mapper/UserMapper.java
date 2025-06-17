package org.sample.mapper;

import org.apache.ibatis.annotations.Param;
import org.sample.domain.UserVO;

public interface UserMapper {
	
	int insertUser(UserVO user);

    UserVO selectUserById(@Param("userid") int userid);

    int updateUser(UserVO user);

    int deleteUser(@Param("userid") int userid);
}
