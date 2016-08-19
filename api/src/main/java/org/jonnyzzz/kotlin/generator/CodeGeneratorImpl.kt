package org.jonnyzzz.kotlin.generator

fun kotlinWriter(builder: KotlinWriter.() -> Unit): String {
  class KotlinWriterImpl(val offset: String = "", val writer: StringBuilder = StringBuilder()) : KotlinWriter {
    private val NL = "\n"

    override fun appendln(line: String) {
      if (line.isEmpty()) {
        writer.append(NL)
      } else {
        writer.append(offset + line + NL)
      }
    }

    override fun offset(): KotlinWriter = KotlinWriterImpl(offset + "  ", writer)

    override fun toString(): String = writer.toString()
  }

  return KotlinWriterImpl().apply { builder() }.toString()
}
