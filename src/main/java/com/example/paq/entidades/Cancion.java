package com.example.paq.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "cancion") 

public class Cancion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 100)
	private String nombre; 
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "albumid")
	private Album album;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 10000)
	private String audio; 

	@NotNull
	@NotBlank
	@ManyToOne(fetch = FetchType.LAZY)
	private Genero genero; 
	
	  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	    @JoinTable(name = "cancion_artista",
	            joinColumns = @JoinColumn(name = "cancion_id"),
	            inverseJoinColumns = @JoinColumn(name = "artista_id"))
	  @Builder.Default
	  @NotNull
		@NotBlank
	    private List<Artista> artistas = new ArrayList<>();
}
