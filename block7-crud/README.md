## Nombre proyecto Maven

**block7-crud**

## Tiempo estimado:

**16 horas**

## Objetivos
Realizar un CRUD sobre la tabla Persona (usando H2 como base de datos).
<br>
La tabla persona es la siguiente:
```
table persona
{
    nombre string,
    edad string,
    poblacion string  
}
```
Para ello se deberán crear 4 clases de controladores, una para cada una de las opciones:  
- Añadir Persona. Petición POST. Body:  { “name”:”Jesús” …. } http://URL/persona
- Modificar por id. Petición PUT. http://URL/persona/{id} - Body se manda los datos.
<br>
Tener en cuenta que si no se manda un campo, en el objeto recibido, este estará a nulo y NO queremos modificar a NULO los campos.

    En el caso de que el ID no exista devolver un 404 - Persona no encontrada.


- Borrar (por id). Petición DELETE. http://URL/persona/{id} - Devolver un objeto Persona o un 404: Persona no encontrada.
- Consultar.
  - por Id Petición GET http://URL/persona/{id} Devolver Persona o un código HTTP 404 si no existe.
  - Por nombre  http://URL/persona/nombre/{nombre}  Devolver List<Persona>
  - Devolver todos los registros  . http://URL/persona. Devolver List<Persona>

Se deberá crear una clase de servicio que será la que inyectaremos en los controladores. Esa clase tendrá la lógica para el mantenimiento de los datos.
    
**Usar @RequestMapping(“persona”)** para que todas las llamadas sean tipo http://URL/persona/...
