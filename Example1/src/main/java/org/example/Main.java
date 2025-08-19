package org.example;



// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
        import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

        import com.fasterxml.jackson.databind.ObjectMapper;
        import java.util.HashMap;

public cl {
    public static void main(String[] args) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "John");
        map.put("age", 30);

        ObjectMapper objectMapper = new ObjectMapper();

        // Convert HashMap to JSON String
        String json = objectMapper.writeValueAsString(map);

        System.out.println(json);
    }
}



