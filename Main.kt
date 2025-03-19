import javax.swing.JOptionPane

fun cifrar(texto: String, niveles: Int): String {
    if (niveles <= 1) return texto

    val filas = Array(niveles) { StringBuilder() }
    var posicion = 0
    var direccion = 1

    for (letra in texto) {
        filas[posicion].append(letra)
        posicion += direccion
        if (posicion == 0 || posicion == niveles - 1) direccion *= -1
    }

    return filas.joinToString("")
}

fun descifrar(textoCifrado: String, niveles: Int): String {
    if (niveles <= 1) return textoCifrado

    val posiciones = mutableListOf<Int>()
    var posicion = 0
    var direccion = 1

    for (i in textoCifrado.indices) {
        posiciones.add(posicion)
        posicion += direccion
        if (posicion == 0 || posicion == niveles - 1) direccion *= -1
    }

    val filas = Array(niveles) { StringBuilder() }
    val orden = posiciones.sorted().withIndex()
    var indice = 0

    for ((i, fila) in orden) {
        filas[fila].append(textoCifrado[i])
    }

    val textoDescifrado = StringBuilder()
    for (i in posiciones.indices) {
        textoDescifrado.append(filas[posiciones[i]][0])
        filas[posiciones[i]].deleteCharAt(0)
    }

    return textoDescifrado.toString()
}

fun main() {
    val opciones = arrayOf("Cifrar", "Descifrar", "Salir")

    while (true) {
        val opcion = JOptionPane.showOptionDialog(
            null, "Seleccione una opción:", "Cifrado Rail Fence",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, opciones, opciones[0]
        )

        if (opcion == 2 || opcion == JOptionPane.CLOSED_OPTION) break

        val texto = JOptionPane.showInputDialog("Ingrese el texto:") ?: continue
        if (texto.isEmpty()) continue

        val niveles = JOptionPane.showInputDialog("Ingrese el número de niveles:")?.toIntOrNull() ?: continue

        val resultado = if (opcion == 0) cifrar(texto, niveles) else descifrar(texto, niveles)

        JOptionPane.showMessageDialog(null, "Resultado: $resultado")
    }
}
