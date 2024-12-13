package edu.t1.dbUser;

import java.sql.SQLException;

public interface PaysService {
    public Pays setPay(Integer userId, double sumPay, String accDt, String accCt) throws SQLException;
}
