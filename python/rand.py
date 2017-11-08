import random
import sys
import os
suma = 0
contador = 1
equisBarra = 0
f = open('random.txt','w')
for i in range(100):
    r = random.uniform(10,20)
    num = round(r,3)
    suma += num
    f.write('%2.3f \t' %num)
    
    if contador % 10 == 0:
        f.write('\n')
    print(str(num)+'\t', end = '')
    contador+=1
equisBarra = suma/100
f.write('''
Promedio:
%2.3f


''' % (suma/100))
print('\n\nPromedio\n %2.3f' %(equisBarra))



f.close()

