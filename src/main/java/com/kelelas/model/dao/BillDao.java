package com.kelelas.model.dao;

import com.kelelas.model.dto.BillDTO;
import com.kelelas.model.entity.Bill;

import java.util.List;

public interface BillDao extends GenericDao<Bill> {
    List<BillDTO> getLocaleBillsByStatusAndUserId(String locale, int UserId, int status);
}
