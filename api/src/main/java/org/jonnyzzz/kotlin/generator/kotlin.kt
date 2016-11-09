package org.jonnyzzz.kotlin.generator


fun KotlinWriter.`interface`(name : String, builder : KotlinWriter.() -> Unit) {
  block("interface $name") {
    builder()
  }
}
