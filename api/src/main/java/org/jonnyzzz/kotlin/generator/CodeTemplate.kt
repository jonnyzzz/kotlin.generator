package org.jonnyzzz.kotlin.generator

import java.text.SimpleDateFormat
import java.util.*

fun KotlinWriter.generateShortHeader() {
  appendln("///////////////////////////////////////////")
  appendln("/// THIS IS AUTO GENERATED FILE")
  appendln("/// YOU MAY EDIT IT ON YOUR OWN RISK")
  appendln("///////////////////////////////////////////")
}

fun KotlinWriter.generateFileHeader(name: String, version: String) {
  appendln("///////////////////////////////////////////")
  appendln("/// THIS IS AUTO GENERATED FILE")
  appendln("/// YOU MAY EDIT IT ON YOUR OWN RISK")
  appendln("/// ${name} VERSION=$version")
  appendln("/// generated on ${SimpleDateFormat.getDateTimeInstance().format(Date())}")
  appendln("///////////////////////////////////////////")
  appendln()
}
