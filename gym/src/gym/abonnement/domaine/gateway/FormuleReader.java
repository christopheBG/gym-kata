package gym.abonnement.domaine.gateway;

import java.util.UUID;

import gym.abonnement.domaine.modele.Formule;

public interface FormuleReader {

	Formule recupererPar(UUID formuleId);

}
