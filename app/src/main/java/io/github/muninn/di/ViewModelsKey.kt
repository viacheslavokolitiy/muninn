package io.github.muninn.di

import android.arch.lifecycle.ViewModel

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by viacheslavokolitiy on 02.12.2017.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention
@MapKey
annotation class ViewModelsKey(val value: KClass<out ViewModel>)
