## Nombre proyecto Maven
**block5-command-line-runner**

## Tiempo estimado:
**4 horas**

## Objetivos
1) Realizar programa con tres funciones que se deberán ejecutar al arrancar el programa.
   - Una mostrará el texto “Hola desde clase inicial”
   - Otra escribirá “Hola desde clase secundaria” 
   - La tercera función pondrá “Soy la tercera clase”. 
   

Se deberá utilizar @Postconstruct en la primera función y la interface CommandLineRunner en los dos siguientes. ¿En qué orden se muestran los mensajes? ¿Por qué?
      
Por ejemplo:
```java
   @SpringBootApplication
   public class Main {

   @Bean
   CommandLineRunner ejecutame()
        {
            return p -> {
                 System.out.println("Linea a ejecutar cuando arranque");
            };
        }
   }
   ```

2) Modificar la tercera función para que imprima los valores pasados como parámetro al ejecutar el programa.
