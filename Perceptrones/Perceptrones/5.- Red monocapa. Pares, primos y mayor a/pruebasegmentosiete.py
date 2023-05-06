import numpy as np

neuronas = 3
entradas = 8
salida = np.zeros(neuronas)
pesos = np.zeros((neuronas, entradas))
usr_input = np.zeros(entradas, dtype=int) 
usr_input[7] = 1  
arch = open("./pesosNotG.txt", "r")
raw = arch.read()
arch.close()
splitted_data = raw.split(",")

for i in range(neuronas):
    indexer = i * entradas
    for j in range(entradas):
        pointer = indexer + j
        pesos[i][j] = float(splitted_data[pointer])

input_count = 0
while input_count != 7:
    try:
        entrada = int(input(f"Entrada {input_count + 1}: "))
        if entrada not in (0, 1):
            raise ValueError
        usr_input[input_count] = entrada
        input_count += 1

    except ValueError:
        print("Ingrese un valor númerico entre 0 y 1")

ii = 0
sumaN = np.zeros(neuronas)
for ii in range(neuronas):
    j = 0
    for j in range(entradas):
        sumaN[ii] += pesos[ii][j] * usr_input[j]
    for s in range(neuronas):
        if sumaN[s] > 0:
            salida[s] = 1
        else:
            salida[s] = 0

for idx in range(entradas - 1):
    print(f"{ usr_input[idx] }", end=" ")

print(" = ", end=" ")
for idx in range(3):
    print(f"{ int(salida[idx]) }", end=" ")
print("")
