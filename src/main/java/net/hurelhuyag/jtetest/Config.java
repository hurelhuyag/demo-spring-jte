package net.hurelhuyag.jtetest;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.PrintWriterOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletContext;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Map;

@ComponentScan("net.hurelhuyag.jtetest")
@EnableWebMvc
class Config implements WebMvcConfigurer {

    @Bean
    TemplateEngine templateEngine(ServletContext context) throws MalformedURLException, URISyntaxException {
        var root = Path.of(context.getResource("/WEB-INF/views/").toURI());
        var codeResolver = new DirectoryCodeResolver(root);
        return TemplateEngine.create(codeResolver, root.resolve("WEB-INF/classes/"), ContentType.Html, this.getClass().getClassLoader());
    }

    @Bean
    ViewResolver viewResolver(TemplateEngine templateEngine) {
        return (viewName, locale) -> (model, request, response) -> {
            //noinspection unchecked
            response.setContentType("text/html; charset=UTF-8");
            templateEngine.render(viewName + ".jte", (Map<String, Object>) model, new PrintWriterOutput(response.getWriter()));
        };
    }


}
