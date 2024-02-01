package ru.nsu.morozov.cinemaapp.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.nsu.morozov.cinemaapp.R
import ru.nsu.morozov.cinemaapp.databinding.CatalogFragmentBinding
import ru.nsu.morozov.cinemaapp.domain.entity.Film
import ru.nsu.morozov.cinemaapp.presentation.AppViewModelProvider
import ru.nsu.morozov.cinemaapp.presentation.CatalogState
import ru.nsu.morozov.cinemaapp.presentation.CatalogViewModel

class CatalogFragment : Fragment() {
    private var _binding: CatalogFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<CatalogViewModel> { AppViewModelProvider.Factory }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatalogFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val adapter = CatalogAdapter(
        onMore = { film ->
            val bundle = FilmFragmentArgs.Builder(film.id).build().toBundle()
            findNavController().navigate(R.id.navigation_film, bundle)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = CatalogFragmentBinding.bind(view)
        binding.recyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                CatalogState.Initial -> Unit
                CatalogState.Loading -> showProgress()
                is CatalogState.Content -> showContent(state.items)
                is CatalogState.Error -> showError(state.msg)
            }

        }

        viewModel.loadData()
    }

    private fun showProgress() {
        with(binding) {
            errorContent.isVisible = false
            recyclerView.isVisible = false
            progressBar.isVisible = true
        }
    }

    private fun showContent(films: List<Film>) {
        with(binding) {
            progressBar.isVisible = false
            errorContent.isVisible = false
            recyclerView.isVisible = true

            adapter.submitList(films)
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progressBar.isVisible = false
            recyclerView.isVisible = false
            errorContent.isVisible = true

            errorText.text = message
            errorButton.setOnClickListener {
                viewModel.loadData()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}