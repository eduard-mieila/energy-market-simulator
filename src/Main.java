import com.fasterxml.jackson.databind.ObjectMapper;
import databases.Game;
import input.GameInputData;

import java.io.File;

public class Main {

    public static void main(final String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        GameInputData gameInput = objectMapper.readValue(new File(args[0]), GameInputData.class);

        Game currentGame = new Game(gameInput);
//        System.out.println(currentGame);



    }
}
