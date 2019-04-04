package com.asif.ethereum.configs;

import com.asif.ethereum.common.Constant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.common.base.Predicates;
import com.google.common.net.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private final String DATA_TYPE = "string";
  private final String HEADER_PARAMETER_TYPE = "header";
  private final String HEADER_DESCRIPTION = "Provide wallet Id";


  @Bean
  public Docket api() {
    Parameter parameter = new ParameterBuilder()
        .name(Constant.WALLET_ID)
        .modelRef(new ModelRef(DATA_TYPE))
        .description(HEADER_DESCRIPTION)
        .parameterType(HEADER_PARAMETER_TYPE)
        .required(true)
        .build();

    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.asif.ethereum.modules"))
        .paths(Predicates.or(PathSelectors.regex("/v1/.*")))
        .build()
        .globalOperationParameters(Arrays.asList(parameter))
        .directModelSubstitute(LocalDateTime.class, String.class)
        .useDefaultResponseMessages(false);
  }
}
