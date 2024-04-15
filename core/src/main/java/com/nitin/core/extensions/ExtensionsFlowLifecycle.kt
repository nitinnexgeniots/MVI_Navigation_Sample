package com.nitin.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Collect with lifecycle
 * @author Anand
 *
 * @param T
 * @param key
 * @param lifecycleOwner
 * @param minActiveState
 * @param action
 * @receiver
 */// Based on: https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
@Composable
inline fun <reified T> Flow<T>.collectWithLifecycle(
    key: Any = Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    noinline action: suspend (T) -> Unit
) {
    val lifecycleAwareFlow = remember(this, lifecycleOwner) {
        flowWithLifecycle(
            lifecycle = lifecycleOwner.lifecycle,
            minActiveState = minActiveState
        )
    }

    LaunchedEffect(key) {
        lifecycleAwareFlow.collect { action(it) }
    }
}

// Source: https://github.com/android/architecture-samples/blob/main/app/src/main/java/com/example/android/architecture/blueprints/todoapp/util/CollectAsStateWithLifecycle.kt
// To be removed when Lifecycle 2.6.0 becomes stable

/**
 * Collect as state with lifecycle
 *
 * @param T
 * @param lifecycle
 * @param minActiveState
 * @return
 */
@Composable
fun <T> StateFlow<T>.collectAsStateWithLifecycle(
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): State<T> = collectAsStateWithLifecycle(
    initialValue = remember { this.value },
    lifecycle = lifecycle,
    minActiveState = minActiveState
)

/**
 * Collect as state with lifecycle
 *
 * @param T
 * @param initialValue
 * @param lifecycle
 * @param minActiveState
 * @return
 */
@Composable
fun <T> Flow<T>.collectAsStateWithLifecycle(
    initialValue: T,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): State<T> {
    val currentValue = remember(this) { initialValue }
    return produceState(
        initialValue = currentValue,
        key1 = this,
        key2 = lifecycle,
        key3 = minActiveState
    ) {
        lifecycle.repeatOnLifecycle(minActiveState) {
            this@collectAsStateWithLifecycle.collect {
                this@produceState.value = it
            }
        }
    }
}
