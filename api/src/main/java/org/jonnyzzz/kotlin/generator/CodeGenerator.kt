package org.jonnyzzz.kotlin.generator

import org.apache.commons.lang3.StringEscapeUtils


interface KotlinWriter {
  fun appendln(line: String = "")

  fun offset(): KotlinWriter
}

fun KotlinWriter.block(text: String, omitEmpty: Boolean = false, builder: KotlinWriter.() -> Unit) {
  var hasContent = false
  val that = this
  val offset = that.offset()
  object : KotlinWriter by offset {
    override fun appendln(line: String) {
      if (hasContent == false) {
        that.appendln(text + " {")
        hasContent = true
      }
      offset.appendln(line)
    }
  }.builder()

  when {
    hasContent -> appendln("}")
    !omitEmpty -> appendln(text + " { }")
    else -> appendln(text)
  }
}

fun KotlinWriter.blockWithParams(text: String, args: Array<out Pair<String, String>?>,
                                 omitEmpty: Boolean = false, builder: KotlinWriter.() -> Unit) {
  val blockNameBuilder = StringBuilder()
  blockNameBuilder.append(text)
  blockNameBuilder.append(args.filterNotNull().map { "${it.first}: ${it.second}" }.joinToString(", ", prefix = " (", postfix = ")"))
  this.block(blockNameBuilder.toString(), omitEmpty = omitEmpty, builder = builder)
}

fun KotlinWriter.task(name: String, vararg args: Pair<String, String>?, builder: KotlinWriter.() -> Unit) {
  blockWithParams("task $name", args, false, builder)
}

fun KotlinWriter.setter(name: String, value: String?) {
  if (value == null) return
  val encoded = value.quote()
  appendln("$name = $encoded")
}

fun KotlinWriter.comment(text: String) {
  text.split(Regex("[\n]")).forEach {
    appendln("// $it")
  }
}

fun String.identifier(): String {
  val s = this.replace(Regex("[\\.\\-\\+]"), "_")

  if (s.isEmpty()) return "__empty__"

  return buildString {
    fun escape(ch: Char) {
      append("_0x")
      append(Integer.toHexString(ch.toInt()))
      append("_")
    }

    val ch0 = s.get(0)
    if (!Character.isJavaIdentifierStart(ch0)) {
      escape(ch0)
    } else {
      append(ch0)
    }

    for (ch in s.drop(1)) {
      if (!Character.isJavaIdentifierPart(ch)) {
        escape(ch)
      } else {
        append(ch)
      }
    }
  }
}

fun String.escaped(forDoubleQuotedString: Boolean = true): String {
  val escapeJava = StringEscapeUtils.escapeJava(this)!!
  if (!forDoubleQuotedString) return escapeJava
  return escapeJava.replace("\$", "\\\$")
}

fun String.quote(): String {
  val encoded = escaped(false)
  return "\'$encoded\'"
}

fun String.dquote(): String {
  val encoded = escaped()
  return "\"$encoded\""
}
