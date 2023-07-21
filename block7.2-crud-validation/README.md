## Nombre proyecto Maven

**block7.2-crud-validation**

## Tiempo estimado:

**8 horas parte 1 + 4 horas parte 2 + 8 horas parte 3.**

## Objetivos

### Parte 1 - Crear CRUD de tabla Persona
``` 
table persona
{
id_persona int [pk, increment]
usuario string [not null max-length: 10 min-length: 6]
password string  [not null]
name string [not null]
surname string  
company_email string  [not null ]
personal_email string [not null]
city string [not null]
active boolean  [not null]
created_date date  [not null]
imagen_url string
termination_date date
}
```

Realizar validaciones necesarias con lógica en java (no usar etiqueta @Valid)
```java 
if (usuario==null) {throw new Exception(“Usuario no puede ser nulo”); }
if (usuario.length()>10) { throw  new Exception(“Longitud de usuario no puede ser superior a 10 caracteres)
```
EI ID autoincremental se puede hacer con estas simples instrucciones:
```java
@GeneratedValue
private int id;
```

Poner 3  endpoints en la búsqueda.
- Buscar por ID
- Buscar por nombre de usuario (campo usuario)
- Mostrar todos los registros.

Usar DTOS, interfaces y clases de servicio.

Nota: No es necesario crear la carpeta repository. Para hacer más simple el ejercicio se pueden poner todos los servicios en application.

### Parte 2 - Crear excepciones
Crear dos tipos de excepción al CRUD anteriormente realizado:
- EntityNotFoundException que generará un código HTTP 404. Se lanzará cuando no se encuentren registros en un findById o si al borrar o modificar un registro este no existe.
- UnprocessableEntityException que devolverá un 422 (UNPROCESSABLE ENTITY) cuando la validación de los campos no cumpla los requisitos establecidos, al modificar o insertar un registro.

Ambas excepciones deberán devolver un objeto llamado CustomError con los campos:
```
Date timestamp;
Int HttpCode;
String mensaje; // Devolverá el mensaje de error.
```
### Parte 3 - Relaciones entre entidades

Añadir las siguientes tablas:
```
table student
{
id_student string [pk, increment]
id_persona string [ref:-  persona.id_persona] -- Relación OneToOne con la tabla persona.
num_hours_week int   [not null] -- Número de horas semanales del estudiante en formación
coments string
id_profesor string [ref: > profesor.id_profesor] -- Un estudiante solo puede tener un profesor.
branch string [not null] -- Rama principal delestudiante (Front, Back, FullStack)
}
```

```
table profesor
{
id_profesor string [pk, increment]
id_persona string [ref:- persona.id_persona] -- Relación OneToOne con la tabla persona.
coments string
branch string [not null] -- Materia principal que imparte. Por ejemplo: Front
}
```

```
table estudiante_asignatura
{
id_asignatura String [pk, increment]
id_student string [ref: > student.id_student] -- Un estudiante puede tener N asignaturas
asignatura string  -- Ejemplo: HTML, Angular, SpringBoot...
coments string
initial_date date [not null], -- Fecha en que estudiante empezó a estudiar la asignatura
finish_date date  -- Fecha en que estudiante termina de estudiar la asignatura
}
```

--- Ejemplo de entidades --
```java
@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Integer id_student;
@OneToOne
@JoinColumn(name = "id_persona")
Persona persona;
@Column(name = "horas_por_semana")
Integer num_hours_week;
@Column(name = "comentarios")
String coments;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "id_profesor")
Profesor profesor;
@Column(name = "rama")
String branch;
@OneToMany
List<Alumnos_Estudios> estudios;
}
```
```java
@Entity
@Table(name = "estudios")
@Getter
@Setter
public class Alumnos_Estudios {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id_study;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id")
    Profesor profesor;
    @ManyToMany(cascade = CascadeType.ALL)
    Student student;
    @Column(name = "asignatura")
    String asignatura;
    @Column(name = "comentarios")
    String comment;
    @Column(name = "initial_date")
    Date initial_date;
    @Column(name = "finish_date")
    Date finish_date;
}
```

La relación gráfica entre las tablas la tenéis en este enlace: https://dbdiagram.io/d/60938b29b29a09603d139d64

La relación de la tabla student y profesor con Persona es one-to-one<br>
La relación entre estudiante y profesor es tipo one-to-many. Un estudiante puede tener un solo profesor y un profesor puede tener N estudiantes.<br>
Un estudiante puede estar en N estudios por lo cual la relación es many-to-many.<br>
Realizar CRUD sobre TODAS las tablas. Cuando se busque por ID la persona, si es estudiante, debe devolver los datos de ‘estudiante’, ‘profesores’ y los estudios realizados por el estudiante. Si la persona es profesor, sacará los datos de la entidad profesor relacionada, los estudiantes a su cargo, y para cada estudiante los estudios realizados<br><br>
Como se puede apreciar los índices son tipo String y autoincrementales. Recordar que en la primera parte de este ejercicio tenéis cómo crear campos autoincrementales del tipo String.

a) Realizar CRUD de estudiante con endpoints similares a los de persona. De momento, no poner relación con profesor ( id_profesor )<br>
Modificar endpoint “/estudiante/{id}” para que acepte el QueryParam ‘ouputType’. Por defecto deberá tener el valor ‘simple’ (es decir si no se manda cogerá como valor el literal ‘simple’).
En el caso de que el valor de ‘outputType’ sea ‘simple‘,  la consulta devolverá sólo los datos del estudiante. En el caso de que sea ‘full’ devolverá los datos del estudiante y de la persona asociada.<br>
Ejemplo:
GET “http://localhost:8080/{id}?outputType=full”
```json

{
"id_student" : 1,
"num_hours_week" : 40,
"coments": “No puede trabajar por las tardes”,
"id_persona": 1,
"user": ‘abarj’,
"password": “1234567”,
"Name": “Alvaro”,
"Surname": “Apellido”,
"company_email": “alvaro@bosonit.com”,
"personal_email": “alvaro@gmail.com”,
"city" : “Madrid”,
"Active": true
"created_date": ‘2023-06-20'
"imagen_url": “http://pictures.com/imagen1.png”,
"termination_date": null
}
```
Como se puede observar habrá que crear diferentes DTOs de salida.

b) Realizar CRUD de las tablas restantes.<br>
Tener en cuenta que UNA persona solo puede ser o profesor o estudiante. <br>
En TODOS los endpoints de búsqueda de la entidad persona (por ID, por nombre o todas las personas), aceptar un parámetro que indique si debe devolver solo los datos de la persona o también los de estudiante o profesor si fuera alguno de ellos.

c) Crear endpoint en ‘estudiante_asignatura’ que permita buscar por id de estudiante. Este endpoint sacará todas las asignaturas que tiene un estudiante.<br>
Realizar chequeos lógicos: ¿Borrar persona si tiene un estudiante/profesor asignado? ¿Borrar asignatura si tiene estudiantes asignados?<br>
d) Realizar endpoints de estudiante para asignarle una o más asignaturas. Crear otro endpoint para desasignar una o más asignaturas (deberá poder recibir una lista de IDs de asignaturas).

### Parte 4 - Feign
En el CRUD anteriormente realizado incluir en la entidad Persona el siguiente endpoint.<br>
@RestControler(“/profesor/{id})<br>
ProfesorOutputDto GET getProfesor(@PathVariable int id)<br>

Este endpoint deberá llamar al de la entidad profesor que devuelve el profesor por id, en el puerto 8081 usando RestTemplate.<br>
**Usando  Feign:**<br>
Crear la llamada anterior usando  feign.<br>
Para hacer la prueba deberemos tener dos instancias de nuestro programa lanzado. Una corriendo en el puerto 8080 y otra en el puerto 8081.
La prueba consistirá en llamar a GET localhost:8080/persona/profesor/1 y que ésta llamada llame a localhost:8081/profesor/1 devolviéndonos los datos del profesor.

**Importante:**<br>
Para usar feign hay que añadir la dependencia ‘OpenFeign” que es parte del paquete Spring Cloud. Este paquete es independiente de Spring Boot y  hay que tener cuidado con las versiones. Un Spring Cloud, versión X, puede no funcionar con Spring Boot Y.

Para asegurarse de que no hay problemas lo más fácil es crear el proyecto,  desde https://start.spring.io, con la versión de Spring Boot deseada y  añadiendo OpenFeign, comprobar qué versión se añade en la página web.
