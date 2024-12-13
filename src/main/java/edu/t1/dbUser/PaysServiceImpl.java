package edu.t1.dbUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PaysServiceImpl implements PaysService {
    private final PaysRepo paysRepo;

    @Autowired
    public PaysServiceImpl(PaysRepo paysRepo) {
        this.paysRepo = paysRepo;
    }

    public Pays setPay(Integer userId, double sumPay, String accDt, String accCt) throws SQLException {
        Pays pays = new Pays();
        pays.setUserId(userId);
        pays.setSumPay(sumPay);
        pays.setAccDt(accDt);
        pays.setAccCt(accCt);
        pays = paysRepo.save(pays);
        System.out.println("pays: "+pays);
        return pays;
    }

}
