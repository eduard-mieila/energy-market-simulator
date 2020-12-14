import com.fasterxml.jackson.databind.ObjectMapper;
import databases.Game;
import input.GameInputData;
import output.OutputData;

import java.io.File;

public final class Main {

    private Main() { }

    public static void main(final String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        GameInputData gameInput = objectMapper.readValue(new File(args[0]), GameInputData.class);

        Game currentGame = new Game(gameInput);
//        System.out.println(currentGame);
        currentGame.runGame();

        OutputData output = new OutputData(currentGame);
        //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);



    }
}
