package lastprofstanding.engine

import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

class Sound {
    class SoundFile(val filename: String, val background: Boolean) {
        companion object {
            val ROBLOX_DEATH_SOUND = SoundFile("roblox_death.wav", false)
            val SETLX_SOUNDTRACK = SoundFile("setlx_soundtrack.wav", true)
        }

        fun getFileName(): String {
            val root = "src/desktopMain/kotlin/lastprofstanding/res/audio/"
            return root + filename
        }
    }

    companion object {
        private var instance: Sound? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Sound().also { instance = it }
            }
    }

    private val backgroundClips = mutableListOf<Clip>()

    fun play(sound: SoundFile) {
        try {
            val inputStream = AudioSystem.getAudioInputStream(File(sound.getFileName()))
            val clip = AudioSystem.getClip()
            clip.open(inputStream)
            clip.start()
            if (sound.background) {
                backgroundClips.add(clip)
            }
        } catch (exception: Exception) {
            print("Sound error: ${exception.localizedMessage}")
        }
    }

    fun stopBackgroundPlayback() {
        for (clip in backgroundClips) {
            clip.stop()
        }
    }
}