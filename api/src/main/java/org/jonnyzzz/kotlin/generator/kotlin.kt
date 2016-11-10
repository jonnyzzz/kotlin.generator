package org.jonnyzzz.kotlin.generator


fun KotlinWriter.`interface`(name : String, builder : KotlinWriter.() -> Unit) {
  block("interface $name") {
    builder()
  }
}

fun KotlinWriter.imports(vararg types: Class<*>) {
  imports(* types.map { it.name.replace("$", ".") }.distinct().toTypedArray())
}

fun KotlinWriter.imports(vararg types: String) {
  types.map { it }.distinct().sortedBy { it }.forEach {
    appendln("import $it")
  }
}

fun KotlinWriter.`package`(name : String) {
  appendln("package $name")
  appendln()
}

