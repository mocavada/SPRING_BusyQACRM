package com.busyqa.crm.repo;

import com.busyqa.crm.model.clients.Lead;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface LeadRepository extends UserBaseRepository<Lead> {

    List<Lead> findAllByDtypeAndClientStatus(String type, String status);

    List<Lead> findAllByDtypeAndClientStatusOrderByIdDesc(String type, String status);


}




