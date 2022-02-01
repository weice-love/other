package cn.weicelove.coupon.controller;

import cn.weicelove.coupon.model.CouponResp;
import cn.weicelove.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    @Autowired
    private CouponService couponService;

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<CouponResp> coupons = couponService.listCoupons();
        modelAndView.addObject("users", coupons);
        return modelAndView;
    }

    @RequestMapping("/{couponId}")
    public RedirectView checkCoupon(@PathVariable("couponId")Long couponId) {

        RedirectView redirectView = new RedirectView();
        boolean used = couponService.checkUsed(couponId);
        if (used) {
            redirectView.addStaticAttribute("couponId", couponId);
            // result会重定向到当前页面的父级目录+当前设置名称
            redirectView.setUrl("result");
            // /result会重定向到当前/result下
//            redirectView.setUrl("/result");
        } else {
            redirectView.addStaticAttribute("couponId", couponId);
            redirectView.setUrl("verification");
        }
        return redirectView;
    }

    @RequestMapping("/result")
    public ModelAndView result(@RequestParam("couponId")Long couponId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coupon/result");
        return modelAndView;
    }

    @RequestMapping("/verification")
    public ModelAndView verification(@RequestParam("couponId")Long couponId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coupon/verification");
        return modelAndView;
    }

    @RequestMapping("/generate/{sum}")
    @ResponseBody
    public String generate(@PathVariable("sum") Integer sum) {
        int successSum = couponService.generateCoupon(sum);
        return "success";
    }

    @RequestMapping("/use/{couponId}")
    @ResponseBody
    public String useCoupon(@PathVariable("couponId") Integer couponId) {
        int successSum = couponService.useCoupon(couponId);
        return "success";
    }



}
