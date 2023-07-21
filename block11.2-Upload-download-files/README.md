## Nombre proyecto Maven

**block11.2-upload-download-files**

## Objetivos

1) Permitir subir un fichero incluyendo como metadato la categoría.<br>
Guardar el fichero en disco duro, en una ruta (path) que se elija (por ejemplo c:/tmp)  y en una tabla (en H2) llamada ‘fichero’ los siguientes campos:
   - id
   - nombre de fichero
   - Fecha de subida
   - Categoría.
   
   Devolver la entidad ‘Fichero’ con los datos, incluyendo un ID único.<br>
   Descargar fichero, buscándolo por diferentes métodos (id y nombre de fichero).


2) Crear programa con estos endpoints.
- Subir fichero:  
     Petición POST. /upload/{tipo} (@RequestParam("file") MultipartFile file, String cátegoria).
   
   Solo aceptara ficheros con la extensión indicada en la URL (es decir lo que venga en ‘tipo’)
   

   - Para especificar dónde se deben guardar los ficheros se llamará a este endpoint:
        - Petición GET /setpath?path={directorio_donde_se_guardan_ficheros}
     

- Descargar fichero:
  - GET file?id=ID_FICHERO
  - GET file?nombre=NOMBRE

El programa al arrancar permite mandar un parámetro que es el directorio donde debe guardar los ficheros. Ejemplo: java –jar MIPROGRAMA.jar “/DIRECTORIO_A_GUARDAR”. Si no se especifica esta variable ponerlo en el directorio donde se lanza java

NOTA: NO es necesario utilizar Thymeleaf como en el ejemplo de la teoría. Se pueden subir los ficheros desde Postman..
