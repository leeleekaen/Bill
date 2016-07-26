package bill.zts.com.bill.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import bill.zts.com.bill.R;
import bill.zts.com.bill.presenter.IView.IMianView;
import bill.zts.com.bill.presenter.IView.IRefreshView;
import bill.zts.com.bill.presenter.MainPresenter;
import bill.zts.com.bill.ui.adapter.DataAdapter;
import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.activity.BaseActivity;
import mvp.zts.com.mvp_base.ui.activity.BaseSwipeRefreshActivity;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MainActivity2 extends BaseActivity<MainPresenter> implements IMianView,NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.app_bar_SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private DataAdapter mDataAdapter;
    private  List<Integer> lis_int = new ArrayList<Integer>();


    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this,this);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getCurrentMonthDatas();

    }
    @Override
    protected void intiData() {

    }

    @Override
    public void onBackPressed() {
         if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
             mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initView() {

        setTitle("!");

        mSwipeRefreshLayout.setEnabled(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, DatePickerActivity.class);
                startActivity(intent);

            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initRecycleView();
    }

    private void initRecycleView() {
        recyclerview.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        mDataAdapter = new DataAdapter(mContext,lis_int);
        recyclerview.setAdapter(mDataAdapter);
    }


    @Override
    public void fillInitData(List mData) {
        mDataAdapter.insertedAllItem(mData);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void appendMoreDataToView(List mData) {

    }

    @Override
    public void hasNoMoreData() {

    }
}