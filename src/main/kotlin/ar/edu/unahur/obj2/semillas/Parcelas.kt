package ar.edu.unahur.obj2.semillas


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
        return plantas.any{it.horasAlSol() < horasDeSolQueRecibe}
    }

    fun hayEspacio(): Boolean {
        return plantas.size < this.cantidadMaximaDePlantas()
    }

    fun plantarPlanta(planta: Planta) {
        if (this.hayEspacio() and (this.horasDeSolQueRecibe <= (planta.horasAlSol() + 2)))  {
            plantas.add(planta)
        }
        else {
            error("Se alcanzó el límite de plantas en la parcela o bien las plantas no toleran tanto sol.")
        }
    }
    fun seAsociaBien(planta: Planta): Boolean {
        var resultado = false
        if(this.esEcologica) {
            if(!this.tieneComplicaciones() and planta.esIdeal(this)) {
                resultado = true
            }
        }
        else if((this.plantas.size <= 2) and planta.esFuerte()) {
            resultado = true
        }
        return resultado
    }
    fun porcentajeDeBienAsociadas() = 1.0*plantas.count{seAsociaBien(it)} / maxOf(plantas.size,1)
}


