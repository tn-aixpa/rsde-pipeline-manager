package it.smartcommunitylabdhlab.rsde.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/template2")
public class ConsumeController2 {

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

	return restTemplate.getForEntity(URL, Object.class);
    }

    @GetMapping("/employees/{id}")
    public Object getEmployeesById(@PathVariable Integer id) {
	return restTemplate.getForEntity(URL + "/" + id, Object.class).getBody();
    }

    @PostMapping("/employees")
    public String createEmployees(@RequestBody Employee employee) {
	return restTemplate.postForEntity(URL, employee, String.class).getBody();
    }

    @PutMapping("/employees/{id}")
    public void updateEmployees(@PathVariable Integer id, @RequestBody Employee employee) {
	restTemplate.put(URL + "/" + id, employee);
    }

    @DeleteMapping("employees/{id}")
    public void deleteEmployees(@PathVariable Integer id) {
	restTemplate.delete(URL + "/" + id);
    }

}

