package edu.t1.task8;

import edu.t1.dbUser.Pays;
import edu.t1.dbUser.PaysService;
import edu.t1.dbUser.UserDb;
import edu.t1.dbUser.UserService;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.sql.SQLException;

@Endpoint
public class PayEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UserService userService;

    private PaysService paysService;

    @Autowired
    public PayEndpoint(UserService userService, PaysService paysService) {
        this.userService = userService;
        this.paysService = paysService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserLimitRequest")
    @ResponsePayload
    public GetUserLimitResponse getUserLimitRequest(@RequestPayload GetUserLimitRequest request) throws SQLException {
        GetUserLimitResponse response = new GetUserLimitResponse();
        UserDb userDb = userService.getUserById(request.getUserId());
        if (userDb == null) {
            userDb = userService.createUser(request.getUserId(), "user name "+request.getUserId(), 10000);
        }
        User res = new User();
        res.setUserId(userDb.getId());
        res.setUserName(userDb.getUserName());
        res.setSumLimit(userDb.getSumLimit());
        response.setUser(res);
        response.setDescription("Ура, пользователь найден/создан");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "execPayRequest")
    @ResponsePayload
    public ExecPayResponse execPayRequest(@RequestPayload ExecPayRequest request) throws SQLException {
        ExecPayResponse response = new ExecPayResponse();

        try {
            UserDb userDb = userService.getUserById(request.getUserId());
            if (userDb.getSumLimit() > 0 && request.getSumPay() <= userDb.getSumLimit()) {
                Pays pay = paysService.setPay(request.getUserId(),request.getSumPay(),request.getAccDebet(), request.getAccCredit());
                System.out.println("pay: "+pay);
                userService.downUserLimit(request.getUserId(), request.getSumPay());
                response.setStatus("success");
                response.setDescription("Платеж исполнен: "+pay.toString());
            }
            else throw new RuntimeException("Ошибка проведения платежа");
        } catch (RuntimeException e) {
            response.setStatus("error");
            response.setDescription(e.getMessage());
        }
        return response;
    }
}