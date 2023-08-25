package com.example.block26SpringBootAWSLambda.config;

import com.example.block26SpringBootAWSLambda.entity.Character;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class AwsLambdaConfig {

    @Bean
    public Filter getFilter() {
        return new SecurityFilter();
    }

    /*
    Spring Cloud Function adopta y se basa (soporta) en las 3 interfaces funcionales
    principales definidas por Java
       - Proveedor<O> -> Retorna valor pero no recibe ningún parámetro
        (Supplier)

       - Función<I,O> -> Recibimos un parámetro y debemos retornar otro parámetro
        (Function)

       - Consumidor<I> -> Recibe un parámetro pero no retorna nada
        (Consumer)

    Las funciones lambda cuando trabajamos con Function de Spring Cloud por defecto reciben las siguientes peticiones http:
    - El Supplier siempre tiene que ser una petición de tipo GET
    - El Consumer lo ideal es hacerlo con una petición POST (parámetro en el body de la petición)
    - Las Function se pueden manejar tanto con GET como con POST
     */

    @Bean
    public Supplier<String> greeting() { // Debe devolver una función lambda con un valor
        return (/*Aquí no recibe nada*/) -> "Hello World!";
    }

    @Bean
    public Consumer<String> printParam() {
        return (param) -> {
            System.out.println(param);
        };
    }

    @Bean
    public Function<String, String> receiveParam() {
        return (param) -> {
            String name = param.toUpperCase();
            return name;
        };
    }

    // Generate a JSON a través de un Supplier
    @Bean
    public Supplier<Map<String, Object>> createCharacter() {
        return () -> {
            Map<String, Object> character = new HashMap<>();
            character.put("name", "Thadius");
            character.put("healthPoints", 100);
            character.put("skills", "Charge");

            return character;
        };
    }

    // Recibir JSON y retornar String
    @Bean
    public Function<Map<String, Object>, String> receiveCharacter() {
        return (param) -> {
            param.forEach((key, value) -> System.out.println(key + " - " + value.toString()));

            return "Character received";
        };
    }

    // Recibir un Objeto y retornar un Objeto
    @Bean
    public Function<Character, Character> receiveAnObject() {
        return (param) -> param;
    }

    // Recibir un JSON y retornar un JSON
    @Bean
    public Function<Map<String, Object>, Map<String, Object>> processCharacters() {
        return (param) -> {
            Map<String, Object> mapCharacter = param;

            mapCharacter.forEach((key, value) -> System.out.println(key + " - " + value.toString()));

            Map<String, Object> newCharacter = new HashMap<>();
            newCharacter.put("name", "Nimbus");
            newCharacter.put("healthPoints", 50);
            newCharacter.put("skills", new String[]{"Provoke", "Stealth"});

            return newCharacter;
        };
    }
}
