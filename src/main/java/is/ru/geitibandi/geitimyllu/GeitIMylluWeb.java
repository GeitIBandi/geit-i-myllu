package is.ru.geitibandi.geitimyllu;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

// Class that handles incoming web requests and sends RESTful responses in return
public class GeitIMylluWeb implements SparkApplication {
    public static void main(String[] args){
        SparkApplication webapp = new GeitIMylluWeb();
        webapp.init();
    }

    public void init(){
        final GeitIMyllu game = new GeitIMyllu();

        get(new Route("/api/v1/turn"){
            @Override
            public Object handle(Request request, Response response){
                return game.getTurn();
            }
        });

        post(new Route("/api/v1/play/:nr"){
            @Override
            public Object handle(Request request, Response response){
                return game.play(Integer.parseInt(":param"));
            }
        });
    }
}