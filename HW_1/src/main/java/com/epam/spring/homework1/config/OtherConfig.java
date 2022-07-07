package com.epam.spring.homework1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.epam.spring.homework1.others")
@Import(PetConfig.class)
public class OtherConfig {}
