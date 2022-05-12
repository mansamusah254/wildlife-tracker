import static spark.Spark.*;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main (String [] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null){
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port= 4567;
        }
        Spark.port(port);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String,Object>model = new HashMap<>();
            return new ModelAndView(model,"index.hbs");

        },new HandlebarsTemplateEngine());

        get("/animal/add", (request, response)-> {
            Map<String,Object>model = new HashMap<String,Object>();
            return new ModelAndView(model,"animal.hbs");
        },new HandlebarsTemplateEngine());

//        get("/sightings", ((request, response) -> new ModelAndView(model, "sightings.hbs")), new HandlebarsTemplateEngine());

        get("/animals/new", (request, response)-> {
            Map<String,Object>model = new HashMap<String,Object>();
            model.put("newAnimal",Animal.all());
            return new ModelAndView(model,"animals.hbs");
        },new HandlebarsTemplateEngine());

//        get("sightings/new", ((request, response) -> new ModelAndView(model, "sighting.hbs")), new HandlebarsTemplateEngine());

        post("/animal/add/new", (request, response) -> {
            Map<String,Object>model = new HashMap<String,Object>();
            String animal_name = request.queryParams("animal_name");
            int animal_id = Integer.parseInt(request.queryParams("animal_id"));
            int animal_age = Integer.parseInt(request.queryParams("animal_age"));
            Animal animal = new Animal(animal_id, animal_age, animal_name);
            animal.save();
            response.redirect("/");
            return new ModelAndView(model, "animal.hbs");
        });

        get("/sighting/add", (request, response)-> {
            Map<String,Object>model = new HashMap<>();
            model.put("newSighting",Sighting.all());
            return new ModelAndView(model,"sighting.hbs");
        },new HandlebarsTemplateEngine());


        post("/sighting/add", (request, response) -> {
            String animal_name = request.queryParams("animal_name");
            String location = request.queryParams("location");
            String ranger_name = request.queryParams("ranger_name");
            Sighting newSighting = new Sighting(animal_name,location, ranger_name);
            newSighting.save();
            response.redirect("/");
            return  null;
        });
    }
}
