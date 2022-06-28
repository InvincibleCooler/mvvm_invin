package invin.mvvm_invin

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import invin.mvvm_invin.databinding.FragmentDetailBinding
import invin.mvvm_invin.databinding.ListitemEmptyBinding
import invin.mvvm_invin.databinding.ListitemMainBinding
import invin.mvvm_invin.db.ApiEntity
import invin.mvvm_invin.viewmodel.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class DetailFragment : Fragment() {
    companion object {
        private const val TAG = "DetailFragment"
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var localAdapter: LocalAdapter

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localAdapter = LocalAdapter(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.allData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                localAdapter.clear()
                localAdapter.items = Collections.singletonList(WrappedData().apply {
                    viewType = localAdapter.viewTypeEmpty
                })
                localAdapter.notifyDataSetChanged()
            } else {
                localAdapter.clear()
                localAdapter.items = it.map {
                    WrappedData().apply {
                        viewType = localAdapter.viewTypeItem
                        data = it
                    }
                }.toMutableList()
                localAdapter.notifyItemRangeChanged(0, it.size)
            }
        }

        viewModel.showProgress.observe(viewLifecycleOwner) {
            binding.loadingProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.plus.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insertData()
            }
        }
        binding.minus.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.deleteAllData()
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = localAdapter
            setHasFixedSize(true)
            requestFocus()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class LocalAdapter(private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val viewTypeEmpty = 1
        val viewTypeItem = 2

        var items = mutableListOf<WrappedData>()
            set(value) {
                field.addAll(value)
            }

        fun clear() {
            items.clear()
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun getItemViewType(position: Int): Int {
            return items[position].viewType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                viewTypeItem -> {
                    ItemViewHolder(ListitemMainBinding.inflate(LayoutInflater.from(activity), parent, false))
                }
                else -> {
                    EmptyViewHolder(ListitemEmptyBinding.inflate(LayoutInflater.from(activity), parent, false))
                }
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder.itemViewType) {
                viewTypeEmpty -> {
                    // do nothing
                }
                viewTypeItem -> {
                    val vh = holder as ItemViewHolder
                    val data = items[position].data as ApiEntity

                    vh.binding.tvTitle.text = data.body
                }
            }
        }

        private inner class EmptyViewHolder(_binding: ListitemEmptyBinding) : RecyclerView.ViewHolder(_binding.root)
        private inner class ItemViewHolder(_binding: ListitemMainBinding) : RecyclerView.ViewHolder(_binding.root) {
            val binding = _binding
        }
    }
}