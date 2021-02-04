package cn.weicelove.util.bridge;

/**
 * @author :  清风
 * description :
 * create date :  2021/2/4 15:41
 */
public class FacePayMode implements IPayMode {
    @Override
    public boolean security(String userId) {
        return "1234".equals(userId);
    }
}
