package org.jonnyzzz.kotlin.generator


fun KotlinWriter.`interface`(name: String, builder: KotlinWriter.() -> Unit) {
  block("interface $name") {
    builder()
  }
}

fun KotlinWriter.imports(vararg types: Class<*>) {
  imports(* types.map { it.name.replace("$", ".") }.distinct().toTypedArray())
}


//TODO: imports should provide way to filter duplicates
fun KotlinWriter.imports(vararg types: String) {
  types.map { it }.distinct().sortedBy { it }.forEach {
    appendln("import $it")
  }
}

fun KotlinWriter.`package`(name: String) {
  appendln("package $name")
  appendln()
}

fun KotlinWriter.clazz(header: String,
                       params: KotlinWriter.() -> Unit,
                       inherits: String,
                       block: KotlinWriter.() -> Unit) {
  appendln(header + " (")
  offset().params()
  appendln(") : $inherits {")
  offset().block()
  appendln("}")
  appendln()
}
