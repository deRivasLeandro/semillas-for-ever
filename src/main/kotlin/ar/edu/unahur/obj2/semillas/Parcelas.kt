package ar.edu.unahur.obj2.semillas

import java.lang.Exception

class Parcela(val ancho: Double, val largo: Double, val horasDeSolQueRecibe: Double) {
    val plantas = mutableListOf<Planta>()

    fun superficie(): Double {
        return ancho * largo
    }

    fun cantidadMaximaDePlantas(): Double {
        if(ancho > largo) return this.superficie() / 5
        else return this.superficie() / 3 + largo
    }

    fun tieneComplicaciones(): Boolean {
        return plantas.any{p -> p.horasAlSol() < horasDeSolQueRecibe}
    }

    fun hayEspacio(): Boolean {
        return plantas.size < this.cantidadMaximaDePlantas()
    }

    fun plantarPlanta(planta: Planta) {
        if (this.hayEspacio()) {
            plantas.add(planta)
        }
        else {
            throw Exception("Se alcanzó el límite de plantas en la parcela.")
        }
    }
}

