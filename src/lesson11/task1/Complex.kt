@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.lang.NullPointerException
import java.lang.StringBuilder
import kotlin.math.pow

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(
        try {
            Regex("""(-\d+\.*\d*)|(\d+\.*\d*)""").find(s)!!.value.toDouble()
        } catch (e: NullPointerException) {
            throw NullPointerException("Incorrect Complex Number Format")
        },
        Regex("""(-\d+\.*\d*)|(\d+\.*\d*)""").findAll(s).last().value.toDouble()
    )

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(other.re + this.re, other.im + this.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(re * other.re - im * other.im, re * other.im + other.re * im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex =
        Complex(
            (re * other.re + im * other.im) / (other.re.pow(2) + other.im.pow(2)),
            (other.re * im - re * other.im) / (other.re.pow(2) + other.im.pow(2)),
        )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = (other is Complex) && (other.re == re) && (other.im == im)

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        val ans = StringBuilder()
        ans.append("$re")
        if (im < 0) ans.append("$im") else
            ans.append("+$im")
        ans.append("i")
        return ans.toString()
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }
}