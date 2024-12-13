package edu.t1.dbUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface PaysRepo extends CrudRepository<Pays, Integer> {
    public Pays getByUserId(int id);
}
