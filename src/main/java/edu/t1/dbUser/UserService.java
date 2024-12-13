package edu.t1.dbUser;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public UserDb getUserById(Integer id) throws SQLException;
    public double getUserLimit(Integer userId) throws SQLException;
    public UserDb setUserLimit(Integer userId, double sumLimit) throws SQLException;
    public UserDb upUserLimit(Integer userId, double sumLimit) throws SQLException;
    public UserDb downUserLimit(Integer userId, double sumLimit) throws SQLException;
    public UserDb createUser(Integer id, String username, double sumLimit) throws SQLException;
    public List<UserDb> getAllBy() throws SQLException;
    public void updateAllLimits() throws SQLException;
}
