package org.spring.homeWork2.beans;

        import org.springframework.beans.BeansException;
        import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
        import org.springframework.beans.factory.config.BeanPostProcessor;
        import org.springframework.stereotype.Component;

@Component
public class PostProcessorBean implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Bean) {
            if (((Bean) bean).getName() == null || ((Bean) bean).getValue() < 0) {
                System.out.println("Bean is not valid");
            } else {
                System.out.println("Bean is valid");
            }
        }
        return bean;
    }
}
