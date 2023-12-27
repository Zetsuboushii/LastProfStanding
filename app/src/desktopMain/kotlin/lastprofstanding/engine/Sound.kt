package lastprofstanding.engine

import lastprofstanding.forceResourceStream
import java.io.BufferedInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

class Sound {
    class SoundFile(val filename: String, val background: Boolean) {
        companion object {
            val ROBLOX_DEATH_SOUND = SoundFile("roblox_death.wav", false)
            val SETLX_SOUNDTRACK = SoundFile("setlx_soundtrack.wav", true)
        }

        fun getFileName(): String {
            val root = "audio/"
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
            val bufferedStream = BufferedInputStream(forceResourceStream(sound.getFileName()))
            val inputStream = AudioSystem.getAudioInputStream(bufferedStream)
            val clip = AudioSystem.getClip()
            clip.open(inputStream)
            clip.start()
            if (sound.background) {
                backgroundClips.add(clip)
            }
        } catch (exception: Exception) {
            print("Sound error: $exception - ${exception.localizedMessage}")
            print(exception.stackTraceToString())
        }
    }

    fun stopBackgroundPlayback() {
        for (clip in backgroundClips) {
            clip.stop()
        }
    }
}