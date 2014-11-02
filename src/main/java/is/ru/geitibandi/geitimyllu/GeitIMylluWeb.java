package is.ru.geitibandi.geitimyllu;

import spark.Route;
import spark.Request;
import spark.Response;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;
import spark.servlet.SparkApplication;

// Class that handles incoming web requests and sends RESTful responses in return
public class GeitIMylluWeb implements SparkApplication {
    private static final String API_URL = "/api/v1";

    public static void main(String[] args) {
        staticFileLocation("/public");
        SparkApplication webapp = new GeitIMylluWeb();
        String port = System.getenv("PORT");
        if (port != null) {
            setPort(Integer.valueOf(port));
        }
        webapp.init();
    }

    public void init() {
        final GeitIMyllu game = new GeitIMyllu();

        get(new Route(API_URL + "/state") {
            @Override
            public Object handle(Request request, Response response) {
                return game.getGameState();
            }
        });

        post(new Route(API_URL + "/play/row/col") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    game.play(Integer.parseInt(request.queryParams("row")),
                    Integer.parseInt(request.queryParams("col")));
                }
                catch (IllegalArgumentException e) {
                    response.status(403);
                    response.body(e.getMessage());
                }
                return "";
            }
        });

        post(new Route(API_URL + "/reset") {
            @Override
            public Object handle(Request request, Response response) {
                game.resetGame();
                return "";
            }
        });

        get(new Route(API_URL + "/board") {
            @Override
            public Object handle(Request request, Response response) {
                return game.boardAsString();
            }
        });

        get(new Route(API_URL + "/turn") {
            @Override
            public Object handle(Request request, Response response) {
                return game.getTurn();
            }
        });

        get(new Route(API_URL + "/whosturn") {
            public Object handle(Request request, Response response) {
                return game.getCurrentPlayer();
            }
        });

        get(new Route(API_URL + "/played") {
            public Object handle(Request request, Response response) {
                return game.getPlayedGames();
            }
        });

        get(new Route(API_URL + "/won") {
            public Object handle(Request request, Response response) {
                boolean gameWon = game.gameWon();
                boolean boardFull = game.boardFull();
                if (gameWon) {
                    return game.getCurrentPlayer();
                }
                else if (boardFull) {
                    return 'T';
                }
                return 'N';
            }
        });

        get(new Route(API_URL + "/won/:player") {
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

        get(new Route(API_URL + "/ties") {
            public Object handle(Request request, Response response) {
                return game.getTies();
            }
        });
    }
}
