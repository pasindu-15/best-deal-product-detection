
package com.uom.msc.cse.sdoncloud.detection.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${base-url.context}")
    private String context;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.uom.msc.cse.sdoncloud.detection.application.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(apiInfo()).pathProvider(new BasePathAwareRelativePathProvider(context));
    }
    private ApiInfo apiInfo() {
        String title ="Best Deal Application";
        return new ApiInfo(
                title,
                "Welcome to Best Deal Application Platform",
                "v1.0",
                "Terms of services",
                new Contact("Admin", "http://www.bestdeal.com", "pasinduhewagamage15@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
        private String basePath;

        public BasePathAwareRelativePathProvider(String basePath) {
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return basePath;
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
        }
    }

}



///////

//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.util.UriComponentsBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.paths.AbstractPathProvider;
//import springfox.documentation.spring.web.paths.Paths;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Value("${base-url.context}")
//    private String context;
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select().apis(RequestHandlerSelectors.basePackage("adl.dte.firstreloadoffer"))
//                .paths(regex("/.*"))
//                .build()
//                .apiInfo(apiInfo()).pathProvider(new BasePathAwareRelativePathProvider(context));
//    }
//
//    private ApiInfo apiInfo() {
//        String title = "first-reload-offer";
//        return new ApiInfo(
//                title,
//                "Welcome to First-Reload-Offer REST API Integration Platform",
//                "v1.0",
//                "Terms of service",
//                new Contact("Admin", "http://www.axiatadigitallabs.com", "adl@axiatadigitallabs.com"),
//                "License of API", "API license URL", Collections.emptyList());
//    }
//
//    class BasePathAwareRelativePathProvider extends AbstractPathProvider {
//        private String basePath;
//
//        public BasePathAwareRelativePathProvider(String basePath) {
//            this.basePath = basePath;
//        }
//
//        @Override
//        protected String applicationPath() {
//            return basePath;
//        }
//
//        @Override
//        protected String getDocumentationPath() {
//            return "/";
//        }
//
//        @Override
//        public String getOperationPath(String operationPath) {
//            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
//            return Paths.removeAdjacentForwardSlashes(
//                    uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
//        }
//    }
//
//}

