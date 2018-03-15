package wirtualnyswiat.krolestwo;

import java.awt.Point;

import wirtualnyswiat.Organizm;

public abstract class Roslina extends Organizm
{
    @Override
    public void akcja()
    {
        int prawdopodobienstwo = swiat.nextInt() % 101;
	if (prawdopodobienstwo > 97)
	{
            Point wolna_poz = swiat.losujWolnePole(this.pozycja);
            if (wolna_poz.getX() != -1 && wolna_poz.getY() != -1) swiat.naKoniecKolejki(this.rozmnozenie(wolna_poz));
	}
    }

    @Override
    public void kolizja(Organizm organizm)
    {
	if (organizm.getSila() < this.sila) swiat.usunOrganizm(organizm);
	else
        {
            swiat.usunOrganizm(this);
            swiat.ruchOrganizmu(organizm, this.pozycja);
	}
    }
}
