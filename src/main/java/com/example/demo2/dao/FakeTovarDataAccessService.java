package com.example.demo2.dao;
import com.example.demo2.model.Tovar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTovarDataAccessService implements TovarDao{

    private static List<Tovar> DB = new ArrayList<>();

    @Override
    public List<Tovar> selectAllTovars() {
        return DB;
    }

    @Override
    public Optional<Tovar> selectTovarById(UUID id) {
        return DB.stream().filter(tovar ->tovar.getId()==id).findFirst();
    }

    @Override
    public int deleteTovarById(UUID id) {
        Optional<Tovar> objectMaybe = selectTovarById(id);
        if(objectMaybe.isEmpty()){
            return 0;
        }
        DB.remove(objectMaybe.get());
        return 1;
    }

    @Override
    public int updateTovarById(UUID id, Tovar updatetovar) {
        return selectTovarById(id).map(tovar -> {
            int indexofObjectToUpdate = DB.indexOf(tovar);
            if (indexofObjectToUpdate>=0){
                DB.set(indexofObjectToUpdate,new Tovar(id,updatetovar.getName(),updatetovar.getWeight(),updatetovar.getShape(),updatetovar.isPresence(),updatetovar.getPathToImage()));
                return 1;
            }
            return 0;

        }).orElse(0);
    }
    @Override
    public int insertTovar(UUID id,Tovar tovar) {
        DB.add(new Tovar(id,tovar.getName(),tovar.getWeight(),tovar.getShape(), tovar.isPresence(),tovar.getPathToImage()));
        return 1;
    }

}
