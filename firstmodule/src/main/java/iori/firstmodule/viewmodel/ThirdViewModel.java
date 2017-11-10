package iori.firstmodule.viewmodel;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import iori.basecore.base.BaseViewModel;
import iori.basecore.listener.OnMultiplyNetworkListener;
import iori.basecore.listener.OnNormalNetworkListener;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.network.exception.RxError;
import iori.basecore.network.rx.ResponseObserver;
import iori.basecore.repository.RepositoryFactory;

/**
 * Created by ThinkPad on 2017/11/3.
 */

public class ThirdViewModel extends BaseViewModel<OnMultiplyNetworkListener> {

    private Context context;

    public ThirdViewModel(Context context) {
        this.context = context;
    }

    public void getTranslateForTop(){
        RepositoryFactory.getTranslateRepo(context).getTranslate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<TranslateModel>() {
                    @Override
                    public void onSuccess(TranslateModel translateModel) {
                        onNetworkListener.successForTop(translateModel);
                    }

                    @Override
                    public void onError(RxError rxError) {
                        onNetworkListener.error(rxError.getMes());
                    }
                });
    }

    public void getTranslateForMid(){
        RepositoryFactory.getTranslateRepo(context).getTranslate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<TranslateModel>() {
                    @Override
                    public void onSuccess(TranslateModel translateModel) {
                        onNetworkListener.successForMid(translateModel);
                    }

                    @Override
                    public void onError(RxError rxError) {
                        onNetworkListener.error(rxError.getMes());
                    }
                });
    }

    public void getTranslateForBottom(){
        RepositoryFactory.getTranslateRepo(context).getTranslate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseObserver<TranslateModel>() {
                    @Override
                    public void onSuccess(TranslateModel translateModel) {
                        List<TranslateModel> translateModelList = new ArrayList<>();
                        translateModelList.add(translateModel);
                        onNetworkListener.successForBottom(translateModelList);
                    }

                    @Override
                    public void onError(RxError rxError) {
                        onNetworkListener.error(rxError.getMes());
                    }
                });
    }
}
