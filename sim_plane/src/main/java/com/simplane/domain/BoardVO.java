package com.simplane.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoardVO {

    private Long boardid;
    private String title;
    private String content;
    private String imagePath;
    private Date regDate;
}
