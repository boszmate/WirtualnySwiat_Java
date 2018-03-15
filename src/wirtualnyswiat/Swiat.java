package wirtualnyswiat;

import wirtualnyswiat.krolestwo.zwierzat.Wilk;
import wirtualnyswiat.krolestwo.zwierzat.Owca;
import wirtualnyswiat.krolestwo.zwierzat.Antylopa;
import wirtualnyswiat.krolestwo.zwierzat.Panda;
import wirtualnyswiat.krolestwo.zwierzat.Lew;
import wirtualnyswiat.krolestwo.roslin.Trawa;
import wirtualnyswiat.krolestwo.roslin.Guarana;
import wirtualnyswiat.krolestwo.roslin.Mlecz;


import java.util.*;
import java.awt.Point;

public class Swiat
{
    private java.util.ArrayList<Organizm> organizmy = new java.util.ArrayList<>();
    private java.util.ArrayList<Organizm> kolejka = new java.util.ArrayList<>();
    private Random losuj = new Random();
    
    public int nextInt()
    {
        return Math.abs(losuj.nextInt());
    }
    
    private java.util.ArrayList<Point> losujPole()
    {
        java.util.ArrayList<Point> zwrocPozycje = new java.util.ArrayList<>();
        while (zwrocPozycje.size() != 30)
        {
            int x = nextInt() % 20;
            int y = nextInt() % 20;
            boolean wrzuc = true;
            for (Point zwrocPozycje2 : zwrocPozycje) 
            {
                if (zwrocPozycje2.getX() == x && zwrocPozycje2.getY() == y) 
                {
                    wrzuc = false;
                    break;
                }
            }
            if (wrzuc) zwrocPozycje.add(new Point(x, y));
        }
        return zwrocPozycje;
    }
    
    
    public Swiat(int x)
    {
        losuj.setSeed(x);

        java.util.ArrayList<Point> wylosowane = losujPole();

        while (!wylosowane.isEmpty())
        {
            int los = nextInt() % 8;
            Organizm organizm = null;
            switch (los)
            {
                case 0:
                    organizm = new Wilk(wylosowane.get(0), this);
                    break;
                case 1:
                    organizm = new Owca(wylosowane.get(0), this);
                    break;
                case 2:
                    organizm = new Antylopa(wylosowane.get(0), this);
                    break;
                case 3:
                    organizm = new Lew(wylosowane.get(0), this);
                    break;
                case 4:
                    organizm = new Panda(wylosowane.get(0), this);
                    break;
                case 5:
                    organizm = new Trawa(wylosowane.get(0), this);
                    break;
                case 6:
                    organizm = new Guarana(wylosowane.get(0), this);
                    break;
                case 7:
                    organizm = new Mlecz(wylosowane.get(0), this);
                    break;
            }
            dodajOrganizm(organizm);
            wylosowane.remove(0);
        }
    }
    
    public void clear()
    {
        organizmy.clear();
    }
    
    public void dodajOrganizm(Organizm organizm)
    {
        //sprawdzenie czy duplikat
        if (getOrganizm(organizm.pozycja) != null) usunOrganizm(getOrganizm(organizm.pozycja));
        organizmy.add(organizm);
    }
    
    public void usunOrganizm(Organizm organizm)
    {
        for (int i = 0; i < organizmy.size(); i++)
        {
            if (organizmy.get(i) == organizm)
            {
                organizmy.remove(i);
                break;
            }
        }
    }
    
    public void naKoniecKolejki(Organizm organizm)
    {
        kolejka.add(organizm);
    }
    
    public void wykonajTure()
    {
        Collections.sort(organizmy);
        for (int i = 0; i < organizmy.size(); i++)
        {
            Organizm obecny = organizmy.get(i);
            organizmy.get(i).setWiek(organizmy.get(i).getWiek() + 1);
            organizmy.get(i).akcja();
            if (i == organizmy.size()) continue;
            if (obecny != organizmy.get(i)) i--;
        }
        for (int i = 0; i < organizmy.size(); i++) organizmy.get(i).setRozmnozyc(false);
        for (int i = 0; i < kolejka.size();)
        {
            dodajOrganizm(kolejka.get(0));
            kolejka.remove(0);
        }
    }

    public Organizm getOrganizm(Point poz)
    {
        for (Organizm i : organizmy)
        {
            if (i == null) continue;
            if (i.getPozycja().equals(poz)) return i;
        }
        return null;
    }
    
    public void ruchOrganizmu(Organizm organizm, Point pozycja_dolcelowa)
    {
        organizm.setPozycja(pozycja_dolcelowa);
    }
    
    public Point obokPoleAntylopa(Point poz)
    {
        java.util.ArrayList<Point> wolna_poz = new java.util.ArrayList<>();
        for (int i = -2; i < 3; i++)
        {
            for (int j = -2; j < 3; j++)
            {
                if (i == 0 && j == 0) continue;
                int x = (int)poz.getX() + i;
                int y = (int)poz.getY() + j;
                if (x >= 0 && x < 20 && y >= 0 && y < 20)
                {
                    Point wrzuc = new Point(x, y);
                    wolna_poz.add(wrzuc);
                }
            }
        }
        if (wolna_poz.isEmpty()) return new Point(-1, -1);
        else return wolna_poz.get(nextInt() % wolna_poz.size());
    }
    
    public Point obokPole(Point poz)
    {
        java.util.ArrayList<Point> wolna_poz = new java.util.ArrayList<>();
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if (i == 0 && j == 0) continue;
                int x = (int)poz.getX() + i;
                int y = (int)poz.getY() + j;
                if (x >= 0 && x < 20 && y >= 0 && y < 20)
                {
                    Point wrzuc = new Point(x, y);
                    wolna_poz.add(wrzuc);
                }
            }
        }
        if (wolna_poz.isEmpty()) return new Point(-1, -1);
        else return wolna_poz.get(nextInt() % wolna_poz.size());
    }
    
    public Point losujWolnePole(Point poz)
    {
        java.util.ArrayList<Point> wolna_poz = new java.util.ArrayList<>();
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if (i == 0 && j == 0) continue;
                int x = (int)poz.getX() + i;
                int y = (int)poz.getY() + j;
                if (x >= 0 && x < 20 && y >= 0 && y < 20)
                {
                    Point wrzuc = new Point(x, y);
                    if (getOrganizm(new Point(wrzuc)) == null && !jestWKolejce(wrzuc))
                    {
                        wolna_poz.add(wrzuc);
                    }
                }
            }
        }
        if (wolna_poz.isEmpty()) return new Point(-1, -1);
        else return wolna_poz.get(nextInt() % wolna_poz.size());
    }
    
    public boolean jestWKolejce(Point poz)
    {
        for (Organizm i : kolejka)
        {
            if (i.getPozycja().equals(poz)) return true;
        }
        return false;
    }

 
 }