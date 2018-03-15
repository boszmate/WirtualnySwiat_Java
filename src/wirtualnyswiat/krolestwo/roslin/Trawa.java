package wirtualnyswiat.krolestwo.roslin;

import java.awt.Point;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Roslina;

public class Trawa extends Roslina
{
    public Trawa(Point pozycja, Swiat swiat)
    {
	this.sila = 0;
	this.inicjatywa = 0;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'T';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Trawa(pozycja, swiat);
    }

}
