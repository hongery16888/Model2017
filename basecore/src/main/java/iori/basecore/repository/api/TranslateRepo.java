package iori.basecore.repository.api;

import io.reactivex.Flowable;
import iori.basecore.model.translate.TranslateModel;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public interface TranslateRepo {

    Flowable<TranslateModel> getTranslate();

}
