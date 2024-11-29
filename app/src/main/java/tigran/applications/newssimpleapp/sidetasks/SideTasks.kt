package tigran.applications.newssimpleapp.sidetasks

fun main() {

}

fun safeDivide(a: Int?, b: Int?): Int? {
    if (a == null || b == null) return null
    return a / b
}

fun String.countVowels(): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    return lowercase().count { it in vowels }
}