package gym.abonnement.domaine.modele;

import java.util.UUID;

public record Formule (UUID formuleId, Prix prix) {}