package com.kelelas.model.service;

import com.kelelas.model.dao.DaoFactory;
import com.kelelas.model.dao.HistoryDao;
import com.kelelas.model.dto.HistoryDTO;
import com.kelelas.model.entity.History;
import com.kelelas.model.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public class HistoryService {
    DaoFactory factory;
    HistoryDao dao;

    public HistoryService() {
        this.factory = DaoFactory.getInstance();
        this.dao = factory.createHistoryDao();
    }

    public History getStoryById(int id){
        return dao.findById(id).orElseThrow(DBException::new);
    }

    public void save(History history){
        dao.create(history);
    }

    public void update(History history){
        history.setDate(LocalDateTime.now());
        dao.update(history);}

    public List<HistoryDTO> getLocaleStoriesByUserId(HttpServletRequest request, int userId){
        return dao.getLocaleStoriesByUserId(request.getSession().getAttribute("lang").toString(), userId);
    }

    public List<HistoryDTO> getLocaleStoriesByStatus(HttpServletRequest request, int status){
        return dao.getLocaleStoriesByStatus(request.getSession().getAttribute("lang").toString(), status);
    }

    public List<HistoryDTO> getLocaleStoriesByStatusAndUserId(HttpServletRequest request, int status,  int userId){
        return dao.getLocaleStoriesByStatusAndUserId(request.getSession().getAttribute("lang").toString(), userId,  status);
    }

    public List<HistoryDTO> getLocaleStories(HttpServletRequest request, int offset, int amountOfRecords){
        return dao.getLocaleStories(request.getSession().getAttribute("lang").toString(), offset, amountOfRecords);
    }

    public int numberOfRowsInTable(){
        return dao.numberOfRowsInTable();
    }

    public void setAutoCommitFalse(){
        dao.setAutoCommitFalse();
    }

    public void commit() {
        dao.commit();
    }

    public void rollback() {
        dao.rollback();
    }
}
