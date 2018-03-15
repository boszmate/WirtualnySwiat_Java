package wirtualnyswiat.krolestwo.roslin;

import java.awt.Point;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Roslina;

public class Guarana extends Roslina
{
    public Guarana(Point pozycja, Swiat swiat)
    {
        this.sila = 0;
	this.inicjatywa = 0;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'G';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Guarana(pozycja, swiat);
    }
    
    @Override
    public void kolizja(Organizm organizm)
    {
	if (organizm.getSila() < this.sila) swiat.usunOrganizm(organizm);
	else
        {
            organizm.setSila(organizm.getSila() + 3);
            swiat.usunOrganizm(this);
            swiat.ruchOrganizmu(organizm, this.pozycja);
	}
    }
}
