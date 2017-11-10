package iori.firstmodule.adapter;

import android.content.Context;

import iori.basecore.base.BaseBindingDelegateAdapter;
import iori.basecore.model.translate.TranslateModel;
import iori.firstmodule.R;
import iori.firstmodule.databinding.VTopViewBinding;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class VTopAdapter extends BaseBindingDelegateAdapter<TranslateModel, VTopViewBinding> {

    public VTopAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.v_top_view;
    }

    @Override
    protected void onBindItem(VTopViewBinding binding, TranslateModel item) {
        binding.setModel(item);
        binding.executePendingBindings();
    }

}
