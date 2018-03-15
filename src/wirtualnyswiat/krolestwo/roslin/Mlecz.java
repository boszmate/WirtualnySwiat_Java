
package wirtualnyswiat.krolestwo.roslin;

import java.awt.Point;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Roslina;

public class Mlecz extends Roslina
{
    public Mlecz(Point pozycja, Swiat swiat)
    {
	this.sila = 0;
	this.inicjatywa = 0;
        this.wiek = 0;
        this.pozycja = pozycja;
	this.znak = 'M';
        this.swiat = swiat;
    }
    
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Mlecz(pozycja, swiat);
    }
    
    @Override
    public void akcja()
    {
        for (int i = 0; i < 3; i++) 
        {
            int prawdopodobienstwo = swiat.nextInt() % 101;
            if (prawdopodobienstwo > 97)
            {
                 Point wolna_poz = swiat.losujWolnePole(this.pozycja);
                 if (wolna_poz.getX() != -1 && wolna_poz.getY() != -1) swiat.naKoniecKolejki(this.rozmnozenie(wolna_poz));
            }
        }
    }

}
