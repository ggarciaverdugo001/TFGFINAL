package com.example.paq.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.paq.entidades.Album;
import com.example.paq.entidades.Cancion;
import com.example.paq.entidades.Genero;
import com.example.paq.servicios.AlbumService;
import com.example.paq.servicios.ArtistaService;
import com.example.paq.servicios.CancionService;
import com.example.paq.servicios.GeneroService;



@Controller

@RequestMapping("/")
public class AlbumController {
	
	@Autowired
	AlbumService servicio;
	
	@Autowired
	GeneroService generoServicio; 
	
	@Autowired
	ArtistaService artistaServicio; 
	
	@Autowired
	CancionService cancionServicio; 
	
	@GetMapping("/")
	public String MostrarListado(Model modelo) {
			Iterable<Album> albums = servicio.obtenerTodos(); 
			modelo.addAttribute("albums", albums); 
	
		Iterable<Genero> generos = generoServicio.obtenerTodos();
		modelo.addAttribute("generos", generos);
		return ("/principal"); 
		
	}
	
	@GetMapping("/{id}")
	public String mostrarDetalle(@PathVariable Long id, Model modelo) {
		Album detalle = servicio.obtenerPorId(id);
		detalle.setGeneros(new ArrayList<>(generoServicio.buscaPorAlbum(id)));
		detalle.setArtistas(new ArrayList<>(artistaServicio.buscaPorArtista(id)));
		modelo.addAttribute("detalle", detalle); 
		
		Iterable<Cancion> canciones = cancionServicio.obtenerTitulos(id);
		modelo.addAttribute("canciones", canciones);
		return "/album";
	}
	
	@GetMapping("/filtrar")
	public String filtrarPorGenero(@RequestParam("genero") String genero, Model model) {
	    List<Album> albumsFiltrados = servicio.buscaPorGenero(genero);
	    model.addAttribute("albums", albumsFiltrados);
	    Iterable<Genero> generos = generoServicio.obtenerTodos();
		model.addAttribute("generos", generos);
	    if(genero.equals("Todos")) {
	    	MostrarListado(model);
	    }
	    return "/principal";
	    
	}

	
	
	


}
