package app.mastani.cathashtad.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.mastani.cathashtad.Screen

class TopLevelBackStack(startKey: Screen) {

    // Maintain a stack for each top level route
    var topLevelStacks : LinkedHashMap<Screen, SnapshotStateList<Screen>> = linkedMapOf(
        startKey to mutableStateListOf(startKey)
    )

    // Expose the current top level route for consumers
    var topLevelKey by mutableStateOf(startKey)

    // Expose the back stack so it can be rendered by the NavDisplay
    val backStack = mutableStateListOf(startKey)

    fun updateBackStack() =
        backStack.apply {
            clear()
            addAll(topLevelStacks.flatMap { it.value })
        }

    fun addTopLevel(key: Screen){
        // If the top level doesn't exist, add it
        if (topLevelStacks[key] == null){
            topLevelStacks.put(key, mutableStateListOf(key))
        } else {
            // Otherwise just move it to the end of the stacks
            topLevelStacks.apply {
                remove(key)?.let {
                    put(key, it)
                }
            }
        }
        topLevelKey = key
        updateBackStack()
    }

    fun add(key: Screen){
        topLevelStacks[topLevelKey]?.add(key)
        updateBackStack()
    }

    fun removeLast(){
        val removedKey = topLevelStacks[topLevelKey]?.removeLastOrNull()
        // If the removed key was a top level key, remove the associated top level stack
        topLevelStacks.remove(removedKey)
        topLevelKey = topLevelStacks.keys.last()
        updateBackStack()
    }

    fun isOnTopLevel() = backStack.last() in topLevelStacks.keys
}