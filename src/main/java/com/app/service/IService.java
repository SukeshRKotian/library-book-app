package com.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface to perform crud operation
 * @param <T>
 */
public interface IService<T> {

	Page<T> findAll(Pageable pageable, String searchText);

	Page<T> findAll(Pageable pageable);

	Page<T> findAllByLibraryId(Pageable pageable, Long libraryId);

	T findByBookIdAndLibraryId(Long bookId, Long libraryId);

	T findById(Long id);
	
	T saveOrUpdate(T t);
	
	String deleteById(Long id);
}
