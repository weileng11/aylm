package cn.wl.aylm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.wl.aylm.R;
import cn.wl.aylm.base.BaseNormalAdapter;
import cn.wl.aylm.net.response.AllOrderRespBean;

/**
 * Author:Bruce
 * Package:cn.wl.aylm.adapter
 * time:2017/9/14.
 * contact：weileng143@163.com
 *
 * @description
 */

public class RecordItemAdapter extends BaseNormalAdapter<AllOrderRespBean.DataBean>{
    private String mType;
    private Context context;
    public RecordItemAdapter(Context context,String orderType) {
        super(context);
        this.context=context;
        mType = orderType;
    }

    @Override
    public View builderItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diagnose_item, parent, false);
            holder.tv_diagnose_state= (TextView) convertView.findViewById(R.id.tv_diagnose_state);
            holder.btn_diagnose_evaluate = (Button) convertView.findViewById(R.id.btn_diagnose_evaluate);
            holder.btn_diagnose_buy= (TextView) convertView.findViewById(R.id.btn_diagnose_buy);

            holder.tv_diagnose_time = (TextView) convertView.findViewById(R.id.tv_diagnose_time);
            holder.tv_diagnose_name = (TextView) convertView.findViewById(R.id.tv_diagnose_name);
            holder.tv_diagnose_price= (TextView) convertView.findViewById(R.id.tv_diagnose_price);

            holder.imgItem = (ImageView) convertView.findViewById(R.id.iv_diagonse);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AllOrderRespBean.DataBean listBean = getData().get(position);
        holder.tv_diagnose_time.setText(listBean.CreateTime);
        holder.tv_diagnose_name.setText(listBean.Goods.get(0).Name);
        holder.tv_diagnose_price.setText( String.valueOf(listBean.Goods.get(0).BuyPrice));
        //图片
        Glide.with(context).load(listBean.Goods.get(0).OriginalImge).into(holder.imgItem);
        return convertView;
    }

    class ViewHolder {
        private TextView tv_diagnose_state;
        private Button btn_diagnose_evaluate;
        private TextView btn_diagnose_buy;

        private TextView tv_diagnose_time;
        private TextView tv_diagnose_name;
        private TextView tv_diagnose_price;

        private ImageView imgItem;

    }

}
