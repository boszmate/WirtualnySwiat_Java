package wirtualnyswiat.krolestwo;

import java.awt.Point;

import wirtualnyswiat.Organizm;

public abstract class Zwierze extends Organizm
{
    @Override
    public void akcja()
    {
        Point ruch = swiat.obokPole(this.pozycja);
        if (swiat.getOrganizm(ruch) != null) swiat.getOrganizm(ruch).kolizja(this);
	else swiat.ruchOrganizmu(this, ruch);
    }
                
    @Override
    public void kolizja(Organizm organizm)
    {
        if (organizm.getZnak() == this.znak)
        {
            Point pozycja_potomka = swiat.obokPole(this.pozycja);
            if (this.pozycja.getX() != -1 && !rozmnozyc && !organizm.getRozmnozyc())
            {
                this.rozmnozyc = true;
                organizm.setRozmnozyc(true);

		swiat.naKoniecKolejki(this.rozmnozenie(pozycja_potomka));
            }
	}
	else
        {
            if (organizm.getZnak() == 'L')
            {
                if(this.getSila() > 4)
                {
                    if (this.sila > organizm.getSila()) swiat.usunOrganizm(organizm);
                    else
                    {
                        swiat.usunOrganizm(this);
                        swiat.ruchOrganizmu(organizm, this.pozycja);
                    }
                }
                else
                {
                    Point unik = swiat.obokPole(this.pozycja);
                    if (swiat.getOrganizm(unik) != null) swiat.getOrganizm(unik).kolizja(this);
                    else swiat.ruchOrganizmu(organizm, unik);
                }
            }
            else
            {
                if (this.sila > organizm.getSila()) swiat.usunOrganizm(organizm);
                    else
                    {
                        swiat.usunOrganizm(this);
                        swiat.ruchOrganizmu(organizm, this.pozycja);
                    }
            }

	}
    }
}
