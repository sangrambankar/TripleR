package com.blakky.tripler.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blakky.tripler.R;
import com.blakky.tripler.adapter.DataAdapter;
import com.blakky.tripler.model.Android;
import com.blakky.tripler.restclient.IRestClient;
import com.blakky.tripler.restclient.RestUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class TripleRActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<Android> mAndroidArrayList;
    private DataAdapter mAdapter;
    private IRestClient mRestClient = null;
    private CompositeDisposable mCompositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_r);
        ButterKnife.bind(this);

        mCompositeDisposable = new CompositeDisposable();
        initRecyclerView();
        loadData();

    }

    public void initRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void loadData(){
        mRestClient = RestUtils.createRestClient();
        mCompositeDisposable.add(mRestClient.register()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        androids -> {
                            handleResponse(androids);
                        },
                        Throwable ->{
                            handleError(Throwable);
                        }
                ));


    }

    private void handleResponse(List<Android> androidList) {
        mAndroidArrayList = new ArrayList<>(androidList);
        mAdapter = new DataAdapter(mAndroidArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void handleError(Throwable error) {

        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
