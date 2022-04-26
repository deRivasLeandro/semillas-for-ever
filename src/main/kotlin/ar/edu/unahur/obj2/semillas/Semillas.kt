package ar.edu.unahur.obj2.semillas

abstract class Planta(open var altura: Double, open val anioSemilla: Int) {

    open fun horasAlSol(): Double {
        return 7.0
    }

    open fun esFuerte(): Boolean {
        return this.horasAlSol() > 9
    }

    open fun daSemillas(): Boolean {
        return this.esFuerte()
    }

    abstract fun espacio(): Double

    abstract fun esIdeal(parcela: Parcela) : Boolean

}

open class Menta(override var altura: Double, override val anioSemilla: Int) : Planta(altura, anioSemilla) {

    override fun espacio(): Double {
        return this.altura + 1.0
    }

    override fun daSemillas(): Boolean {
        return super.daSemillas() xor (altura > 0.4)
    }

    override fun esIdeal(parcela: Parcela): Boolean {
        return parcela.superficie() > 6
    }
}

open class Soja(override var altura: Double, override val anioSemilla: Int) : Planta(altura, anioSemilla) {

    override fun horasAlSol(): Double {
        if(altura < 0.5) {
            return 6.0
        }
        else if(altura in 0.5..1.0) {
            return 8.0
        }
        else {
            return 12.0
        }
    }
    
    override fun espacio(): Double {
        return this.altura / 2
    }

    override fun daSemillas(): Boolean {
        return super.daSemillas() xor (anioSemilla > 2007) and (altura in 0.75..0.9)
    }

    override fun esIdeal(parcela : Parcela): Boolean {
        return parcela.horasDeSolQueRecibe == this.horasAlSol()
    }
}

class Quinoa(override var altura: Double, override val anioSemilla: Int, val espacio: Double) : Planta(altura, anioSemilla) {

    override fun horasAlSol(): Double {
        if(this.espacio < 0.3) {
            return 10.0
        }
        else {
            return 7.0
        }
    }

    override fun espacio(): Double {
        return this.espacio
    }

    override fun daSemillas(): Boolean {
        return super.daSemillas() xor (anioSemilla in 2001..2008)
    }
    override fun esIdeal(parcela: Parcela): Boolean {
        return parcela.plantas.any{p -> p.altura < 1.5}
    }
}

class SojaTransgenica(override var altura: Double, override val anioSemilla: Int) : Soja(altura, anioSemilla) {

    override fun daSemillas(): Boolean {
        return false
    }
    override fun esIdeal(parcela: Parcela): Boolean {
        return parcela.cantidadMaximaDePlantas() == 1
    }
}

class Peperina(override var altura: Double, override val anioSemilla: Int) : Menta(altura, anioSemilla) {

    override fun espacio(): Double {
        return super.espacio() * 2
    }
}