package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.customer;

public class mydao {
EntityManagerFactory e=Persistence.createEntityManagerFactory("dev");
EntityManager m=e.createEntityManager();
EntityTransaction t=m.getTransaction();

public void save(customer c) {
	t.begin();
	m.persist(c);
	t.commit();
}

public customer fetchByEmail(String email){
    List<customer>list=m.createQuery("select  x from customer x where email=?1").setParameter(1,email)
    .getResultList();
    		if(list.isEmpty())
    			return null;
    		else 
    			return list.get(0);
}
public customer fetchByMobile(long mobile) {

    List<customer>list=m.createQuery("select  x from customer x where mobile=?1").setParameter(1,mobile)
    .getResultList();
    		if(list.isEmpty())
    			return null;
    		else 
    			return list.get(0);

}
}
