<<<<<<< HEAD
package com.example.block7CrudValidation.exceptions;

import java.util.Calendar;

public class UnprocessableEntityException extends Exception{
    public CustomError UnprocessableEntityException() {
        CustomError customError = new CustomError(Calendar.getInstance().getTime(), 422, "Debe rellenar los campos correctamente");
        return customError;
    }
=======
package com.example.block7CrudValidation.exceptions;

import java.util.Calendar;

public class UnprocessableEntityException extends Exception{
    public CustomError UnprocessableEntityException() {
        CustomError customError = new CustomError(Calendar.getInstance().getTime(), 422, "Debe rellenar los campos correctamente");
        return customError;
    }
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
}