/*
package net.hurelhuyag.jtetest;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.output.PrintWriterOutput;
import gg.jte.resolve.DirectoryCodeResolver;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

public class Main {

    CodeResolver codeResolver = new DirectoryCodeResolver(Path.of("/home/hurlee/IdeaProjects/demo-jte/src/main/webapp/WEB-INF/views/"));
    TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);

    public Main() {
        var output = new PrintWriter(System.out, false, StandardCharsets.UTF_8);
        var model = Map.<String, Object>of(
                "message", new Message("Hello World"),
                "message2", new Message("Hello World")
        );
        templateEngine.render("index.jte", model, new PrintWriterOutput(output));
        output.flush();
    }

    public static void main(String[] args){
        new Main();
    }
}
*/
