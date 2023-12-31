package com.bway.bwayproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name="book_tbl")

public class Book {

	@Id
	@GeneratedValue
	
	private int id;
	private String name;
	private String bookno;
	private String sem;
}
