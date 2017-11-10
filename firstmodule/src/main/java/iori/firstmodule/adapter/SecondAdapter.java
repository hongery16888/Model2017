package iori.firstmodule.adapter;

import android.content.Context;

import iori.basecore.base.BaseBindingAdapter;
import iori.basecore.model.translate.TranslateModel;
import iori.firstmodule.R;
import iori.firstmodule.databinding.SecondItemBinding;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class SecondAdapter extends BaseBindingAdapter<TranslateModel, SecondItemBinding> {

    public SecondAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.second_item;
    }

    @Override
    protected void onBindItem(SecondItemBinding binding, TranslateModel item) {
        binding.setModel(item);
        binding.executePendingBindings();
    }
}
