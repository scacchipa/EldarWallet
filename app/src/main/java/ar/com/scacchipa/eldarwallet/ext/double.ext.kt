package ar.com.scacchipa.eldarwallet.ext

fun Double.format(digits: Int) = "$%.${digits}f".format(this)
