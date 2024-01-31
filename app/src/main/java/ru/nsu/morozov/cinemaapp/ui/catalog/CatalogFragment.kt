package ru.nsu.morozov.cinemaapp.ui.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            Toast.makeText(context, film.name, Toast.LENGTH_SHORT).show()
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
            Toast.makeText(context, "Loading today films", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showContent(films: List<Film>) {
        with(binding) {
            adapter.submitList(films)
        }
    }

    private fun showError(message: String) {
        with(binding) {
            Log.d("JSONERROR", message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}