package com.docking.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.docking.recycle.adapter.GridAdapter;
import com.docking.recycle.adapter.LinearAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by docking on 16/4/25 22:17.
 * 参考: http://blog.csdn.net/lmj623565791/article/details/45059587
 */
public class MainActivity extends Activity {

    private String urls[] = {
            "http://a.hiphotos.baidu.com/zhidao/pic/item/37d12f2eb9389b50b7da53068335e5dde7116eb9.jpg",
            "http://b.hiphotos.baidu.com/zhidao/pic/item/64380cd7912397dd88b6edd55b82b2b7d1a28717.jpg",
            "http://c.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=ff9116af9058d109c4b6a1b4e168e087/11385343fbf2b211b1d6f09fc88065380dd78e62.jpg"
    };

    private List<RecycleBean> mList = new ArrayList<>();

    // 核型控件
    private RecyclerView mRecyclerView = null;
    // 纵向适配器
    private LinearAdapter mLinearAdapter = null;
    // 管理RecycleView 展示类型: 竖向/横向/瀑布流
    private LinearLayoutManager mLinearLayoutManager = null;
    // Grid Adapter
    private GridAdapter mGridAdapter = null;
    private GridLayoutManager mGridLayoutManager = null;
    // 瀑布流
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        testData();
        initView();
        updateData();

    }

    private void initView() {
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
//        initLinearLayoutH();
        initGridLayout();
//        initStaggeredGridLayout();
        // 固定Item,提高效率
//        mRecyclerView.setHasFixedSize(true);
    }

    /*
     * 纵向(类似ListView)
     */
    private void initLinearLayoutV() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearAdapter = new LinearAdapter(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mLinearAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    /*
    * 纵向(类似ListView)
    */
    private void initLinearLayoutH() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLinearAdapter = new LinearAdapter(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mLinearAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
    }

    /*
     * 类似GridView
     */
    private void initGridLayout() {
        // 横向(类似GridView)
        mGridLayoutManager = new GridLayoutManager(this, 5);
        mGridAdapter = new GridAdapter(this, mList);
        mGridAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mGridAdapter);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
    }

    /*
     * 瀑布流
     */
    private void initStaggeredGridLayout() {
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mGridAdapter = new GridAdapter(this, mList);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    private void testData() {
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int pos = random.nextInt(2);
            RecycleBean bean = new RecycleBean();
            bean.setTitle("第 " + i + " 项");
            bean.setUrl(urls[pos]);
            bean.setType(i%2);
            mList.add(bean);
        }
    }

    private void updateData() {
        if(null != mLinearAdapter) {
            mLinearAdapter.replace(mList);
        }
    }


    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

        }

        @Override
        public void onItemClick(View view, RecycleBean bean) {
            Toast.makeText(MainActivity.this, "点击" + bean.getTitle(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };
}
