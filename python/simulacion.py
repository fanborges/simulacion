import time
import math, random

class Aleatorio:
    
    def uniforme(a: float, b: float):
        x = random.uniform(a, b)
        return x
    
    def exponencial(m: float):
        y = random.uniform(0,1)
        x = -1 * m * math.log(1-y)
        return x
    
    def erlang(k: int, m: float):
        x = 0
        me = m / k
        for i in range(k):
            x += exponencial(me)
        return x
    
    def normal(m: float, v: float):
        suma = 0
        for i in range(12):
            suma += random.uniform(0,1)
        z = suma - 6
        x = (math.sqrt(v) * z) + m
        return x
    
    def geometrica(p: float):
        x = 1
        f = p
        s = f
        y = random.uniform(0,1)
        print (y)
        while(y > s):
            f = f * (1 - p)
            s += f
            x += 1
        return x
    
    def poisson (lam: float):
        x = 0
        f = math.exp(-1*lam)
        s = f
        y = random.uniform(0,1)
        while(y > s):
            f = f * lam/(x+1)
            s += f
            x += 1
        return x
    
    def binomial(n: int, p: float):
        x = 0
        f = math.exp(n * math.log(1-p))
        s = f
        y = random.uniform(0,1)
        while(y > s):
            f = f*p*(n-x)/((x+1)*(1-p))
            s += f
            x += 1
        return x
    
    def Aleatoria(tipo: int, par1: float, par2: float):
        x = 0
    
        if tipo == 0:
            x = poisson(par1)
        elif tipo == 1:
            x = erlang(tipo, par1)
        elif tipo == 2:
            x = erlang(tipo, par1)
        elif tipo == 3:
            x = erlang(tipo, par1)
        elif tipo == 4:
            x = erlang(tipo, par1)
        elif tipo == 5:
            x = erlang(tipo, par1)
        elif tipo == 6:
            x = erlang(tipo, par1)
        elif tipo == 7:
            x = erlang(tipo, par1)
        elif tipo == 8:
            x = erlang(tipo, par1)
        elif tipo == 9:
            x = erlang(tipo, par1)
        elif tipo == 10:
            x = normal(par1, par2)
        elif tipo == 11:
            x = binomial(par1, par2)
        elif tipo == 12:
            x = uniforme(par1, par2)
        elif tipo == 13:
            x = geometrica(par1)
        elif tipo > 13 or tipo < 0:
            print("Opcion no valida")
        return x
    
    if __name__ == '__main__':
        print()


class simulacion:

    lista_eventos = []
    llegadas = 0
    cuenta_eventos = 0
    cuenta_rellenar = 0
    rellenar_cajero = 0
    clientes_atendidos = 0
    clientes_insatisfechos=0
    clientes_sistema = 0
    reorden = 20000
    cantidad_fija = 50000
    dinero_cajero = 50000
    reloj = 0
    tiempo_offline = 0
    tiempo_offline_total = 0
    dinero_solicitado = Aleatorio.uniforme(0,50)*100
    numero_de_abastecimientos = 0
    cantidad_abastecida_total = 0
    acumulado_servicio = 0
    mondisp = 0
    cajero_ocupado = False
    cajero_en_linea = True
    cajero_abierto = False
    secuencial = []
    usuarios = []

    def abrir_banco(self,r):
        self.reloj = r
        self.cuenta_eventos+=1
        self.cajero_abierto= True
        self.lista_eventos.append([self.reloj + Aleatorio.exponencial(120),"llegar"])
        self.evenToString(self,"abrir",self.reloj)

    def cerrar_banco(self,r):
        self.reloj = r
        self.cuenta_eventos+=1
        self.cajero_abierto = False
        self.evenToString(self,"cerrar",self.reloj)

    def cliente_llegada(self,r):
        self.reloj = r
        self.cuenta_eventos+=1
        self.llegadas+=1
        if (self.cajero_abierto):
            self.lista_eventos.append([self.reloj + Aleatorio.exponencial(120),"llegar"])
        if(self.cajero_en_linea and self.cajero_abierto):
            if(self.clientes_sistema < 8):
                self.secuencial.append([self.reloj,0,0,0,0])
                self.clientes_sistema+=1
                if(not self.cajero_ocupado):
                    self.lista_eventos.append([self.reloj,"atender"])
            else:
                self.clientes_insatisfechos+=1
        else:
            self.clientes_insatisfechos+=1
        self.evenToString(self,"LLegada de cliente",self.reloj)

    def atencion_de_cliente(self,r):
        self.reloj = r
        self.cuenta_eventos+=1
        self.cajero_ocupado = True
        self.secuencial[0][1] = self.reloj 
        if(self.secuencial[0][1]-self.secuencial[0][0] > (5*60)):
            self.clientes_insatisfechos+=1
        self.lista_eventos.append([self.reloj + Aleatorio.exponencial(60),"salida"])
        self.secuencial[0][3] = Aleatorio.uniforme(0,50)*100
        if(self.secuencial[0][3] < self.dinero_cajero):
            self.secuencial[0][4] = self.secuencial[0][3]
        elif(self.secuencial[0][3] > self.dinero_cajero):
            self.secuencial[0][4] = self.dinero_cajero
            self.clientes_insatisfechos+=1
        self.evenToString(self,"Atención de cliente",self.reloj)

    def salida_cliente(self,r):
        self.reloj = r
        self.cuenta_eventos+=1
        self.secuencial[0][2] = self.reloj
        self.acumulado_servicio += self.secuencial[0][2]-self.secuencial[0][1]
        self.dinero_cajero -= self.secuencial[0][4]
        self.mondisp+= self.secuencial[0][4]
        self.usuarios.append(self.secuencial.pop(0))
        self.clientes_sistema-=1
        self.clientes_atendidos+=1
        self.cajero_ocupado = False
        if(self.dinero_cajero <= self.reorden):
            self.lista_eventos.append([self.reloj + Aleatorio.exponencial(120), "abastecer"])
        if(self.dinero_cajero == 0):
            self.cajero_en_linea = False
            self.tiempo_offline = self.reloj
            self.clientes_insatisfechos+= len(self.secuencial)          
            self.secuencial.clear()
            self.clientes_sistema = 0

        if (self.clientes_sistema > 0):
            self.lista_eventos.append([self.reloj,"atender"])

        self.evenToString(self,"salida",self.reloj)

    def abastece_cajero(self,r):
        self.reloj = r
        self.cuenta_eventos += 1
        self.cantidad_abastecida_total+= self.cantidad_fija - self.dinero_cajero
        self.dinero_cajero = self.cantidad_fija
        self.numero_de_abastecimientos+=1
        if(not self.cajero_en_linea):
            self.cajero_en_linea = True
            self.tiempo_offline = self.reloj - self.tiempo_offline
            self.tiempo_offline_total+= self.tiempo_offline
        self.evenToString(self,"abastecimiento",self.reloj)

    def eventoListener(self):
        evento = self.lista_eventos.pop()
        if(evento[1] == "abrir_banco"):
            self.abrir_banco(self,evento[0])
        elif(evento[1] == "cerrar_banco"):
            self.cerrar_banco(self,evento[0])
        elif(evento[1] == "llegar"):
            self.cliente_llegada(self,evento[0])
        elif(evento[1] == "atender"):
            self.atencion_de_cliente(self,evento[0])
        elif(evento[1] == "salida"):
            self.salida_cliente(self,evento[0])
        elif(evento[1] == "abastecer"):
            self.abastece_cajero(self,evento[0])

    def evenToString(self,evento, tiempo):
        dsp = '''
        Hora: {}
        Evento: {}
        LLegada de clientes: {}
        Clientes Atendidos: {}
        Clientes Insatisfechos: {}
        Clientes en este Momento: {}
        Dinero en el cajero: {}
        '''.format(time.strftime("%H:%M:%S", time.gmtime(tiempo)),evento,self.llegadas,self.clientes_atendidos,self.clientes_insatisfechos,self.clientes_sistema,self.dinero_cajero)
        print(dsp)


    def totales(self):
        dispromedio = 0
        tiempespera = 0
        for i in self.usuarios:
            dispromedio+=i[3]
            tiempespera+=(i[1]-i[0])
        dispromedio/=len(self.usuarios)
        tiempespera/=len(self.usuarios)
        total = '''
        Llegadas de clientes: {}
        Clientes atendidos: {}
        Clientes Insatisfechos: {}
        Número de reabastecimientos: {}
        Cantidad reabastecida: {}
        Cantidad de disposición promedio: {}
        Tiempo de Servicio Promedio: {}
        Tiempo de Espera promedio: {}
        Tiempo fuera de servicio del cajero: {}            
        '''.format(self.llegadas,self.clientes_atendidos,self.clientes_insatisfechos,self.numero_de_abastecimientos,self.cantidad_abastecida_total,dispromedio,self.acumulado_servicio/len(self.usuarios),tiempespera,self.tiempo_offline_total)
        print(total)
        


s = simulacion
s.lista_eventos.append([32400,"abrir_banco"])
s.lista_eventos.append([64800,"cerrar_banco"])
while(s.reloj <= 64800 and len(s.lista_eventos) > 0):
    s.lista_eventos = sorted(s.lista_eventos, reverse = True)
    s.eventoListener(s)
s.totales(s)















