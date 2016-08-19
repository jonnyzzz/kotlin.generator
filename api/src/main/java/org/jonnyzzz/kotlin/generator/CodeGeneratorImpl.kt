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

fun kotlinBlockWriter(builder: KotlinMixinWriter.() -> Unit): String =
        buildString {
          object : KotlinMixinWriter {
            override fun constant(name: String) {
              append(name)
            }

            override fun function(name: String, vararg params: String) {
              if (!params.isEmpty()) throw Error("Not supported yet")

              append(name)
            }

            override fun block(name: String, vararg params: String, blockWriter: KotlinWriter.() -> Unit) {
              if (!params.isEmpty()) throw Error("Not supported yet")

              append(kotlinWriter {
                block(name) {
                  blockWriter()
                }
              })
            }
          }.builder()
        }
