package com.aagamshah.matchmate.presentation.adapter

import com.aagamshah.matchmate.R
import com.aagamshah.matchmate.databinding.CellProfileBinding
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.bumptech.glide.Glide

class ProfileAdapter(
    val users: MutableList<ProfileModel>,
    val choiceCallback: (String, Boolean?) -> Unit
) :
    BaseAdapter<ProfileModel, CellProfileBinding>(
        users,
        CellProfileBinding::inflate
    ) {
    override fun bind(
        binding: CellProfileBinding,
        item: ProfileModel,
        position: Int
    ) {
        binding.tvName.text = item.name.first
        binding.tvAge.text = item.dob.age.toString()
        binding.tvCity.text = item.city

        Glide.with(binding.root.context)
            .load(item.picture.large)
            .circleCrop()
            .into(binding.ivProfileImage)

        binding.ivAccept.setImageResource(R.drawable.ic_accept)
        binding.ivReject.setImageResource(R.drawable.ic_reject)

        when (item.isAccepted) {
            true -> binding.ivAccept.setImageResource(R.drawable.ic_accept_selected)
            false -> binding.ivReject.setImageResource(R.drawable.ic_reject_selected)
            null -> Unit
        }

        binding.ivAccept.setOnClickListener {
            val newValue = if (item.isAccepted == true) null else true
            users[position] = item.copy(isAccepted = newValue)
            notifyItemChanged(position)
            choiceCallback(item.id, newValue)
        }

        binding.ivReject.setOnClickListener {
            val newValue = if (item.isAccepted == false) null else false
            users[position] = item.copy(isAccepted = newValue)
            notifyItemChanged(position)
            choiceCallback(item.id, newValue)
        }
    }

    fun updateData(users: List<ProfileModel>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

}