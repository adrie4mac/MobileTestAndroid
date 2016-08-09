package com.adriesavana.mobileappstest.module.doctorlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adriesavana.mobileappstest.R;
import com.adriesavana.mobileappstest.component.DoctorItem;
import com.adriesavana.mobileappstest.component.LoadMoreListItem;
import com.adriesavana.mobileappstest.module.detail.PageDetail;
import com.adriesavana.mobileappstest.network.gson.GList;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

/**
 * Created by adri on 8/8/16.
 */

public class PageDoctorList extends Activity implements DoctorListView {

    private RecyclerView mList;
    private FastItemAdapter<IItem> adapter = new FastItemAdapter<>();
    private LoadMoreListItem mLoadMoreItem = new LoadMoreListItem();

    private DoctorListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list);
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(adapter);
        mPresenter = new DoctorListPresenterImpl(this);
        mPresenter.getDataList();

    }

    @Override
    public void setNextPage(boolean isActive) {
    }

    @Override
    public void addDoctor(GList mDetails) {
        adapter.add(new DoctorItem(this, mDetails, new DoctorItem.ItemListener() {
            @Override
            public void openDetail(int id) {
                openPageDetail(id);
            }
        }));
    }

    @Override
    public void showLoading() {
        adapter.add(mLoadMoreItem);
    }

    @Override
    public void hideLoading() {
        adapter.remove(adapter.getAdapterPosition(mLoadMoreItem));
    }

    private void openPageDetail(int id) {
        Intent intent = new Intent(this, PageDetail.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

}
