package com.adriesavana.mobileappstest.module.search;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.adriesavana.mobileappstest.R;
import com.adriesavana.mobileappstest.module.doctorlist.PageDoctorList;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;

/**
 * Created by adri on 8/9/16.
 */

public class PageSearchArea extends Activity implements SearchView {

    private RelativeLayout mBtnToList;
    private ImageView mBtnClear;
    private ArrayAdapter<String> adapter;
    private ProgressDialog mLoadingDialog;
    private SearchPresenter mPresenter;

    //These values show in autocomplete
    String item[]={
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
    };

    private AutoCompleteTextView mTxtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_area);
        initComponent();
        mPresenter = new SearchPresenterImpl(this);
        mPresenter.loadData();
    }

    private void initComponent() {
        mBtnToList = (RelativeLayout) findViewById(R.id.btnToDoctorList);
        mBtnClear = (ImageView) findViewById(R.id.image_view_clear);
        mTxtSearch = (AutoCompleteTextView) findViewById(R.id.edit_text_query);
        RxView.clicks(mBtnToList).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                startActivityToListDoctor();
            }
        });

        RxView.clicks(mBtnClear).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mTxtSearch.setText("");
                mBtnClear.setVisibility(View.GONE);
            }
        });

        mTxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mBtnClear.setVisibility(editable.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    private void startActivityToListDoctor() {
        startActivity(new Intent(this, PageDoctorList.class));
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setMessage("Loading...");
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog.isShowing()) mLoadingDialog.dismiss();
    }

    @Override
    public void loadData(String[] item) {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
        mTxtSearch.setAdapter(adapter);
    }
}
