package com.freework.taskmaster.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskMasterConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
