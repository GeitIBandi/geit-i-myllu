package is.ru.geitibandi.geitimyllu;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

// Class that handles incoming web requests and sends RESTful responses in return
public class GeitIMylluWeb implements SparkApplication {
    private final static String apiUrl = "/api/v1";

    public static void main(String[] args){
        SparkApplication webapp = new GeitIMylluWeb();
        webapp.init();
    }

    public void init(){
        final GeitIMyllu game = new GeitIMyllu();

        get(new Route(apiUrl + "/state") {
            @Override
            public Object handle(Request request, Response response) {
                return game.getGameState();
            }
        });

        post(new Route(apiUrl + "/play/:row/:col") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    game.play(Integer.parseInt(":row"), Integer.parseInt(":col"));
                }
                catch (IllegalArgumentException e) {
                    response.status(403);
                    response.body(e.getMessage());
                }
                return "";
            }
        });

        post(new Route(apiUrl + "/reset") {
            @Override
            public Object handle(Request request, Response response) {
                game.resetGame();
                return "";
            }
        });

        get(new Route(apiUrl + "/board") {
            @Override
            public Object handle(Request request, Response response) {
                return game.boardAsString();
            }
        });

        get(new Route(apiUrl + "/turn") {
            @Override
            public Object handle(Request request, Response response) {
                return game.getTurn();
            }
        });

        get(new Route(apiUrl + "/whosturn") {
            public Object handle(Request request, Response response) {
                return game.getCurrentPlayer();
            }
        });

        get(new Route(apiUrl + "/played") {
            public Object handle(Request request, Response response) {
                return game.getPlayedGames();
            }
        });

        get(new Route(apiUrl + "/won") {
            public Object handle(Request request, Response response) {
                boolean gameWon = game.gameWon();
                boolean boardFull = game.boardFull();
                char winner = (gameWon ? game.getCurrentPlayer() :
                    (boardFull ? 'T' : 'N'));
                return winner;
            }
        });

        get(new Route(apiUrl + "/won/:player") {
            public Object handle(Request request, Response response) {
                String playerStr = ":player";
                char player = playerStr.charAt(0);
                if (player == 'X' || player == 'x') {
                    return game.getGamesWonByPlayerX();
                }
                else if (player == 'O' || player == 'o') {
                    return game.getGamesWonByPlayerO();
                }
                else {
                    throw new IllegalArgumentException("Player must be X or O.");
                }
            }
        });

        get(new Route(apiUrl + "/ties") {
            public Object handle(Request request, Response response) {
                return game.getTies();
            }
        });
    }
}