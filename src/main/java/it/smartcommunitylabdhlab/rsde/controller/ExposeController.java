package it.smartcommunitylabdhlab.rsde.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExposeController {

    private static Map<Integer, Employee> empRepo = new HashMap<>();
    static {

	empRepo.put(1, new Employee(1, "Nawaz", "PD", 1234567.00));
	empRepo.put(2, new Employee(2, "Khurshid", "PD", 3456567.00));

    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getEmployeesById(@PathVariable Integer id) {
	return new ResponseEntity<>(empRepo.get(id), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<Object> getEmployees() {
	return new ResponseEntity<>(empRepo.values(), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployees(@RequestBody Employee employee) {
	empRepo.put(employee.getEmpId(), employee);
	return new ResponseEntity<Object>("Successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployees(@PathVariable Integer id, @RequestBody Employee employee) {
	empRepo.remove(id);
	empRepo.put(employee.getEmpId(), employee);
	return new ResponseEntity<Object>("Successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<Object> deleteEmployees(@PathVariable Integer id) {
	empRepo.remove(id);
	return new ResponseEntity<Object>("Successfully Deleted!!", HttpStatus.OK);
    }

}
