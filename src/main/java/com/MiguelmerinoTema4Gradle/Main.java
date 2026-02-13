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
//nombramos a la primera ia
        var llama = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();
//nombramos la segunda ia
        var gemma = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();
        System.out.println("--- INICIAMOS EL DEBATE ---");
//Ponemos un try/catch para que en caso de error de conexion nos salte el mensaje en vez de la parrafada
        try {
            //Interaccion 1 llama pregunta
            String msg1 = llama.chat("Te gusta mucho warhammer 40000 pregunta sobre la mejor faccion");
            System.out.println("LLAMA: " + msg1);
            //Interaccion 2 responde gemma
            String msg2 = gemma.chat("Responde de forma crítica y breve a este argumento: " + msg1);
            System.out.println("GEMMA: " + msg2);
            //Interaccion 3 llama le debate
            String msg3 = llama.chat("Réplica de forma educada pero firme a este argumento: " + msg2);
            System.out.println("LLAMA: " + msg3);
            //Interaccion 4 acabamos el debate
            String msg4 = gemma.chat("Cierra el debate con una conclusión final basada en: " + msg3);
            System.out.println("GEMMA: " + msg4);
            System.out.println("--- FIN DEL DEBATE ---");
        } catch (Exception e) {
            System.err.println("Error: No se ha podido conectar con el servidor de Ollama. Asegúrate de que esta activo.");
        }
    }
}