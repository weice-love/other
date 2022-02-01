package cn.weicelove.coupon.service.impl;

import cn.weicelove.coupon.constants.CouponEnum;
import cn.weicelove.coupon.entity.CouponDO;
import cn.weicelove.coupon.mapper.CouponMapper;
import cn.weicelove.coupon.model.CouponResp;
import cn.weicelove.coupon.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public List<CouponResp> listCoupons() {
        List<CouponDO> couponDOS = couponMapper.selectList(new LambdaQueryWrapper<>());
        List<CouponResp> result = couponDOS.stream().map(e -> {
            CouponResp couponResp = new CouponResp();
            BeanUtils.copyProperties(e, couponResp);
            return couponResp;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public int generateCoupon(Integer sum) {
//        List<CouponDO> couponDOS = IntStream.range(0, sum).mapToObj(e -> {
//            CouponDO couponDO = new CouponDO();
//            couponDO.setUsed(CouponEnum.UseFlagEnum.UN_USED);
//            couponDO.setCouponName("coupon_" + e);
//            couponDO.setGroupId(0L);
//            int insert = couponMapper.insert(couponDO);
//            return couponDO;
//        }).collect(Collectors.toList());
        // todo 批量新增待实现
            return IntStream.range(0, sum).mapToObj(e -> {
            CouponDO couponDO = new CouponDO();
            couponDO.setUsed(CouponEnum.UseFlagEnum.UN_USED);
            couponDO.setCouponName("coupon_" + e);
            couponDO.setGroupId(0L);
            return couponMapper.insert(couponDO);
        }).reduce(0, Integer::sum);
    }

    @Override
    public boolean checkUsed(Long couponId) {
        LambdaQueryWrapper<CouponDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(CouponDO::getId, couponId)
                .eq(CouponDO::getUsed, CouponEnum.UseFlagEnum.USED);
        return couponMapper.exists(queryWrapper);
    }

    @Override
    public int useCoupon(Integer couponId) {
        CouponDO couponDO = couponMapper.selectById(couponId);
        if (CouponEnum.UseFlagEnum.USED.equals(couponDO.getUsed())) {
            return 1;
        }
        couponDO.setUsed(CouponEnum.UseFlagEnum.USED);
        return couponMapper.updateById(couponDO);
    }
}
