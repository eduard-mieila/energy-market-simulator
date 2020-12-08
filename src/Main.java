import com.fasterxml.jackson.databind.ObjectMapper;
import inputDatas.GameInputData;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        GameInputData gameInput = objectMapper.readValue(new File(args[0]), GameInputData.class);
        System.out.println(gameInput);
    }
}
