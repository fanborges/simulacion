import random
import sys
import math

class Probabilidad:
        
    #devuelve un arreglo con los aleatorios de capacidad n
    def aleatorioU(cantidad, inferior, superior):
        arreglo = []
        for i in range(cantidad):            
            ran = random.uniform(inferior, superior)            
            b = '%2.3f' % ran   
            arreglo.append(float(b))        
        return arreglo
    
    def aleatorioE(cantidad, alpha):
        arreglo = []
        for i in range(cantidad):
            y = random.random()
            x = (-1 * math.log(1-y))/alpha
            b = '%2.3f' % x
            arreglo.append(float(b))
        return arreglo
    
    def exprnd(cantidad, alpha):
        arreglo = []
        for i in range(cantidad):
            b = '%2.3f' % random.expovariate(alpha)
            arreglo.append(float(b))
        return arreglo
        
    def mean(arreglo):
        suma = 0
        for i in arreglo:
            suma+=float(i)
        return float('%2.3f' % (suma/len(arreglo)))
    
    def var(arreglo, promedio):      
        varianza = 0
        for i in arreglo:
            
            varianza += ((float(i) - float(promedio))**2)/(len(arreglo)-1)
        
        return float('%2.3f' % varianza)
   
   
    def imprime(lst,promedio,varianza,nombre):
      f = open(nombre +'.txt','w')
      contador = 1
      for i in lst:
          f.write('%2.3f\t' %i)
          if contador % 10 == 0:
              f.write('\n')
          contador+=1
              
      f.write('\nPromedio: %2.3f' %promedio)
      f.write('\nVarianza: %2.3f' %varianza)
      f.close()  

    a = aleatorioU(100,10,20)
    
    p = mean(a)
    
    var = var(a,p)
    imprime(a,p,var,'random01')
