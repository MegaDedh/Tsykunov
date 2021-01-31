package ru.asshands.softwire.tsykunov.ui.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.databinding.FragmentHotBinding
import ru.asshands.softwire.tsykunov.databinding.FragmentLatestBinding
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.ui.PostAdapter
import ru.asshands.softwire.tsykunov.ui.latest.LatestViewModel
import ru.asshands.softwire.tsykunov.utils.ARG_OBJECT
import ru.asshands.softwire.tsykunov.utils.toLog

class HotFragment : Fragment(R.layout.fragment_hot) {

    private val viewModel: HotViewModel by viewModels()

    private val bind get() = _bind!!
    private var _bind: FragmentHotBinding? = null
    private lateinit var adapter: PostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _bind = FragmentHotBinding.bind(view)
        iniPostAdapter()
        viewModel.dataList.observe(this.viewLifecycleOwner, this::onDataReady)
        viewModel.errorId.observe(this.viewLifecycleOwner, this::showError)

        viewModel.loadData()
    }

    private fun onDataReady(data: List<Post>) {
        adapter.bindData(data)
        adapter.notifyDataSetChanged()
    }

    private fun iniPostAdapter() {
        adapter = PostAdapter()
        val recyclerView = bind.fragmentHotRv
        recyclerView.adapter = adapter

    }

    private fun showError(errorTextId: Int) {
        val text = getText(errorTextId)
        Snackbar.make(bind.fragmentHotRv, text, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        _bind = null
        super.onDestroyView()
    }
}