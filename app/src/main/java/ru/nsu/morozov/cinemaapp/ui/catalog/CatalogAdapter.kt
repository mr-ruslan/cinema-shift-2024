package ru.nsu.morozov.cinemaapp.ui.catalog

import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.nsu.morozov.cinemaapp.R
import ru.nsu.morozov.cinemaapp.databinding.FilmCardBinding
import ru.nsu.morozov.cinemaapp.domain.entity.Film

class CatalogAdapter(
    private var onMore: (Film) -> Unit,
) : ListAdapter<Film, CatalogAdapter.CatalogViewHolder>(FilmDiffCallback()) {

    class CatalogViewHolder(
        private val binding: FilmCardBinding,
        private val onMore: (Film) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            with(binding) {
                filmTitle.text = film.name
                filmSubtitle.text = film.originalName
                filmGenre.text = film.genres.joinToString(", ")
                val year = film.releaseDate.trim().split(" ").last().toIntOrNull()
                "${film.country}${if (year != null) ", $year" else ""}".also { filmOrigin.text = it }
                filmRatingValue.text =
                    film.rating.map { "${it.key} - ${it.value}" }.joinToString("\n")
                binding.infoButton.setOnClickListener {
                    onMore(film)
                }
                Glide.with(binding.filmImage.context)
                    .load("https://shift-backend.onrender.com" + film.image)
                    .placeholder(R.drawable.loading_placeholder)
                    .error(R.drawable.loading_placeholder)
                    .apply(
                        RequestOptions.bitmapTransform(
                            RoundedCorners(
                                TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    12f,
                                    Resources.getSystem().displayMetrics
                                ).toInt()
                            )
                        )
                    )
                    .into(binding.filmImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatalogViewHolder(
        FilmCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onMore,
    )

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FilmDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
