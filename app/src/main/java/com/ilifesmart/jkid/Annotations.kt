package com.ilifesmart.jkid

import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
annotation class JsonExclude

@Target(AnnotationTarget.PROPERTY)
annotation class JsonName(val name: String) // val是强制的

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

// out:说明此处可以使用任何继承Any的类型
@Target(AnnotationTarget.PROPERTY)
annotation class DeserializeInterface(val targetClass: KClass<out Any>)

// KClass对应Java中的Class
// 有点类似 Class<? extends ValueSerializer>
// 接收一个继承(ValueSerializer)的类引用来作为实参
// *: 允许实例化任何类型
@Target(AnnotationTarget.PROPERTY)
annotation class CustomSerializer(val serializerClass: KClass<out ValueSerializer<*>>)