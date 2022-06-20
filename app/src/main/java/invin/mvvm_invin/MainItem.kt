package invin.mvvm_invin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem


class MainItem : AbstractItem<MainItem.ViewHolder>() {
    override val type: Int
        get() = TODO("Not yet implemented")
    override val layoutRes: Int
        get() = TODO("Not yet implemented")
    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

}