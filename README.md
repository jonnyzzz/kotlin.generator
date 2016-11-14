[![Build Status](https://travis-ci.org/jonnyzzz/kotlin.generator.svg?branch=master)](https://travis-ci.org/jonnyzzz/kotlin.generator)

Kotlin Generator
================

This is a tiny library that allows one to generate
Kotlin code  via Kotlin DSLs.

Goals
=====

The project goal is to make Kotlin code generating code to looks as close as possible to a code it generates.

Currently, this librarby is a set of utilities, please feed free to pull request any utilities you have. Let's have all our utilities on a common place to collect enough use-cases (and tests?)

License
-------

Apache 2.0


Binaries
========

You may download binaries from maven artifacts repository from **jCenter** or from here
http://dl.bintray.com/jonnyzzz/maven

In Gradle build script it may be done like this
```gradle
repositories {
  jcenter()
}

dependencies {
  compile "org.jonnyzzz.kotlin.generator:api:<VERSION>"
}
```

