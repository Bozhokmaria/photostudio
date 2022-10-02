package com.solvd.dao.impl.mybatis;

import com.solvd.connection.MyBatisUtil;
import com.solvd.dao.interfaces.BaseBatisDAO;
import com.solvd.dao.interfaces.StudioDAO;
import com.solvd.model.Studio;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class StudioMapperDAO implements BaseBatisDAO<Studio> {
    private final static Logger LOGGER = LogManager.getLogger(StudioMapperDAO.class);

    @Override
    public Studio getById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Studio studio = session.selectOne("src.main.resources.mappers.StudioMapper.getById", id);
        session.close();
        return studio;
    }

    @Override
    public List<Studio> getAll() {
        List<Studio> studios = new LinkedList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        studios = session.selectList("src.main.resources.mappers.StudioMapper.getAll", studios);
        session.close();
        studios.forEach(LOGGER::info);
        return studios;
    }

    @Override
    public void add(Studio studio) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.StudioMapper.add", studio);
        session.commit();
        session.close();
    }

    @Override
    public void update(Studio studio) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.StudioMapper.update", studio);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.StudioMapper.delete", id);
        session.commit();
        session.close();
    }
}
