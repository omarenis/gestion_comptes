package tn.iit.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor// constructeur vide
@AllArgsConstructor // constructeur avec tous les attributs
@RequiredArgsConstructor // constructeur avec les champs required = nnon null
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_client")
public class Client implements Serializable /* Spec JEE */ {
	private static final long serialVersionUID = 1L;

	@Id // PK
	@Include
	@Column(length = 10)
	@NonNull //champs required
	private String cin;
	@NonNull
	private String prenom;
	@NonNull
	private String nom;
	@NonNull
	private String addresse;
	// bidirectionnelle
	@JsonIgnore // cassé la boucle json
	@ToString.Exclude // cassé la boucle ToString
	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE) // mappedBy obligatoire, pour ne pas générer une
																	// colonne de jointure dans client
	private List<Compte> comptes;
}
