import com.fasterxml.jackson.databind.ObjectMapper;
import databases.Game;
import input.GameInputData;
import output.OutputData;

import java.io.File;

public final class Main {

    private Main() { }

    public static void main(final String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read input data
        GameInputData gameInput = objectMapper.readValue(new File(args[0]), GameInputData.class);

        // Convert InputData to Data
        Game currentGame = new Game(gameInput);

        // Run Game Simulation
        currentGame.runGame();

        // Convert Data to OutputData
        OutputData output = new OutputData(currentGame);

        // Write OutputData to JSON file
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);
    }
}
