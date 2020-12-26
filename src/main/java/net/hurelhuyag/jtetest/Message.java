package net.hurelhuyag.jtetest;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.PrintWriterOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import javax.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Map;

public class Message{
    private final String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

@ComponentScan("net.hurelhuyag.jtetest")
@EnableWebMvc
@Controller
class Config implements WebMvcConfigurer {

    @Bean
    TemplateEngine templateEngine(ServletContext context) throws MalformedURLException, URISyntaxException {
        var root = context.getResource("/WEB-INF/views/").toURI();
        var codeResolver = new DirectoryCodeResolver(Path.of(root));
        return TemplateEngine.create(codeResolver, Path.of("/tmp/demo-jte-classes"), ContentType.Html);
    }

    @Bean
    ViewResolver viewResolver(TemplateEngine templateEngine){
        return (viewName, locale) -> (model, request, response) -> {
            templateEngine.render(viewName+".jte", (Map<String, Object>) model, new PrintWriterOutput(response.getWriter()));
        };
    }

    @GetMapping({"/"})
    String index(ModelMap model){
        model.put("message", new Message("Hello World"));
        return "index";
    }
}
