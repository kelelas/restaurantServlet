package com.kelelas.model.service;

import com.kelelas.controller.config.ConstantsConfig;
import com.kelelas.model.dao.BillDao;
import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dto.BillDTO;
import com.kelelas.model.entity.Bill;
import com.kelelas.model.entity.History;
import com.kelelas.model.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class BillService {
    DaoFactory factory;
    BillDao dao;

    public BillService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createBillDao();
    }

    public void saveNewBill(History history){
        Bill bill = new Bill.Builder()
                .id(history.getId())
                .date(LocalDateTime.now())
                .price(history.getPrice())
                .dishes(history.getDishes())
                .status(ConstantsConfig.getIntProperty("status.waitingForPay"))
                .userId(history.getUserId())
                .build();
        dao.create(bill);
    }

    public void update(Bill bill){
        bill.setDate(LocalDateTime.now());
        dao.update(bill);}

    public List<BillDTO> getLocaleBillsByStatusAndUserId(HttpServletRequest request, int status, int userId){
        return dao.getLocaleBillsByStatusAndUserId(request.getSession().getAttribute("lang").toString(), userId,  status);
    }

    public Bill getBillById(int id) throws Exception {
        return dao.findById(id).orElseThrow(DBException::new);
    }

    public void setAutoCommitFalse()  {
        dao.setAutoCommitFalse();
    }

    public void commit()  {
        dao.commit();
    }

    public void rollback()  {
        dao.rollback();
    }
}
