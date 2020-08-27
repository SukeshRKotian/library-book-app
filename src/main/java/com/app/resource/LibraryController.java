package com.app.resource;

import com.app.domain.Library;
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

import javax.ws.rs.Path;

@RestController
@RequestMapping("/v0")
@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
public class LibraryController {

    private final IService<Library> libraryService;

    /**
     * get all libraries based on search criteria
     * @param pageable
     * @param searchText
     * @return
     */
    @GetMapping("/libraries/search/{searchText}")
    public ResponseEntity<Page<Library>> findAll(Pageable pageable, @PathVariable String searchText) {
        return new ResponseEntity<>(libraryService.findAll(pageable, searchText), HttpStatus.OK);
    }

    /**
     * get all libraries
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/libraries")
    public ResponseEntity<Page<Library>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        return new ResponseEntity<>(libraryService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        ), HttpStatus.OK);
    }


    /**
     * find library by id
     * @param id
     * @return
     */
    @GetMapping("/libraries/{id}")
    public ResponseEntity<Library> findById(@PathVariable Long id) {
        return new ResponseEntity<>(libraryService.findById(id), HttpStatus.OK);
    }

    /**
     * save library
     * @param library
     * @return
     */
    @PostMapping("/libraries")
    public ResponseEntity<Library> save(@RequestBody Library library) {
        return new ResponseEntity<>(libraryService.saveOrUpdate(library), HttpStatus.CREATED);
    }

    /**
     * update library
     * @param library
     * @return
     */
    @PutMapping("/libraries")
    public ResponseEntity<Library> update(@RequestBody Library library) {
        return new ResponseEntity<>(libraryService.saveOrUpdate(library), HttpStatus.CREATED);
    }

    /**
     * deleting give library
     * @param id
     * @return
     */
    @DeleteMapping("/libraries/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(libraryService.deleteById(id), HttpStatus.OK);
    }

}
