package org.sample.domain;

import lombok.Data;

@Data
public class UserVO {
    private Long userid;
    private String nickname;
    private String pwd;
    private String email;
}