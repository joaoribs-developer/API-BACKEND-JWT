package com.APIBackend.demo.Utils

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun configSwagger(): Docket =
         Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
             .apiInfo(metaData())

        private fun metaData() = ApiInfoBuilder()
            .title("API CADASTRO DE ATIVIDADES")
            .description("API cadastro e listagem de atividades")
            .version("1.0.0")
            .build()

    @Bean
    fun defaultViewResolver(): InternalResourceViewResolver? {
        return InternalResourceViewResolver()
    }
}