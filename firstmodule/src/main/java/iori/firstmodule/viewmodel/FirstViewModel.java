package iori.firstmodule.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import iori.basecore.base.BaseViewModel;
import iori.basecore.listener.OnNormalNetworkListener;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.network.exception.RxError;
import iori.basecore.network.rx.ResponseObserver;
import iori.basecore.repository.RepositoryFactory;

/**
 * Created by ThinkPad on 2017/11/1.
 */

public class FirstViewModel extends BaseViewModel {

    private Context context;
    public ObservableField<String> from;
    public ObservableField<String> to;
    public ObservableField<String> out;

    public FirstViewModel(Context context) {
        this.context = context;
        from = new ObservableField<>();
        to = new ObservableField<>();
        out = new ObservableField<>();
    }

    public void getTranslate(){
        RepositoryFactory.getTranslateRepo(context).getTranslate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<TranslateModel>() {
                    @Override
                    public void onSuccess(TranslateModel translateModel) {
                        from.set(translateModel.getFrom());
                        to.set(translateModel.getTo());
                        out.set(translateModel.getOut());
                    }

                    @Override
                    public void onError(RxError rxError) {

                    }
                });
    }

}
