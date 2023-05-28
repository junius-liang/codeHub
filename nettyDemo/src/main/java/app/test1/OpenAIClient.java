package app.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OpenAIClient {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/engines/gpt-3.5-turbo/completions";

    public static String generateText(String prompt) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);
        String inputJson = "{\"prompt\": \"" + prompt.replaceAll("\"", "\\\\\"") + "\", \"max_tokens\": 2048, \"temperature\": 0.5}";
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = inputJson.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    public static void main(String[] args) throws IOException {
        String prompt = "Hello, I am a virtual assistant. How can I assist you today?";
        String response = generateText(prompt);
        System.out.println(response);
    }
}
