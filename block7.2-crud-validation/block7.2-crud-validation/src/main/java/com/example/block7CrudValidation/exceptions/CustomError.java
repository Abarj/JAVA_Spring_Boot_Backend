<<<<<<< HEAD
package com.example.block7CrudValidation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private Date timestamp;
    private int HTTPcode;
    private String message;
=======
package com.example.block7CrudValidation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {
    private Date timestamp;
    private int HTTPcode;
    private String message;
>>>>>>> 0215ed2c784a238139adc681a90ff64f94587231
}