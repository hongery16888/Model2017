package iori.basecore.repository.imp;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.Flowable;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.network.RetrofitBuilder;
import iori.basecore.network.response.BaseResponse;
import iori.basecore.network.rx.BaseResponseFunc1;
import iori.basecore.repository.api.TranslateRepo;
import iori.basecore.service.TranslateService;

/**
 * Created by ThinkPad on 2017/11/2.
 */
@EBean(scope = EBean.Scope.Singleton)
public class TranslateRepoImp implements TranslateRepo{

    @Bean
    RetrofitBuilder mRetrofitBuilder;
    private TranslateService mTranslateService;

    @AfterInject
    void initService() {
        mTranslateService = mRetrofitBuilder.build().create(TranslateService.class);
    }

    @Override
    public Flowable<TranslateModel> getTranslate() {
        return mTranslateService.getTranslate().flatMap(new BaseResponseFunc1<TranslateModel>());
    }
}
