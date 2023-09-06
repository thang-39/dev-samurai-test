package vn.devsamurai.codingchallenge.apigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.codec.Decoder;
import feign.codec.Encoder;

import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

//	@Bean
//	public Decoder decoder() {
//		return new JacksonDecoder();
//	}

	@Bean
	public Decoder decoder() {
		return new JacksonDecoder(List.of(new JavaTimeModule()));
	}

	@Bean
	public Encoder encoder() {
		return new JacksonEncoder(List.of(new JavaTimeModule()));
	}

//	@Bean
//	ObjectMapper objectMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//		return objectMapper;
//	}

//	@Bean
//	public Decoder feignDecoder() {
//
//		ObjectFactory<HttpMessageConverters> messageConverters = () -> {
//			HttpMessageConverters converters = new HttpMessageConverters();
//			return converters;
//		};
//		return new SpringDecoder(messageConverters);
//	}

//	@Bean
//	public Decoder customDecoder(ObjectFactory<HttpMessageConverters> objectFactory,
//								 ObjectProvider<HttpMessageConverterCustomizer> customizers) {
//		Decoder decoder = (response, type) -> {
//			if (type.getTypeName().equals(org.springframework.core.io.Resource.class.getName())) {
//				return new SpringDecoder(objectFactory, customizers).decode(response, type);
//			}
//			return new JacksonDecoder(List.of(new JavaTimeModule())).decode(response, type);
//		};
//
//		return new ResponseEntityDecoder(decoder);
//	}



}
