package com.alexengrig.dirandfile.repository;

import com.alexengrig.dirandfile.domain.SnapDirectory;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий снимков директорий
 *
 * @author G. Alex
 */
public interface SnapDirectoryRepository extends CrudRepository<SnapDirectory, Long> {

    /**
     * Получить снимки директорий отсортированные по дате по убыванию
     */
    Iterable<SnapDirectory> findByOrderByDateDesc();

}
