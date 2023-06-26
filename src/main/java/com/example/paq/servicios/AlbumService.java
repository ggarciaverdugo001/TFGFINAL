package com.example.paq.servicios;

import com.example.paq.entidades.Album;

public interface AlbumService {

	
	public Iterable<Album> obtenerTodos();

	public Album obtenerPorId(Long id);

	public void borrar(Long id);

	public Album insertar(Album album);
	
	public Album modificar(Album album);
	
}
