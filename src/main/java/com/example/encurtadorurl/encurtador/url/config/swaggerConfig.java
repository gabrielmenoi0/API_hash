package com.example.encurtadorurl.encurtador.url.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .apis( RequestHandlerSelectors.basePackage( "com.example.encurtadorurl.encurtador.url" ))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API Hash")
                .description("API em desenvolvimento na aula de desenvolvimento avançado de sistemas de informação")
                .version("2.0")
                .build();
    }

}
