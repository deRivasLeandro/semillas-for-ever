package ar.edu.unahur.obj2.semillas

object Inta {
    val parcelas = mutableSetOf<Parcela>()

    fun promedioDePlantas(): Int {
        val plantas = parcelas.sumBy { it.plantas.size }
        val cantidadParcelas = parcelas.size
        if (cantidadParcelas == 0) return 0
        return plantas / cantidadParcelas
    }

    fun masAutosustentable(): Parcela {
        val parcelasConMasDe4Plantas = parcelas.filter { it.plantas.size > 4 }
        val laParcela = parcelas.maxByOrNull { it.porcentajeDeBienAsociadas() }
        if (laParcela != null)
            return laParcela
        throw java.lang.RuntimeException("No hay parcelas que satisfagan el criterio.")
    }
}

