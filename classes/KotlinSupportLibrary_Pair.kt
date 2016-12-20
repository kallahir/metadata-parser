package br.com.edsilfer.kotlin_support.extensions

/**
 * Created by User on 03/12/2016.
 */

fun Pair<Int, Int>.isBetween (number : Int) :Boolean {
    return number >= first && number <= second
}