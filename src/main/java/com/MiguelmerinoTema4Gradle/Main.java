package com.MiguelmerinoTema4Gradle;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //No me funciona el ponerlo desde setings ni desde la esquina, asique me ha tocado forzarlo
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        // El TOKEN no es necesario para interactuar con modelos locales
        final String TOKEN = "PEGA_AQUI_TU_TOKEN";

        var llama = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();

        var gemma = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        List<ChatMessage> history = new ArrayList<>();
//Ponemos un try/catch para que en caso de error de conexion nos salte el mensaje en vez de la parrafada
        try {
        // Interacción 1
        history.add(new UserMessage("Hola, soy Miguel"));
        AiMessage respuesta = llama.chat(history).aiMessage();
        history.add(respuesta);
        // Interacción 2
        history.add(new UserMessage("¿Recuerdas cómo me llamo?"));
        respuesta = llama.chat(history).aiMessage();
        history.add(respuesta);
        System.out.println(respuesta.text());

        } catch (Exception e) {
            System.err.println("Error: No se ha podido conectar con el servidor de Ollama. Asegúrate de que esta activo.");
        }
    }
}