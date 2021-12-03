package gym.abonnement.domaine.modele;

public record Prix(int valeur) {
	
	public Prix calculPourcentageDeReduction(int pourcentage) {
		int valeurPrix = this.valeur();
		valeurPrix = valeurPrix - (valeurPrix * pourcentage) / 100;
		return new Prix(valeurPrix);
	}
}
