package com.solvd.dao.impl.mybatis;

import com.solvd.connection.MyBatisUtil;
import com.solvd.dao.interfaces.CountryDAO;
import com.solvd.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class CountryMapperDAO implements CountryDAO {
    private final static Logger LOGGER = LogManager.getLogger(CityMapperDAO.class);

    @Override
    public Country getById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Country country = session.selectOne("src.main.resources.mappers.CountryMapper.getById", id);
        session.close();
        return country;
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = new LinkedList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        countries = session.selectList("src.main.resources.mappers.CountryMapper.getAll", countries);
        session.close();
        countries.forEach(LOGGER::info);
        return countries;
    }

    @Override
    public void add(Country country) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.CountryMapper.add", country);
        session.commit();
        session.close();
    }

    @Override
    public void update(Country country) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.CountryMapper.update", country);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.CountryMapper.delete", id);
        session.commit();
        session.close();
    }
}
