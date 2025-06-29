package com.aagamshah.matchmate.presentation.adapter

import com.aagamshah.matchmate.databinding.CellProfileBinding
import com.aagamshah.matchmate.domain.model.ProfileModel
import com.bumptech.glide.Glide

class ProfileAdapter(val users: MutableList<ProfileModel>) :
    BaseAdapter<ProfileModel, CellProfileBinding>(
        users,
        CellProfileBinding::inflate
    ) {
    override fun bind(
        binding: CellProfileBinding,
        item: ProfileModel
    ) {
        binding.tvName.text = item.name.first
        binding.tvAge.text = item.dob.age.toString()
        binding.tvCity.text = item.location.city
        Glide.with(binding.root.context).load(item.picture.medium).circleCrop()
            .into(binding.ivProfileImage)
    }

    fun updateData(users: List<ProfileModel>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

}