package demo.recyclerview.swipetoloadlayout.com.swipetoloadlayoutrecylerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import demo.recyclerview.swipetoloadlayout.com.swipetoloadlayoutrecylerviewdemo.R;

/**
 * Created by 林 on 2017/8/8.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mList;


    public MyAdapter(Context context, List<String> list){
        mContext = context;
        mList = list;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {


        SimpleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
//        return mList.size();
        return 10;
    }
}
