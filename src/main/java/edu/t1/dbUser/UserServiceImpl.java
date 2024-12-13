package edu.t1.dbUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public UserDb getUserById(Integer id) throws SQLException {
        UserDb user = userRepo.getUserById(id);
        return user;
    }

    public double getUserLimit(Integer userId) throws SQLException {
        UserDb user = userRepo.getUserById(userId);
        return user.getSumLimit();
    }

    public UserDb setUserLimit(Integer userId, double sumLimit) throws SQLException {
        UserDb user = userRepo.getUserById(userId);
        user.setSumLimit(sumLimit);
        userRepo.save(user);
        return user;
    }

    public UserDb upUserLimit(Integer userId, double sumLimit) throws SQLException {
        UserDb user = userRepo.getUserById(userId);
        user.setSumLimit(user.getSumLimit()+sumLimit);
        userRepo.save(user);
        return user;
    }

    public UserDb downUserLimit(Integer userId, double sumLimit) throws SQLException {
        UserDb user = userRepo.getUserById(userId);
        user.setSumLimit(user.getSumLimit()-sumLimit);
        userRepo.save(user);
        return user;
    }

    public UserDb createUser(Integer id, String username, double sumLimit) throws SQLException {
        UserDb user = new UserDb(id, username, sumLimit);
        user = userRepo.save(user);
        return user;
    }

    public List<UserDb> getAllBy() throws SQLException {
        return userRepo.getAllBy();
    }

    // каждый день в 00:00 обновляем всем лимиты
    @Scheduled(cron="0 0 0 * * *")
    public void updateAllLimits() throws SQLException {
        List<UserDb> allUsers= getAllBy();
        for (UserDb user : allUsers) {
            user.setSumLimit(10000);
            userRepo.save(user);
        }
    }

}
