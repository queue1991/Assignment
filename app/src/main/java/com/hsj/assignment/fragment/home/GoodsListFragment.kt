package com.hsj.assignment.fragment.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hsj.assignment.GoodDetailActivity
import com.hsj.assignment.R
import com.hsj.assignment.base.BaseKotlinFragment
import com.hsj.assignment.common.CommonFireStorePath.COLLECTION_PORK
import com.hsj.assignment.common.CommonFireStorePath.ORDERBY_NO
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_DOCUMENT_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_GOODNAME_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_GOODPRICE_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.common.CommonIntentKey.INTENT_KEY_GOODTYPE_FROM_HOME_TO_DETAIL_ACTIVITY
import com.hsj.assignment.databinding.FragmentGoodsListBinding
import com.hsj.assignment.model.dto.Pork
import com.hsj.assignment.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_goods_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoodsListFragment : BaseKotlinFragment<FragmentGoodsListBinding, HomeViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_goods_list
    override val viewModel: HomeViewModel by viewModel()

    private var adapter: FirestoreRecyclerAdapter<Pork, GoodsViewHolder>? = null
    private lateinit var recyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewDataBinding.viewModel = viewModel
        this.viewDataBinding.fragment = this

        setGoodsList()
    }

    private open class GoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setGoodsInfo(goodsName : String?, type : String?, price : String?){
            val tv_name : TextView =itemView.findViewById(R.id.tv_name)
            tv_name.setText(goodsName)

            val tv_type : TextView =itemView.findViewById(R.id.tv_type)
            tv_type.setText(type)

            val tv_price : TextView =itemView.findViewById(R.id.tv_price)
            tv_price.setText(price)
        }

    }

    /**
     * Firestore에서 실시간으로 상품 리스트를 호출하여 List에 Setting
     * 사용자가 상품을 클릭 시 GoodDetailActivity 시작
     */
    private fun setGoodsList(){
        recyclerView = recyclerview
        recyclerView.layoutManager =
            LinearLayoutManager(activity)

        val rootRef = FirebaseFirestore.getInstance()
        val query: Query = rootRef.collection(COLLECTION_PORK).orderBy(ORDERBY_NO)

        val options: FirestoreRecyclerOptions<Pork> = FirestoreRecyclerOptions.Builder<Pork>()
            .setQuery(query, Pork::class.java)
            .build()

        adapter = object : FirestoreRecyclerAdapter<Pork, GoodsViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_goods, parent, false)

                return GoodsViewHolder(view)
            }

            override fun onBindViewHolder(holder: GoodsViewHolder, p1: Int, pork: Pork) {
                holder.setGoodsInfo(pork.name,pork.type,pork.price)
                holder.itemView.setOnClickListener {
                    val intent = Intent(activity, GoodDetailActivity::class.java)
                    intent.putExtra(INTENT_KEY_DOCUMENT_FROM_HOME_TO_DETAIL_ACTIVITY,snapshots.getSnapshot(p1).id)
                    intent.putExtra(INTENT_KEY_GOODNAME_FROM_HOME_TO_DETAIL_ACTIVITY,pork.name.toString())
                    intent.putExtra(INTENT_KEY_GOODPRICE_FROM_HOME_TO_DETAIL_ACTIVITY,pork.price.toString())
                    intent.putExtra(INTENT_KEY_GOODTYPE_FROM_HOME_TO_DETAIL_ACTIVITY,pork.type.toString())

                    startActivity(intent)
                }
            }

        }

        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        if (adapter != null) {
            adapter!!.stopListening()
        }
    }
}