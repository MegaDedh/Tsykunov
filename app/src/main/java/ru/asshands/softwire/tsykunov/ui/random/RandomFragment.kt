package ru.asshands.softwire.tsykunov.ui.random

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.databinding.FragmentRandomBinding
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.utils.ArrowBehavior
import ru.asshands.softwire.tsykunov.utils.loadImage
import ru.asshands.softwire.tsykunov.utils.loadImageWithPb

class RandomFragment : Fragment(R.layout.fragment_random) {

    private val viewModel: RandomViewModel by viewModels {
        RandomViewModelFactory(requireContext().applicationContext)
    }
    private val bind get() = _bind!!
    private var _bind: FragmentRandomBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _bind = FragmentRandomBinding.bind(view)
        viewModel.post.observe(this.viewLifecycleOwner, this::postOnChanged)
        viewModel.numPost.observe(this.viewLifecycleOwner, this::numPostOnChanged)
        viewModel.errorId.observe(this.viewLifecycleOwner, this::showError)

        bind.fragmentRandomArrowForward.setOnClickListener {
            viewModel.loadPost(ArrowBehavior.FORWARD)
        }

        bind.fragmentRandomArrowBack.setOnClickListener {
            viewModel.loadPost(ArrowBehavior.BACK)
        }

        viewModel.loadPost()
    }


    private fun postOnChanged(post: Post) {
        bind.fragmentRandomDesc.text = post.description

        if (post.gifURL == null) {
            bind.fragmentRandomImageContainer.loadImage("")
            Snackbar.make(
                bind.fragmentRandomImageContainer,R.string.missing_gif_url,
                Snackbar.LENGTH_LONG
            ).show()
        } else {

            bind.fragmentRandomProgressBar.visibility = View.VISIBLE
            bind.fragmentRandomImageContainer.loadImageWithPb(
                post.gifURL,
                bind.fragmentRandomProgressBar
            )
        }
    }

    private fun numPostOnChanged(numPost: Int?) {
        bind.fragmentRandomArrowBack.visibility = when (numPost) {
            0, null -> View.INVISIBLE
            else -> View.VISIBLE
        }
    }

    private fun showError(errorTextId: Int) {
        val text = getText(errorTextId)
        Snackbar.make(bind.fragmentRandomImageContainer, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        _bind = null
        super.onDestroyView()
    }
}