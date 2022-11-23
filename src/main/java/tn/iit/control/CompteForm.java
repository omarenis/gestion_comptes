package tn.iit.control;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompteForm {
	
	@NonNull
	private String clientCin;
	
	@NonNull
	private float solde;
	
	private Long rib;
}
