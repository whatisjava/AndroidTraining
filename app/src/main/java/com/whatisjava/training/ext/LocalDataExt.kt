package com.whatisjava.training.ext

import com.luck.picture.lib.entity.LocalMedia

fun LocalMedia.toString(): String? {
    var path = path
    if (isCut) {
        path = cutPath
    }
    if (isCompressed) {
        path = compressPath
    }
    if (isToSandboxPath) {
        path = sandboxPath
    }
    if (isOriginal) {
        path = originalPath
    }
    if (isWatermarkPath) {
        path = watermarkPath
    }
    return path
}