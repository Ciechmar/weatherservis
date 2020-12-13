package com.sda.weatherservis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

@Configuration
public class AuditingConfiguration {

    @Bean
    public AuditorAware<String> audioAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return Optional.ofNullable(authentication).map(Principal::getName);
        };
    }
}
