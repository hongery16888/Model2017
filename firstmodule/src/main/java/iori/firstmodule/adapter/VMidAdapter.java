package iori.firstmodule.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import iori.basecore.model.translate.TranslateModel;
import iori.basecore.widget.refreshview.RecycleViewDivider;
import iori.firstmodule.R;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class VMidAdapter extends DelegateAdapter.Adapter<VMidAdapter.ItemHolder> {

    private Context context;
    private TranslateModel translateModel;

    public VMidAdapter(Context context, TranslateModel translateModel) {
        this.context = context;
        this.translateModel = translateModel;
    }

    public void refreshData(TranslateModel model){
        this.translateModel = model;
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper layoutHelper = new LinearLayoutHelper();
        layoutHelper.setItemCount(translateModel == null ? 0 : 1);
        return layoutHelper;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VMidAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.v_mid_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, 5, Color.WHITE));

        VMidItemAdapter adapter = new VMidItemAdapter(context);
        holder.recyclerView.setAdapter(adapter);
        adapter.getItems().add(translateModel);
        adapter.getItems().add(translateModel);
        adapter.getItems().add(translateModel);
        adapter.getItems().add(translateModel);

    }

    @Override
    public int getItemCount() {
        return translateModel == null ? 0 : 1;
    }

    static class ItemHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;

        public ItemHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view);
        }
    }
}
