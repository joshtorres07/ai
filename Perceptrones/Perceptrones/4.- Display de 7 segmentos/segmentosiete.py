
import random as rnd
import numpy as np

neuronas = 3
entradas = 8

pE = np.array([
    [1, 1, 1, 1, 1, 1, 0, 1],  
    [0, 1, 1, 0, 0, 0, 0, 1], 
    [1, 1, 0, 1, 1, 0, 1, 1],  
    [1, 1, 1, 1, 0, 0, 1, 1],  
    [0, 1, 1, 0, 0, 1, 1, 1],  
    [1, 0, 1, 1, 0, 1, 1, 1],  
    [1, 0, 1, 1, 1, 1, 1, 1],  
    [1, 1, 1, 0, 0, 0, 0, 1],  
    [1, 1, 1, 1, 1, 1, 1, 1],  
    [1, 1, 1, 1, 0, 1, 1, 1],  
    ])

sE = np.array([
    [1, 0, 0],  
    [0, 0, 0],  
    [1, 1, 0],  
    [0, 1, 0],  
    [1, 0, 0],  
    [0, 1, 0],  
    [1, 0, 1],  
    [0, 1, 1],  
    [1, 0, 1],  
    [0, 0, 1],  
    ])

pesos = np.array([
    [rnd.random() * 10 for i in range(entradas)] for j in range(neuronas)
    ])

aprendiendo = True
salida = np.zeros(neuronas)
error = np.zeros(neuronas)
epocas = 0
tasaA = 0.3

while aprendiendo:
    aprendiendo = False
    i = 0
    for i in range(len(pE)):
        sumaN = np.zeros(neuronas)
        ii = 0
        j = 0
        for ii in range(neuronas):
            for j in range(entradas):
                sumaN[ii] += pesos[ii][j] * pE[i][j]

        for s in range(neuronas):
            if sumaN[s] > 0:
                salida[s] = 1
            else:
                salida[s] = 0

        for s in range(neuronas):
            error[s] = sE[i][s] - salida[s]
            if error[s] != 0:
                aprendiendo = True
                print(f"Aprendiendo Neurona { s }.")
                for jj in range(entradas):
                    pesos[s][jj] += tasaA * error[s] * pE[i][jj]
    epocas += 1

for i in range(len(pE)):
    ii = 0
    j = 0
    sumaN = np.zeros(neuronas)
    for ii in range(neuronas):
        for j in range(entradas):
            sumaN[ii] += pesos[ii][j] * pE[i][j]
        for s in range(neuronas):
            if sumaN[s] > 0:
                salida[s] = 1
            else:
                salida[s] = 0

    for idx in range(entradas - 1):
        print(f"{ pE[i][idx] }", end=" ")

    print(" = ", end=" ")
    for idx in range(3):
        print(f"{ sE[i][idx] }", end=" ")

    print(" perceptron = ", end=" ")
    for idx in range(3):
        print(f"{ int(salida[idx]) }", end=" ")
    print("")


print("epocas", epocas)
cad = ""
for i in range(neuronas):
    for j in range(entradas):
        cad += str(pesos[i][j]) + ","

arch = open("pesosNotG.txt", "w")
arch.write(cad[:len(cad)-1])
arch.close()
