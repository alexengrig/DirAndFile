package com.alexengrig.dirandfile.repository;

import com.alexengrig.dirandfile.domain.SnapDirectory;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository of SnapDirectory
 *
 * @author G. Alex
 */
public interface SnapDirectoryRepository extends CrudRepository<SnapDirectory, Long> {
    Iterable<SnapDirectory> findByOrderByDateDesc();


}
