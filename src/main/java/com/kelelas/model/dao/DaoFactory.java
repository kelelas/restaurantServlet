package com.kelelas.model.dao;

import com.kelelas.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract IngredientDao createIngredientDao();
    public abstract DishDao createDishDao();
    public abstract HistoryDao createHistoryDao();
    public abstract UserDao createUserDao();
    public abstract BillDao createBillDao();
    public abstract CartDao createCartDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){

                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }


}
