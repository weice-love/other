package com.zhongshi.puppy.service.insurance.config;//package com.zhongshi.puppy.service.insurance;

import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.annotation.PostConstruct;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 * @author :  清风
 * description :
 * create date :  2021/1/27 16:26
 */
@Slf4j
@Component
public class NacosServiceInstanceUpAndDownOperator implements ApplicationRunner, Closeable {
    /**
     * nacos服务实例上线
     */
    private static final String OPERATOR_UP = "UP";
    /**
     * nacos服务实例下线
     */
    private static final String OPERATOR_DOWN = "DOWN";

    @Autowired
    NacosServiceRegistry nacosServiceRegistry;

    @Autowired
    NacosRegistration nacosRegistration;

    private ScheduledExecutorService executorService;


    @PostConstruct
    public void init() {
        int poolSize = 1;
        this.executorService = new ScheduledThreadPoolExecutor(poolSize, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName("NacosServiceInstanceUpAndDownOperator");
                return thread;
            }
        });
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        long delay_down = 5000L;  //下线任务延迟
        long delay_up = 10000L;   // 上线任务延迟
        this.executorService.schedule(new InstanceDownAndUpTask(nacosServiceRegistry, nacosRegistration, OPERATOR_DOWN), delay_down, TimeUnit.MILLISECONDS);
        this.executorService.schedule(new InstanceDownAndUpTask(nacosServiceRegistry, nacosRegistration, OPERATOR_UP), delay_up, TimeUnit.MILLISECONDS);
    }

    @Override
    public void shutdown() throws NacosException {
        ThreadUtils.shutdownThreadPool(executorService, log);
    }


    /**
     * 服务实例上下线任务
     */
    class InstanceDownAndUpTask implements Runnable {
        private NacosServiceRegistry nacosServiceRegistry;
        private NacosRegistration nacosRegistration;
        //更新服务实例的状态 ：UP 、DOWN
        private String nacosServiceInstanceOperator;

        InstanceDownAndUpTask(NacosServiceRegistry nacosServiceRegistry, NacosRegistration nacosRegistration, String nacosServiceInstanceOperator) {
            this.nacosServiceRegistry = nacosServiceRegistry;
            this.nacosRegistration = nacosRegistration;
            this.nacosServiceInstanceOperator = nacosServiceInstanceOperator;
        }

        @Override
        public void run() {
            log.info("===更新nacos服务实例的状态to：{}===start=", nacosServiceInstanceOperator);
            this.nacosServiceRegistry.setStatus(nacosRegistration, nacosServiceInstanceOperator);
            log.info("===更新nacos服务实例的状态to：{}===end=", nacosServiceInstanceOperator);

            //上线后，关闭线程池
            if (NacosServiceInstanceUpAndDownOperator.OPERATOR_UP.equals(nacosServiceInstanceOperator)) {
                ThreadUtils.shutdownThreadPool(NacosServiceInstanceUpAndDownOperator.this.executorService, NacosServiceInstanceUpAndDownOperator.this.log);
            }
        }
    }
}
