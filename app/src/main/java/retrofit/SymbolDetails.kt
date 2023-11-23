package retrofit

data class SymbolDetails(
    var CEO: String,
    var chart_data: SymbolDetailsChartData,
    var description: String,
    var details: SymbolDetailsDetails,
    var logo_url: String,
    var sector: String,
    var symbol: String,
)