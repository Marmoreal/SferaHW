package com.example.sferahw.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.sferahw.R
import com.example.sferahw.data.ImagesList
import com.example.sferahw.databinding.ProfileFragmentBinding
import com.example.sferahw.ui.profile.adapter.ChroniclesListAdapter
import com.example.sferahw.ui.profile.adapter.MomentsListAdapter
import com.example.sferahw.ui.profile.adapter.PhotoListAdapter

class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding
    private val photoAdapter = PhotoListAdapter()
    private val momentsAdapter = MomentsListAdapter()
    private val chroniclesAdapter = ChroniclesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImageAndRatingContent.profileImage.load(drawableResId = R.drawable.add_photo_profile)
        binding.layoutLanguageChoose.txtLanguage.text = getString(R.string.not_specified)
        binding.layoutLocationChoose.txtLocation.text = getString(R.string.not_specified)

        photoAdapter.submitList(ImagesList.getChroniclesList().take(4))
        momentsAdapter.submitList(ImagesList.getChroniclesList().take(6))
        chroniclesAdapter.submitList(ImagesList.getChroniclesList())
        binding.listProfilePhoto.adapter = photoAdapter
        binding.listMoments.adapter = momentsAdapter
        binding.listChronicles.adapter = chroniclesAdapter
    }
}