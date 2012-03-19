package model;

import java.util.ArrayList;
import java.util.Map;

// Notes:
// getType geeft de naam van de klas terug
// getNaam geeft de titel van het object terug, null als dit niet relevant is (bij UMLCRUD bijv.)
// getAdditionalInfo geeft alle extra parameters die eventueel opgeslagen moeten worden terug
//    bijv.: class, create, read, update, delete bij UMLCRUD
// getRelatedObjects geeft gerelateerde DiagramObjecten terug
//    bijv.: UMLCRUD connecties bij UMLUsecase
//
// Deze toevoegingen zijn puur voor persisteren, en ook met gedachte dat er makkelijk nieuwe objecten aangemaakt kunnen worden
// Door te verplichten dat DiagramObjecten deze functies implementeren blijft de writer altijd werken
//
// Als iemand later dus een UMLSequence object zou aanmaken, gaan niet opeens alle writers kapot
// Ook hoeft de maker van een writer niks aan te passen aan de writer om nieuwe objecten goed te ondersteunen
//
// Op de manier dat ik deze methodes gebruik in mijn voorbeeld writer kan ik alle info van objecten ophalen
// Zolang een DiagramObject de methodes goed geimplementeerd heeft zal het dan ook goed gepersisteerd worden
// zonder aanpassingen op de writer

public interface DiagramObject {
	String getType();
	String getNaam();
	void setNaam(String nm);
	Map<String, String> getAdditionalInfo();
	ArrayList<DiagramObject> getRelatedObjects();
	void draw(int w, int h, int x, int y);
}
