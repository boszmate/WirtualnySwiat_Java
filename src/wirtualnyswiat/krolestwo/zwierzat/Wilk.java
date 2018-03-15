package wirtualnyswiat.krolestwo.zwierzat;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Zwierze;

import java.awt.Point;

public class Wilk extends Zwierze
{
    public Wilk(Point pozycja, Swiat swiat)
    {
	this.sila = 9;
	this.inicjatywa = 5;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'W';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Wilk(pozycja, swiat);
    }
    
}