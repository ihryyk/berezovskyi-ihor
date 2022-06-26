package org.spring.homeWork2;

import org.spring.homeWork2.configurations.Config2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config2.class);
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        context.close();

    }
}
