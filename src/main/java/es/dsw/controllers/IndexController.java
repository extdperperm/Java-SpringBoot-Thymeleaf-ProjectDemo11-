package es.dsw.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import es.dsw.models.Alumno;

@Controller
public class IndexController {

	@GetMapping(value= {"/","/index"})
	public String index() {
		return "index";
	}
	
	@GetMapping(value= {"/test1"})
	public String test1() {
		
		 final String GET_ALL_URL = "http://localhost:8080/getAll";

	     RestTemplate restTemplate = new RestTemplate();
	     Alumno[] alumnos = restTemplate.getForObject(GET_ALL_URL, Alumno[].class);

	     // Hacer algo con el array de alumnos obtenidos
	     for (Alumno alumno : alumnos) {
	            System.out.println(alumno.getNombre() + ", " + alumno.getApellidos() + ", " + alumno.getEdad());
	     }

		return "index";
	}
	
	@GetMapping(value= {"/test2"})
	public String test2() {
		
        final String GET_ONE_URL = "http://localhost:8080/getOne";

        RestTemplate restTemplate = new RestTemplate();
        String nif = "55665544A";  // NIF de ejemplo
        Alumno alumno = restTemplate.postForObject(GET_ONE_URL + "?nif={nif}", null, Alumno.class, nif);

        // Hacer algo con el objeto alumno obtenido
        System.out.println(alumno.getNombre() + ", " + alumno.getApellidos() + ", " + alumno.getEdad());

		return "index";
	}
	
	@GetMapping(value= {"/test3"})
	public String test3() {
		
	      final String AGREGAR_ALUMNO_URL = "http://localhost:8080/add";

	        // Crear un objeto Alumno para enviar en la solicitud
	        Alumno nuevoAlumno = new Alumno("12345678X", "Jose Miguel", "Pérez", 25, true);

	        // Configurar encabezados de la solicitud
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        // Crear una entidad HttpEntity con el objeto Alumno y los encabezados
	        HttpEntity<Alumno> requestEntity = new HttpEntity<>(nuevoAlumno, headers);

	        // Realizar la solicitud POST y obtener la respuesta
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> responseEntity = restTemplate.postForEntity(AGREGAR_ALUMNO_URL, requestEntity, String.class);

	        // Imprimir la respuesta del servidor
	        System.out.println("Respuesta del servidor: " + responseEntity.getBody());
	          
		return "index";
	}
	
}
