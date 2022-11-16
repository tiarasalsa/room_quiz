package unj.cs.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import unj.cs.student.databinding.StudentViewBinding

class StudentAdapter(private val onItemClicked: (Student) -> Unit) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(DiffCallback){

    class StudentViewHolder(private var binding: StudentViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student){
            binding.apply {
                studentId.text = student.ids
                studentName.text = student.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val viewHolder = StudentViewHolder(
            StudentViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }
}