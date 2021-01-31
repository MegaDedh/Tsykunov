package ru.asshands.softwire.tsykunov.ui.start

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.databinding.FragmentStartBinding
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.utils.ArrowBehavior
import ru.asshands.softwire.tsykunov.utils.loadImage
import ru.asshands.softwire.tsykunov.utils.loadImageWithPb

class StartFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: StartViewModel by viewModels {
        StartViewModelFactory(requireContext().applicationContext)
    }
    private val bind get() = _bind!!
    private var _bind: FragmentStartBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _bind = FragmentStartBinding.bind(view)
        viewModel.post.observe(this.viewLifecycleOwner, this::postOnChanged)
        viewModel.numPost.observe(this.viewLifecycleOwner, this::numPostOnChanged)
        viewModel.errorId.observe(this.viewLifecycleOwner, this::showError)

        bind.fragmentStartArrowForward.setOnClickListener {
            viewModel.loadPost(ArrowBehavior.FORWARD)
        }

        bind.fragmentStartArrowBack.setOnClickListener {
            viewModel.loadPost(ArrowBehavior.BACK)
        }

        viewModel.loadPost()
    }


    private fun postOnChanged(post: Post) {
        bind.fragmentStartDesc.text = post.description

        if (post.gifURL == null) {
            bind.fragmentStartImageContainer.loadImage("")
            Snackbar.make(
                bind.fragmentStartImageContainer,R.string.missing_gif_url,
                Snackbar.LENGTH_LONG
            ).show()
        } else {

            bind.fragmentStartProgressBar.visibility = View.VISIBLE
            bind.fragmentStartImageContainer.loadImageWithPb(
                post.gifURL,
                bind.fragmentStartProgressBar
            )
        }
    }

    private fun numPostOnChanged(numPost: Int?) {
        bind.fragmentStartArrowBack.visibility = when (numPost) {
            0, null -> View.INVISIBLE
            else -> View.VISIBLE
        }
    }

    private fun showError(errorTextId: Int) {
        val text = getText(errorTextId)
        Snackbar.make(bind.fragmentStartImageContainer, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        _bind = null
        super.onDestroyView()
    }
}