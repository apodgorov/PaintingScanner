package com.podgorovaa.paintingscanner

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_success.view.*

@Suppress("DEPRECATION")
class SuccessFragment : Fragment(), OnPreparedListener, OnCompletionListener {
    private var mPlayer: MediaPlayer? = null
    private var am: AudioManager? = null
    private var mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reset() }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_success, container, false)
        val safeArgs: SuccessFragmentArgs by navArgs()
        val code = safeArgs.code
        //val filename = Uri.parse("R.raw.$code")
        val filename = Uri.parse("https://www.dropbox.com/s/1upjc26ygl818fm/p1.mp3")
        val filename2  = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/3DA6tUxAVQsCFw"
        v.fragment_success_text_view_code.text = code
        //mPlayer = MediaPlayer.create(requireContext(), R.raw.painting_number_one)
        //mPlayer = MediaPlayer()
        //mPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        //mPlayer?.setDataSource(filename2)
        //mPlayer?.setOnPreparedListener(this)
        //mPlayer?.setOnCompletionListener { this }
        //mPlayer?.prepareAsync()
        //mPlayer?.start()
        mediaPlayer.run {
            reset()
            setDataSource(filename2)
            prepareAsync()
        }
        v.fragment_success_button_back_to_scanner.setOnClickListener {
            mPlayer?.stop()
            findNavController().navigateUp()
        }

        return v
    }

    private fun releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release()
                //mediaPlayer = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }

}
