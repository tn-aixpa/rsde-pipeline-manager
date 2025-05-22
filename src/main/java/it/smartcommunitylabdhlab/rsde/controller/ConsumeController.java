package it.smartcommunitylabdhlab.rsde.controller;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/template")
public class ConsumeController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;
    
    private String URL;

    
    @PostConstruct
    private void init() {
	URL = "http://localhost:" + port + "/employees";
    }

    @GetMapping("/employees")
    public Object getEmployees() {

	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
	return restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class).getBody();
    }

    @GetMapping("/employees/{id}")
    public Object getEmployeesById(@PathVariable Integer id) {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
	return restTemplate.exchange(URL + "/" + id, HttpMethod.GET, entity, Object.class).getBody();
    }

    @PostMapping("/employees")
    public String createEmployees(@RequestBody Employee employee) {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
	return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping("/employees/{id}")
    public String updateEmployees(@PathVariable Integer id, @RequestBody Employee employee) {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
	return restTemplate.exchange(URL + "/" + id, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping("employees/{id}")
    public Object deleteEmployees(@PathVariable Integer id) {
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
	return restTemplate.exchange(URL + "/" + id, HttpMethod.PUT, entity, Object.class).getBody();
    }

}

class Employee {

    private Integer empId;

    private String empName;

    private String empDept;

    private Double empSalary;

    // Constructors
    //////////////////
    public Employee() {
        super();
    }

    /**
     * @param empId
     * @param empName
     * @param empDept
     * @param empSalary
     */
    public Employee(Integer empId, String empName, String empDept, Double empSalary) {
        super();
        this.empId = empId;
        this.empName = empName;
        this.empDept = empDept;
        this.empSalary = empSalary;
    }

    // Properties
    ////////////////
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }
}
