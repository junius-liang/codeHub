package com.app.gpt;

/**
 * @author junius
 * @date 2023/04/30 11:03
 * @project codeHub
 **/
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatGPTClient {

    public static void main(String[] args) throws IOException {

        // ChatGPT API endpoint
        String endpointUrl = "https://api.openai.com/v1/engines/davinci-codex/completions";

        // Your API key
        String apiKey = "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";

        // The prompt to send to ChatGPT
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your prompt: ");
        String prompt = scanner.nextLine();

        // Create JSON payload
        String payload = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 50}";

        // Send request to ChatGPT API
        URL url = new URL(endpointUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        byte[] input = payload.getBytes(StandardCharsets.UTF_8);
        outputStream.write(input, 0, input.length);

        // Read response from ChatGPT API
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Print response from ChatGPT API
        System.out.println(response.toString());
    }
}

