package com.app.repository;

import com.app.domain.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends PagingAndSortingRepository<Library, Long> {

    @Query("FROM Library l WHERE l.name LIKE %:searchText% OR l.id LIKE %:searchText% OR l.address LIKE %:searchText% ORDER BY l.id ASC")
    Page<Library> findAllLibraries(Pageable pageable, @Param("searchText") String searchText);
}

