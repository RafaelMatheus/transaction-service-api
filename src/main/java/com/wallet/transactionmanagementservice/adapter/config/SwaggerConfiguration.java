package com.wallet.transactionmanagementservice.adapter.config;

import com.wallet.transactionmanagementservice.adapter.controller.TransactionController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        var traceId = new ParameterBuilder()
                .name("traceId")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .useDefaultResponseMessages(false)
                .globalOperationParameters(List.of(traceId))
                .select()
                .apis(RequestHandlerSelectors.basePackage(TransactionController.class.getPackageName()))
                .build()
                .pathMapping("/transaction");
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Api")
                .description("REST API")
                .build();
    }
}