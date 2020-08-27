package com.app.resource;

import com.app.domain.Book;
import com.app.exception.ResourceNotFoundException;
import com.app.service.IService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/v0/libraries")
@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
public class BookController {

    private final IService<Book> bookService;

    /**
     * fetch all books by serach criteria
     * @param key
     * @param pageable
     * @param searchText
     * @return
     */
    @GetMapping("/{key}/books/search/{searchText}")
    public ResponseEntity<Page<Book>> findAll(@PathVariable Long key, Pageable pageable, @PathVariable String searchText) {
        return new ResponseEntity<>(bookService.findAllByLibraryId(pageable, key), HttpStatus.OK);
    }

    /**
     * get all books
     * @param key
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/{key}/books")
    public ResponseEntity<Page<Book>> findAll(@PathVariable Long key, int pageNumber, int pageSize, String sortBy, String sortDir) {
        return new ResponseEntity<>(bookService.findAllByLibraryId(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                ), key
        ), HttpStatus.OK);
    }

    /**
     * get book by ID
     * @param key
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/{key}/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long key, @PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookService.findByBookIdAndLibraryId(id, key), HttpStatus.OK);
    }

    /**
     * add new book
     * @param key
     * @param book
     * @return
     */
    @PostMapping("/{key}/books")
    public ResponseEntity<Book> save(@PathVariable Long key, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.CREATED);
    }

    /**
     * updare existing book
     * @param key
     * @param book
     * @return
     */
    @PutMapping("/{key}/books")
    public ResponseEntity<Book> update(@PathVariable Long key, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.OK);
    }

    /**
     * delete a book
     * @param key
     * @param id
     * @return
     */
    @DeleteMapping("/{key}/books/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long key, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteById(id), HttpStatus.OK);
    }

    /**
     * get all languages
     * @return
     */
    @GetMapping("/{key}/books/languages")
    public  ResponseEntity<Set<String>> findAllLanguages() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("French", "Portuguese", "English", "Russian", "Hindi", "Arabic", "Spanish", "Chinese")), HttpStatus.OK);
    }

    /**
     * get all genres
     * @return
     */
    @GetMapping("/{key}/books/genres")
    public  ResponseEntity<Set<String>> findAllGenres() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Technology", "Science", "History", "Fantasy", "Biography", "Horror", "Romance")), HttpStatus.OK);
    }
}
