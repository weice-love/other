package cn.weicelove.coupon.service;

import cn.weicelove.coupon.model.CouponResp;

import java.util.List;

public interface CouponService {
    List<CouponResp> listCoupons();

    int generateCoupon(Integer sum);

    boolean checkUsed(Long couponId);

    int useCoupon(Integer couponId);
}
