package com.group06.sakila.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenAPIDocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {
        ApiResponse resourceNotFoundAPI = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new MediaType().addExamples("default",
                                new Example().value("""
                                                    {
                                                        "status": 404,
                                                        "message": "Resource Not Found",
                                                        "data": null
                                                    }
                                                    """)))
        );
        ApiResponse badRequestAPI = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new MediaType().addExamples("default",
                                new Example().value("""
                                                    {
                                                        "status": 400,
                                                        "message": "Bad request",
                                                        "data": {}
                                                    }
                                                    """)))
        );
        Components components = new Components();
        components.addResponses("badRequestAPI", badRequestAPI);
        components.addResponses("resourceNotFoundAPI", resourceNotFoundAPI);
        return new OpenAPI()
                .components(components)
                .info(new Info().title("Sakila API Doc").version("1.0.0").description("Sakila sapi document description ..."));
    }
}
