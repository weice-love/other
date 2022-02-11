package org.example.label;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/2/11 17:20
 */
public class MyNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("person", new PersonBeanDefinitionParser());
    }
}
