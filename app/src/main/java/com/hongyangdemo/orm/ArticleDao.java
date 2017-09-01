package com.hongyangdemo.orm;

import android.content.Context;

import com.hongyangdemo.orm.domain.Article;
import com.hongyangdemo.orm.domain.User;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * author： xiongdejin
 * date: 2017/9/1
 * describe:
 */

public class ArticleDao {
    private Dao<Article, Integer> articleDaoOpe;
    private MyDatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public ArticleDao(Context context) {
        try {
            helper = MyDatabaseHelper.getHelper(context);
            articleDaoOpe = helper.getDao(Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个Article
     *
     * @param article
     */
    public void add(Article article) {
        try {
            articleDaoOpe.create(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一个Article
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Article getArticleWithUser(int id) {
        Article article = null;
        try {
            article = articleDaoOpe.queryForId(id);
            helper.getDao(User.class).refresh(article.getUser());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过Id得到一篇文章
     *
     * @param id
     * @return
     */
    public Article get(int id) {
        Article article = null;
        try {
            article = articleDaoOpe.queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 通过UserId获取所有的文章
     *
     * @param userId
     * @return
     */
    public List<Article> listByUserId(int userId) {
        try {
            return articleDaoOpe.queryBuilder().where().eq("user_id", userId).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
