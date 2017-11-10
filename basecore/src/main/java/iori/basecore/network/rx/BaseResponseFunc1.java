package iori.basecore.network.rx;

import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import iori.basecore.network.response.ErrorCode;
import iori.basecore.network.exception.ResponseFailedException;
import iori.basecore.network.response.BaseResponse;
import iori.basecore.network.response.ResponseCode;
import iori.basecore.network.response.RxVoid;
import iori.basecore.repository.RepositoryFactory;

/**
 * Created by shendawei on 17/2/8.
 */

public class BaseResponseFunc1<T> implements Function<BaseResponse<T>, Flowable<T>> {

    @Override
    public Flowable<T> apply(@NonNull final BaseResponse<T> response) throws Exception {

        if (response == null) {
            return Flowable.error(new ResponseFailedException(ErrorCode.NETWORK_ERROR,"网络出现问题1"));
        }
        Log.d("BaseResponse", "response = " + response);

        /*
        如果返回此错误，说明需要登录
         */
        if(response.getStatus()== ResponseCode.NON_LOGIN)
        {
//            RepositoryFactory.getLoginContext(NeoApplication.getContext()).logout();
        }

        //如果请求操作成功且发射数据为空，则发送rxvoid
        if(response.getStatus()==ResponseCode.SUCCESS&&response.getContent()==null)
        {
            return Flowable.just((T) new RxVoid());
        }
        else if(response.getStatus()==ResponseCode.SUCCESS&&response.getContent()!=null)
        {
            return Flowable.just(response.getContent());
        }
        else if(response.getStatus()!=ResponseCode.SUCCESS&&response.getContent()==null)
        {
            return Flowable.error(new ResponseFailedException(response.getStatus(),response.getMessage()));
        }
        else if(response.getStatus()!=ResponseCode.SUCCESS&&response.getContent()!=null)
        {
            return Flowable.error(new ResponseFailedException(response.getStatus(),response.getMessage(),response.getContent()));
        }
        return Flowable.error(new ResponseFailedException(ErrorCode.NETWORK_ERROR,"网络出现问题2"));




      /*  // log code
        Log.d("BaseResponse", "response.code = " + response.getCode());
        //// TODO: 2017/4/4 框架不满足发送验证码部分的判断 ，只能在这处理了  by lyz
        if(response.getCode()== NeoConstantCode.sys_code_img_valid
                ||response.getCode()==NeoConstantCode.sys_code_later)
        {
            //Log.v("BaseResponse",((Send)response.getData()).getCaptcha());
            if(response.getData() instanceof Send) {
                Send send = (Send) response.getData();
                send.setCode(response.getCode());
                return Flowable.just((T) send);
            }
        }

        // 1.code == success 2.code != success, but data != null
        if (response.getCode() == ResponseCode.SUCCESS || response.getData() != null) {
            if(response.getData()==null)
            {
                return Flowable.just((T)new RxVoid());
            }
            return Flowable.just(response.getData());
        }
        else { //3.code != success and data == null
            return Flowable.error(new ResponseFailedException(response.getCode()));
        }*/
    }
}
