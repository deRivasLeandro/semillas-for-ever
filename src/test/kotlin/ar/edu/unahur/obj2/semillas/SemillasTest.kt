package ar.edu.unahur.obj2.semillas

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import kotlin.math.ceil

class SemillasTest : DescribeSpec ({
    // hay una clase Planta que tiene por atributos
    // anioSemilla, altura
    describe("Creación de las plantas") {
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val soja = Soja(0.6, 2009)
        val sojota = Soja(1.3, 2003)
        val sojita = Soja(0.8, 2017)
        val quinoa = Quinoa(0.6, 2000, 6.2)
        val quinita = Quinoa(0.2, 2016, 0.1)
        val sojaTrans = SojaTransgenica(1.2, 2002)
        val sojitaTrans = SojaTransgenica(0.4, 2011)
        val peperina = Peperina(0.9, 2020)
        val peperita = Peperina(0.2, 2021)

        it("probamos los atributos altura  y anioSemilla") {
            menta.altura.shouldBe(1.0)
            menta.anioSemilla.shouldBe(2021)
        }

        it("verificar si da semillas") {
            menta.daSemillas().shouldBeTrue()
            mentita.daSemillas().shouldBeFalse()
            soja.daSemillas().shouldBeFalse()
            sojota.daSemillas().shouldBeFalse()
            sojita.daSemillas().shouldBeTrue()
            quinoa.daSemillas().shouldBeFalse()
            quinita.daSemillas().shouldBeTrue()
            sojaTrans.daSemillas().shouldBeFalse()
            sojitaTrans.daSemillas().shouldBeFalse()
            peperina.daSemillas().shouldBeTrue()
            peperita.daSemillas().shouldBeFalse()
        }

        it("es fuerte") {
            menta.esFuerte().shouldBeFalse()
            soja.esFuerte().shouldBeFalse()
            sojota.esFuerte().shouldBeTrue()
            quinoa.esFuerte().shouldBeFalse()
            quinita.esFuerte().shouldBeTrue()
            sojaTrans.esFuerte().shouldBeTrue()
            sojitaTrans.esFuerte().shouldBeFalse()
            peperina.esFuerte().shouldBeFalse()
        }

        it("espacio") {
            menta.espacio().shouldBe(2.0)
            mentita.espacio().shouldBe(1.3)
            soja.espacio().shouldBe(0.3)
            sojota.espacio().shouldBe(0.65)
            quinoa.espacio().shouldBe(6.2)
            sojaTrans.espacio().shouldBe(0.6)
            sojitaTrans.espacio().shouldBe(0.2)
            peperina.espacio().shouldBe(3.8)
            peperita.espacio().shouldBe(2.4)
        }

        it("verifico la suma de varias") {
            val superficie = mutableListOf(
                soja.espacio(),
                menta.espacio(),
                mentita.espacio()
            ).sum()
            ceil(superficie).shouldBe(4)
        }
    }
})