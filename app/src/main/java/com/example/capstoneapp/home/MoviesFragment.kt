package com.example.capstoneapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.capstoneapp.databinding.FragmentMoviesBinding
import com.example.capstoneapp.detail.DetailActivity
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding

    private val viewModel: MovieViewModel by viewModels()
    private val mAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvMovies?.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = mAdapter
        }

        activity?.let {
            viewModel.allMovies.observe(viewLifecycleOwner){ movies ->
                if (movies.data != null){

                    when (movies){
                        is Resource.Loading<*> -> progressBarEnabled(true)

                        is Resource.Success<*> -> {
                            progressBarEnabled(false)
                            mAdapter.setData(movies.data as ArrayList<Movie>?)
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                            setAdapterCallback()
                        }

                        else -> {
                            progressBarEnabled(false)
                            Toast.makeText(requireContext(), "Error: ${movies.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun setAdapterCallback(){
        mAdapter.setCallback(object : MovieAdapter.MovieCallback{
            override fun onMovieClicked(movie: Movie) {
                Toast.makeText(requireContext(), movie.title, Toast.LENGTH_LONG).show()
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ENTERTAINMENT, movie)
                startActivity(intent)
            }
        })
    }

    private fun progressBarEnabled(state : Boolean){
        if (state){
            binding?.progressBar?.visibility = View.VISIBLE
        }else{
            binding?.progressBar?.visibility = View.GONE
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}