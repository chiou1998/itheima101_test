package com.itheima.mm.service;

import com.itheima.mm.dao.CityDao;
import com.itheima.mm.pojo.Dict;
import com.itheima.mm.utils.LocationUtil;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityService {
    public Map findCityList(Map parameterMap) throws IOException {
        //1先获取城市列表（可能是所有城市列表也可能是首屏推荐城市列表）
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CityDao cityDao = sqlSession.getMapper(CityDao.class);
        List<Dict> dictList = cityDao.findCityList(parameterMap);

        //获取客户端携带过来的请求参数location
        String location = (String) parameterMap.get("location");
        //根据经纬度获取当前城市的名字 调用高德地图的api
        String cityName = LocationUtil.parseLocation(location);
        //调用cityDao的方法更具当前城市名称，查询当前城市信息
        Dict dict = cityDao.findCurrentCity(cityName);

        Map resultMap = new HashMap<>();
        resultMap.put("citys",dictList);
        resultMap.put("location",dict);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return resultMap;
    }

}
