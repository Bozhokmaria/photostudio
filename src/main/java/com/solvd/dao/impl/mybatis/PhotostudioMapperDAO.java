package com.solvd.dao.impl.mybatis;

import com.solvd.connection.MyBatisUtil;
import com.solvd.dao.interfaces.BaseBatisDAO;
import com.solvd.dao.interfaces.PhotostudioDAO;
import com.solvd.model.Photostudio;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class PhotostudioMapperDAO implements BaseBatisDAO<Photostudio> {
    private final static Logger LOGGER = LogManager.getLogger(PhotostudioMapperDAO.class);

    @Override
    public Photostudio getById(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        Photostudio photostudio = session.selectOne("src.main.resources.mappers.PhotostudioMapper.getById", id);
        session.close();
        return photostudio;
    }

    @Override
    public List<Photostudio> getAll() {
        List<Photostudio> photostudios = new LinkedList<>();
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        photostudios = session.selectList("src.main.resources.mappers.PhotostudioMapper.getAll", photostudios);
        session.close();
        return photostudios;
    }

    @Override
    public void add(Photostudio photostudio) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.PhotostudioMapper.add", photostudio);
        session.commit();
        session.close();
    }

    @Override
    public void update(Photostudio photostudio) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.PhotostudioMapper.update", photostudio);
        session.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.PhotostudioMapper.delete", id);
        session.commit();
        session.close();
    }
}
