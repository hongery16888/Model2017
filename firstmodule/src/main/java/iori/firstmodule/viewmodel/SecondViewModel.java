package iori.firstmodule.viewmodel;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import iori.basecore.base.BaseViewModel;
import iori.basecore.listener.OnNormalNetworkListener;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.network.exception.RxError;
import iori.basecore.network.rx.ResponseObserver;
import iori.basecore.repository.RepositoryFactory;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class SecondViewModel extends BaseViewModel<OnNormalNetworkListener> {

    private Context context;

    public SecondViewModel(Context context) {
        this.context = context;


    }

    public void getTranslate(){
        RepositoryFactory.getTranslateRepo(context).getTranslate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<TranslateModel>() {
                    @Override
                    public void onSuccess(TranslateModel translateModel) {
                        onNetworkListener.success(translateModel);
                    }

                    @Override
                    public void onError(RxError rxError) {
                        onNetworkListener.error(rxError.getMes());
                    }
                });
    }

}
