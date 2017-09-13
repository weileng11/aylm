package cn.wl.aylm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.wl.aylm.R;
import cn.wl.aylm.base.BaseNormalAdapter;
import cn.wl.aylm.net.response.NearbyRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.adapter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class NearlyAdapter extends BaseNormalAdapter<NearbyRespBean.NearbyBeanList> {

    private Context mContext;
    public NearlyAdapter(Context context) {
        super(context);
        this.mContext=context;
    }

    @Override
    public View builderItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.home_nearby_item, parent, false);
            holder.tvNearlyName = (TextView) convertView.findViewById(R.id.tv_nearby_name);
            holder.tvNearlyAddress = (TextView) convertView.findViewById(R.id.tv_nearby_address);
            holder.tvNearlyGrade = (TextView) convertView.findViewById(R.id.tv_nearby_grade);

            holder.imgNearly = (ImageView) convertView.findViewById(R.id.iv_nearby);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final NearbyRespBean.NearbyBeanList item = getData().get(position);
        holder.tvNearlyName.setText(item.title);
        holder.tvNearlyAddress.setText(item.area+item.address);
        holder.tvNearlyGrade.setText(item.stars);

        Glide.with(mContext).load(item.content).error(R.mipmap.home_seach).into(holder.imgNearly);
        return convertView;
    }

    class ViewHolder {
        private TextView tvNearlyName;
        private TextView tvNearlyAddress;
        private TextView tvNearlyGrade;
        private ImageView imgNearly;
    }
}
