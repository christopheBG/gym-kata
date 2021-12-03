package gym.abonnement.infrastructure.adaptateur;

import gym.abonnement.domaine.gateway.SouscriptionRepository;
import gym.abonnement.domaine.modele.Abonnement;

public class SouscriptionSpyRepository implements SouscriptionRepository {

	private Abonnement abonnement;
	
	public Abonnement getAbonnement() {
		return abonnement;
	}

	@Override
	public void add(Abonnement abonnement) {
		this.abonnement = abonnement;
	}

}
