package cn.weicelove.coupon.controller;

import cn.weicelove.coupon.entity.AccountDO;
import cn.weicelove.coupon.mapper.AccountMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseController implements CommandLineRunner {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void run(String... args) throws Exception {
        List<AccountDO> accountDOS = accountMapper.selectList(new LambdaQueryWrapper<>());
        System.out.println(accountDOS.size());
    }
}
