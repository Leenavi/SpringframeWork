package org.zerock.dto;

import java.sql.Timestamp;
import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
public class BoardVO {

   private int num;
   private String pass;
   private String name;
   private String email;
   private String title;
   private String content;
   private int readCount;       
   private Timestamp writeDate;
}
