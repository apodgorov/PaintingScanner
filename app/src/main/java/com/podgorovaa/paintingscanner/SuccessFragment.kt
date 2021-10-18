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
    private var mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { reset() }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_success, container, false)
        val safeArgs: SuccessFragmentArgs by navArgs()
        val code = safeArgs.code
        //val filename = Uri.parse("R.raw.$code")
        val filename = "https://filedn.eu/lgIIlvB83JTfr5mbbnJkE2S/mp3/" + code.toString()+ ".mp3"
        val filename2  = "https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/d/3DA6tUxAVQsCFw"
        var filename3 = "https://getfile.dokpub.com/yandex/get/" +code.toString()
        v.fragment_success_text_view_code.text = code
        mediaPlayer.run {
            reset()
            setDataSource(filename3)
            prepareAsync()
        }
        v.fragment_success_button_back_to_scanner.setOnClickListener {
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
