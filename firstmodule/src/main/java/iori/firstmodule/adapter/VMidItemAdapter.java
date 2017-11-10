package iori.firstmodule.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import iori.basecore.base.BaseBindingAdapter;
import iori.basecore.model.translate.TranslateModel;
import iori.firstmodule.R;
import iori.firstmodule.databinding.VMidItemViewBinding;

/**
 * Created by ThinkPad on 2017/11/8.
 */

public class VMidItemAdapter extends BaseBindingAdapter<TranslateModel, VMidItemViewBinding> {

    private Context context;

    public VMidItemAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.v_mid_item_view;
    }

    @Override
    protected void onBindItem(VMidItemViewBinding binding, TranslateModel item) {
        binding.setModel(item);
        binding.executePendingBindings();
    }
}
