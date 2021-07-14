package com.hsj.assignment.fragment.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.hsj.assignment.R
import com.hsj.assignment.base.BaseKotlinFragment
import com.hsj.assignment.common.CommonFireStorePath
import com.hsj.assignment.common.CommonFireStorePath.COLLECTION_REVIEW
import com.hsj.assignment.common.CommonFireStorePath.ORDERBY_DATE
import com.hsj.assignment.databinding.FragmentGoodReviewBinding
import com.hsj.assignment.model.dto.Review
import com.hsj.assignment.util.DateUtil
import com.hsj.assignment.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_good_review.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class GoodReviewFragment : BaseKotlinFragment<FragmentGoodReviewBinding, DetailViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_good_review
    override val viewModel: DetailViewModel by viewModel()

    private var adapter: FirestorePagingAdapter<Review, ReviewViewHolder>? = null
    private lateinit var recyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewDataBinding.viewModel = viewModel
        this.viewDataBinding.fragment = this

        setReviewList()
    }

    private open class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setReviewInfo(userName : String?, contents : String?, score : String?, date : Timestamp){
            val tv_user : TextView =itemView.findViewById(R.id.tv_user)
            tv_user.text = userName

            val tv_contents : TextView =itemView.findViewById(R.id.tv_contents)
            tv_contents.text = contents

            val tv_score : TextView =itemView.findViewById(R.id.tv_score)
            tv_score.text = score.toString() + "점"

            val tv_date : TextView =itemView.findViewById(R.id.tv_date)
            tv_date.text = DateUtil.convertTimestampToDate(date.seconds)
        }

    }

    /**
     * 사용자가 클릭한 상품의 Review List를 Setting
     * 5개를 가져온 후, 스크롤이 마지막에 다다를때 3개 더 로드
     */
    private fun setReviewList(){
        recyclerView = recyclerview_review
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val rootRef = FirebaseFirestore.getInstance()
        val query: Query = rootRef
            .collection(CommonFireStorePath.COLLECTION_PORK)
            .document(viewModel.document.value.toString())
            .collection(COLLECTION_REVIEW)
            .orderBy(ORDERBY_DATE, Query.Direction.DESCENDING)

        val config : PagedList.Config = PagedList.Config.Builder().setInitialLoadSizeHint(5).setPageSize(3).build()

        val options: FirestorePagingOptions<Review> = FirestorePagingOptions.Builder<Review>()
            .setLifecycleOwner(this)
            .setQuery(query, config, Review::class.java)
            .build()

        adapter = object : FirestorePagingAdapter<Review, ReviewViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_review, parent, false)

                return ReviewViewHolder(view)
            }

            override fun onBindViewHolder(holder: ReviewViewHolder, p1: Int, review: Review) {
                holder.setReviewInfo(review.user,review.contents,review.score, review.date!!)
                holder.itemView.setOnClickListener {

                }
            }

            override fun onLoadingStateChanged(state: LoadingState) {
                super.onLoadingStateChanged(state)
                when(state){
                    LoadingState.ERROR -> {
                        Log.d("onLoadingStateChanged", "ERROR" )
                    }

                    LoadingState.LOADED -> {
                        Log.d("onLoadingStateChanged", "LOADED" )

                    }

                    LoadingState.FINISHED -> {
                        Log.d("onLoadingStateChanged", "FINISHED" )
                    }

                    LoadingState.LOADING_MORE -> {
                        Log.d("onLoadingStateChanged", "LOADING_MORE" )
                    }

                    LoadingState.LOADING_INITIAL -> {
                        Log.d("onLoadingStateChanged", "LOADING_INITIAL" )
                    }
                }
            }

        }
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}