package wirtualnyswiat;

import java.awt.Point;

public abstract class Organizm implements Comparable<Organizm>
{
    public abstract void akcja();
    public abstract void kolizja(Organizm organizm);
    public abstract Organizm rozmnozenie(Point pozycja);
    
    public int getSila()
    {
	return sila;
    }
    public int getInicjatywa()
    {
	return inicjatywa;
    }
    public int getWiek()
    {
	return wiek;
    }
    public Point getPozycja()
    {
	return pozycja;
    }
    public Swiat getSwiat()
    {
    	return swiat;
    }
    public char getZnak()
    {
    	return znak;
    }
    public boolean getRozmnozyc()
    {
    	return rozmnozyc;
    }
    
    
    
    
    public void setSila(int x)
    {
    	sila = x;
    }
    public void setInicjatywa(int x)
    {
    	inicjatywa = x;
    }
    public void setWiek(int x)
    {
    	wiek = x;
    }
    public void setPozycja(Point x)
    {
        pozycja = x;
    }
    public void setSwiat(Swiat x)
    {
	swiat = x;
    }
    public void setZnak(char x)
    {
        znak = x;
    }
    public void setRozmnozyc(boolean x)
    {
        rozmnozyc = x;
    }
    
    
    
    public void rysowanie()
    {
	System.out.print(znak);
    }
    
    @Override
    public int compareTo(Organizm organizm) 
    {
        if (inicjatywa != organizm.getInicjatywa())
        {
            if (inicjatywa > organizm.getInicjatywa()) return -1;
            else return 1;
        }
        else
        {
            if (wiek > organizm.getWiek()) return -1;
            else return 1;
        }
    }
    
    @Override
    public String toString()
    {
        return znak + " " + sila + " " + inicjatywa + " " + wiek + " " + (int)pozycja.getX() + " " + (int)pozycja.getY();
    }
    
    
    
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected Point pozycja = new Point();
    protected Swiat swiat;
    protected char znak;
    protected boolean rozmnozyc;
}
