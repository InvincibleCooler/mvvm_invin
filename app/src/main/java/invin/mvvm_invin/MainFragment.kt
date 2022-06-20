package invin.mvvm_invin

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mikepenz.fastadapter.FastAdapter
import invin.mvvm_invin.databinding.FragmentMainBinding
import invin.mvvm_invin.db.ApiDataBase
import invin.mvvm_invin.net.RequestManager
import invin.mvvm_invin.net.Resource
import invin.mvvm_invin.repository.book.BookLocalDataSource
import invin.mvvm_invin.repository.book.BookRemoteDataSource
import invin.mvvm_invin.repository.book.BookRepository
import invin.mvvm_invin.viewmodel.MainViewModel


class MainFragment : Fragment() {
    companion object {
        private const val TAG = "MainFragment"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var fastAdapter: FastAdapter<MainItem>
    private val viewModel by viewModels<MainViewModel> {
        val dao = ApiDataBase.getInstance(requireActivity()).apiDao()
        val serviceApi = RequestManager.getServiceApi(null)

        val localDataSource = BookLocalDataSource(dao)
        val remoteDataSource = BookRemoteDataSource(serviceApi)

        val bookRepository = BookRepository(localDataSource, remoteDataSource)
        MainViewModel.Factory(bookRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel.resouce.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data == null) {
                        Log.d(TAG, "no list")
                    } else {
                        Log.d(TAG, "yes list")
                    }
                }
                Resource.Status.ERROR -> {

                }
            }
        }

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getBookList()
                    return true
                }
                return false
            }
        })

//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(requireActivity())
//            adapter = localAdapter
//            setHasFixedSize(true)
//            requestFocus()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}