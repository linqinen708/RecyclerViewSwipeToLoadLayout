package demo.recyclerview.swipetoloadlayout.com.swipetoloadlayoutrecylerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.recyclerview.swipetoloadlayout.com.swipetoloadlayoutrecylerviewdemo.adapter.MyAdapter;
import demo.recyclerview.swipetoloadlayout.com.swipetoloadlayoutrecylerviewdemo.utils.LogT;


/**
 * 源码地址https://github.com/Aspsine/SwipeToLoadLayout,有兴趣的小伙伴可以去看看
 * 本人只是将其中关于RecyclerView的上拉下拉刷新整理出来，并对源码做了一定修改
 * 可以去看看文章http://blog.csdn.net/qq_33337504/article/details/77977312，里面我写了自己修改的内容
 * */
public class MainActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    private List<String> mList = new ArrayList<>();

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*不会用ButterKnife的小伙伴可以直接用findViewById()来找控件*/
        ButterKnife.bind(this);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter mAdapter = new MyAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);

        /*如果想自己设置id，就设置setTargetView*/
        mSwipeToLoadLayout.setTargetView(mRecyclerView);

        /*如果使用setRefreshHeaderView和setLoadMoreFooterView方法，则不需要在xml中写入R.layout.layout_twitter_header，R.layout.layout_classic_footer*/
        /*设置下拉刷新布局*/
        mSwipeToLoadLayout.setRefreshHeaderView(LayoutInflater.from(this).inflate(R.layout.layout_twitter_header, mSwipeToLoadLayout, false));
        /*设置上拉加载更多布局*/
        mSwipeToLoadLayout.setLoadMoreFooterView(LayoutInflater.from(this).inflate(R.layout.layout_classic_footer, mSwipeToLoadLayout, false));

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        mSwipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        LogT.i("正在刷新:");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        LogT.i("加载更多数据:");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
            }
        }, 2000);
    }
}
