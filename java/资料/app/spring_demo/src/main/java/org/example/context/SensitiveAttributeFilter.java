package org.example.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/23 16:54
 */
public class SensitiveAttributeFilter implements BeanFactoryPostProcessor {

    private final Set<String> sensitiveAttribute;

    public SensitiveAttributeFilter() {
        sensitiveAttribute = new HashSet<>();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.stream().forEach(e -> {
                if (isSensitive(e.getName())) {
                    e.setConvertedValue("******");
                }
            });
//            StringValueResolver resolver = strVal -> {
//                if (isSensitive(strVal)) {
//                    return "******";
//                }
//                return strVal;
//            };
//            BeanDefinitionVisitor beanDefinitionVisitor = new BeanDefinitionVisitor(resolver);
//            beanDefinitionVisitor.visitBeanDefinition(beanDefinition);
        }
    }

    private boolean isSensitive(String strVal) {
        if (StringUtils.isEmpty(strVal)) {
            return false;
        }
        return sensitiveAttribute.contains(strVal.toUpperCase());
    }

    public Set<String> getSensitiveAttribute() {
        return sensitiveAttribute;
    }

    public void setSensitiveAttribute(Set<String> sensitiveAttribute) {
        this.sensitiveAttribute.clear();
        sensitiveAttribute.forEach(e -> this.sensitiveAttribute.add(e.toUpperCase()));
    }
}
