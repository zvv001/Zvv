package com.vv.zvv.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vv.zvv.JavaBean.ProductList;
import com.vv.zvv.R;
import com.vv.zvv.Utils.ToastUtil;

import java.util.List;

/* RecycleViewAdapter 适配器 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyHolder> {
    Context mContext;
    List<ProductList.PagerBean.ListBean> mList;

    public MyRecycleViewAdapter(Context context, List<ProductList.PagerBean.ListBean> list) {
        mContext = context;
        mList = list;
    }

    //刷新数据
    public void upDate(List<ProductList.PagerBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }


    /* 创建ViewHolder 引入 xml 到 ViewHolder */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_listview_lvda, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    /* 操作 item 的地方 */
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.mTextView.setText(mList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(mContext, mList.get(position).getName());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /* ViewHolder */
    class MyHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.tv_item_listView);
        }
    }

}
