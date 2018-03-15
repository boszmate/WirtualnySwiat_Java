package wirtualnyswiat.krolestwo.zwierzat;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Zwierze;

import java.awt.Point;

public class Lew extends Zwierze
{
    public Lew(Point pozycja, Swiat swiat)
    {
	this.sila = 11;
	this.inicjatywa = 7;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'L';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Lew(pozycja, this.swiat);
    }
    
}
