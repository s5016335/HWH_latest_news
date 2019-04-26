package com.example.jiancheng.http_test.View.Latest_news;

import com.example.jiancheng.http_test.Data.Item;
import com.example.jiancheng.http_test.base.BasePresenter;
import com.example.jiancheng.http_test.base.BaseView;

import java.util.List;

public interface NewsContract {

    interface View extends BaseView<Presenter> {

        void ShowNewList(List<Item> itemList);

    }

    interface Presenter extends BasePresenter {
        void loadNews();
    }


}
