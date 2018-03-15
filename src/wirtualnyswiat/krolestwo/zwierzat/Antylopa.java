package wirtualnyswiat.krolestwo.zwierzat;

import wirtualnyswiat.Swiat;
import wirtualnyswiat.Organizm;
import wirtualnyswiat.krolestwo.Zwierze;

import java.awt.Point;

public class Antylopa extends Zwierze
{
    public Antylopa(Point pozycja, Swiat swiat)
    {
        this.sila = 4;
        this.inicjatywa = 4;
        this.wiek = 0;
        this.pozycja = pozycja;
        this.znak = 'A';
	this.swiat = swiat;
    }
    
        
    @Override
    public Organizm rozmnozenie(Point pozycja)
    {
        return new Antylopa(pozycja, swiat);
    }
    
    
    @Override
    public void akcja()
    {
        Point ruch = swiat.obokPoleAntylopa(this.pozycja);
        if (swiat.getOrganizm(ruch) != null) swiat.getOrganizm(ruch).kolizja(this);
	else swiat.ruchOrganizmu(this, ruch);
    }

    @Override
    public void kolizja(Organizm organizm)
    {
       if (organizm.getZnak() == this.znak)
        {
            Point pozycja_potomka = swiat.obokPole(this.pozycja);
            if (this.pozycja.getX() != -1 && !this.rozmnozyc && !organizm.getRozmnozyc())
            {
                this.rozmnozyc = true;
                organizm.setRozmnozyc(true);
		swiat.naKoniecKolejki(this.rozmnozenie(pozycja_potomka));
            }
	}
        else if (swiat.nextInt() % 101 > 50)
        {
            Point p = swiat.losujWolnePole(this.pozycja);
            if (p.getX() != -1)
            {
                swiat.ruchOrganizmu(this, p);
                swiat.ruchOrganizmu(organizm, this.pozycja);
            }
            else {
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
                
                
//                if (sila > organizm.getSila())
//		swiat.usunOrganizm(organizm);
//                else
//                {
//                    swiat.usunOrganizm(this);
//                    swiat.moveOrganizm(organizm, this.pozycja);
//                }
            }
        }

    }
}
                
    
