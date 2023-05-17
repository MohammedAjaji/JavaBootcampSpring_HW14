package com.example.spring_homework14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Employees {

    @NotEmpty(message = "id should not be empty ")
    @Size(min = 2, message = "id should be at least 2")
    private String id;

    @NotEmpty(message = "name should not be empty ")
    @Size(min = 4, message = "username should be at least 4 ")
    private String name;

    @NotEmpty(message = "position should not be empty ")
    @Pattern(regexp = "[supervisor ,coordinator]", message = "must be supervisor or coordinator only")
    private String position;


    @NotNull(message = "age should not be empty ")
    @Min(25)
    @Pattern(regexp = "^[0-100]*$", message = "Age should be at least 25")
    private int age;

    @NotNull(message = "employmentYear should not be empty ")
    @Min(1)
    @Pattern(regexp = "^[0-100]*$")
    private int employmentYear;

    @NotNull(message = "annualLeave should not be empty ")
    @Pattern(regexp = "^[0-100]*$")
    private int annualLeave;

    private boolean onLeave = false;


}
