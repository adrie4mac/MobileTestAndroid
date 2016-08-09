package com.adriesavana.mobileappstest.component;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adriesavana.mobileappstest.R;
import com.adriesavana.mobileappstest.network.gson.GDetail;
import com.adriesavana.mobileappstest.network.gson.GList;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import rx.functions.Action1;

/**
 * Created by adri on 8/8/16.
 */

public class DoctorItem extends AbstractItem<DoctorItem, DoctorItem.ViewHolder> {
    private static final ViewHolderFactory<? extends ViewHolder> FACTORY = new ItemFactory();

    private ItemListener mListener;
    private GList mDetail;
    private Context mCtx;

    public DoctorItem(Context ctx, GList detail, ItemListener listener) {
        this.mListener = listener;
        this.mDetail = detail;
        this.mCtx = ctx;
    }

    @Override
    public int getType() {
        return DoctorItem.class.hashCode();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_item;
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);

        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(true);
        }

        Glide.with(mCtx).load(mDetail.photo).fitCenter().dontAnimate().into(holder.picture);

        holder.name.setText("dr."+mDetail.name);
        holder.sprcialis.setText(mDetail.speciality);
        holder.area.setText(mDetail.area);
        double nominal = Double.parseDouble(String.valueOf(mDetail.rate));

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String formattedPrice = kursIndonesia.format(nominal);
        int spaceIndex = formattedPrice.indexOf(",");

        if (spaceIndex != -1) {
            formattedPrice = formattedPrice.substring(0, spaceIndex);
        }
        holder.range.setText(mDetail.currency + " " + formattedPrice);

        RxView.clicks(holder.btnDetail).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (mListener != null) {
                    mListener.openDetail(mDetail.id);
                }
            }
        });
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

    public interface ItemListener {

        void openDetail(int id);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CircularImageView picture;
        private LinearLayout btnDetail;
        private TextView name;
        private TextView area;
        private TextView sprcialis;
        private TextView range;

        public ViewHolder(View view) {
            super(view);
            picture = (CircularImageView) view.findViewById(R.id.profilePicture);
            btnDetail = (LinearLayout) view.findViewById(R.id.wrapper);
            name = (TextView) view.findViewById(R.id.nameItem);
            area = (TextView) view.findViewById(R.id.areaItem);
            sprcialis = (TextView) view.findViewById(R.id.specialityItem);
            range = (TextView) view.findViewById(R.id.rangeitem);
        }
    }
}
