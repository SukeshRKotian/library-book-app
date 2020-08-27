package com.app.service.impl;

import com.app.domain.Library;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.LibraryRepository;
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
 * Class describing Service implementation of library
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryServiceImpl implements IService<Library> {

    private final LibraryRepository libraryRepository;

    /**
     * get all libraries by search text
     * @param pageable
     * @param searchText
     * @return
     */
    @Override
    public Page<Library> findAll(Pageable pageable, String searchText) {
        return libraryRepository.findAllLibraries(pageable, searchText);
    }

    /**
     * list all libraries
     * @param pageable
     * @return
     */
    @Override
    public Page<Library> findAll(Pageable pageable) {
        return libraryRepository.findAll(pageable);
    }

    /**
     * find all by library id
     * @param pageable
     * @param libraryId
     * @return
     */
    @Override
    public Page<Library> findAllByLibraryId(Pageable pageable, Long libraryId) {
        return libraryRepository.findAllLibraries(pageable, "");
    }

    /**
     *
     * @param bookId
     * @param libraryId
     * @return
     */
    @SneakyThrows
    @Override
    public Library findByBookIdAndLibraryId(Long bookId, Long libraryId) {
        return libraryRepository.findById(libraryId)
                .orElseThrow(()-> new ResourceNotFoundException("Library not found :: " + libraryId));
    }

    /**
     * find library by ID
     * @param id
     * @return
     */
    @SneakyThrows
    @Override
    public Library findById(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Library not found :: " + id));
    }

    /**
     * save or update a library detail
     * @param library
     * @return
     */
    @Override
    @Transactional
    public Library saveOrUpdate(Library library) {
        return libraryRepository.save(library);
    }

    /**
     * remove library by ID
     * @param id
     * @return
     */
    @Override
    @Transactional
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            libraryRepository.deleteById(id);
            jsonObject.put("message", "Library deleted successfully");
        } catch (JSONException e) {
            log.error("Error occurred while deleting library "+id, e);
        }
        return jsonObject.toString();
    }

}
