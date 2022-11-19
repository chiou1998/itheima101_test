package com.itheima.mm.dao;

import com.itheima.mm.pojo.Dict;

import java.util.List;
import java.util.Map;

public interface CityDao {
    List<Dict> findCityList(Map parameterMap);

    Dict findCurrentCity(String cityName);
}
