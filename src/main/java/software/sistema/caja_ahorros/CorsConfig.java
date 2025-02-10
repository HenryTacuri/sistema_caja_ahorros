package software.sistema.caja_ahorros;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Habilitar CORS para todas las rutas de la aplicación
        registry.addMapping("/**")
                .allowedOrigins("*")  // Origen permitido (angular)
                .allowedMethods("*")
                .allowedHeaders("*");;
    }
}