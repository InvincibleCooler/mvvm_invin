package invin.mvvm_invin

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import invin.mvvm_invin.databinding.FragmentMainBinding
import invin.mvvm_invin.databinding.ListitemEmptyBinding
import invin.mvvm_invin.databinding.ListitemMainBinding
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.net.res.BookInfo
import invin.mvvm_invin.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class MainFragment : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var localAdapter: LocalAdapter

    private var page = "1"

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localAdapter = LocalAdapter(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.resource.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data == null) {
                        localAdapter.items = Collections.singletonList(WrappedData().apply {
                            viewType = localAdapter.viewTypeEmpty
                        })
                    } else {
                        val books = it.data.books
                        if (books.isNullOrEmpty().not()) {
                            localAdapter.clear()
                            localAdapter.items = books!!.map {
                                WrappedData().apply {
                                    viewType = localAdapter.viewTypeItem
                                    data = it
                                }
                            }.toMutableList()
                            localAdapter.notifyItemRangeChanged(0, books.size)
                        }
                    }
                }
                Resource.Status.ERROR -> {

                }
            }
        }

        viewModel.showProgress.observe(viewLifecycleOwner) {
            binding.loadingProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val query = binding.etSearch.text.toString()
                    if (query.isNotEmpty()) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getBookList(query)
                        }
                    }
                    return true
                }
                return false
            }
        })

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
                    val data = items[position].data as BookInfo

                    Glide.with(activity).load(data.image).into(vh.binding.ivThumb)

                    vh.binding.tvTitle.text = data.title
                    vh.binding.tvSubTitle.text = data.subtitle
                    vh.binding.tvPrice.text = data.price
                }
            }
        }

        private inner class EmptyViewHolder(_binding: ListitemEmptyBinding) : RecyclerView.ViewHolder(_binding.root)
        private inner class ItemViewHolder(_binding: ListitemMainBinding) : RecyclerView.ViewHolder(_binding.root) {
            val binding = _binding
        }
    }
}