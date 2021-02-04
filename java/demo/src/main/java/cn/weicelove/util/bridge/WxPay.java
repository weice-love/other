package cn.weicelove.util.bridge;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 15:38
 */
public class WxPay extends Ipay {

    public WxPay(IPayMode IPayMode) {
        super(IPayMode);
    }

    @Override
    boolean transfer(String userId) {
        boolean security = IPayMode.security(userId);
        if (!security) {
            return false;
        }
        return true;
    }
}
