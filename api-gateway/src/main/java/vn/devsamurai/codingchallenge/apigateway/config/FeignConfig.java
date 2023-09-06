package vn.devsamurai.codingchallenge.apigateway.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FeignConfig {
    @Bean
    ErrorDecoder errorDecoder() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String s, Response response) {
                return null;
            }
        };
    }
}
