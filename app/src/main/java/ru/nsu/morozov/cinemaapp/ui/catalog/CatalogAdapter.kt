package ru.nsu.morozov.cinemaapp.ui.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nsu.morozov.cinemaapp.databinding.FilmCardBinding
import ru.nsu.morozov.cinemaapp.domain.entity.Film

class CatalogAdapter(
    private var onMore: (Film) -> Unit,
) : ListAdapter<Film, CatalogAdapter.CatalogViewHolder>(FilmDiffCallback()) {

    class CatalogViewHolder(
        private val binding: FilmCardBinding,
        private val onMore: (Film) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val titleView = binding.filmTitle

        fun bind(film: Film) {
            titleView.text = film.name
            binding.infoButton.setOnClickListener {
                onMore(film)
            }
            Glide.with(binding.filmImage.context)
                .load("https://shift-backend.onrender.com"+film.image)
                //.apply(RequestOptions().placeholder(R.drawable.placeholder_image))
                .into(binding.filmImage)
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
