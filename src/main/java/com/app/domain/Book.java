package com.app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	
	@NotNull
	@Column(name = "title")
	private String title;
	
	@NotNull
	@Column(name = "author")
	private String author;
	
	@NotNull
	@Column(name = "cover_photo_url")
	private String coverPhotoURL;
	
	@NotNull
	@Column(name = "isbn_number")
	private Long isbnNumber;
	
	@NotNull
	@Column(name = "price")
	private Double price;
	
	@NotNull
	@Column(name = "language")
	private String language;

	@NotNull
	@Column(name = "genre")
	private String genre;

	@NotNull
	@Column(name = "borrowed")
	private Boolean borrowed;

	@NotNull
	@Column(name = "returned")
	private Boolean returned;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "library_id")
	private Library library;
}
