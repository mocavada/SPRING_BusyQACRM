package com.busyqa.crm.repo;

import com.busyqa.crm.model.clients.Lead;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface LeadRepository extends UserBaseRepository<Lead> {

    Optional<Lead> findByEmail(String email);
    Optional<Lead> findById(Long id);
}


//    @Override
//    public List<Client> getAllLead() {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
//        Root<Client> client = cq.from(Client.class);
//
//        cq.select(client).where(cb.equal(client.get("clientStatus"), "Lead"));
//        TypedQuery<Client> q = entityManager.createQuery(cq);
//        List<Client> allLeads = q.getResultList();
//        return allLeads;
//    }
//


//    @Override
//    public List<Client> getAllClient() {
//        String jpql = "SELECT c FROM Client c ORDER BY c.id";
//        return (List<Client>) entityManager.createQuery(jpql)
//                .getResultStream()
//                .collect(Collectors.toList());
//    }



//    @Override
//    public boolean clientExist(String email) {
//        String jpql = "from Client as a WHERE a.email =: email";
//        int count = entityManager.createQuery(jpql)
//                .setParameter("email",email)
//                .getResultList().size();
//        return count > 0;
//    }