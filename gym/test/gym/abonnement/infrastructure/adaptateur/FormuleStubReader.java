package gym.abonnement.infrastructure.adaptateur;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import gym.abonnement.domaine.gateway.FormuleReader;
import gym.abonnement.domaine.modele.Formule;

public class FormuleStubReader implements FormuleReader {

	private Map<UUID, Formule> associationUUIDFormule = new HashMap<>();
	
	public FormuleStubReader(Formule formule) {
		associationUUIDFormule.put(formule.formuleId(), formule);
	}

	@Override
	public Formule recupererPar(UUID formuleId) {
		return associationUUIDFormule.get(formuleId);
	}

}
