package gym.abonnement.domaine.usecase;

import gym.abonnement.domaine.gateway.FormuleReader;
import gym.abonnement.domaine.gateway.SouscriptionRepository;
import gym.abonnement.domaine.modele.Abonnement;
import gym.abonnement.domaine.modele.Formule;

public class SouscriptionUnAbonnementUseCase {
	
	private SouscriptionRepository souscriptionRepository;
	private FormuleReader formuleReader;

	public SouscriptionUnAbonnementUseCase(SouscriptionRepository souscriptionRepository
			, FormuleReader formuleReader) {
		this.souscriptionRepository = souscriptionRepository;
		this.formuleReader = formuleReader;
	}

	public void execute(SouscrireUnAbonnementCommande souscrireUnAbonnementCommande) {
		Formule formule = formuleReader.recupererPar(souscrireUnAbonnementCommande.formuleId());
		Abonnement abonnement
			= new Abonnement(souscrireUnAbonnementCommande.abonnementId()
					, formule.prix()
					, souscrireUnAbonnementCommande.typeAbonnement());
		souscriptionRepository.add(abonnement);
		// exemple évènement : eventDispatcher.dispatch(abonnementEvent);
	}

}