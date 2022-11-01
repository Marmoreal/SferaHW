package com.example.sferahw.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColorStateList
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sferahw.R
import com.example.sferahw.data.ImagesList
import com.example.sferahw.databinding.ProfileFragmentBinding
import com.example.sferahw.ui.profile.adapter.ChroniclesListAdapter
import com.example.sferahw.ui.profile.adapter.MomentsListAdapter
import com.example.sferahw.ui.profile.adapter.PhotoListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
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

        with(binding) {

            Glide.with(requireContext())
                .load(R.drawable.add_photo_profile)
                .into(profileImageAndRatingContent.profileImage)

            layoutLanguageChoose.txtLanguage.text = getString(R.string.not_specified)
            layoutLocationChoose.txtLocation.text = getString(R.string.not_specified)

            txtAboutMe.onFocusChangeListener = OnFocusChangeListener { _, focus ->
                if (focus) {
                    layoutAboutMe.counterTextColor = getColorStateList(
                        requireContext(),
                        R.color.purple_200
                    )
                } else {
                    layoutAboutMe.counterTextColor = getColorStateList(
                        requireContext(),
                        R.color.on_surface87
                    )
                }
            }

            photoAdapter.submitList(ImagesList.getImagesList().take(4))
            momentsAdapter.submitList(ImagesList.getImagesList().take(6))
            chroniclesAdapter.submitList(ImagesList.getImagesList())
            listProfilePhoto.adapter = photoAdapter
            listMoments.adapter = momentsAdapter
            listChronicles.adapter = chroniclesAdapter

            btnPeople.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToPeopleFragment())
            }
        }
    }
}