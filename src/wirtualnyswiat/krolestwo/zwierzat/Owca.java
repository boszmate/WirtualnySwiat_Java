package wirtualnyswiat.krolestwo.zwierzat;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Zwierze;


import java.awt.Point;

public class Owca extends Zwierze
{
    public Owca(Point pozycja, Swiat swiat)
    {
	this.sila = 4;
	this.inicjatywa = 4;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'O';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Owca(pozycja, swiat);
    }
}
