package com.hongyangdemo.orm;

import android.content.Context;

import com.hongyangdemo.orm.domain.User;
import com.j256.ormlite.dao.Dao;

/**
 * author： xiongdejin
 * date: 2017/9/1
 * describe:
 */

public class UserDao {
    private Context context;
    private Dao<User, Integer> userDaoOpe;
    private MyDatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public UserDao(Context context){
        this.context = context;
        try{
            helper = MyDatabaseHelper.getHelper(context);
            userDaoOpe = helper.getDao(User.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void add(User user){
        try{
            userDaoOpe.create(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
