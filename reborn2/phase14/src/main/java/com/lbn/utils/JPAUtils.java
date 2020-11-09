package com.lbn.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    private static EntityManagerFactory factory;


    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }


    /**
     * 获取实体类管理器
     * @return
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }


}
