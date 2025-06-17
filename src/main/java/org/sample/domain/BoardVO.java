package org.sample.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	private Long boardid;
	private Long productid;
	private Long userid;
	private String status;
	private Date regDate;
}
