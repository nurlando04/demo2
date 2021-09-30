package com.example.demo2.dao;
import com.example.demo2.model.Tovar;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
public interface TovarDao {

    int insertTovar(UUID id, Tovar tovar);

    default int insertTovar(Tovar tovar){
        UUID id=UUID.randomUUID();
        return insertTovar(id,tovar);
    }

    List<Tovar> selectAllTovars();

    Optional<Tovar> selectTovarById(UUID id);

    int deleteTovarById(UUID id);

    int updateTovarById(UUID id, Tovar tovar);


}
