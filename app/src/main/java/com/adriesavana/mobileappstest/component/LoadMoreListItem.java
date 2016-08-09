package com.adriesavana.mobileappstest.component;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.adriesavana.mobileappstest.R;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;

/**
 * Created by adri on 8/8/16.
 */

public class LoadMoreListItem extends AbstractItem<LoadMoreListItem, LoadMoreListItem.ViewHolder> {

    private static final ViewHolderFactory<? extends ViewHolder> FACTORY = new ItemFactory();

    private LoadMoreListener mListener;

    public LoadMoreListItem(LoadMoreListener listener) {
        mListener = listener;
    }

    public LoadMoreListItem() {
    }

    @Override
    public int getType() {
        return LoadMoreListItem.class.hashCode();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_load_more;
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(true);
        }

        Handler h = new Handler();

        h.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (mListener != null) {
                    mListener.onLoadMore();
                }
            }
        }, 350);


    }

    @Override
    public ViewHolderFactory<? extends ViewHolder> getFactory() {
        return FACTORY;
    }

    protected static class ItemFactory implements ViewHolderFactory<ViewHolder> {
        public ViewHolder create(View v) {
            return new ViewHolder(v);
        }
    }

    public interface LoadMoreListener {

        void onLoadMore();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
