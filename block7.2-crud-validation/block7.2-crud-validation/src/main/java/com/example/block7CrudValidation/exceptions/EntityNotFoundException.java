<<<<<<< HEAD
package com.example.block7CrudValidation.exceptions;

import java.util.Calendar;

public class EntityNotFoundException extends Exception {
    public CustomError EntityNotFoundException(String errorMessage) {
        CustomError customError = new CustomError(Calendar.getInstance().getTime(), 404, "No se han podido encontrar resultados");
        return customError;
    }
=======
package com.example.block7CrudValidation.exceptions;

import java.util.Calendar;

public class EntityNotFoundException extends Exception {
    public CustomError EntityNotFoundException(String errorMessage) {
        CustomError customError = new CustomError(Calendar.getInstance().getTime(), 404, "No se han podido encontrar resultados");
        return customError;
    }
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
}