package retrofit

data class SymbolSummary(
    var change_percent: Double,
    var current_price: Double,
    var logo_url: String,
    var symbol: String = String()
)