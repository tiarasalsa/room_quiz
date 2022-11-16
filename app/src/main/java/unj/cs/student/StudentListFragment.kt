package unj.cs.student

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import unj.cs.student.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {
//    companion object{
//        const val TOAST_MESSAGE = "toast_message"
//    }
    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var toastMessage: String
    private val viewModel: StudentViewModel by activityViewModels {
        StudentViewModelFactory(
            (activity?.application as StudentApplication).database.studentDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let{
//            toastMessage = it.getString(TOAST_MESSAGE).toString()
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        val root : View = binding.root
        //binding.viewModel = viewModel
        //val studentRecyclerView: RecyclerView = binding.studentViewRecycler
        //studentRecyclerView.adapter = StudentAdapter {}
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StudentAdapter{
            val action: NavDirections = StudentListFragmentDirections.actionStudentListFragmentToStudentFormFragment(it.id, "Update Student")
            view.findNavController().navigate(action)
        }
        binding.studentViewRecycler.adapter = adapter
        viewModel.allStudents.observe(this.viewLifecycleOwner) {
            items -> items.let { adapter.submitList(it) }
        }
        val addButton : FloatingActionButton = binding.addButton

        addButton.setOnClickListener{
            val action: NavDirections = StudentListFragmentDirections.actionStudentListFragmentToStudentFormFragment(-1, "Add Student")
            view.findNavController().navigate(action)
        }
//        if (toastMessage != "null"){
//            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
//        }
//        toastMessage = "null"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}