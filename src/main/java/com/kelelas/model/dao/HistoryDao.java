package com.kelelas.model.dao;

import com.kelelas.model.dto.HistoryDTO;
import com.kelelas.model.entity.History;

import java.util.List;

public interface HistoryDao extends GenericDao<History> {
    List<HistoryDTO> getLocaleStories(String locale, int offset, int amount);
    List<HistoryDTO> getLocaleStoriesByStatusAndUserId(String locale, int UserId, int status);
    List<HistoryDTO> getLocaleStoriesByStatus(String locale, int status);
    List<HistoryDTO> getLocaleStoriesByUserId(String locale, int UserId);
    int numberOfRowsInTable();
}
