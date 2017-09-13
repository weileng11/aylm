package cn.wl.aylm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.wl.aylm.R;
import cn.wl.aylm.base.BaseNormalAdapter;
import cn.wl.aylm.bean.PublicInfo;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.adapter
 * time:2017/9/13.
 * contact：weileng143@163.com
 *
 * @description
 */

public class MyFragmentAdapter extends BaseNormalAdapter<PublicInfo>{
//    List<PublicInfo> mData;
//    public HomeAdapter(Context context, List<PublicInfo> datas) {
//        super(context, datas);
//        this.mData=datas;
//    }

    public MyFragmentAdapter(Context context) {
        super(context);

    }
    @Override
    public View builderItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.personal_item, parent, false);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_content);
            holder.imgItem = (ImageView) convertView.findViewById(R.id.iv_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final PublicInfo listBean = getData().get(position);
        holder.tvName.setText(listBean.getName());
        //图片
        holder.imgItem.setBackgroundResource(listBean.getIvContent());
        return convertView;
    }

    class ViewHolder {
        private TextView tvName;
        private ImageView imgItem;

    }
}
