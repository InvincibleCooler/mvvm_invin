package invin.mvvm_invin

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import invin.mvvm_invin.constants.MainViewType
import invin.mvvm_invin.net.res.BookInfo


class MainItemList(private val bookInfo: BookInfo) : AbstractItem<MainItemList.ViewHolder>() {
    companion object {
        private const val TAG = "MainItemList"
    }

    override val type = MainViewType.VIEW_TYPE_ITEM
    override val layoutRes = R.layout.listitem_main

    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        Glide.with(holder.itemView.context).load(bookInfo.image).into(holder.ivThumb)

        holder.tvTitle.text = bookInfo.title
        holder.tvSubTitle.text = bookInfo.subtitle
        holder.tvPrice.text = bookInfo.price
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)
        holder.ivThumb.setImageDrawable(null)
        holder.tvTitle.text = ""
        holder.tvSubTitle.text = ""
        holder.tvPrice.text = ""
    }

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivThumb: ImageView = view.findViewById(R.id.iv_thumb)
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var tvSubTitle: TextView = view.findViewById(R.id.tv_sub_title)
        var tvPrice: TextView = view.findViewById(R.id.tv_price)
    }
}