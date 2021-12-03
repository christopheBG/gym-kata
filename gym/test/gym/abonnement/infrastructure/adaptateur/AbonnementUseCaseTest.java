package gym.abonnement.infrastructure.adaptateur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import gym.abonnement.domaine.gateway.FormuleReader;
import gym.abonnement.domaine.modele.Abonnement;
import gym.abonnement.domaine.modele.Formule;
import gym.abonnement.domaine.modele.Prix;
import gym.abonnement.domaine.modele.TypeAbonnement;
import gym.abonnement.domaine.usecase.SouscriptionUnAbonnementUseCase;
import gym.abonnement.domaine.usecase.SouscrireUnAbonnementCommande;

public class AbonnementUseCaseTest {
	
	@Test
	void doitSouscrireUnAbonnementDe10eurosParMoisPourUneFormuleMensuelDe10euros() {
		// Given
		UUID abonnementId = UUID.randomUUID();
		Abonnement abonnementAttendu = genererAbonnementMensuel(abonnementId, 10);
		Formule formule = genererFormuleAuPrixDe(10);
		SouscriptionSpyRepository souscriptionRepository = new SouscriptionSpyRepository();
		FormuleReader formuleReader = new FormuleStubReader(formule);
		SouscriptionUnAbonnementUseCase souscriptionUnAbonnementUseCase = new SouscriptionUnAbonnementUseCase(
				souscriptionRepository, formuleReader);
		SouscrireUnAbonnementCommande souscrireUnAbonnementCommande
			= new SouscrireUnAbonnementCommande(abonnementId
					, formule.formuleId()
					, TypeAbonnement.MENSUEL);

		// When
		souscriptionUnAbonnementUseCase.execute(souscrireUnAbonnementCommande);

		// Then
		verificationAbonnement(abonnementAttendu, souscriptionRepository);
	}

	@Test
	void doitSouscrireUnAbonnementDe9eurosParMoisPourUneFormuleAnnuelDe10eurosCarReductionDe10Pourcent() {
		// Given
		UUID abonnementId = UUID.randomUUID();
		Abonnement abonnementAttendu = genererAbonnementAnnuel(abonnementId, 9);
		Formule formule = genererFormuleAuPrixDe(10);
		SouscriptionSpyRepository souscriptionRepository = new SouscriptionSpyRepository();
		FormuleReader formuleReader = new FormuleStubReader(formule);
		SouscriptionUnAbonnementUseCase souscriptionUnAbonnementUseCase = new SouscriptionUnAbonnementUseCase(
				souscriptionRepository, formuleReader);
		SouscrireUnAbonnementCommande souscrireUnAbonnementCommande
			= new SouscrireUnAbonnementCommande(abonnementId, formule.formuleId()
					, TypeAbonnement.ANNUEL);

		// When
		souscriptionUnAbonnementUseCase.execute(souscrireUnAbonnementCommande);

		// Then
		verificationAbonnement(abonnementAttendu, souscriptionRepository);
	}
	
	//void doitListerToutesLesFormulesDisponibles() {
		//assertEquals(formulesAttendues, QueryHandler(listerToutesLesFormulesQuery));
	//}

	private Formule genererFormuleAuPrixDe(int prix) {
		return new Formule(UUID.randomUUID(), new Prix(prix));
	}
	
	private Abonnement genererAbonnementMensuel(UUID abonnementId, int prix) {
		return new Abonnement(abonnementId
				, new Prix(prix), TypeAbonnement.MENSUEL);
	}
	
	private Abonnement genererAbonnementAnnuel(UUID abonnementId, int prix) {
		return new Abonnement(abonnementId
				, new Prix(prix), TypeAbonnement.ANNUEL);
	}
	
	private void verificationAbonnement(Abonnement abonnementAttendu,
			SouscriptionSpyRepository souscriptionRepository) {
		Abonnement abonnementRecu = souscriptionRepository.getAbonnement();
		assertEquals(abonnementAttendu, abonnementRecu);
		assertEquals(abonnementAttendu.prix(), abonnementRecu.prix());
	}
}