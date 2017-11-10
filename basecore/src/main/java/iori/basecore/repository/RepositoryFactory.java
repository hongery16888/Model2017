package iori.basecore.repository;

import android.content.Context;

import iori.basecore.repository.api.TranslateRepo;
import iori.basecore.repository.imp.TranslateRepoImp_;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public class RepositoryFactory {

    public static TranslateRepo getTranslateRepo(Context context){
        return TranslateRepoImp_.getInstance_(context);
    }

}
