package ar.edu.unahur.obj2.semillas

import io.kotest.assertions.throwables.shouldThrowMessage
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class ParcelasTest: DescribeSpec ({
    describe("creación de las parcelas") {
        val parcelaMendoza = Parcela(6.5, 10.0, 7.0, false)
        val parcelaChubut = Parcela(4.0, 9.0, 10.0, true)
        val parcelaMisiones = Parcela(5.0, 3.0, 8.0, false)
        val parcelaPampa = Parcela(1.0, 3.0, 6.5, true)
        val parcelaSalta = Parcela(5.0, 1.0, 5.0, true)
        val parcelaCordoba = Parcela(11.0, 2.5, 6.0, false)
        val parcelaJujuy = Parcela(20.0, 10.0, 4.0, true)
        val parcelaChaco = Parcela(3.0, 15.0, 3.0, true)
        // generamos plantas para plantar en las parcelas
        val menta = Menta(1.0, 2021)
        val mentita = Menta(0.3, 2021)
        val mentota = Menta(2.1, 2000)
        val soja = Soja(0.6, 2009)
        val sojota = Soja(1.3, 2003)
        val sojita = Soja(0.8, 2017)
        val quinoa = Quinoa(0.6, 2000, 6.2)
        val quinita = Quinoa(0.2, 2016, 0.5)
        val quimini = Quinoa(0.3, 2022, 0.1)
        val quinota = Quinoa(1.2, 2017, 8.3)
        val sojaTrans = SojaTransgenica(1.2, 2002)
        val sojitaTrans = SojaTransgenica(0.4, 2011)
        val peperina = Peperina(0.9, 2020)
        val peperita = Peperina(0.2, 2021)
        //plantamos las plantas en las parcelas
        parcelaMendoza.plantarPlanta(menta)
        parcelaMendoza.plantarPlanta(soja)
        parcelaMendoza.plantarPlanta(quinoa)
        parcelaMendoza.plantarPlanta(peperina)
        parcelaChubut.plantarPlanta(sojota)
        parcelaMisiones.plantarPlanta(sojita)
        parcelaMisiones.plantarPlanta(quinita)
        parcelaMisiones.plantarPlanta(sojaTrans)
        parcelaPampa.plantarPlanta(mentota)
        parcelaCordoba.plantarPlanta(quinota)
        parcelaCordoba.plantarPlanta(quimini)
        parcelaJujuy.plantarPlanta(menta)
        parcelaJujuy.plantarPlanta(mentita)
        parcelaJujuy.plantarPlanta(mentota)
        parcelaJujuy.plantarPlanta(sojaTrans)
        parcelaJujuy.plantarPlanta(sojitaTrans)
        parcelaChaco.plantarPlanta(quinoa)
        parcelaChaco.plantarPlanta(soja)
        parcelaChaco.plantarPlanta(sojita)
        parcelaChaco.plantarPlanta(sojota)
        parcelaChaco.plantarPlanta(peperina)

        it("probamos el método superficie") {
            parcelaMendoza.superficie().shouldBe(65.0)
            parcelaChubut.superficie().shouldBe(36.0)
            parcelaMisiones.superficie().shouldBe(15.0)
        }

        it("cantidad máxima de plantas") {
            parcelaMendoza.cantidadMaximaDePlantas().shouldBe(31)
            parcelaChubut.cantidadMaximaDePlantas().shouldBe(21)
            parcelaMisiones.cantidadMaximaDePlantas().shouldBe(3)
            parcelaPampa.cantidadMaximaDePlantas().shouldBe(4)
            parcelaSalta.cantidadMaximaDePlantas().shouldBe(1)
        }

        it("plantamos plantas y corroboramos cuantas hay plantadas") {
            parcelaMendoza.plantas.size.shouldBe(4)
            shouldThrowMessage("Se alcanzó el límite de plantas en la parcela o bien las plantas no toleran tanto sol.") {
                parcelaChubut.plantarPlanta(peperita)
                parcelaChubut.plantarPlanta(mentita)
            }
            parcelaChubut.plantas.size.shouldBe(1)
            shouldThrowMessage("Se alcanzó el límite de plantas en la parcela o bien las plantas no toleran tanto sol.") {
                parcelaMisiones.plantarPlanta(sojitaTrans)
            }
            parcelaMisiones.plantas.size.shouldBe(3)
        }

        it("las parcelas tienen complicaciones") {
            parcelaMendoza.tieneComplicaciones().shouldBeFalse()
            parcelaChubut.tieneComplicaciones().shouldBeFalse()
            parcelaMisiones.tieneComplicaciones().shouldBeTrue()
            parcelaPampa.tieneComplicaciones().shouldBeFalse()
            parcelaSalta.tieneComplicaciones().shouldBeFalse()
        }

        it("parcelas ideales") {
            menta.esIdeal(parcelaMisiones).shouldBeTrue()
            menta.esIdeal(parcelaPampa).shouldBeFalse()
            peperina.esIdeal(parcelaChubut).shouldBeTrue()
            peperina.esIdeal(parcelaPampa).shouldBeFalse()
            quinoa.esIdeal(parcelaMisiones).shouldBeTrue()
            quinoa.esIdeal(parcelaPampa).shouldBeFalse()
            soja.esIdeal(parcelaMisiones).shouldBeTrue()
            soja.esIdeal(parcelaMendoza).shouldBeFalse()
            sojaTrans.esIdeal(parcelaMendoza).shouldBeFalse()
            sojaTrans.esIdeal(parcelaSalta).shouldBeTrue()
        }

        it("parcelas ecológicas e industriales: se asocia bien") {
            // parcelas ecológicas
            parcelaChubut.seAsociaBien(peperina).shouldBeTrue()
            parcelaPampa.seAsociaBien(peperina).shouldBeFalse()
            parcelaSalta.seAsociaBien(sojaTrans).shouldBeTrue()
            parcelaChubut.seAsociaBien(sojaTrans).shouldBeFalse()
            // parcelas industriales
            parcelaCordoba.seAsociaBien(quimini).shouldBeTrue()
            parcelaMendoza.seAsociaBien(quimini).shouldBeFalse()
            parcelaCordoba.seAsociaBien(sojota).shouldBeTrue()
            parcelaMisiones.seAsociaBien(sojota).shouldBeFalse()
        }

        it("test de inta - 3 parcelas con 6 plantas en total - Ninguna parcela con más de 4 plantas") {
            Inta.parcelas.add(parcelaCordoba)
            Inta.parcelas.add(parcelaChubut)
            Inta.parcelas.add(parcelaMisiones)
            Inta.promedioDePlantas().shouldBe(2)
        }

        it("test de inta - 4 parcelas con 14 plantas en total - dos parcelas con más de 4 plantas (Jujuy con 3 bien asociadas y Chaco con 2") {
            Inta.parcelas.clear()
            Inta.parcelas.add(parcelaMisiones)
            Inta.parcelas.add(parcelaPampa)
            Inta.parcelas.add(parcelaChaco)
            Inta.parcelas.add(parcelaJujuy)
            Inta.promedioDePlantas().shouldBe(3)
            Inta.masAutosustentable().shouldBe(parcelaJujuy)
        }

        it("test de inta - 3 parcelas con 7 plantas en total - una parcela con más de 4 plantas") {
            Inta.parcelas.clear()
            Inta.parcelas.add(parcelaJujuy)
            Inta.parcelas.add(parcelaCordoba)
            Inta.parcelas.add(parcelaSalta)
            Inta.promedioDePlantas().shouldBe(2)
            Inta.masAutosustentable().shouldBe(parcelaJujuy)
        }
    }})

