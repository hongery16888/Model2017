package iori.firstmodule.adapter;

import android.content.Context;

import iori.basecore.base.BaseBindingDelegateAdapter;
import iori.basecore.model.translate.TranslateModel;
import iori.firstmodule.R;
import iori.firstmodule.databinding.VBottomViewBinding;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class VBottomAdapter extends BaseBindingDelegateAdapter<TranslateModel, VBottomViewBinding> {

    public VBottomAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.v_bottom_view;
    }

    @Override
    protected void onBindItem(VBottomViewBinding binding, TranslateModel item) {
        binding.setModel(item);
        binding.executePendingBindings();
    }
}
