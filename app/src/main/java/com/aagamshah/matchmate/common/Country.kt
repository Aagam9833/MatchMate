package com.aagamshah.matchmate.common

enum class Country(val displayName: String) {
    AU("Australia"),
    BR("Brazil"),
    CA("Canada"),
    CH("Switzerland"),
    DE("Germany"),
    DK("Denmark"),
    ES("Spain"),
    FI("Finland"),
    FR("France"),
    GB("United Kingdom"),
    IE("Ireland"),
    IN("India"),
    IR("Iran"),
    MX("Mexico"),
    NL("Netherlands"),
    NO("Norway"),
    NZ("New Zealand"),
    RS("Serbia"),
    TR("Turkey"),
    UA("Ukraine"),
    US("United States");

    override fun toString(): String = displayName
}