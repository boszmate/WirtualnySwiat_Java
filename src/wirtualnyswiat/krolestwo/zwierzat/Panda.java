package wirtualnyswiat.krolestwo.zwierzat;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Zwierze;

import java.awt.Point;

public class Panda extends Zwierze
{
    public Panda(Point pozycja, Swiat swiat)
    {
	this.sila = 7;
	this.inicjatywa = 3;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'P';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Panda(pozycja, swiat);
    }
    
    @Override
    public void akcja()
    {
        if (swiat.nextInt() % 101 > 75) 
        {
            Point ruch = swiat.obokPole(this.pozycja);
            if (swiat.getOrganizm(ruch) != null) swiat.getOrganizm(ruch).kolizja(this);
            else swiat.ruchOrganizmu(this, ruch);
        }
    }
 
}
