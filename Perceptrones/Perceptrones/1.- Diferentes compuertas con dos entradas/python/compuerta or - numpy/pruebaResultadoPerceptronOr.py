arch = open("pesosOr.txt", "r")
pp = arch.read()
arch.close()
pesos = pp.split(",")

x1 = int(input("entrada 1: "))
x2 = int(input("entrada 2: "))

sumaN = x1 * float(pesos[0]) + x2 * float(pesos[1]) + float(pesos[2])

if sumaN > 0:
    salida = 1
else:
    salida = 0

print(f"{ x1 }, { x2 } = { salida }")
