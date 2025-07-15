package io.dustin.finstage.financial.domain

data class Symbol(
        val symbol: String,
        val name: String,
        val country: String
) {
    companion object {
        fun of(symbol: String, name: String, country: String): Symbol {
            return Symbol(symbol, name, country)
        }
    }
}
