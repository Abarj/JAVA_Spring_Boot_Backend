## Nombre proyecto Maven

**block11-Spring-Web-avanzado (hay que modificar el proyecto existente block7.2-crud-validation)**

## Tiempo estimado:

**2 horas.**
## Objetivos
Permitir realizar peticiones entre dominios modificando el módulo del ejercicio CRUD con validación para la ruta ‘/person’.
Para probar ir a la página: https://codepen.io/de4imo/pen/VwMRENP .

Actualizar el back para que funcionen estas llamadas:
Alta: Tipo. POST -  Ruta: http://localhost:8080/addperson  

Objeto mandado:
```js
'usuario': document.getElementById('username').value,
'password': document.getElementById('passwd').value,
'name': document.getElementById('name').value,
'surname': document.getElementById('surname').value,
'company_email': document.getElementById('emailcomp').value,
'personal_email': document.getElementById('emailpers').value,
'city': document.getElementById('city').value,
'active': document.getElementById('active').checked,
'created_date': document.getElementById('created_date').value,
'imagen_url': document.getElementById('imagen_url').value,
'termination_date': document.getElementById('finish_date').value,
})
```  
Consulta: http://localhost:8080/getall

List < Person > (Mismos campos que en el add)
