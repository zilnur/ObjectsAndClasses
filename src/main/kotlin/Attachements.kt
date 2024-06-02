sealed interface Attachements {
    val type: String

    data class AudioAttachements(override val type: String = "audio",
                                 val audio: Audio): Attachements

    data class VideoAttachements(override val type: String = "video",
                                 val video: Video): Attachements

    data class PhotoAttachements(override val type: String = "photo",
                                 val photo: Photo): Attachements

    data class FileAttachements(override val type: String = "file",
                                val file: File): Attachements

    data class StickerAttachements(override val type: String = "sticker",
                                   val sticker: Sticker): Attachements
}

data class Audio(
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val url: String
)

data class Video(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val player: String
)

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val photo130: String
)

data class File(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val url: String
)

data class Sticker(
    val productId: Int,
    val stickerId: Int,
    val animationUrl: String
)