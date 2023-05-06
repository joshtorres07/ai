import numpy as np
from random import random

p = np.array([[0, 0, 1], [0, 1, 1], [1, 0, 1], [1, 1, 1]])
sE = np.array([0, 1, 1, 1])
W = np.array([random() * 10, random() * 10, random() * 10])

aprendiendo = True
tasaA = 0.3 
epocas = 0
salida = 0
while aprendiendo:
    aprendiendo = False
    for i in range(len(p)):
        sumaN = 0
        for j in range(len(p[i])):
            sumaN += p[i][j] * W[j]

        if sumaN > 0:
            salida = 1
        else:
            salida = 0

        error = sE[i] - salida

        if error != 0:
            for j in range(len(W)):
                W[j] += tasaA * error * p[i][j]
            aprendiendo = True

    print("aprendiendo")
    epocas += 1

for i in range(len(p)):
    sumaN = 0
    for j in range(len(p[i])):
        sumaN += p[i][j] * W[j]

    if sumaN > 0:
        salida = 1
    else:
        salida = 0

    print(f"{ p[i][0] }, { p[i][1] } = { sE[i] }, perceptron = { salida }")

print("Epocas =", epocas)
cad = ""
for i in range(len(W)):
    cad += str(W[i]) + ","
cad = cad[:len(cad) - 1]
arch = open("pesosOr.txt", "w")
arch.write(cad)
arch.close()
