package org.sample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
    private Long productid;
    private String description;
    private long price;
    private String condition;
    private String brand;
    private String modelname;
}