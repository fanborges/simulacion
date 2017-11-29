/*
 * Distribucion.java
 * 
 * Copyright 2017 clemente <clemente@clemente-K53E>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.util.Random;
import java.lang.Math;
public class Distribucion {
    
    public static double uniforme(){
        
       return Math.random();
        
    }
    
	public static double exponencial(double m){
        double x, y;
        y = uniforme();
        x = -1*m*Math.log(1-y);
        return x;       
    }
    
    public static double erlang(int k, double m){
        
        int i;
        double x, me;
        x = 0;
        me = m/k;
        for(i=1;i<=k;i++){
            x+=exponencial(me);
            
        }
        return x;
    }
    
    public static double normal(double m, double v){
        int i;
        double x, z, sum;
        sum = 0;
        for (i=1;i<=12;i++){
            sum+= uniforme();
        }
        z = sum-6;
        x = Math.sqrt(v)*z+m;
        return x;
    }
    
    public static int geometrica(double p){
        int x;
        double f, s, y;
        x = 1;
        f = p;
        s = f;
        y = uniforme();
        while(y>s){
            f*=(1-p);
            s+=f;
            x+=1;
        }
        
        return x;
    }
    
    public static int poisson(double lam){
        int x;
        double f, s, y;
        x = 0;
        f = Math.exp(-1*lam);
        s = f;
        y = uniforme();
        while(y>s){
            f*=lam/(x+1);
            s+=f;
            x+=1;
        }
        return x;
    }
    
    public static double binomial(double n, double p){
        double x;
        double f, s, y;
        x = 0;
        f = Math.exp(n*Math.log(1-p));
        s = f;
        y = uniforme();
        while(y>s){
            f*=p*(n-x)/((x+1)*(1+p));
            s+=f;
            x+=1;
        }
        return x;
    }
    
    public static double aleatoria(int tipo, double par1, double par2){
        double x;
        x = 0;
        switch(tipo){
            case 0: x = poisson(par1); break;
            case 1: x = erlang(tipo,par1); break;
            case 2: x = erlang(tipo,par1); break;
            case 3: x = erlang(tipo,par1); break;
            case 4: x = erlang(tipo,par1); break;
            case 5: x = erlang(tipo,par1); break;
            case 6: x = erlang(tipo,par1); break;
            case 7: x = erlang(tipo,par1); break;
            case 8: x = erlang(tipo,par1); break;
            case 9: x = erlang(tipo,par1); break;
            case 10: x = normal(par1,par2); break;
            case 11: x = binomial(par1,par2); break;
            case 12: x = uniforme(); break;
            case 13: x = geometrica(par1); break;
            
        }
        return x;
    }

	public static void main (String args[]) {
		int i;
        double x;
        for (i = 1; 1<=10; i++){
            x = aleatoria(11,10,0.1);
            System.out.println(x);
            
        }
            
    }
        
}

