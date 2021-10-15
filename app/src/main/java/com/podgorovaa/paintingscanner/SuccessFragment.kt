package com.podgorovaa.paintingscanner

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_success.view.*
import android.media.AudioManager
import android.net.Uri

class SuccessFragment : Fragment() {
    private var mPlayer: MediaPlayer? = null
    private var am: AudioManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_success, container, false)
        val safeArgs: SuccessFragmentArgs by navArgs()
        val code = safeArgs.code
        //val filename = Uri.parse("R.raw.$code")
        val filename = Uri.parse("https://www.dropbox.com/s/1upjc26ygl818fm/p1.mp3")
        val filename2  = "https://www.dropbox.com/s/1upjc26ygl818fm/p1.mp3"
        v.fragment_success_text_view_code.text = code
        //mPlayer = MediaPlayer.create(requireContext(), R.raw.painting_number_one)
        //mPlayer = MediaPlayer()
        mPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mPlayer?.setDataSource(filename2)
        mPlayer?.prepare()
        //mPlayer?.setDataSource(requireContext(), R.raw.p1)
        //mPlayer?.set
        mPlayer?.start()

        v.fragment_success_button_back_to_scanner.setOnClickListener {
            mPlayer?.stop()
            findNavController().navigateUp()
        }

        return v
    }

}
