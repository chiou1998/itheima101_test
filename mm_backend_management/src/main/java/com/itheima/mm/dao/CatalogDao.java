package com.itheima.mm.dao;

import com.itheima.mm.pojo.Catalog;
import com.itheima.mm.pojo.Tag;

import java.util.List;

public interface CatalogDao {

    Long findCountByCourseId(Integer id);

    List<Catalog> findCatalogListByCourseId(Integer course);
}
