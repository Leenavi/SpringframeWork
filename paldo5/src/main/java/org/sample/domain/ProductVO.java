package org.sample.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {

	private Long productid;
	private String modelName;
	private String description;
	private Long price;
	private String condition;
	private String brand;
}
