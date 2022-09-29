package com.solvd.dao.impl.mybatis;

import com.solvd.connection.MyBatisUtil;
import com.solvd.dao.interfaces.LocationDAO;
import com.solvd.model.Location;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class LocationMapperDAO implements LocationDAO {
    private final static Logger LOGGER = LogManager.getLogger(LocationMapperDAO.class);

    @Override
    public Location getById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Location location = session.selectOne("src.main.resources.mappers.LocationMapper.getById", id);
        session.close();
        return location;
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations = new LinkedList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        locations = session.selectList("src.main.resources.mappers.LocationMapper.getAll", locations);
        session.close();
        locations.forEach(LOGGER::info);
        return locations;
    }

    @Override
    public void add(Location location) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.LocationMapper.add", location);
        session.commit();
        session.close();
    }

    @Override
    public void update(Location location) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.LocationMapper.update", location);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.LocationMapper.delete", id);
        session.commit();
        session.close();
    }
}
