package com.lbn.jpa;

import com.lbn.pojo.Customer;
import com.lbn.utils.JPAUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.IOException;
import java.util.List;

public class TestJPA {
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void before() {
        em = JPAUtils.getEntityManager();
        //获取事务对象
        tx = em.getTransaction();
        //开启事务
        tx.begin();
    }

    @After
    public void after() {
        //提交事务
        tx.commit();
        //释放资源
        em.close();
    }

    @Test
    public void test1() throws IOException {
        for (int i = 1; i < 11; i++) {
            Customer c = new Customer();
            c.setCustName("传智播客" + i);
            //保存操作
            em.persist(c);
        }

    }

    @Test
    public void test2() throws IOException {
        Customer customer = em.find(Customer.class, 1L);
        System.out.println(customer);
    }

    @Test
    public void test3() throws IOException {
        Customer customer = em.find(Customer.class, 2L);
        customer.setCustName("大法师");
        em.merge(customer);
        System.out.println(customer);
    }

    @Test
    public void test4() throws IOException {
        Customer customer = em.find(Customer.class, 2L);
        em.remove(customer);
        System.out.println(customer);
    }

    @Test
    public void test5() throws IOException {

        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
        list.forEach(c-> System.out.println(c));
    }

    @Test
    public void test6() throws IOException {

        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        query.setFirstResult(2);
        query.setMaxResults(5);
        List list = query.getResultList();
        list.forEach(c-> System.out.println(c));
    }

    @Test
    public void test7() throws IOException {

        String jpql = "from Customer where custName like ?";
        Query query = em.createQuery(jpql);
        query.setParameter(1,"%1%");
        List list = query.getResultList();
        list.forEach(c-> System.out.println(c));
    }

    @Test
    public void test8() throws IOException {

        String jpql = "from Customer where custName like ? order by id desc ";
        Query query = em.createQuery(jpql);
        query.setParameter(1,"%1%");
        List list = query.getResultList();
        list.forEach(c-> System.out.println(c));
    }

    @Test
    public void test9() throws IOException {

        String jpql = "select count(*) from Customer where custName like ? order by id desc ";
        Query query = em.createQuery(jpql);
        query.setParameter(1,"%1%");
        Object result = query.getSingleResult();
        System.out.println(result);
    }
}
