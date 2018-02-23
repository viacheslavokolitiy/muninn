package io.github.muninn.model

/**
 * Created by viacheslavokolitiy on 14.02.2018.
 */
data class StorageFile(val fileName: String, val filePath: String,
                       val isDirectory: Boolean, val isFile: Boolean,
                       val hasParent: Boolean, val previousFile: StorageFile?)