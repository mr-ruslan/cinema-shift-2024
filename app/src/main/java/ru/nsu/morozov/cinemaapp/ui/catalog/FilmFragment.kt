package ru.nsu.morozov.cinemaapp.ui.catalog

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.nsu.morozov.cinemaapp.databinding.FilmFragmentBinding
import ru.nsu.morozov.cinemaapp.domain.entity.Film
import ru.nsu.morozov.cinemaapp.presentation.AppViewModelProvider
import ru.nsu.morozov.cinemaapp.presentation.FilmInfoState
import ru.nsu.morozov.cinemaapp.presentation.FilmViewModel

class FilmFragment : Fragment() {
    private var _binding: FilmFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<FilmViewModel> { AppViewModelProvider.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FilmFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                FilmInfoState.Initial -> Unit
                FilmInfoState.Loading -> showProgress()
                is FilmInfoState.Content -> showContent(state.item)
                is FilmInfoState.Error -> showError(state.msg)
            }

        }

        arguments?.let {
            val args = FilmFragmentArgs.fromBundle(it)
            viewModel.loadData(args.filmId)
        }
    }

    private fun showProgress() {
        with(binding) {
            //Toast.makeText(context, "Loading film info", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showContent(film: Film) {
        with(binding) {
            filmTitle.text = film.name
            filmSubtitle.text = film.description
            filmGenre.text = film.genres.joinToString(", ")
            filmOrigin.text = "США"
            filmRatingValue.text = film.rating.map { "${it.key} - ${it.value}" }.joinToString("\n")
            infoButton.setOnClickListener {
                Toast.makeText(context, "Not implemented", Toast.LENGTH_SHORT).show()
            }
            Glide.with(filmImage.context)
                .load("https://shift-backend.onrender.com" + film.image)
                //.apply(RequestOptions().placeholder(R.drawable.placeholder_image))
                .apply(
                    RequestOptions().transform(
                        CenterCrop(),
                        RoundedCorners(
                            TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP,
                                12f,
                                Resources.getSystem().displayMetrics
                            ).toInt()
                        )
                    )
                )
                .into(filmImage)
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