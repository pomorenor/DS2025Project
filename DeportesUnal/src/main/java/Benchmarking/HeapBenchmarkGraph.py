import matplotlib.pyplot as plt

print("Ingrese nombre del archivo:", end=" ")
file_name = input();
print("Ingrese el tamaño inicial de la lista:", end=" ")
elem_first = int(input())
print("Ingrese el tamaño final de la lista:", end= " ")
elem_last = int(input())
print("Ingrese la cantidad de datos que tiene: ", end=" ")
quant_data = int(input())
elem_steps = (elem_last - elem_first) // quant_data
print("Ingrese unidad de tiempo:", end=" ")
time_unit = input()
methods = [""]
heap_method_to_times = {}
elements = [i for i in range(elem_first, elem_last + 1, elem_steps)]
methods = [
        "insert",
        "delete", 
        "extractMax", 
        "getMax"
        ]

with open(file_name, 'r', encoding='utf-8') as f:
    print("Leyendo el archivo " + file_name)
    lines = f.readlines()
    for line in lines:
        words = line.split()
        if(words[0] != "Heap"):
            continue
        structure_type = words[0]
        method = words[1]
        structure_size = int(words[2])
        list_time = int(words[3])
        if(structure_type == "Heap"):
            if(method in heap_method_to_times):
                heap_method_to_times[method].append(list_time)
            else:
                heap_method_to_times[method] = [list_time]

    print("Archivo leído correctamente")

for method in methods:
    plt.clf()
    heap_times = heap_method_to_times[method]
    print(f"Creando grafica para {method}")
    plt.title("Análisis de " + method)
    plt.xlabel("Número de elementos en el montículo")
    plt.ylabel(f"Tiempo de ejecución ({time_unit})")
    plt.plot(elements, heap_times, 'r-', label="Montículo")
    plt.legend()
    plt.show()

print("Saliendo programa de manera exitosa")
