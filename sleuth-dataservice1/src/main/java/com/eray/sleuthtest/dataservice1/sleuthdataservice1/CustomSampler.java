package com.eray.sleuthtest.dataservice1.sleuthdataservice1;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSampler {

    @Bean
    public Sampler sampler() {
        return new Sampler() {
            @Override
            public boolean isSampled(long l) {
                System.out.println("custom sampler is used!" + l);
                return true;
            }
        };

    }
}
