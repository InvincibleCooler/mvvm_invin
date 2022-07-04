package invin.mvvm_invin.delegation.viewholder

import androidx.recyclerview.widget.RecyclerView
import invin.mvvm_invin.databinding.ItemEmptyViewBinding


/**
 * Copyright (C) 2022 Kakao Inc. All rights reserved.
 *
 * Created by Invincible on 04/07/2022
 *
 */
class EmptyViewHolder(_binding: ItemEmptyViewBinding) : RecyclerView.ViewHolder(_binding.root) {
    init {
        _binding.tvEmpty.text = "Wow~~~"
    }
}