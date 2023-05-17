package com.example.spring_homework14.Contraller;


import com.example.spring_homework14.ApiResponse.ApiResponse;
import com.example.spring_homework14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeesController {

    ArrayList<Employee> employees =new ArrayList<>();

    @GetMapping("/get")
    public ArrayList getEmployee(){
        return employees;
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors){
        if (errors.hasErrors()){
            String message  = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);

        return ResponseEntity.status(200).body(new ApiResponse("Employee added"));
    }

    @PutMapping("update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @Valid @RequestBody Employee employee, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(index,employee);

        return ResponseEntity.status(200).body(new ApiResponse("Employee updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index, @RequestBody Employee employee) {

        if (employee.getName().equals(employees.get(index).getName())){
            employees.remove(index);
            return ResponseEntity.status(200).body(new ApiResponse("Employee removed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Sorry can delete this Employee need to enter the Employee's name right"));
    }
    @PutMapping("/onleave/{index}")
    public ResponseEntity onLeaveEmployee(@PathVariable int index, @RequestBody Employee employee) {

        if (!employee.getName().equals(employees.get(index).getName())){
            employees.get(index).setOnLeave(employee.isOnLeave());
            return ResponseEntity.status(200).body(new ApiResponse("On leave status updated!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("sorry wrong name"));
    }


    @PutMapping("/leave/{index}")
    public ResponseEntity leaveEmployee(@PathVariable int index,
                                        @Valid @RequestBody Employee employee, Errors errors){
        if (!employee.getName().equals(employees.get(index).getName())){
            return ResponseEntity.status(400).body(new ApiResponse("sorry wrong name"));
        }

        if (employees.get(index).isOnLeave()){
            employees.get(index).setAnnualLeave( employees.get(index).getAnnualLeave() - 1);
            return ResponseEntity.status(400).body(new ApiResponse("Employee already on leave"));
        }
        if (employees.get(index).getAnnualLeave() > employee.getAnnualLeave()){
            employees.get(index).setAnnualLeave( employees.get(index).getAnnualLeave() - employee.getAnnualLeave());
            employees.get(index).setOnLeave(true);
            return ResponseEntity.status(200).body(new ApiResponse("Employee can go do a leave"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Employee out of leave days"));
    }




}
