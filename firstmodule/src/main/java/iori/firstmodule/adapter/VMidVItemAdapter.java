package iori.firstmodule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.List;

import iori.basecore.model.translate.TranslateModel;
import iori.firstmodule.R;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class VMidVItemAdapter extends DelegateAdapter.Adapter<VMidVItemAdapter.ItemHolder> {

    private Context context;
    private List<TranslateModel> translateModels = new ArrayList<>();

    public VMidVItemAdapter(Context context, TranslateModel translateModel) {
        this.context = context;
        this.translateModels.add(translateModel);
    }

    public void refreshData(TranslateModel model){
        this.translateModels.add(model);
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper();
        layoutHelper.setItemCount(translateModels.size());
        return layoutHelper;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VMidVItemAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.v_mid_v_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.out.setText(translateModels.get(position).getOut());
    }

    @Override
    public int getItemCount() {
        return translateModels.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView out;

        public ItemHolder(View itemView) {
            super(itemView);
            out = (TextView) itemView.findViewById(R.id.out);
        }
    }
}
