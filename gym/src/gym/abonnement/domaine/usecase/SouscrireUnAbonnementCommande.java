package gym.abonnement.domaine.usecase;

import java.util.UUID;

import gym.abonnement.domaine.modele.TypeAbonnement;

public record SouscrireUnAbonnementCommande(UUID abonnementId, UUID formuleId
		, TypeAbonnement typeAbonnement) {}