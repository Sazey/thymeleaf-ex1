package org.example;

import java.time.LocalDate;

import org.example.model.ToyRobot;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;;

/**
 * Hello Thymeleaf
 */
public class TemplateApp
{
    private TemplateEngine templateEngine;

    public void start(io.javalin.http.Context ctx){
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver( configureTemplateResolver() );

        final TemplateSpec templateSpec = createTemplateSpec();

        String html = templateEngine.process(
            templateSpec,
            initVariableData( new ToyRobot( "Fred", LocalDate.of( 2022, 2, 2 ), 38 )));

//        templateEngine.process(
//            templateSpec,
//            initVariableData( new ToyRobot( "Pluto", LocalDate.of( 1873, 4, 1 ), 13 )));
//
//        templateEngine.process(
//            templateSpec,
//            initVariableData( new ToyRobot( "Ming", LocalDate.now(), 21 )));

        ctx.html(html);
    }

    //FIXME
    private ITemplateResolver configureTemplateResolver(){
        final FileTemplateResolver resolver = new FileTemplateResolver();

        // Get the absolute path to your project directory
        String projectPath = System.getProperty("user.dir");

        // Set resolver templates location
        resolver.setPrefix(projectPath + "/src/main/resources/templates/");

        // Set resolver suffic teplate name won't need suffix now
        resolver.setSuffix(".html");

        // Set temlte mode to HTML
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    private TemplateSpec createTemplateSpec(){
        return new TemplateSpec("helloWorld", TemplateMode.HTML);
    }

    private Context initVariableData(ToyRobot aRobot ){
        final Context ctx = new Context();
        ctx.setVariable( "bot", aRobot );
        return ctx;
    }
}