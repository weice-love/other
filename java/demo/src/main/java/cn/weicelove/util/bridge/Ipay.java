package cn.weicelove.util.bridge;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 15:36
 */
public abstract class Ipay {

    protected IPayMode IPayMode;

    public Ipay(IPayMode IPayMode) {
        this.IPayMode = IPayMode;
    }

    abstract boolean transfer(String userId);
}
