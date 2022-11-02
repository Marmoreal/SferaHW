package com.example.sferahw.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.sferahw.data.PeopleList
import com.example.sferahw.databinding.PeopleFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    @Inject
    lateinit var glide: RequestManager

    @Inject
    lateinit var peopleAdapter: PeopleListAdapter

    private lateinit var binding: PeopleFragmentBinding
    private val viewModel: PeopleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PeopleFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            listPeople.adapter = peopleAdapter.apply {
                setOnItemClick { id ->
                    viewModel.onClickSubscribe(id)
                }
            }
        }

        with(viewModel) {
            loadData(PeopleList.getPeopleList())

            userListLiveData.observe(viewLifecycleOwner) {
                peopleAdapter.submitList(it)
            }
        }
    }
}