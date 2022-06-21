package invin.mvvm_invin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.AbstractItem
import invin.mvvm_invin.constants.MainViewType


class MainItemEmpty : AbstractItem<MainItemEmpty.ViewHolder>() {
    override val type = MainViewType.VIEW_TYPE_EMPTY
    override val layoutRes = R.layout.listitem_empty

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

}