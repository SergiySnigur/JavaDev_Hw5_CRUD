package jdbc_database.settings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prefs {
    public static final String PREFS_FILENAME = "prefs.json";
    public static final String DATABASE_JDBC_CONNECTION_URL = "dataBaseUrl";

    private Map<String, Object> prefs = new HashMap<>();

    public Prefs() {
        this(PREFS_FILENAME);
    }

    public Prefs(String fileName) {
        try {
            String json = Files.readString(Paths.get(fileName));

            TypeToken<?> typeToken = TypeToken.getParameterized(Map.class, String.class, Object.class);
            prefs = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPref(String key) {
        return prefs.get(key).toString();
    }
}


