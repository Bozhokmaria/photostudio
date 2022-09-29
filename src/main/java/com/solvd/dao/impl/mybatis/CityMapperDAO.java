package com.solvd.dao.impl.mybatis;

import com.solvd.connection.MyBatisUtil;
import com.solvd.dao.interfaces.CityDAO;
import com.solvd.model.City;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
public class CityMapperDAO implements CityDAO {
    private final static Logger LOGGER = LogManager.getLogger(CityMapperDAO.class);

    @Override
    public City getById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        City city = session.selectOne("src.main.resources.mappers.CityMapper.getById", id);
        session.close();
        return city;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new LinkedList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        cities = session.selectList("src.main.resources.mappers.CityMapper.getAll", cities);
        session.close();
        cities.forEach(LOGGER::info);
        return cities;
    }

    @Override
    public void add(City city) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.CityMapper.add", city);
        session.commit();
        session.close();
    }

    @Override
    public void update(City city) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.CityMapper.update", city);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.CityMapper.delete", id);
        session.commit();
        session.close();
    }
}