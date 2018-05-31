package com.eve.bookmarks.dao;

import com.eve.bookmarks.entitys.BookMark;
import org.springframework.data.repository.CrudRepository;

public interface BookMarkRepository extends CrudRepository<BookMark, Long> {

}
