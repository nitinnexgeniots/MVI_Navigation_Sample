package com.nitin.network.util


import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class AnnotatedConverterFactory(private val mFactoryMap: Map<Class<*>, Converter.Factory>) :
    Converter.Factory() {
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Json

    @Retention(AnnotationRetention.RUNTIME)
    annotation class Xml

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val factory = mFactoryMap[Json::class.java]
        return factory!!.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit
        )
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            val factory = mFactoryMap[annotation.annotationClass.java]
            if (factory != null) {
                return factory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        //try to default to json in case no annotation on current method was found
        val jsonFactory = mFactoryMap[Json::class.java]
        return jsonFactory?.responseBodyConverter(type, annotations, retrofit)
    }

    class Builder {
        var mFactoryMap: MutableMap<Class<*>, Converter.Factory> = LinkedHashMap()
        fun add(factoryType: Class<out Annotation?>?, factory: Converter.Factory?): Builder {
            if (factoryType == null) throw NullPointerException("factoryType is null")
            if (factory == null) throw NullPointerException("factory is null")
            mFactoryMap[factoryType] = factory
            return this
        }

        fun build(): AnnotatedConverterFactory {
            return AnnotatedConverterFactory(mFactoryMap)
        }
    }
}