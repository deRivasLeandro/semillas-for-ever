package ar.edu.unahur.obj2.semillas

import java.lang.Exception

class Parcela(val ancho: Double, val largo: Double, val horasDeSolQueRecibe: Double, var esEcologica: Boolean) {
    val plantas = mutableListOf<Planta>()

    fun superficie(): Double {
        return ancho * largo
    }

    fun cantidadMaximaDePlantas(): Int {
        if(ancho > largo) return (this.superficie() / 5).toInt()
        else return (this.superficie() / 3 + largo).toInt()
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
    fun porcentajeDeBienAsociadas() : Double { return (this.plantas.count{p->p.seAsociaBien(this)}/this.plantas.size*100).toDouble() }
}

object Inta {
    val parcelas = mutableSetOf<Parcela>()

    fun promedioDePlantas() : Int {
        return parcelas.sumBy{p -> p.plantas.size} / parcelas.size
    }
    fun masAutosustentable() : Double { return this.parcelas.filter{ p->p.plantas.size>4}.maxOf{ pl->pl.porcentajeDeBienAsociadas()} }

}

