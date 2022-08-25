import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces.activities.AddHappyPlaceActivity
import com.example.happyplaces.activities.MainActivity
import com.example.happyplaces.databinding.ItemHappyPlaceBinding
import com.example.happyplaces.models.HappyPlaceModel

class HappyPlacesAdapter (var list : ArrayList <HappyPlaceModel> ) :
    RecyclerView.Adapter < HappyPlacesAdapter.ViewHolder > () {

    private var onClickListener : OnClickListener? = null
    private var context : Context? = null

    inner class ViewHolder ( binding : ItemHappyPlaceBinding) :
        RecyclerView.ViewHolder ( binding.root ) {
        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
        val ivPlaceImage = binding.ivPlaceImage
    }

    override fun onCreateViewHolder (parent : ViewGroup, viewType : Int ) : ViewHolder {
        context = parent.context
        return ViewHolder ( ItemHappyPlaceBinding.inflate (
            LayoutInflater.from ( context ), parent, false
        )
        )
    }

    fun setOnClickListener(onClickListener: OnClickListener)    {
        this.onClickListener = onClickListener
    }

    override fun onBindViewHolder ( holder : ViewHolder, position : Int ) {
        val model: HappyPlaceModel = list[position]

        if (holder is ViewHolder) {
            holder.ivPlaceImage.setImageURI(Uri.parse(model.image))
            holder.tvTitle.text = model.title
            holder.tvDescription.text = model.description

            holder.itemView.setOnClickListener{
                if(onClickListener != null) {
                    onClickListener!!.onClick(position, model)
                }
            }
        }
    }

    fun notifyEditItem(activity : Activity, position : Int, requestCode : Int){
        val intent = Intent(context, AddHappyPlaceActivity::class.java)
        intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, list[position])
        activity.startActivityForResult(intent, requestCode)
        notifyItemChanged(position)
    }

    override fun getItemCount () : Int {
        return list.size
    }

    interface OnClickListener   {
        fun onClick(position: Int, model: HappyPlaceModel){

        }
    }

}