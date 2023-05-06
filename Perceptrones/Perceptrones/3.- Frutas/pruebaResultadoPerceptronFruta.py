arch = open("pesosFrutas.txt", 'r')
pp = arch.read()
arch.close()
pesos = pp.split(",")


x1 = float(input("Entrada 1: "))
x2 = float(input("Entrada 2: "))

sumaN = x1 * float(pesos[0]) + x2 * float(pesos[1]) + float(pesos[2])

if sumaN > 0:
    salida = 1
else:
    salida = 0

frutaP = 'Piña' if salida == 0 else 'Manzana'

print(f"{ x1 }, { x2 } = { salida } ({ frutaP })")
