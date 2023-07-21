## Nombre proyecto Maven
**block5.3-Logging**

## Tiempo estimado: 
4 horas + 3 horas parte opcional

Crear un programa que escriba los logs de tipo "Error" en un fichero y los de tipo "Warning" o inferiores solo en la consola.


## Requisitos:
- Configurar el sistema de logs para que los logs de tipo "Error" se almacenen en un fichero.
- Los logs de tipo "Warning" o inferiores deben mostrarse únicamente por consola.
- Utilizar la URL https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html para consultar todas las configuraciones posibles relacionadas con el logging. Se sugiere el uso del filtro ThresholdFilter para lograr el objetivo.



## Parte Opcional:
Los ficheros de log deberán seguir el siguiente patrón de nombres: spring-logging-NN.log, donde NN es un número consecutivo. El programa debe generar un nuevo fichero de log cada vez que se inicie la aplicación y mantener un máximo de 5 ficheros.

Por ejemplo:

El primer fichero será: spring-logging.log
El segundo fichero será: spring-logging-01.log
Y así sucesivamente hasta spring-logging-05.log (si existe este último fichero, será borrado y los ficheros se renombrarán de forma consecutiva).
Además, configurar el sistema de logs para que si un fichero excede los 5K de longitud, se realice la rotación del fichero.

Aclaración importante: Para realizar esta parte opcional, habría que crear un fichero 'logback-spring.xml' o 'logback.xml'. Sin embargo, en el ámbito del curso, solo se requiere conocer las posibilidades generales para configurar el sistema de logging, y no se requiere crear dicho fichero. Estableciendo propiedades en el archivo application.properties, como logging.file.name o logging.logback.rollingpolicy.max-file-size, generalmente será suficiente para configurar el sistema de log.

Es necesario utilizar los diferentes niveles de log (warning, info, etc.) y mostrar los logs de tipo "Warning" o inferiores por consola, mientras que los de tipo "Error" se deben almacenar en un fichero.
