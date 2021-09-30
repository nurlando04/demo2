package com.example.demo2.dao;

import com.example.demo2.model.Tovar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class TovarDataAccessService implements TovarDao {

    private final JdbcTemplate jdbcTemplate;

    public TovarDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertTovar(UUID id,Tovar tovar) {
        String sql="";
        sql = "INSERT into tovar VALUES ('" + id +
                    "','" + tovar.getName() + "'," + tovar.getWeight() + "," + tovar.getShape()[0] + "," + tovar.getShape()[1] + "," + tovar.getShape()[2] + "," + tovar.isPresence() + ",'" + tovar.getPathToImage() + "')";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Tovar> selectAllTovars() {
        String sql = "Select * FROM tovar";
        return jdbcTemplate.query(sql, new RowMapper<Tovar>() {
            @Override
            public Tovar mapRow(ResultSet resultSet, int i) throws SQLException {
                UUID tovarid = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                int weight = resultSet.getInt("weight");
                int len = resultSet.getInt("len");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                boolean presence = resultSet.getBoolean("presence");
                String pathToImage=resultSet.getString("pathtoimage");
                return new Tovar(tovarid, name, weight, new int[]{len, width, height}, presence,pathToImage);
            }
        });
    }



    @Override
    public Optional<Tovar> selectTovarById(UUID id) {
        String sql = "Select * FROM tovar WHERE id = '"+id+"'";
        Tovar tovar = jdbcTemplate.queryForObject(sql, new RowMapper<Tovar>() {
            @Override
            public Tovar mapRow(ResultSet resultSet, int i) throws SQLException {
                UUID tovarid = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                int weight = resultSet.getInt("weight");
                int len = resultSet.getInt("len");
                int width = resultSet.getInt("width");
                int height = resultSet.getInt("height");
                boolean presence = resultSet.getBoolean("presence");
                String pathToImage=resultSet.getString("pathtoimage");
                return new Tovar(tovarid, name, weight, new int[]{len, width, height}, presence,pathToImage);
            }
        });
        return Optional.ofNullable(tovar);
    }

    @Override
    public int deleteTovarById(UUID id) {
        String sql = "DELETE from tovar where id='" + id+"'";

        return jdbcTemplate.update(sql);
    }

    @Override
    public int updateTovarById(UUID id, Tovar tovar) {
        String allvalues="";
        if(tovar.getName()!="null"){allvalues+="name='"+tovar.getName()+"',";}
        if(tovar.getWeight()!=0){allvalues+="weight="+tovar.getWeight()+",";}
        if(tovar.getShape().equals(new int[]{0,0,0})) {
            if (tovar.getShape()[0] != 0) {
                allvalues += "len=" + tovar.getShape()[0] + ",";
            }
            if (tovar.getShape()[1] != 0) {
                allvalues += "width=" + tovar.getShape()[1] + ",";
            }
            if (tovar.getShape()[2] != 0) {
                allvalues += "height=" + tovar.getShape()[2] + ",";
            }
        }
        if(tovar.isPresence()!=null){allvalues+="presence="+tovar.isPresence()+",";}
        if(tovar.getPathToImage()!="null"){allvalues+="pathtoimage='"+tovar.getPathToImage()+"'";}
        String sql = "UPDATE tovar SET "+allvalues+" where id = '"+id+"'";
        return jdbcTemplate.update(sql);
    }
}
