package gym.abonnement.domaine.modele;

import java.util.Objects;
import java.util.UUID;

public class Abonnement {
	
	private static final int POURCENTAGE_REDUCTION_POUR_UN_ABONNEMENT_ANNUEL = 10;

	private UUID abonnementId;

	private Prix prix;
	private TypeAbonnement typeAbonnement;

	public Abonnement(UUID abonnementId, Prix prix, TypeAbonnement typeAbonnement) {
		this.abonnementId = abonnementId;
		this.prix = prix;
		this.typeAbonnement = typeAbonnement;
	}

	public Prix prix() {
		if (estAbonnementAnnuel(typeAbonnement)) {
			prix = prix.calculPourcentageDeReduction(POURCENTAGE_REDUCTION_POUR_UN_ABONNEMENT_ANNUEL);
		}
		return prix;
	}

	private boolean estAbonnementAnnuel(TypeAbonnement mensuel) {
		return mensuel == TypeAbonnement.ANNUEL;
	}

	@Override
	public String toString() {
		return "Abonnement [prix=" + prix + ", typeAbonnement=" + typeAbonnement
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(abonnementId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Abonnement other = (Abonnement) obj;
		return Objects.equals(abonnementId, other.abonnementId);
	}
}