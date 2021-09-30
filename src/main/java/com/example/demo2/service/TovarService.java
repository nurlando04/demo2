package com.example.demo2.service;

import com.example.demo2.dao.TovarDao;
import com.example.demo2.model.Tovar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TovarService {

    private TovarDao tovarDao;
    @Autowired
    public TovarService(@Qualifier("postgres") TovarDao tovarDao){
        this.tovarDao= tovarDao;
    }

    public int addTovar(Tovar tovar){
        return tovarDao.insertTovar(tovar);
    }

    public List<Tovar> getAllTovars(){
        return tovarDao.selectAllTovars();
    }

    public Optional<Tovar> getTovarById(UUID id){
        return tovarDao.selectTovarById(id);
    }

    public int deleteTovar(UUID id){
        return tovarDao.deleteTovarById(id);
    }

    public int updateTovar(UUID id, Tovar newtovar){
        return tovarDao.updateTovarById(id,newtovar);
    }
}
