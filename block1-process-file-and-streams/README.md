## Nombre proyecto Maven
**block1-process-file-and-streams**

## Tiempo estimado: 
**12 horas**


Crear un método que reciba la ruta de un fichero CSV (por ejemplo: people.csv) y devuelva una lista de personas. El formato del fichero es el siguiente:
Cada línea del fichero corresponde a una persona (clase Person).
Cada línea puede contener hasta 3 campos separados por dos puntos (:). Los campos serán los siguientes:
name:town:age

El campo name es obligatorio, el resto de campos son opcionales. Nota: cuando no se informe el campo age guardaremos la persona con 0 años. Si hay alguna línea en el fichero en la que el campo age sea 0, consideraremos que esa persona tiene edad desconocida.
Si cualquiera de las líneas no tiene formato correcto el método debe lanzar una excepción InvalidLineFormatException. Esta excepción debe incluir un mensaje descriptivo incluyendo la línea que provocó el error y la causa de la excepción.
Nota: no usamos Stream en este método porque no permite tratar correctamente la excepción InvalidLineFormatException. Stream no se recomienda cuando hay que tratar excepciones.
Crear un segundo método que filtre las personas obtenidas al leer el fichero y devuelve esa lista de personas. Usar Stream para filtrar.

## Requisitos:
- Para crear el método que lee el fichero, no usar Stream, usar las clases NIO: https://www.baeldung.com/reading-file-in-java
- Para crear el segundo método, usar Stream.
- La ruta al fichero se le pasa al programa vía argumentos y será una ruta relativa.
- Tratar la excepción InvalidLineFormatException capturándola desde el método que invoca la función, imprimiendo su mensaje e imprimiendo el Stack Trace.

## Contenido de un fichero de ejemplo:
```
Jesús:Logroño:41
Andrés:Madrid:19
Ángel Mari:Valencia:
Laura Saenz::23
Fernando:Zaragoza
Sergio:Sevilla:36
María Calvo::38
Roberto:Madrid:20
Carlos:Barcelona:37
```

## Ejemplos de líneas incorrectas:
- Fernando:Zaragoza  -> Falta el último delimitador de campo (:)
- Sergio             -> Faltan dos delimitadores de campo
- :Sevilla:36        -> El nombre es obligatorio. Hay 3 espacios en el campo y esto se considera como blank.

## Objetivos
a) Invocar a la función con un filtro que conserve únicamente las personas menores de 25 años. Imprimir la lista devuelta en la consola. Para los campos opcionales vacíos, imprimir: unknown. Nota: no deben aparecer las personas con 0 años, es decir, con edad desconocida.

Salida esperada según el fichero de ejemplo:
```
Name: Andrés. Town: Madrid. Age: 19
Name: Laura Sáenz. Town: unknown. Age: 23
Name: Roberto. Town: Madrid. Age: 20
```


## Objetivos
b) Invocar a la función con un filtro que elimine las personas cuyo nombre empiece con la letra A. Imprimir la lista devuelta en la consola. Para los campos opcionales vacíos, imprimir: unknown.

c) Usando el resultado del apartado a), crear un Stream a partir de la lista y obtener el primer elemento cuya ciudad sea Madrid. Si existe algún elemento imprimirlo. Tratar correctamente el Optional.

d) Usando el resultado del apartado a), crear un Stream a partir de la lista y obtener el primer elemento cuya ciudad sea Barcelona. Si existe algún elemento imprimirlo. Tratar correctamente el Optional.
