package com.simplane.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class BoardVO {

    private Long boardid;
    private String title;
    private String content;
    private Date regDate;
    private String imagePath;
}
