package com.APIBackend.demo.Utils

import com.APIBackend.demo.Model.Users
import com.APIBackend.demo.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
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
@Configuration
@EnableWebSecurity
class SecurityConfiguration: WebSecurityConfigurerAdapter(){
    @Autowired
    private lateinit var jwtUtils: JWTUtils
    @Autowired
    private lateinit var userRepository: UserRepository
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/cadastro").permitAll()
            .anyRequest().authenticated()
        http.cors().configurationSource(configCors())
        http.addFilter(JWTAutorization(authenticationManager(), jwtUtils, userRepository))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
fun configCors():CorsConfigurationSource{
    val configuration = CorsConfiguration()
    configuration.allowedOrigins = mutableListOf("*")
    configuration.allowedMethods = mutableListOf("*")
    configuration.allowedHeaders = mutableListOf("*")
    var source = UrlBasedCorsConfigurationSource()
    source.registerCorsConfiguration("/**",configuration)
    return source
}
}