package com.example.paq.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "artista") 

public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 100)
	private String nombre; 
	
	@Lob
	@Size(max = 2000)
	private String biografia;
	
	@NotNull
	@NotBlank
	 @ManyToMany(mappedBy = "artistas") 
	 @Builder.Default
	    private List<Cancion> canciones = new ArrayList<>(); 
	
	@NotNull
	@NotBlank
	 @ManyToMany(mappedBy = "artistas")
	 @Builder.Default
	    private List<Album> albums = new ArrayList<>();
}
