package com.app.service.impl;

import com.app.domain.Book;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.BookRepository;
import com.app.service.IService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Class describing Service implementation of book
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IService<Book> {


	private final BookRepository bookRepository;

	/**
	 * find all books by search criteria
	 * @param pageable
	 * @param searchText
	 * @return
	 */
	@Override
	public Page<Book> findAll(Pageable pageable, String searchText) {
		return bookRepository.findAllBooks(pageable, searchText);
	}

	/**
	 * get all books
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	/**
	 * get all books by library id
	 * @param pageable
	 * @param libraryId
	 * @return
	 */
	@Override
	public Page<Book> findAllByLibraryId(Pageable pageable, Long libraryId) {
		return bookRepository.findByLibraryId(pageable, libraryId);
	}

	/**
	 * get book by libraryId and bookId
	 * @param bookId
	 * @param libraryId
	 * @return
	 */
	@SneakyThrows
	@Override
	public Book findByBookIdAndLibraryId(Long bookId, Long libraryId) {
		return bookRepository.findByBookIdAndLibraryId(bookId, libraryId)
				.orElseThrow(()->new ResourceNotFoundException("Book not found :: " + bookId));
	}

	/**
	 * get book by ID
	 * @param id
	 * @return
	 */
	@Override
	public Book findById(Long id) {
		return null;
	}

	/**
	 * save a bok details
	 * @param book
	 * @return
	 */
	@Transactional
	@Override
	public Book saveOrUpdate(Book book) {
		return bookRepository.save(book);
	}

	/**
	 * Delete a book
	 * @param id
	 * @return
	 */
	@Transactional
	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			bookRepository.deleteById(id);
			jsonObject.put("message", "Book deleted successfully");
		} catch (JSONException e) {
			log.error("Error occurred while deleting book "+id, e);
		}
		return jsonObject.toString();
	}

}
