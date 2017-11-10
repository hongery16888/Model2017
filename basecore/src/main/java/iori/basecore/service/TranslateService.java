package iori.basecore.service;

import io.reactivex.Flowable;
import iori.basecore.model.translate.TranslateModel;
import iori.basecore.network.response.BaseResponse;
import retrofit2.http.GET;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public interface TranslateService {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=global%20world")
    Flowable<BaseResponse<TranslateModel>> getTranslate();

}
