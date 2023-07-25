## Nombre proyecto Maven

**block16-spring-cloud**

## Objetivos
1.- Desarrollar una aplicación backend robusta con dos entidades (Cliente y Viaje) que permita realizar operaciones CRUD y gestionar pasajeros en viajes.
2.- Implementar una aplicación backend-Front que interactúe con la aplicación backend utilizando RestTemplate o Feign para generar tickets con información de pasajeros y viajes.
3.- Crear una aplicación Eureka-Naming-Server para el registro y descubrimiento de servicios (microservicios) en el entorno de Spring Cloud.
4.- Desarrollar una aplicación API-Gateway que funcione como punto de entrada a los microservicios y facilite su conexión con Eureka.
5.- Dockerizar las aplicaciones para permitir una fácil implementación y manejar las dependencias entre servicios y bases de datos.

## Requisitos
- La aplicación backend debe tener endpoints para realizar operaciones CRUD en las entidades Cliente y Viaje.
- La aplicación backend debe ser capaz de gestionar la cuenta de pasajeros en cada viaje, limitándola a 40 pasajeros.
- La aplicación backend-Front debe utilizar RestTemplate o Feign para comunicarse con la aplicación backend y generar tickets con información de pasajeros y viajes.
- La aplicación Eureka-Naming-Server debe permitir el registro y descubrimiento de microservicios.
- La aplicación API-Gateway debe funcionar como punto de entrada a los microservicios y establecer la conexión con Eureka.
- La dockerización de las aplicaciones debe realizarse utilizando docker-compose para gestionar las dependencias entre servicios y bases de datos de manera efectiva.

### Parte 1 - Crear aplicación Backend
Primero se creará una aplicación backend con dos entidades:
Cliente.
Con las variables:
	Id, nombre, apellido, edad, email y teléfono.

Los endpoints serán un crud básico, crear borrar, actualizar y buscar tanto por id como obtener todos los clientes.
Viaje (Es un autobús de 40 plazas).
Con las variables:
	Id, origin, destination, departureDate, arrivalDate,  passenger y status.
Los endpoints serán los siguientes:
Crud básico para cada viaje. 
Añadir pasajero a viaje. Usaremos el id de cada uno, se verá de la siguiente forma:
localhost:8080/trip/addPassenger/4/8


Haremos una cuenta de pasajeros que hay en cada viaje, ya que al añadir un pasajero a cada viaje deberá de limitarse a la cuenta de 40 pasajeros.
localhost:8080/passenger/count/{tripId}
Un endpoint para cambiar la disponibilidad del viaje ya que es posible que el autobús se averíe.
localhost:8080/trip/{tripId}/{tripStatus}
Finalmente crearemos un endpoint que nos indique la disponibilidad del viaje.
localhost:8080/trip/verify/{tripId}
Hemos de tener en cuenta que ambas entidades están conectadas a una base de datos.


### Parte 2 - Crear aplicación Backend-Frontend
Segundo haremos la aplicación backend-Front que constará de la siguiente entidad:
Ticket. ( Se guarda en base de datos diferente a la de viajes o clientes)

Con las variables:
id, passengerId, passengerName, passenger Lastname, passgener Email, tripOrigin, tripDestination, departureDate y arrivalDate.
Tendremos que crear dos entidades a parte exactamente iguales que las del backend pero sin acceso a la base de datos, ya que con RestTemplate recogeremos variables de tipo cliente y viaje.


Como es un caso de práctica resumimos los endpoint a uno solo, que será el que cree el ticket, obteniendo el pasajero a través del id y añadiendolo al viaje.
localhost:8080/generateTicket/{userId}/{tripId}

Para esta parte necesitaremos conocer el uso de RestTemplate o Feign, que hará uso de la aplicación backend ejecutándose de forma simultánea a esta.

Para esta aplicación se podrá usar la misma base de datos u otra diferente (si es así mejor, se entenderá mejor por que los microservicios son tan usados).



### Parte 3 - Crear aplicación Eureka-naming-server(Discovery-Server)
Tercero crearemos la aplicación Eureka-Naming-Server.	

### Parte 4 - Crear aplicación API-Gateway
Cuarto crearemos la aplicación api-gateway, que nos abrirá los puertos y permitirá relacionar nuestros microservicios con eureka.

Finalmente dockerizar la aplicación,  respetando como dependen de las bases de datos y entre sí.
