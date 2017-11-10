package iori.firstmodule.adapter;

import android.content.Context;

import iori.basecore.base.BaseBindingDelegateAdapter;
import iori.basecore.model.translate.TranslateModel;
import iori.firstmodule.R;
import iori.firstmodule.databinding.VMidItemViewBinding;

/**
 * Created by ThinkPad on 2017/11/9.
 */

public class VBTopAdapter extends BaseBindingDelegateAdapter<TranslateModel, VMidItemViewBinding> {

    public VBTopAdapter(Context context) {
        super(context);
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
