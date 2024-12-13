package edu.t1.dbUser;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<UserDb, Integer> {
    public UserDb getUserById(Integer id) throws SQLException;
    public List<UserDb> getAllBy() throws SQLException;
}
